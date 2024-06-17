package sangyunpark99.dessertspot.domain.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sangyunpark99.dessertspot.domain.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(final String email);

    Optional<User> findByEmailAndPassword(final String email, final String password);

    boolean existsByEmail(final String email);
}
