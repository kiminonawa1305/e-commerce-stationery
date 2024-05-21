package com.lamnguyen.stationery_kimi.repository;

import com.lamnguyen.stationery_kimi.entity.User;
import com.lamnguyen.stationery_kimi.repository.custom.IUserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>, IUserRepositoryCustom {
    User findFirstByEmail(String email);
}
