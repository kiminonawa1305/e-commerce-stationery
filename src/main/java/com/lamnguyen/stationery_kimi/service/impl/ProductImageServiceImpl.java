package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.dto.ProductImageDTO;
import com.lamnguyen.stationery_kimi.entity.ProductImage;
import com.lamnguyen.stationery_kimi.repository.IProductImageRepository;
import com.lamnguyen.stationery_kimi.service.IProductImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements IProductImageService {
    @Autowired
    private IProductImageRepository productImageRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductImageDTO> findByProductId(Long productId) {
        return convertToDTO(productImageRepository.findByProduct_Id(productId));
    }

    @Override
    public ProductImageDTO findFirstByProductId(Long productId) {
        ProductImage image = productImageRepository.findFirstByProduct_Id(productId);
        if (image == null) return ProductImageDTO.builder()
                .url("/images/product/demo_product.webp")
                .id(-1L)
                .type("")
                .build();
        return convertToDTO(image);
    }

    private ProductImageDTO convertToDTO(ProductImage productImage) {
        return modelMapper.map(productImage, ProductImageDTO.class);
    }

    private List<ProductImageDTO> convertToDTO(List<ProductImage> productImages) {
        return productImages.stream().map(this::convertToDTO).toList();
    }
}
