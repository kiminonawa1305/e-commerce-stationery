package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.dto.ProductDTO;
import com.lamnguyen.stationery_kimi.entity.Product;
import com.lamnguyen.stationery_kimi.exception.ApplicationException;
import com.lamnguyen.stationery_kimi.exception.ErrorCode;
import com.lamnguyen.stationery_kimi.repository.IProductRepository;
import com.lamnguyen.stationery_kimi.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    IProductRepository productRepository;
    @Autowired(required = true)
    private ModelMapper modelMapper;

    @Override
    public ProductDTO findProductById(Long id) {
        return null;
    }

    @Override
    public ProductDTO addProduct(Product product) {
        Product productEntity = productRepository.saveAndFlush(product);
        return convertToDTO(productEntity);
    }

    @Override
    public ProductDTO updateProduct(Product product) {
        if (product.getId() == null) throw new ApplicationException(ErrorCode.NULL_ID_PRODUCT);
        Product productEntity = productRepository.saveAndFlush(product);
        return convertToDTO(productEntity);
    }

    @Override
    public ProductDTO lockProductById(Product product) {
        if (product.getId() == null) throw new ApplicationException(ErrorCode.NULL_ID_PRODUCT);
        productRepository.lockProductById(product.getId(), product.getLock());
        return convertToDTO(product);
    }

    @Override
    public List<ProductDTO> findAll(Integer page) {
        List<Product> result = productRepository.findAll(Pageable.ofSize(20).withPage(page)).getContent();
        if (result.isEmpty()) throw new ApplicationException(ErrorCode.PRODUCT_NOT_FOUND);
        return convertToDTOList(result);
    }

    @Override
    public List<ProductDTO> findAllByLockFalse(Integer page) {
        List<Product> result = productRepository.findAllByLockFalse(Pageable.ofSize(20).withPage(page)).getContent();
        if (result.isEmpty()) throw new ApplicationException(ErrorCode.PRODUCT_NOT_FOUND);
        return convertToDTOList(result);
    }

    private ProductDTO convertToDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    private List<ProductDTO> convertToDTOList(List<Product> products) {
        return products.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
