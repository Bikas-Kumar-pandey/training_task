package com.user.controller;
//
//import com.user.dto.LoginRequest;
import com.user.dto.UserAddress;
import com.user.dto.UserRequest;
import com.user.dto.UserResponse;
import com.user.entity.UserAddressEntity;
import com.user.entity.UserEntity;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping("/user-details")
    public UserRequest userRegistration(@RequestBody UserRequest request){
      return   userService.userRegistration(request);
    }

    @GetMapping("/login")
    public void loginPage(@RequestParam("email")String email,@RequestParam("password") String password) throws Exception {
       userService.logingPage(email,password);
    }

    @GetMapping("/find-user-by-id/{id}")
    public UserResponse findUserById(@PathVariable int id){
     return    userService.findUserById(id);
    }

    @PostMapping("/address/{id}")
    public List<UserEntity> userAddress(@PathVariable int id,@RequestBody List<UserAddressEntity> userAddress) throws Exception {
        return userService.userAddress(id,userAddress);
    }

    @GetMapping("find-all-users")
    public List<UserEntity> getAllUsersDetails(){
return userService.getAllUsersDetails();
    }
}
//simple