package vivatech.nurse_mobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vivatech.nurse_mobile.model.AttemptLogin;

import java.util.Optional;

@Repository
public interface AttemptLoginRepository extends JpaRepository<AttemptLogin, Integer> {
    Optional<AttemptLogin> findTopByUserIdOrderByIdDesc(Integer userId);
}
