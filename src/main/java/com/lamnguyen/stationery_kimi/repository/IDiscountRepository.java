package com.lamnguyen.stationery_kimi.repository;

import com.lamnguyen.stationery_kimi.entity.Discount;
import com.lamnguyen.stationery_kimi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface IDiscountRepository extends JpaRepository<Discount, Long> {
    Discount findByStartDateLessThanEqualAndEndDateGreaterThanAndProductsContaining(LocalDateTime now, LocalDateTime now_, Product product);
}
