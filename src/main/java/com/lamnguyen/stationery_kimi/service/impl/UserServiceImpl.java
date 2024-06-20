package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.controller.UserRestController;
import com.lamnguyen.stationery_kimi.dto.UserDTO;
import com.lamnguyen.stationery_kimi.entity.User;
import com.lamnguyen.stationery_kimi.exception.ApplicationException;
import com.lamnguyen.stationery_kimi.exception.ErrorCode;
import com.lamnguyen.stationery_kimi.repository.IUserRepository;
import com.lamnguyen.stationery_kimi.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDTO login(String email, String password) {
        User user = userRepository.findFirstByEmail(email);
        if (user == null) throw new ApplicationException(ErrorCode.USER_NOT_FOUND);
        return convertToDTO(user);
    }

    @Override
    public UserDTO changeProfile(Long id, User user) {
        User oldUser = userRepository.findById(id).orElse(null);
        if (oldUser == null) throw new ApplicationException(ErrorCode.USER_NOT_FOUND);
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setPhone(user.getPhone());
        oldUser.setPhone(user.getPhone());
        User result = userRepository.saveAndFlush(oldUser);
        return convertToDTO(result);
    }

    @Override
    public UserDTO updatePassword(Long id, UserRestController.UpdatePasswordRequest user) {
        User oldUser = userRepository.findById(id).orElse(null);
        if (oldUser == null) throw new ApplicationException(ErrorCode.USER_NOT_FOUND);
        if (!passwordEncoder.matches(user.oldPassword(), oldUser.getPassword()))
            throw new ApplicationException(ErrorCode.WRONG_PASSWORD);
        if (!user.newPassword().equals(user.confirmNewPassword()))
            throw new ApplicationException(ErrorCode.PASSWORD_NOT_MATCH);
        oldUser.setPassword(passwordEncoder.encode(user.newPassword()));
        return save(oldUser);
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
    public UserDTO findDTOById(Long id) {
        return convertToDTO(findById(id));
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) throw new ApplicationException(ErrorCode.USER_NOT_FOUND);
        return user;
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
