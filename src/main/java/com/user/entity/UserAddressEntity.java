package com.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class UserAddressEntity {
    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private int id;

    private String addresses;

}
