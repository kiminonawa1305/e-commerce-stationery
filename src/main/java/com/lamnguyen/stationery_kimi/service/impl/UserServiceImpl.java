package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.dto.UserDTO;
import com.lamnguyen.stationery_kimi.entity.User;
import com.lamnguyen.stationery_kimi.exception.ApplicationException;
import com.lamnguyen.stationery_kimi.exception.ErrorCode;
import com.lamnguyen.stationery_kimi.repository.IUserRepository;
import com.lamnguyen.stationery_kimi.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IUserRepository userRepository;


    @Override
    public UserDTO login(String email, String password) {
        User user = userRepository.findFirstByEmail(email);
        if (user == null) throw new ApplicationException(ErrorCode.USER_NOT_FOUND);
        return convertToDTO(user);
    }

    @Override
    public UserDTO changeProfile(User user) {
        User result = userRepository.saveAndFlush(user);
        return convertToDTO(result);
    }

    @Override
    public User checkUserExist(String email) {
        User user = userRepository.findFirstByEmail(email);
        if (user == null) throw new ApplicationException(ErrorCode.USER_NOT_FOUND);
        return user;
    }

    @Override
    public UserDTO save(User user) {
        return convertToDTO(userRepository.saveAndFlush(user));
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) throw new ApplicationException(ErrorCode.USER_NOT_FOUND);
        return convertToDTO(user);
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findFirstByEmail(email);
        if (user == null) throw new ApplicationException(ErrorCode.USER_NOT_FOUND);
        return user;
    }

    private UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private List<UserDTO> convertToDTOList(List<User> users) {
        return users.stream().map(this::convertToDTO).toList();
    }
}
