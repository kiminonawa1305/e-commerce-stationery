package com.lamnguyen.stationery_kimi.repository;

import com.lamnguyen.stationery_kimi.entity.VoucherUser;
import com.lamnguyen.stationery_kimi.repository.custom.IVoucherUserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVoucherUserRepository extends JpaRepository<VoucherUser, Long> , IVoucherUserRepositoryCustom {
}
