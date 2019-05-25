package com.example.userDetails.controller;


import com.example.userDetails.entity.UserEntity;
import com.example.userDetails.model.SuccessResponseDTO;
import com.example.userDetails.model.UserDTO;
import com.example.userDetails.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;

@RestController
public class UserController {



    @Autowired
    UserService userService;

    @RequestMapping("/login/getuser")
    public UserDTO getOrder(@RequestParam String email){
        return userService.getUserByEmail(email);
    }




    @GetMapping("/login")
    public SuccessResponseDTO checkUser(@RequestParam String email , @RequestParam String password){

        String e = email;
        String p = password;

        return userService.checkUserByEmail(e,p);
    }


    @PostMapping(value = "/signup")
    public UserEntity createUser(@RequestBody UserDTO userDTO){

        return userService.createUser(userDTO);

    }


    @PutMapping("/signup/update")
    public UserDTO updateUser(@RequestBody UserDTO userDTO){
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/delete")
    public UserDTO deleteUser(@RequestParam String email){
        return userService.deleteUser(email);
    }

    @GetMapping("/logout")
    public boolean deleteToken(@RequestParam String token){
        return userService.deleteToken(token);
    }

    @GetMapping("/checktoken")
    public boolean checkByToken(@RequestParam String token,@RequestParam String email){
        return userService.checkByToken(token,email);
    }
}
