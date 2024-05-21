package com.lamnguyen.stationery_kimi.repository;

import com.lamnguyen.stationery_kimi.entity.User;
import com.lamnguyen.stationery_kimi.entity.VerifyEmailStatus;
import com.lamnguyen.stationery_kimi.repository.custom.IVerifyEmailStatusRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVerifyEmailStatusRepository extends JpaRepository<VerifyEmailStatus, Long>, IVerifyEmailStatusRepositoryCustom {
}
