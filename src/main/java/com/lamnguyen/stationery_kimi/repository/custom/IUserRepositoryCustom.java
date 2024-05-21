package com.lamnguyen.stationery_kimi.repository.custom;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserRepositoryCustom {
    @Query(value = "SELECT u.password FROM User as u WHERE u.email = :email")
    String findPasswordByEmail(@Param("email") String email);
}
