package com.lamnguyen.stationery_kimi.service;

import com.lamnguyen.stationery_kimi.dto.ProductDTO;
import com.lamnguyen.stationery_kimi.dto.ProductDisplayDTO;
import com.lamnguyen.stationery_kimi.dto.ProductOptionDTO;
import com.lamnguyen.stationery_kimi.dto.ProductSeeMoreDTO;
import com.lamnguyen.stationery_kimi.entity.Product;

import java.util.ArrayList;
import java.util.List;

public interface IProductService {
    ProductDTO findProductById(Long id);

    ProductDTO addProduct(Product product);

    ProductDTO updateProduct(Product product);

    ProductDTO lockProductById(Product product);


    List<ProductDisplayDTO> findAllByLockFalse(Integer limit, Integer page);

    List<ProductDisplayDTO> findByCategory(Long id, Integer limit, Integer page);

    ProductSeeMoreDTO seeMore(Long id);
}
