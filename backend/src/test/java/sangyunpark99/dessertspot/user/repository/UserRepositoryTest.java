package sangyunpark99.dessertspot.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import sangyunpark99.dessertspot.config.JpaConfig;
import sangyunpark99.dessertspot.domain.user.entity.User;
import sangyunpark99.dessertspot.domain.user.repository.UserRepository;

@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@Import(JpaConfig.class)
public class UserRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    UserRepository userRepository;

    @Test
    void 사용자를_저장한다() {
        // given
        final User user = User.builder()
                .email("abc@abc.com")
                .password("abcdef")
                .username("abc")
                .build();

        // when
        final User savedUser = userRepository.save(user);
        em.flush(); // DB 반영
        em.clear(); // 영속성 컨텍스트 초기화

        // then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(savedUser.getId()).isEqualTo(user.getId());
            softAssertions.assertThat(savedUser.getPassword()).isEqualTo(user.getPassword());
            softAssertions.assertThat(savedUser.getUsername()).isEqualTo(user.getUsername());
        });
    }

    @Test
    void 이메일로_존재하는_사용자를_조회한다() {
        // given
        final User user = User.builder()
                .email("abc@abc.com")
                .password("abcdef")
                .username("abc")
                .build();

        // when
        userRepository.save(user);
        em.flush();
        em.clear();

        final Optional<User> foundUser = userRepository.findByEmail(user.getEmail());

        // then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(foundUser).isPresent();
            softAssertions.assertThat(foundUser.get().getId()).isEqualTo(user.getId());
            softAssertions.assertThat(foundUser.get().getEmail()).isEqualTo(user.getEmail());
            softAssertions.assertThat(foundUser.get().getPassword()).isEqualTo(user.getPassword());
            softAssertions.assertThat(foundUser.get().getUsername()).isEqualTo(user.getUsername());
        });
    }

    @Test
    void 이메일로_존재하지_않는_사용자를_조회한다() {
        // given
        final User user = User.builder()
                .email("abc@abc.com")
                .password("abcdef")
                .username("abc")
                .build();

        final String otherEmail = "bcd@bcd.com";

        // when
        userRepository.save(user);
        em.flush();
        em.clear();

        final Optional<User> foundUser = userRepository.findByEmail(otherEmail);

        // then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(foundUser).isNotPresent();
        });
    }

    @Test
    void 이메일로_존재하는_사용자를_확인한다() {
        // given
        final User user = User.builder()
                .email("abc@abc.com")
                .password("abcdef")
                .username("abc")
                .build();

        // when
        userRepository.save(user);
        em.flush();
        em.clear();

        final boolean check = userRepository.existsByEmail(user.getEmail());

        assertThat(check).isTrue();
    }

    @Test
    void 이메일로_존재하지않는_사용자를_확인한다() {
        // given
        final User user = User.builder()
                .email("abc@abc.com")
                .password("abcdef")
                .username("abc")
                .build();

        final String otherEmail = "bcd@bcd.com";

        // when
        userRepository.save(user);
        em.flush();
        em.clear();

        final boolean check = userRepository.existsByEmail(otherEmail);

        assertThat(check).isFalse();
    }
}
