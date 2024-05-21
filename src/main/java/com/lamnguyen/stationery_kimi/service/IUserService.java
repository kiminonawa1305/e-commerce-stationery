package com.lamnguyen.stationery_kimi.service;

import com.lamnguyen.stationery_kimi.dto.UserDTO;
import com.lamnguyen.stationery_kimi.entity.User;

public interface IUserService {
    UserDTO login(String email, String password);

    UserDTO changeProfile(User user);

    User checkUserExist(String email);

    UserDTO save(User user);

    UserDTO findById(Long id);

    User findUserByEmail(String email);
}
