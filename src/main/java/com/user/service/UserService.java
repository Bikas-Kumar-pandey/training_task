package com.user.service;

//import com.user.dto.LoginRequest;
import com.user.dto.UserAddress;
import com.user.dto.UserRequest;
import com.user.dto.UserResponse;
import com.user.entity.UserAddressEntity;
import com.user.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserRequest userRegistration(UserRequest request);

    void logingPage(String email,String password) throws Exception;

    UserResponse findUserById(int id);

    List<UserEntity> userAddress(int id, List<UserAddressEntity> userAddress) throws Exception;

    List<UserEntity> getAllUsersDetails();
}
