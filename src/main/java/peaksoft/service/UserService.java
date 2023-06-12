package peaksoft.service;

import peaksoft.dto.AuthenticationResponse;
import peaksoft.dto.SignInRequest;
import peaksoft.dto.SignUpRequest;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.UserRequest;
import peaksoft.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    AuthenticationResponse saveUser();

//    UserResponse getUser();
//    List<UserResponse>getAll();
//    SimpleResponse updateUser(UserRequest userRequest);
//    SimpleResponse delete();
}