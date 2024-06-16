package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.entity.User;
import com.lamnguyen.stationery_kimi.entity.VerifyEmailStatus;
import com.lamnguyen.stationery_kimi.enums.VerifyEmailStatusType;
import com.lamnguyen.stationery_kimi.exception.ApplicationException;
import com.lamnguyen.stationery_kimi.exception.ErrorCode;
import com.lamnguyen.stationery_kimi.repository.IVerifyEmailStatusRepository;
import com.lamnguyen.stationery_kimi.request.VerifyUserRequest;
import com.lamnguyen.stationery_kimi.service.IEmailService;
import com.lamnguyen.stationery_kimi.service.IUserService;
import com.lamnguyen.stationery_kimi.service.IVerifyEmailStatusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VerifyEmailStatusServiceImpl implements IVerifyEmailStatusService {
    @Autowired
    private IVerifyEmailStatusRepository verifyEmailStatusRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private IEmailService emailService;
    @Autowired
    private ModelMapper modelMapper;
    @Value("${email.time-out}")
    private int timeOut;
    private String titleEmailVerifyRegister = "Xác thực tài khoản";

    public String generateVerificationCode() {
        int numberCode = (int) (Math.random() * 999999);
        int length = String.valueOf(numberCode).length();
        return switch (length) {
            case 1 -> "00000" + numberCode;
            case 2 -> "0000" + numberCode;
            case 3 -> "000" + numberCode;
            case 4 -> "00" + numberCode;
            case 5 -> "0" + numberCode;
            default -> String.valueOf(numberCode);
        };
    }

    public VerifyEmailStatus save(VerifyEmailStatus verifyEmailStatus) {
        return verifyEmailStatusRepository.saveAndFlush(verifyEmailStatus);
    }

    @Override
    public Long verify(VerifyUserRequest verifyUserRequest) {
        User user = userService.findUserByEmail(verifyUserRequest.getEmail());
        VerifyEmailStatus verifyEmailStatus = user.getVerifyEmailStatus();
        if (!verifyEmailStatus.getCode().equals(verifyUserRequest.getVerificationCode()))
            throw new ApplicationException(ErrorCode.WRONG_VERIFY_CODE);

        if (verifyEmailStatus.getExpiredAt().isBefore(java.time.LocalDateTime.now())) {
            if (!verifyEmailStatus.getType().equals(VerifyEmailStatusType.VERIFY_EMAIL.toString()))
                throw new ApplicationException(ErrorCode.TEST_ERROR);

            String code = generateVerificationCode();
            emailService.sendMessage(verifyUserRequest.getEmail(), titleEmailVerifyRegister, emailService.getTemplate(user.getLastName() + " " + user.getFirstName(), code));

            verifyEmailStatus.setExpiredAt(LocalDateTime.now().plusMinutes(timeOut));
            verifyEmailStatus.setCode(code);
            save(verifyEmailStatus);
            throw new ApplicationException(ErrorCode.VERIFY_CODE_EXPIRED);
        }

        verifyEmailStatus.setCode(null);
        verifyEmailStatus.setExpiredAt(null);
        verifyEmailStatus.setType(VerifyEmailStatusType.VERIFY_SUCCESS.toString());
        verifyEmailStatusRepository.saveAndFlush(verifyEmailStatus);

        return user.getId();
    }

    @Override
    public Boolean isVerify(User user) {
        return user.getVerifyEmailStatus().getType().equals(VerifyEmailStatusType.VERIFY_SUCCESS.toString());
    }
}
