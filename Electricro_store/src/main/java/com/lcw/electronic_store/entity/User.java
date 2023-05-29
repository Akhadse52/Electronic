package com.lcw.electronic_store.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;
    @Column(name= "user_name")
    private  String name;
    @Column(name="user_password",length = 10)
    private String password;
    @Column(name="user_email",unique = true,length = 128)
    private String email;
    @Column(name="mobile_number",unique = true,length = 10)
    private long mobileNumber;
    private String gender;
    @Column(length = 1000)
    private String about;
    @Column(name ="user_image_name")
    private String imageName;


}
