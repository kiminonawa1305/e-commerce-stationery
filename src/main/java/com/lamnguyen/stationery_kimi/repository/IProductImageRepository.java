package com.lamnguyen.stationery_kimi.repository;

import com.lamnguyen.stationery_kimi.entity.ProductImage;
import com.lamnguyen.stationery_kimi.repository.custom.IProductImageRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductImageRepository extends JpaRepository<ProductImage, Long>, IProductImageRepositoryCustom {
}
