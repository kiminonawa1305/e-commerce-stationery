package com.lamnguyen.stationery_kimi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lamnguyen.stationery_kimi.entity.Bill;
import com.lamnguyen.stationery_kimi.entity.VerifyEmailStatus;
import com.lamnguyen.stationery_kimi.entity.VoucherUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserDTO implements Serializable {
    private Long id;
    private String avatar;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
