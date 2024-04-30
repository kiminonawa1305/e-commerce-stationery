package com.lamnguyen.stationery_kimi.repository;

import com.lamnguyen.stationery_kimi.entity.BillStatus;
import com.lamnguyen.stationery_kimi.repository.custom.IBillStatusRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBillStatusRepository extends JpaRepository<BillStatus, Long>, IBillStatusRepositoryCustom {
}
