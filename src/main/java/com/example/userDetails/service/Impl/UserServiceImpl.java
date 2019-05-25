package com.example.userDetails.service.Impl;


import com.example.userDetails.entity.UserEntity;
import com.example.userDetails.model.SuccessResponseDTO;
import com.example.userDetails.model.UserDTO;
import com.example.userDetails.repository.UserRepository;
import com.example.userDetails.security.BCrypt;
import com.example.userDetails.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserEntity createUser(UserDTO userDTO) {
        // TODO IMPORTANT check if user already exists, if so dont over overwrite on top of it.

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);
        String token =  BCrypt.hashpw(userEntity.getPassword(),BCrypt.gensalt(12));
        userEntity.setToken(token);
        UserEntity result = userRepository.save(userEntity);
        return userEntity;


    }

    @Override
    public SuccessResponseDTO checkUserByEmail(String email, String password) {
        UserEntity result = userRepository.findOne(email);
        SuccessResponseDTO successResponseDTO = new SuccessResponseDTO();
        successResponseDTO.setSuccess(false);

        if(result == null){
             return new SuccessResponseDTO(false,null,"User does not exist. Do sign up first",email);
        }
        if((!(result.getPassword().equals(password)))){
            return new SuccessResponseDTO(false,null,"Incorrect Password",email);
        }else{
            String token = BCrypt.hashpw(password,BCrypt.gensalt(12));
            UserEntity user = userRepository.findOne(email);
            user.setToken(token);
            userRepository.save(user);
            return new SuccessResponseDTO(true,token,"Logged In",email);
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

    @Override
    public boolean deleteToken(String token) {
        UserEntity userEntity = userRepository.findByToken(token);
        if(userEntity != null){
            userEntity.setToken(null);
            userRepository.save(userEntity);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkByToken(String token,String email) {
        if(token == null || token == "" || email == null || email == "")
            return false;

        UserEntity userEntity = userRepository.findByToken(token);
        if(userEntity != null){
            return true;
        }
        return false;
    }
}
