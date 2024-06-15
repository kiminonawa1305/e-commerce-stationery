package com.lamnguyen.stationery_kimi.service;

import com.lamnguyen.stationery_kimi.dto.ProductOptionDTO;

import java.util.List;

public interface IProductOptionService {
    List<ProductOptionDTO> findByProductId(Long productId);

    ProductOptionDTO findById(Long id);
}
