package com.user.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserAddress {
    private int id;
    private List<String> address;
}
