package com.user.entity;


import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data

@NoArgsConstructor
@AllArgsConstructor

@Table(name="usr_detail")
@EnableEncryptableProperties
public class UserEntity {
    @Id
    @Column(name = "usr_id")

    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;

    private String mobile;

    private String email;
    private String password;
    private String user;



    @OneToMany(cascade =CascadeType.ALL)
    @JoinColumn(name = "fk_usr")
    private List<Address> userAddresses;


}

