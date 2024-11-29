package vivatech.nurse_mobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vivatech.nurse_mobile.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByContactNumber(String mobile);
}
