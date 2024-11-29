package vivatech.nurse_mobile.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vivatech.nurse_mobile.model.AttemptLogin;
import vivatech.nurse_mobile.model.UserOTP;
import vivatech.nurse_mobile.model.Users;
import vivatech.nurse_mobile.repository.AttemptLoginRepository;
import vivatech.nurse_mobile.repository.UserOtpRepository;
import vivatech.nurse_mobile.repository.UsersRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class Utility {
    @Value("${app.otp.fixed}")
    private boolean OTP_FIXED;
    @Value("${app.otp.validity}")
    private Integer OTP_VALIDITY_TIME;
    @Autowired
    private AttemptLoginRepository attemptLoginRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserOtpRepository userOtpRepository;

    public boolean checkIfUserBlocked(Integer userId) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Fetch the most recent block time
        Optional<AttemptLogin> recentAttempt = attemptLoginRepository.findTopByUserIdOrderByIdDesc(userId);

        if (recentAttempt.isPresent() && recentAttempt.get().getBlockTime() != null) {
            LocalDateTime blockTime = recentAttempt.get().getBlockTime();

            if (blockTime.isBefore(currentDateTime)) {
                // Reset counter after block time is over
                Users user = usersRepository.findById(userId).orElse(null);

                if (user != null) {
                    user.setIsSuspended(0);
                    user.setAttemptCounter((short)0);
                    usersRepository.save(user);
                }
                return false;
            } else if (blockTime.isAfter(currentDateTime)) {
                return true;
            }
        }

        return false;
    }

    public String generateOTP(String mobile) {
        Random random = new Random();
        return OTP_FIXED ? "123456" : String.valueOf(random.nextInt(900000) + 100000);
    }

    public void insertOTP(Integer userId, String otp, String isFrom) {
        UserOTP userOTP = new UserOTP();
        userOTP.setUserId(userId);
        userOTP.setOtp(md5Hash(otp));
        userOTP.setExpiredAt(LocalDateTime.now().plusMinutes(OTP_VALIDITY_TIME));
        userOTP.setStatus("0");
        userOTP.setIsFrom(isFrom);
        userOTP.setType(null);

        userOtpRepository.save(userOTP);
    }
    public String md5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

