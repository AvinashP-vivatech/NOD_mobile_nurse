package vivatech.nurse_mobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vivatech.nurse_mobile.model.UsersInitiated;

@Repository
public interface UsersInitiatedRepository extends JpaRepository<UsersInitiated, Integer> {
    UsersInitiated findByMobileNumber(String mobile);
}
