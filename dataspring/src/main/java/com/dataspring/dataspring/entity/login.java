package com.dataspring.dataspring.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class login {
    @Id
    private String name;
    private String pwd;
    private String crypt;
}
