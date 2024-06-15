package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.dto.ProductOptionDTO;
import com.lamnguyen.stationery_kimi.entity.ProductOption;
import com.lamnguyen.stationery_kimi.exception.ApplicationException;
import com.lamnguyen.stationery_kimi.exception.ErrorCode;
import com.lamnguyen.stationery_kimi.repository.IProductOptionRepository;
import com.lamnguyen.stationery_kimi.service.IProductOptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductOptionServiceImpl implements IProductOptionService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IProductOptionRepository productOptionRepository;

    @Override
    public List<ProductOptionDTO> findByProductId(Long productId) {
        List<ProductOption> productOption = productOptionRepository.findByQuantityGreaterThanEqualAndProduct_Id(1, productId);
        return productOption.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ProductOptionDTO findById(Long id) {
        ProductOption productOption = productOptionRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.PRODUCT_OPTION_NOT_FOUND));
        return convertToDTO(productOption);
    }

    private ProductOptionDTO convertToDTO(ProductOption productOption) {
        return modelMapper.map(productOption, ProductOptionDTO.class);
    }

    private List<ProductOptionDTO> convertToDTO(List<ProductOption> productOptions) {
        return productOptions.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
