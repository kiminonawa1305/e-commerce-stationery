package com.lamnguyen.stationery_kimi.repository.custom;

import com.lamnguyen.stationery_kimi.dto.CartItemDisplay;
import com.lamnguyen.stationery_kimi.dto.ProductDTO;
import com.lamnguyen.stationery_kimi.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepositoryCustom {
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.lock = :lock WHERE p.id = :productId")
    Integer lockProductById(@Param("productId") Long productId, @Param("lock") Boolean lock);

    @Transactional
    @Query("SELECT distinct p.brand FROM Product p WHERE p.category.id = :categoryId")
    List<String> findBrandsByCategory_Id(@Param("categoryId") Long categoryId);
}
