package com.lamnguyen.stationery_kimi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
    private String phone;
    private Boolean lock;
}
