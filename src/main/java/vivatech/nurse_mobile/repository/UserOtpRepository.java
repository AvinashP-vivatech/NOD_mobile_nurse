package vivatech.nurse_mobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vivatech.nurse_mobile.model.UserOTP;

@Repository
public interface UserOtpRepository extends JpaRepository<UserOTP, Integer> {
}
