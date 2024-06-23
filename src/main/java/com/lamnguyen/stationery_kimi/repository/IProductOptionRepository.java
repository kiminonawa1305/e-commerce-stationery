package com.lamnguyen.stationery_kimi.repository;

import com.lamnguyen.stationery_kimi.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductOptionRepository extends JpaRepository<ProductOption, Long> {
    List<ProductOption> findByQuantityGreaterThanEqualAndProduct_Id(Integer quantity, Long productId);

    List<ProductOption> findAllByProduct_Id(Long productId);
}
