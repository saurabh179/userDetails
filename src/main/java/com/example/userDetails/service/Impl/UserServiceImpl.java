package com.example.userDetails.service.Impl;


import com.example.userDetails.entity.UserEntity;
import com.example.userDetails.model.UserDTO;
import com.example.userDetails.repository.UserRepository;
import com.example.userDetails.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public void createUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);
        UserEntity result = userRepository.save(userEntity);
        UserDTO resultDTO = new UserDTO();
        BeanUtils.copyProperties(result, resultDTO);


    }

    @Override
    public boolean checkUserByEmail(String email,String password) {
        UserEntity result = userRepository.findOne(email);
        if((!result.getPassword().equals( password))){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        UserEntity result = userRepository.findOne(email);
        UserDTO resultDTO = new UserDTO();
        BeanUtils.copyProperties(result,resultDTO);
        return resultDTO;
    }

    @Override
    public UserDTO deleteUser(String email) {
        userRepository.delete(email);
        return null;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);
        UserEntity result = userRepository.save(userEntity);
        UserDTO resultDTO = new UserDTO();
        BeanUtils.copyProperties(result, resultDTO);

        return resultDTO;
    }
}
