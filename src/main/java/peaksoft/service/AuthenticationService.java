package peaksoft.service;

import peaksoft.dto.AuthenticationResponse;
import peaksoft.dto.SignInRequest;
import peaksoft.dto.SignUpRequest;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.UserRequest;
import peaksoft.dto.response.UserResponse;

import java.util.List;

public interface AuthenticationService {
    AuthenticationResponse signUp(SignUpRequest signUpRequest);

    AuthenticationResponse signIn(SignInRequest signInRequest);
//    UserResponse getUser();
//
//    List<UserResponse> getAllUser();
//
//    SimpleResponse updateUser(UserRequest updateUserRequest);
//
//    SimpleResponse deleteUser();
//    AuthenticationResponse saveUser();
//
//    UserResponse getUserById(Long id);
//
//
//    SimpleResponse updateUser(Long id, UserRequest userRequest);
//
//    SimpleResponse deleteUser(Long Id);
}
