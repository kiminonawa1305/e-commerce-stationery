package com.lamnguyen.stationery_kimi.repository;

import com.lamnguyen.stationery_kimi.entity.BillStatus;
import com.lamnguyen.stationery_kimi.repository.custom.IBillStatusRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBillStatusRepository extends JpaRepository<BillStatus, Long>, IBillStatusRepositoryCustom {
    List<BillStatus> findAllByBill_Id(Long billId);
}
