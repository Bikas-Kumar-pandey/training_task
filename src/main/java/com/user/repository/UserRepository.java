package com.user.repository;

//import com.user.dto.LoginRequest;
import com.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;


@EnableJpaRepositories
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findByEmailAndPassword(String email, String password);

    List<UserEntity> findAllById(String id);

    boolean existsByEmail(String email);



    String  existsByPassword(String password);

    boolean existsByMobile(String user);

    UserEntity findByEmail(String user);

    UserEntity findByMobile(String user);

    boolean existsByUser(String user);
}
