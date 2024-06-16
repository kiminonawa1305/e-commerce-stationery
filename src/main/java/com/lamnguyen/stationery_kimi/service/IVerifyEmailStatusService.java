package com.lamnguyen.stationery_kimi.service;

import com.lamnguyen.stationery_kimi.entity.User;
import com.lamnguyen.stationery_kimi.entity.VerifyEmailStatus;
import com.lamnguyen.stationery_kimi.request.VerifyUserRequest;

public interface IVerifyEmailStatusService {
    String generateVerificationCode();

    VerifyEmailStatus save(VerifyEmailStatus emailStatus);

    Long verify(VerifyUserRequest verifyUserRequest);

    Boolean isVerify(User user);
}
