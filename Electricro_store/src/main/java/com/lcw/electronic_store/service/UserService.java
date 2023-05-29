package com.lcw.electronic_store.service;

import com.lcw.electronic_store.dtos.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    //update
    UserDto updateUser(UserDto userDto,String userId);

    //delete
    void deleteUser(String userId);

    //get single user by id
    UserDto getUserByUserId(String userId);

    //get single user by email
    UserDto getUserByEmail(String email);

    //get All user
    List<UserDto> getAllUser(int pageNumber, int pageSize);
    //search User
    List<UserDto> searchUser(String keyword);

    UserDto getUserByEmailAndPassword(String email,String password);



}
