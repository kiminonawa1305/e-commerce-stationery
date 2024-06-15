package com.lamnguyen.stationery_kimi.service;

import com.lamnguyen.stationery_kimi.dto.ProductImageDTO;
import com.lamnguyen.stationery_kimi.entity.ProductImage;

import java.util.List;

public interface IProductImageService {
    List<ProductImageDTO> findByProductId(Long productId);

    ProductImageDTO findFirstByProductId(Long productId);
}
