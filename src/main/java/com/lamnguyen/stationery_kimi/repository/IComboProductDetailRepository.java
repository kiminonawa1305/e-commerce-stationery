package com.lamnguyen.stationery_kimi.repository;

import com.lamnguyen.stationery_kimi.entity.ComboProductDetail;
import com.lamnguyen.stationery_kimi.repository.custom.IComboProductDetailRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComboProductDetailRepository extends JpaRepository<ComboProductDetail, Long>, IComboProductDetailRepositoryCustom {
}
