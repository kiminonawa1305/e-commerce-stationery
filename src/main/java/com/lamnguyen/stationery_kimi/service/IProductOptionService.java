package com.lamnguyen.stationery_kimi.service;

import com.lamnguyen.stationery_kimi.dto.DatatableApiRequest;
import com.lamnguyen.stationery_kimi.dto.ProductOptionDTO;
import com.lamnguyen.stationery_kimi.entity.ProductOption;

import java.util.List;

public interface IProductOptionService {
    List<ProductOptionDTO> findByProductId(Long productId);

    List<ProductOptionDTO> findAllByProductId(Long productId, DatatableApiRequest request);

    ProductOptionDTO findById(Long id);

    ProductOptionDTO buy(Long id, Integer quantity);

    ProductOptionDTO delete(Long id);

    ProductOptionDTO update(Long id, ProductOption productOption);

    ProductOptionDTO add(Long productId, ProductOption productOption);
}
