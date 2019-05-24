package com.example.userDetails.controller;


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




    @RequestMapping("/login")
    public boolean checkUser(@RequestParam String email , @RequestParam String password){

        String e = email;
        String p = password;

        return userService.checkUserByEmail(e,p);
    }


    @PostMapping(value = "/signup")
    public boolean createUser(@RequestBody UserDTO userDTO){
        try {
            userService.createUser(userDTO);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @PutMapping("/signup/update")
    public UserDTO updateUser(@RequestBody UserDTO userDTO){
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/delete")
    public UserDTO deleteUser(@RequestParam String email){
        return userService.deleteUser(email);
    }
}
