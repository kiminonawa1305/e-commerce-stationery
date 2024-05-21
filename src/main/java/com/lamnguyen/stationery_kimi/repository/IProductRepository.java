package com.lamnguyen.stationery_kimi.repository;

import com.lamnguyen.stationery_kimi.entity.Product;
import com.lamnguyen.stationery_kimi.repository.custom.IProductRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long>, IProductRepositoryCustom {
    Slice<Product> findAllByLockFalse(Pageable pageable);
}
