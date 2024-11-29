package vivatech.nurse_mobile.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vivatech.nurse_mobile.config.Utility;
import vivatech.nurse_mobile.dto.APIGatewayEnum;
import vivatech.nurse_mobile.model.Users;
import vivatech.nurse_mobile.model.UsersInitiated;
import vivatech.nurse_mobile.repository.UsersInitiatedRepository;
import vivatech.nurse_mobile.repository.UsersRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class NursePartnerService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private Utility utility;
    @Autowired
    private UsersInitiatedRepository usersInitiatedRepository;

    public Map<String,Object> attemptLogin(String mobile) {
        Users loginData = usersRepository.findByContactNumber(mobile);
        Map<String, Object> response = new HashMap<>();
        if (loginData != null) {
            // Check if user is blocked
            boolean isBlocked = utility.checkIfUserBlocked(loginData.getUserId());
            if (isBlocked) {
                response.put("status", "403");
                response.put("is_active", false);
                response.put("message", "Login attempts are blocked.");
                response.put("data", new HashMap<>());
                return response;
            }

            if (APIGatewayEnum.UserType.Patient.equals(loginData.getType()) || APIGatewayEnum.UserType.Doctor.equals(loginData.getType())
                    || APIGatewayEnum.UserType.Clinic.equals(loginData.getType())) {
                if ("I".equals(loginData.getStatus())) {
                    response.put("status", "403");
                    response.put("is_active", false);
                    response.put("message", "User is inactive.");
                    response.put("data", new HashMap<>());
                    return response;
                } else {
                    String otp = utility.generateOTP(mobile);

                    utility.insertOTP(loginData.getUserId(), otp, "Login");

                    log.info("Otp for login : {}", otp);
                    String smsMessage = "<#> Your OTP for login is: " + otp;
//                    if (!isDevelopment) {
//                        helperComponent.sendSMSOtp(loginData.getCountryCode() + mobile, smsMessage);
//                    }

                    response.put("status", "200");
                    response.put("message", "OTP sent successfully.");
                    Map<String, Object> userData = new HashMap<>();
                    userData.put("is_registered", "Yes");
                    userData.put("userData", loginData);
                    response.put("data", userData);
                    return response;
                }
            } else {
                response.put("status", "403");
                response.put("message", "Invalid user type.");
                response.put("data", new HashMap<>());
                return response;
            }
        } else {
            // Handle unregistered user
            UsersInitiated userInitiated = usersInitiatedRepository.findByMobileNumber(mobile);
            if (userInitiated == null) {
                userInitiated = new UsersInitiated();
                userInitiated.setMobileNumber(Integer.valueOf(mobile));
                userInitiated.setCreatedAt(LocalDateTime.now());
                usersInitiatedRepository.save(userInitiated);
            }

            String otp = utility.generateOTP(mobile);

            utility.insertOTP(userInitiated.getId(), otp, "Registration");

            String smsMessage = "<#> Your OTP for registration is: " + otp;
//            if (!isDevelopment) {
//                helperComponent.sendSMSOtp("+91" + userInitiated.getMobileNumber(), smsMessage);
//            }

            response.put("status", "200");
            response.put("message", "OTP sent successfully.");
            Map<String, Object> userData = new HashMap<>();
            userData.put("is_registered", "No");
            userData.put("userData", userInitiated);
            response.put("data", userData);
            return response;
        }
    }
}
