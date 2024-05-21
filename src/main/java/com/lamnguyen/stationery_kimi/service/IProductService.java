package com.lamnguyen.stationery_kimi.service;

import com.lamnguyen.stationery_kimi.dto.ProductDTO;
import com.lamnguyen.stationery_kimi.entity.Product;

import java.util.List;

public interface IProductService {
    ProductDTO findProductById(Long id);

    ProductDTO addProduct(Product product);

    ProductDTO updateProduct(Product product);

    ProductDTO lockProductById(Product product);

    List<ProductDTO> findAll(Integer page);

    List<ProductDTO> findAllByLockFalse(Integer page);
}
