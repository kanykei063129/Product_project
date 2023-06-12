package peaksoft.service.serviceImpl;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.config.JwtService;
import peaksoft.dto.AuthenticationResponse;
import peaksoft.dto.SignInRequest;
import peaksoft.dto.SignUpRequest;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.UserRequest;
import peaksoft.dto.response.UserResponse;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.exceptions.MyException;
import peaksoft.repository.UserRepository;
import peaksoft.service.AuthenticationService;
import peaksoft.service.BasketService;

import java.time.ZonedDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final BasketService basketService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new NullPointerException("User with email: %s already exist".formatted(signUpRequest.getEmail()));
        }
        User user = User.builder()
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build();
        user.setRole(Role.USER);
        user.setCreatedDate(ZonedDateTime.now());
        user.setUpdatedDate(ZonedDateTime.now());
        userRepository.save(user);
        basketService.saveBasket(user.getEmail());

        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .email(signUpRequest.getEmail())
                .token(token)
                .build();
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        if (signInRequest.getEmail().isBlank()) {
            throw new NullPointerException("Incorrect email: %s format".formatted(signInRequest.getEmail()));
        }
        User user = userRepository.getUserByEmail(signInRequest.getEmail()).orElseThrow(() -> new NullPointerException("User with email: %s not found".formatted(signInRequest.getEmail())));
        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            throw new NullPointerException("Incorrect password");
        }

        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder().email(
        user.getEmail())
                .token(token)
                .build();
    }

//    @Override
//    public UserResponse getUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//        return userRepository.getUserEmail(email).orElseThrow(() -> new NotFoundException("User email: %s not found".formatted(email)));
//
//    }
//
//    @Override
//    public List<UserResponse> getAllUser() {
//        return userRepository.getAllUser();
//    }
//
//    @Override
//    public SimpleResponse updateUser(UpdateUserRequest updateUserRequest) {
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new NotFoundException("User email: %s not found".formatted(email)));
//        if (userRepository.existsByEmailAndIdNot(updateUserRequest.email(), user.getId())) {
//            throw new AlreadyExistException("Email %s already exist".formatted(updateUserRequest.email()));
//        }
//        user.setFirstName(updateUserRequest.firstName() == null || updateUserRequest.firstName().isBlank() || user.getFirstName().equals(updateUserRequest.firstName()) ? user.getFirstName() : updateUserRequest.firstName());
//        user.setLastName(updateUserRequest.lastName() == null || updateUserRequest.lastName().isBlank() || user.getLastName().equals(updateUserRequest.lastName()) ? user.getLastName() : updateUserRequest.lastName());
//        user.setEmail(updateUserRequest.email() == null || updateUserRequest.email().isBlank() || user.getEmail().equals(updateUserRequest.password()) ? user.getEmail() : updateUserRequest.email());
//        user.setPassword(updateUserRequest.password() == null || updateUserRequest.password().isBlank() || user.getPassword().equals(updateUserRequest.password()) ? user.getPassword() : updateUserRequest.password());
//        user.setUpdatedDate(ZonedDateTime.now());
//        userRepository.save(user);
//        return SimpleResponse.builder()
//                .httpStatus(HttpStatus.OK)
//                .message("User with id: %s successfully updated".formatted(user.getId()))
//                .build();
//    }
//
//    @Override
//    public SimpleResponse deleteUser() {
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new NotFoundException("User email: %s not found".formatted(email)));
//        userRepository.delete(user);
//        return SimpleResponse.builder()
//                .status(HttpStatus.OK)
//                .message("User with email: %s successfully deleted".formatted(email))
//                .build();
//    }
}

//    private final UserRepository userRepository;
//    private final JwtService jwtService;
//    private final PasswordEncoder passwordEncoder;
//
//
//    @Override
//    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
//        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//            throw new EntityExistsException(String.format(
//                    "User with email: %s already exists!", signUpRequest.getEmail()));
//        }
//        User user = User.builder()
//                .firstName(signUpRequest.getFirstName())
//                .lastName(signUpRequest.getLastName())
//                .email(signUpRequest.getEmail())
//                .password(passwordEncoder.encode(signUpRequest.getPassword()))
//                .createdDate(ZonedDateTime.now())
//                .build();
//        userRepository.save(user);
//
//        String jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse
//                .builder()
//                .token(jwtToken)
//                .email(user.getEmail())
//                .build();
//    }
//    @Override
//    public AuthenticationResponse signIn(SignInRequest signInRequest) {
//        User user = userRepository.getUserByEmail(signInRequest.getEmail())
//                .orElseThrow(() -> new EntityNotFoundException(
//                        "USer with email: " + signInRequest.getEmail() + " not found"
//                ));
//
//        if(signInRequest.getEmail().isBlank()){
//            throw new BadCredentialsException("Email doesn't exist!");
//        }
//
//        if(!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())){
//            throw new BadCredentialsException("Incorrect password!");
//        }
//
//        String jwtToken=jwtService.generateToken(user);
//
//        return AuthenticationResponse
//                .builder()
//                .email(user.getEmail())
//                .token(jwtToken)
//                .build();
//    }
//
//    @Override
//    public AuthenticationResponse saveUser() {
//        User user = User.builder()
//                .firstName("Admin")
//                .lastName("Admin")
//                .email("admin@gmail.com")
//                .password(passwordEncoder.encode("admin123"))
//                .build();
//        user.setRole(Role.ADMIN);
//        user.setCreatedDate(ZonedDateTime.now());
//        user.setUpdatedDate(ZonedDateTime.now());
//        userRepository.save(user);
//
//        String token = jwtService.generateToken(user);
//
//        return AuthenticationResponse.builder()
//                .email(user.getEmail())
//                .token(token)
//                .build();
//    }
//
//    @Override
//    public UserResponse getUserById(Long id) {
//        return userRepository.gerUserId(id).orElseThrow(() -> new NullPointerException("User with id: " +id+ " not found"));
//    }
//
//    @Override
//    public SimpleResponse updateUser(Long id, UserRequest userRequest) {
//        try{
//        User user = userRepository.findById(id).orElseThrow(()->new MyException("User with id: " +id+ " not found!!!"));
//        user.setFirstName(userRequest.firstName());
//        user.setLastName(userRequest.lastName());
//        user.setEmail(userRequest.email());
//        user.setPassword(userRequest.password());
//        user.setUpdatedDate(ZonedDateTime.now());
//        userRepository.save(user);
//        return SimpleResponse.builder()
//                .status(HttpStatus.OK)
//                .message("User with id: " +id+ " successfully updated")
//                .build();
//    }catch (MyException e){
//        System.out.println(e.getMessage());
//    }
//        return null;
//    }
//
//    @Override
//    public SimpleResponse deleteUser(Long id) {
//        User user = userRepository.findById(id).orElseThrow(() -> new NullPointerException("User with id:" +id+ " not found"));
//        userRepository.delete(user);
//        return SimpleResponse.builder()
//                .status(HttpStatus.OK)
//                .message("User with id: " +id+ "successfully deleted")
//                .build();
//    }
//}
