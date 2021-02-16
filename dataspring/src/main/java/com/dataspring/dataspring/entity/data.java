package com.dataspring.dataspring.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class data {
    @Id
    private String bookname;
    private String context;
    private String person;
    private String time;
}
