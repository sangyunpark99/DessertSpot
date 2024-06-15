package sangyunpark99.dessertspot.domain.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import sangyunpark99.dessertspot.domain.user.entity.User;

public interface JpaUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(final String email);

    boolean existsByEmail(final String email);
}
