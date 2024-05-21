package com.lamnguyen.stationery_kimi.repository.custom;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProductRepositoryCustom {
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.lock = :lock WHERE p.id = :productId")
    Integer lockProductById(@Param("productId") Long productId, @Param("lock") Boolean lock);
}
