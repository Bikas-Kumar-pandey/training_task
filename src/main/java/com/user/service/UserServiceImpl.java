package com.user.service;



import com.user.dto.UserRequest;
import com.user.dto.UserResponse;
import com.user.entity.UserAddressEntity;
import com.user.entity.UserEntity;
import com.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserRequest userRegistration(UserRequest request) throws Exception {
        boolean b = userRepository.existsByEmail(request.getEmail());
        if(b==true){
            throw new Exception("Email id already Exists");
        }
        boolean valid = isValid(request);
        if(valid==false){
            throw new Exception("Please Enter vaid mail");
        }

        UserEntity entity = new UserEntity();

        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setMobile(request.getMobile());
        entity.setEmail(request.getEmail());
        entity.setPassword(request.getPassword());


        userRepository.save(entity);

        return request;
    }

    @Override
    public String logingPage(String email, String password) throws Exception {
        UserEntity byEmailAndPassword = userRepository.findByEmailAndPassword(email, password);
        if (byEmailAndPassword == null) {
            throw new Exception("Please Enter Correct Email id and password.");
        } else
            return "Welcome, you have  successfully logged in ";
    }

    @Override
    public UserResponse findUserById(int id) {

        Optional<UserEntity> byId = userRepository.findById(id);
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
    public UserEntity userAddress(int id, List<UserAddressEntity> userAddress) throws Exception {
        Optional<UserEntity> byId = userRepository.findById(id);
        if(!byId.isPresent()){
            throw new Exception("User with given id is not present .");
        }
        UserEntity userEntity = byId.get();

        List<UserAddressEntity>userAddressEntityList = new ArrayList<>();
for (UserAddressEntity entity:userAddress){
    UserAddressEntity address =new UserAddressEntity();
    address.setAddresses(entity.getAddresses());
    userAddressEntityList.add(address);
}
        userEntity.setUserAddresses(userAddressEntityList);
return userRepository.save(userEntity);


    }

    @Override
    public List<UserEntity> getAllUsersDetails() {
        List<UserEntity> all = userRepository.findAll();
        UserResponse response = new UserResponse();
        for(UserEntity entity:all) {
            response.setId(entity.getId());
            response.setFirstName(entity.getFirstName());
            response.setLastName(entity.getLastName());
            response.setMobile(entity.getEmail());
            response.setEmail(entity.getEmail());

        }

        all.forEach(x -> {
            x.getUserAddresses();x.getId();
        });
        log.info(String.valueOf(all));
return all;

    }



    public static boolean isValid(UserRequest request)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (request.getEmail() == null)
            return false;
        return pat.matcher(request.getEmail()).matches();
    }

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
