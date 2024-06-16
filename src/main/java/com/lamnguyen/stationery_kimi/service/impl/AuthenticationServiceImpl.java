package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.dto.UserDTO;
import com.lamnguyen.stationery_kimi.entity.User;
import com.lamnguyen.stationery_kimi.entity.VerifyEmailStatus;
import com.lamnguyen.stationery_kimi.enums.UserRole;
import com.lamnguyen.stationery_kimi.enums.VerifyEmailStatusType;
import com.lamnguyen.stationery_kimi.exception.ApplicationException;
import com.lamnguyen.stationery_kimi.exception.ErrorCode;
import com.lamnguyen.stationery_kimi.request.UserRegisterRequest;
import com.lamnguyen.stationery_kimi.request.VerifyUserRequest;
import com.lamnguyen.stationery_kimi.service.IAuthenticationService;
import com.lamnguyen.stationery_kimi.service.IEmailService;
import com.lamnguyen.stationery_kimi.service.IUserService;
import com.lamnguyen.stationery_kimi.service.IVerifyEmailStatusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {
    @Autowired
    private IUserService userService;

    @Autowired
    private IVerifyEmailStatusService verifyEmailStatusService;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${email.time-out}")
    private int timeOut;

    private String titleEmailVerifyRegister = "Xác thực tài khoản",
            titleVerifyForgetPassword = "Xác thực đổi mật khẩu";


    @Override
    public UserDTO login(String email, String password) {
        User user = userService.findUserByEmail(email);
        String passwordUse = user.getPassword();
        if (!passwordEncoder.matches(password, passwordUse)) throw new ApplicationException(ErrorCode.WRONG_PASSWORD);
        if (!verifyEmailStatusService.isVerify(user)) throw new ApplicationException(ErrorCode.VERIFY_EMAIL);
        return userService.login(email, password);
    }

    @Override
    public Long forgotPassword(String email) {
        User user = userService.findUserByEmail(email);

        LocalDateTime expiredAt = LocalDateTime.now().plusMinutes(timeOut);
        String code = verifyEmailStatusService.generateVerificationCode();
        String template = emailService.getTemplate(user.getLastName() + " " + user.getFirstName(), code);

        emailService.sendMessage(user.getEmail(), titleVerifyForgetPassword, template);

        VerifyEmailStatus verifyEmailStatus = user.getVerifyEmailStatus();
        verifyEmailStatus.setCode(code);
        verifyEmailStatus.setExpiredAt(expiredAt);
        verifyEmailStatusService.save(verifyEmailStatus);

        return user.getId();
    }

    @Override
    public UserDTO register(UserRegisterRequest userRegisterRequest) {
        String email = userRegisterRequest.getEmail();
        User userExist = userService.findUserByEmail(email);

        LocalDateTime expiredAt = LocalDateTime.now().plusMinutes(timeOut);
        String code = verifyEmailStatusService.generateVerificationCode();
        String template = emailService.getTemplate(userRegisterRequest.getLastName() + " " + userRegisterRequest.getFirstName(), code);
        VerifyEmailStatus verifyEmailStatus = null;

        if (userExist != null) {
            if (userExist.getVerifyEmailStatus().getType().equals(VerifyEmailStatusType.VERIFY_SUCCESS.toString()))
                throw new ApplicationException(ErrorCode.EMAIL_EXISTED);

            if (userExist.getVerifyEmailStatus().getType().equals(VerifyEmailStatusType.VERIFY_EMAIL.toString())) {
                verifyEmailStatus = userExist.getVerifyEmailStatus();
                verifyEmailStatus.setCode(code);
                verifyEmailStatus.setExpiredAt(expiredAt);
                verifyEmailStatusService.save(verifyEmailStatus);
                throw new ApplicationException(ErrorCode.VERIFY_EMAIL);
            }
        } else {
            verifyEmailStatus = VerifyEmailStatus.builder()
                    .code(code)
                    .expiredAt(expiredAt)
                    .type(VerifyEmailStatusType.VERIFY_EMAIL.toString())
                    .build();
            verifyEmailStatusService.save(verifyEmailStatus);
        }

        emailService.sendMessage(email, titleEmailVerifyRegister, template);

        User user = convertUserRegisterVerify(userRegisterRequest);
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        user.setVerifyEmailStatus(verifyEmailStatus);
        user.setRole(UserRole.USER.toString());
        UserDTO userDTO = userService.save(user);
        return UserDTO.builder()
                .email(userDTO.getEmail())
                .build();
    }

    @Override
    public UserDTO changePassword(User user, String newPassword) {
        return userService.changeProfile(user);
    }

    @Override
    public UserDTO changeProfile(User user) {
        return userService.changeProfile(user);
    }


    @Override
    public User checkUserExist(String email) {
        return userService.checkUserExist(email);
    }

    @Override
    public UserDTO verify(VerifyUserRequest verifyUserRequest) {
        verifyEmailStatusService.verify(verifyUserRequest);
        return UserDTO.builder().email(verifyUserRequest.getEmail()).build();
    }

    @Override
    public void resetPassword(Long id, String password) {
        User user = userService.findById(id);
        if (user.getVerifyEmailStatus().getCode() != null) throw new ApplicationException(ErrorCode.VERIFY_EMAIL);
        user.setPassword(passwordEncoder.encode(password));
        userService.save(user);
    }

    private User convertUserRegisterVerify(UserRegisterRequest userRegisterRequest) {
        return modelMapper.map(userRegisterRequest, User.class);
    }

    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
