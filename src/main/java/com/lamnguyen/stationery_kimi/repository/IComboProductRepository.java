package com.lamnguyen.stationery_kimi.repository;

import com.lamnguyen.stationery_kimi.entity.ComboProduct;
import com.lamnguyen.stationery_kimi.repository.custom.IComboProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComboProductRepository extends JpaRepository<ComboProduct, Long>, IComboProductRepositoryCustom {
}
