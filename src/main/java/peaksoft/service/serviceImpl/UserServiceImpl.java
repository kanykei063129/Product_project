package peaksoft.service.serviceImpl;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.config.JwtService;
import peaksoft.dto.AuthenticationResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.UserRequest;
import peaksoft.dto.response.UserResponse;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.exceptions.MyException;
import peaksoft.repository.UserRepository;
import peaksoft.service.UserService;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

@PostConstruct
    @Override
    public AuthenticationResponse saveUser() {
        User user = User.builder()
                .firstName("Admin")
                .lastName("Admin")
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("admin123"))
                .build();
        user.setRole(Role.ADMIN);
        user.setCreatedDate(ZonedDateTime.now());
        user.setUpdatedDate(ZonedDateTime.now());
        userRepository.save(user);

        String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .email(user.getEmail())
                .token(token)
                .build();
//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserResponse getUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//        return userRepository.getUserEmail(email).orElseThrow(() -> new NullPointerException("User email: " + email + " not found"));
//
//    }
//
//    @Override
//    public List<UserResponse> getAll() {
//        return userRepository.getAllUser();
//    }
//
//    @Override
//    public SimpleResponse updateUser(UserRequest userRequest) {
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new NullPointerException("User email: %s not found".formatted(email)));
//        if (userRepository.existsByEmailAndIdNot(userRequest.email(), user.getId())) {
//            throw new BadCredentialsException("Email %s already exist".formatted(userRequest.email()));
//        }
//        user.setFirstName(userRequest.firstName() == null || userRequest.firstName().isBlank() || user.getFirstName().equals(userRequest.firstName()) ? user.getFirstName() : userRequest.firstName());
//        user.setLastName(userRequest.lastName() == null || userRequest.lastName().isBlank() || user.getLastName().equals(userRequest.lastName()) ? user.getLastName() : userRequest.lastName());
//        user.setEmail(userRequest.email() == null || userRequest.email().isBlank() || user.getEmail().equals(userRequest.password()) ? user.getEmail() : userRequest.email());
//        user.setPassword(userRequest.password() == null || userRequest.password().isBlank() || user.getPassword().equals(userRequest.password()) ? user.getPassword() : userRequest.password());
//        user.setUpdatedDate(ZonedDateTime.now());
//        userRepository.save(user);
//        return SimpleResponse.builder()
//                .status(HttpStatus.OK)
//                .message("User with id: %s successfully updated".formatted(user.getId()))
//                .build();
//    }
//
//    @Override
//    public SimpleResponse delete() {
//        try {
//            String email = SecurityContextHolder.getContext().getAuthentication().getName();
//            User user = userRepository.getUserByEmail(email).orElseThrow(() -> new MyException("User email: %s not found".formatted(email)));
//            userRepository.delete(user);
//            return SimpleResponse.builder()
//                    .status(HttpStatus.OK)
//                    .message("User with email: %s successfully deleted".formatted(email))
//                    .build();
//        } catch (MyException e) {
//            System.out.println(e.getMessage());
//        }
//        return null;
//    }
    }
}
