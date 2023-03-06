package com.Depos.Instagram.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstname;

    @Column(name ="last_name")
    private String lastname;
    @Column(name = "age")
    private int age;
    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phonenumber;

}
