package com.user.service;



import com.user.dto.UserRequest;
import com.user.dto.UserResponse;
import com.user.entity.Address;

import com.user.entity.UserEntity;
import com.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserRequest userRegistration(UserRequest request) throws Exception {
        UserEntity entity = new UserEntity();
       if( userRepository.existsByEmail(request.getEmail())){
           throw new RuntimeException("Duplicate mail id");
       }
        if(userRepository.existsByMobile(request.getMobile())){
            throw new RuntimeException("Mobile no: already exists");
        }
        entity.setEmail(request.getEmail());
        entity.setMobile(request.getMobile());
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setPassword(request.getPassword());
        userRepository.save(entity);
        return request;
    }

    @Override
    public String logingPage(String user, String password) throws Exception {

        if (userRepository.existsByEmail(user) || userRepository.existsByMobile(user)) {
            UserEntity users = null;
            if (userRepository.existsByEmail(user)) {
                users = userRepository.findByEmail(user);
            } else {
                users = userRepository.findByMobile(user);
            }
            String passwords = users.getPassword();
            if (passwords.equals(password)) {
                return "Welcome, you have  successfully logged in ";
            } else {
                throw new Exception("In correct user");
            }
        }
        return "wrong User";
    }

    @Override
    public UserResponse findUserById(int id) throws Exception {

        Optional<UserEntity> byId = userRepository.findById(id);
        if(byId.isEmpty()){
            throw new Exception("Invalid user id , please check  ");
        }
        UserResponse userResponse = new UserResponse();

        UserEntity entity = byId.get();
        userResponse.setId(entity.getId());
        userResponse.setFirstName(entity.getFirstName());
        userResponse.setLastName(entity.getLastName());
        userResponse.setEmail(entity.getEmail());
        userResponse.setMobile(entity.getMobile());
        return userResponse;
    }

     @Override
    public UserEntity addAddress(int id, List<Address> userAddress) throws Exception {
        Optional<UserEntity> byId = userRepository.findById(id);
        if(byId.isEmpty()){
            throw new Exception("User not present with id : "+id);
        }
        UserEntity user = byId.get();
        List<Address> addresses=null;
        if(user.getUserAddresses()!=null){
            addresses = user.getUserAddresses();
        }else {
            addresses=new ArrayList<>();
        }

        addresses.addAll(userAddress);

        user.setUserAddresses(addresses);
        return userRepository.save(user);


    }

    @Override
    public List<UserEntity> getAllUsersDetails() {
        List<UserEntity> all = userRepository.findAll();
        UserResponse response = new UserResponse();
        for(UserEntity entity:all) {
            response.setId(entity.getId());
            response.setFirstName(entity.getFirstName());
            response.setLastName(entity.getLastName());
        }

        all.forEach(x -> {
            x.getUserAddresses();x.getId();
        });
        log.info(String.valueOf(all));
return all;

    }

    @Override
    public Boolean checkPassword(String  password) {
        String  byPassword = userRepository.existsByPassword(password);
        log.info(byPassword);
        String  checkpw = String.valueOf(BCrypt.checkpw(password, byPassword));

     if(BCrypt.checkpw(password, checkpw)) {
         log.info("true");
     }else{
         log.info("Not match");
     }
        return null;
    }
//--------------------------------------------------------------------------------------------------------

//    public static boolean isValid(UserRequest request)
//    {
//        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
//                "[a-zA-Z0-9_+&*-]+)*@" +
//                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
//                "A-Z]{2,7}$";
//
//        Pattern pat = Pattern.compile(emailRegex);
//        if (request.getEmail() == null)
//            return false;
//        return pat.matcher(request.getEmail()).matches();
//    }

//    public static void main(String[] args)
//    {
//        ArrayList<String> address = new ArrayList<>();
//
//        address.add("review-team@geeksforgeeks.org");
//        address.add("writing.geeksforgeeks.org");
//
//        for(String i : address){
//            if (isValid(i))
//                System.out.println(i + " - Yes");
//            else
//                System.out.println(i + " - No");
//        }
//    }
//}



}
