package com.example.lab5_20171198.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter

@Setter


public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String password;


    @Column(name = "phonenumber")
    private String numeroTel;

    @Column(name = "enabled")
    private Integer enabled;

    @Column(name = "hire_date")
    private String hireDate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Jobs job;




}
