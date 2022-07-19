package com.user.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data

@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Table(name="usr_detail")
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


    @OneToMany(cascade =CascadeType.ALL)
    @JoinColumn(name = "fk_usr")
    private List<UserAddressEntity> userAddresses;


}

