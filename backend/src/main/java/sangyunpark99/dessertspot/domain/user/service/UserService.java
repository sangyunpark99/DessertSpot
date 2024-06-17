package sangyunpark99.dessertspot.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sangyunpark99.dessertspot.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
}
