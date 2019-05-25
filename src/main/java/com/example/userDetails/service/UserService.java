package com.example.userDetails.service;

import com.example.userDetails.entity.UserEntity;
import com.example.userDetails.model.SuccessResponseDTO;
import com.example.userDetails.model.UserDTO;

public interface UserService {


    UserEntity createUser(UserDTO userDTO) ;
    SuccessResponseDTO checkUserByEmail(String email, String password);
    UserDTO getUserByEmail(String email);
    UserDTO deleteUser(String email);
    UserDTO updateUser(UserDTO userDTO);
    boolean deleteToken(String token);
    boolean checkByToken(String token,String email);


}
