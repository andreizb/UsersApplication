package com.test.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "users_data")
public class User {

    @Id
    private Long id;

    private String username;

    private String password;

    private String email;

}
