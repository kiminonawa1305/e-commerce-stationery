package com.lamnguyen.stationery_kimi.repository.custom;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepositoryCustom {
    @Transactional
    @Query("SELECT distinct p.brand FROM Product p WHERE p.category.id = :categoryId")
    List<String> findBrandsByCategory_Id(@Param("categoryId") Long categoryId);
}
