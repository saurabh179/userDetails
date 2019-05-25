package com.example.userDetails.service;

import com.example.userDetails.model.UserDTO;

public interface UserService {


    void createUser(UserDTO userDTO) ;
    String checkUserByEmail(String email,String password);
    UserDTO getUserByEmail(String email);
    UserDTO deleteUser(String email);
    UserDTO updateUser(UserDTO userDTO);
    boolean deleteToken(String token);


}
