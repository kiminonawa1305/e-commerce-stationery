package com.lamnguyen.stationery_kimi.service;

import com.lamnguyen.stationery_kimi.dto.*;
import com.lamnguyen.stationery_kimi.entity.Product;

import java.util.List;

public interface IProductService {
    ProductDTO findProductById(Long id);

    ProductDTO addProduct(Product product);

    ProductDTO updateProduct(Product product);

    ProductDTO lockProductById(Product product);

    List<ProductDisplayDTO> findAllByLockFalse(Integer limit, Integer page);

    List<ProductDisplayDTO> findByCategory(Long id, Integer limit, Integer page);

    ProductSeeMoreDTO seeMore(Long id);

    CartItemDisplay findCartItemById(Long id);

    ProductDetailDTO findProductDetailById(Long id);

    List<String> findBrandsByCategoryId(Long categoryId);

    List<ProductDetailDTO> findAll(DatatableApiRequest request);
}
