package com.lcw.electronic_store.controller;

import com.lcw.electronic_store.dtos.ApiResponseMessage;
import com.lcw.electronic_store.dtos.UserDto;
import com.lcw.electronic_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/test")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
       return new  ResponseEntity(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable ("userId") String userId){
        return  new ResponseEntity<>(userService.updateUser(userDto, userId),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
        ApiResponseMessage message = ApiResponseMessage
                .builder()
                .message("User is delete Successfully !!")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return  new ResponseEntity<>(message,HttpStatus.OK);
    }
    @GetMapping("/getuserbyid/{userId}")
    public ResponseEntity<UserDto> getUserByUserId(@PathVariable("userId") String userId){
       return new ResponseEntity<>(userService.getUserByUserId(userId),HttpStatus.OK);
    }
    @GetMapping("/getalluser")
    public List<UserDto> getAllUser(@RequestParam (value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
                                    @RequestParam (value = "pageSize",defaultValue = "10",required = false) int pageSize
                                   // @RequestParam (value = "sortBy",defaultValue = "") String sortBy,
                                    //@RequestParam (value = "sortDir",defaultValue = "asc",required = false) String sortDir
                                    ){

        return userService.getAllUser(pageNumber,pageSize);
    }
    @GetMapping("/searchuser/{keyword}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable("keyword") String keyword){
        return new ResponseEntity<>(userService.searchUser(keyword),HttpStatus.OK);
    }

    @GetMapping("/getuserbyemailandpassword/{email}/{password}")
    public ResponseEntity<UserDto> getUserByEmailAndPassword(@PathVariable("email") String email, @PathVariable("password") String password){
       return new ResponseEntity<>(userService.getUserByEmailAndPassword(email,password),HttpStatus.OK);
    }
}
