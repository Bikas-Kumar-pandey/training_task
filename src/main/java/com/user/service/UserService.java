package com.user.service;

//import com.user.dto.LoginRequest;
import com.user.dto.UserRequest;
import com.user.dto.UserResponse;
import com.user.entity.Address;

import com.user.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserRequest userRegistration(UserRequest request) throws Exception;

    String logingPage(String email,String password) throws Exception;

    UserResponse findUserById(int id) throws Exception;

    UserEntity addAddress(int id, List<Address> userAddress) throws Exception;

    List<UserEntity> getAllUsersDetails();

    Boolean checkPassword(String password);
}
