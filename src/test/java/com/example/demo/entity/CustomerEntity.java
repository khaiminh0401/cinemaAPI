package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {
    Integer id;
    String name;
    String password;
    String email;
    String phone;
    String address;
    Boolean gender;
    String keyfacebook;
    String avatar;
    String token;
    Boolean active;
    String created_at;
}
