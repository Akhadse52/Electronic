package com.lcw.electronic_store.dtos;

import com.lcw.electronic_store.validate.ImageNameValid;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String userId;
    @Size(min = 3 , max = 15 , message = "Invalid Name !!!")
    private  String name;
    @NotBlank(message = "Password is Required")
    private String password;
   // @Email(message = "Invalid Email")
   @Pattern(regexp = "^[a-z0-9][-a-z0-9.__]+@([-a-z0-9]+\\.)+[a-z]{2,5}$", message = "Invalid User Email")
    @NotBlank(message = "Email not black")
    private String email;
    private long mobileNumber;
    private String gender;
    private String about;
    @ImageNameValid
    private String imageName;
}
