package com.example.springboottesting.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_DEMO")
@Data
public class Demo {

    @Id
    private String id;

    @Column(name = "val")
    private String value;
}
