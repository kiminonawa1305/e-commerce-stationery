package com.lamnguyen.stationery_kimi.repository;

import com.lamnguyen.stationery_kimi.entity.Bill;
import com.lamnguyen.stationery_kimi.entity.BillStatus;
import com.lamnguyen.stationery_kimi.repository.custom.IBillRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBillRepository extends JpaRepository<Bill, Long>, IBillRepositoryCustom {
    List<Bill> findByUser_Id(Long userId);
}
