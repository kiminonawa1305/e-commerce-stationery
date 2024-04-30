package com.lamnguyen.stationery_kimi.repository;

import com.lamnguyen.stationery_kimi.entity.BillVoucher;
import com.lamnguyen.stationery_kimi.repository.custom.IBillVoucherRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBillVoucherRepository extends JpaRepository<BillVoucher, Long>, IBillVoucherRepositoryCustom {
}
