package com.lamnguyen.stationery_kimi.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VerifyUserRequest {
    private String email;
    private String verificationCode;
}
