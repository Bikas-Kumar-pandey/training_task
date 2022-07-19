package com.user.controller;

import com.user.dto.UserRequest;
import com.user.dto.UserResponse;
import com.user.entity.Address;

import com.user.entity.UserEntity;
import com.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/user")
    public UserRequest userRegistration(@RequestBody UserRequest request) throws Exception {
      return   userService.userRegistration(request);
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam("email")String email,@RequestParam("password") String password) throws Exception {
      return userService.logingPage(email,password);
    }

    @GetMapping("/user/{id}")
    public UserResponse findUserById(@PathVariable int id) throws Exception {
     return    userService.findUserById(id);
    }

    @PostMapping("/address/{id}")
    public UserEntity addAddress(@PathVariable int id, @RequestBody List<Address> userAddress) throws Exception {
        return userService.addAddress(id,userAddress);
    }

    @GetMapping("user")
    public List<UserEntity> getAllUsersDetails(){
return userService.getAllUsersDetails();
    }


//    @GetMapping("/password/{password}")
//    public Boolean checkPassword(@PathVariable String password){
//       return userService.checkPassword(password);
//    }

}
