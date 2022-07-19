package com.user.repository;

//import com.user.dto.LoginRequest;
import com.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Optional;


@EnableJpaRepositories
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findByEmailAndPassword(String email, String password);

    List<UserEntity> findAllById(String id);

}
