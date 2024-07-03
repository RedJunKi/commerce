package com.project.commerce.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long id) {
        return getUser(id);
    }

    public void save(UserDto userDto) {
        validateDuplicateUsername(userDto.getUsername());

        User user = User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .address(userDto.getAddress())
                .build();

        userRepository.save(user);
    }

    public void update(Long id, UserDto userDto) {
        User user = getUser(id);

        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
    }

    private User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
    }

    private void validateDuplicateUsername(String username) {
        Optional<User> findUser = userRepository.findByUsername(username);
        if (findUser.isPresent()) {
            throw new IllegalArgumentException("중복된 회원 아이디입니다.");
        }

    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
