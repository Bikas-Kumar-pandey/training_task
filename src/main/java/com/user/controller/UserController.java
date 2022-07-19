package com.user.controller;

import com.user.dto.UserRequest;
import com.user.dto.UserResponse;
import com.user.entity.UserAddressEntity;
import com.user.entity.UserEntity;
import com.user.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping("/user-details")
    public UserRequest userRegistration(@RequestBody UserRequest request) throws Exception {
      return   userService.userRegistration(request);
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam("email")String email,@RequestParam("password") String password) throws Exception {
      return userService.logingPage(email,password);
    }

    @GetMapping("/find-user-by-id/{id}")
    public UserResponse findUserById(@PathVariable int id) throws Exception {
     return    userService.findUserById(id);
    }

    @PostMapping("/address/{id}")
    public UserEntity userAddress(@PathVariable int id,@RequestBody List<UserAddressEntity> userAddress) throws Exception {
        return userService.userAddress(id,userAddress);
    }

    @GetMapping("find-all-users")
    public List<UserEntity> getAllUsersDetails(){
return userService.getAllUsersDetails();
    }


    @GetMapping("/password/{password}")
    public Boolean checkPassword(@PathVariable String password){
       return userService.checkPassword(password);
    }

}
