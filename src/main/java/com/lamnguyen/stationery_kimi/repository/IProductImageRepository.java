package com.lamnguyen.stationery_kimi.repository;

import com.lamnguyen.stationery_kimi.entity.ProductImage;
import com.lamnguyen.stationery_kimi.repository.custom.IProductImageRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductImageRepository extends JpaRepository<ProductImage, Long>, IProductImageRepositoryCustom {
    List<ProductImage> findByProduct_Id(Long productId);

    ProductImage findFirstByProduct_Id(Long productId);
}
