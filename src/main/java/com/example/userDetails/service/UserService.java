package com.example.userDetails.service;

import com.example.userDetails.model.UserDTO;

public interface UserService {


    void createUser(UserDTO userDTO) ;
    boolean checkUserByEmail(String email,String password);
    UserDTO getUserByEmail(String email);
    UserDTO deleteUser(String email);
    UserDTO updateUser(UserDTO userDTO);
}
