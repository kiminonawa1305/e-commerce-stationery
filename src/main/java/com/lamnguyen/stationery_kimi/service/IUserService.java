package com.lamnguyen.stationery_kimi.service;

import com.lamnguyen.stationery_kimi.controller.UserRestController;
import com.lamnguyen.stationery_kimi.dto.DatatableApiRequest;
import com.lamnguyen.stationery_kimi.dto.UserDTO;
import com.lamnguyen.stationery_kimi.entity.User;

import java.util.List;

public interface IUserService {
    UserDTO login(String email, String password);

    UserDTO changeProfile(Long id, User user);

    UserDTO updatePassword(Long id, UserRestController.UpdatePasswordRequest user);

    User checkUserExist(String email);

    UserDTO save(User user);

    UserDTO findDTOById(Long id);

    User findById(Long id);

    User findUserByEmail(String email);

    List<UserDTO> findAll(DatatableApiRequest request);

    UserDTO lock(Long id);
}
