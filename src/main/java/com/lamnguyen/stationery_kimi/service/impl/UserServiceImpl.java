package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.controller.UserRestController;
import com.lamnguyen.stationery_kimi.dto.DatatableApiRequest;
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

import java.util.ArrayList;
import java.util.Comparator;
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

    @Override
    public List<UserDTO> findAll(DatatableApiRequest request) {
        List<UserDTO> users = new ArrayList<>(convertToDTOList(userRepository.findAll()));
        searchProduct(users, request);
        sortProduct(users, request);
        return users;
    }

    @Override
    public UserDTO lock(Long id) {
        User user = findById(id);
        user.setLock(!user.getLock());
        return convertToDTO(userRepository.save(user));
    }

    private UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private List<UserDTO> convertToDTOList(List<User> users) {
        return users.stream().map(this::convertToDTO).toList();
    }

    private void sortProduct(List<UserDTO> users, DatatableApiRequest request) {
        if (users.size() > 1) {
            request.getOrder().forEach(order -> {
                switch (order.getName()) {
                    case "id" -> {
                        switch (order.getDir()) {
                            case "asc" -> users.sort(Comparator.comparingLong(UserDTO::getId));
                            case "desc" -> users.sort((c1, c2) -> Long.compare(c2.getId(), c1.getId()));
                        }
                    }
                    case "email" -> {
                        switch (order.getDir()) {
                            case "asc" -> users.sort(Comparator.comparing(UserDTO::getEmail));
                            case "desc" -> users.sort((c1, c2) -> c2.getEmail().compareTo(c1.getEmail()));
                        }
                    }
                    case "firstName" -> {
                        switch (order.getDir()) {
                            case "asc" -> users.sort(Comparator.comparing(UserDTO::getFirstName));
                            case "desc" -> users.sort((c1, c2) -> c2.getFirstName().compareTo(c1.getFirstName()));
                        }
                    }
                    case "lastName" -> {
                        switch (order.getDir()) {
                            case "asc" -> users.sort(Comparator.comparing(UserDTO::getLastName));
                            case "desc" -> users.sort((c1, c2) -> c2.getLastName().compareTo(c1.getLastName()));
                        }
                    }
                    case "phone" -> {
                        switch (order.getDir()) {
                            case "asc" -> users.sort(Comparator.comparing(UserDTO::getPhone));
                            case "desc" -> users.sort((c1, c2) -> c2.getPhone().compareTo(c1.getPhone()));
                        }
                    }
                    case "role" -> {
                        switch (order.getDir()) {
                            case "asc" -> users.sort(Comparator.comparing(UserDTO::getRole));
                            case "desc" -> users.sort((c1, c2) -> c2.getRole().compareTo(c1.getRole()));
                        }
                    }
                }
            });
        }
    }

    private void searchProduct(List<UserDTO> users, DatatableApiRequest request) {
        String searchValue = request.getSearch().getValue();
        if (searchValue != null && !searchValue.isBlank())
            users.removeIf(user -> !user.getPhone().toLowerCase().contains(searchValue.toLowerCase()) && !user.getId().toString().contains(searchValue.toLowerCase()) && !user.getEmail().toLowerCase().contains(searchValue.toLowerCase()) && !user.getLastName().toLowerCase().contains(searchValue.toLowerCase()) && !user.getFirstName().toLowerCase().contains(searchValue.toLowerCase()) && !user.getRole().toLowerCase().contains(searchValue.toLowerCase()));
    }
}
