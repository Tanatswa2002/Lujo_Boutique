package com.Tanatswa.Lujo_Boutique.Admin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "Admin")

public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer admin_id;

    @Column(name = "first_name",nullable = false,updatable = false)
    private String FirstName;

    @Column(name = "last_name",nullable = false)
    private String LastName;

    @Column(name = "phone_num",nullable = false,updatable = false)
    private String PhoneNum;

    @Column(name = "password",nullable = false)
    private String Password;

    @Column(name = "username")
    private String Username;


}
