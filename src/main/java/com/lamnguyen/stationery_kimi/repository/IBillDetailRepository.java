package com.lamnguyen.stationery_kimi.repository;

import com.lamnguyen.stationery_kimi.entity.BillDetail;
import com.lamnguyen.stationery_kimi.repository.custom.IBillDetailRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBillDetailRepository extends JpaRepository<BillDetail, Long>, IBillDetailRepositoryCustom {
    List<BillDetail> findAllByBill_Id(Long billId);
}
