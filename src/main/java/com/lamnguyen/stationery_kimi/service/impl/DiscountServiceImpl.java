package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.dto.DiscountDTO;
import com.lamnguyen.stationery_kimi.entity.Discount;
import com.lamnguyen.stationery_kimi.entity.Product;
import com.lamnguyen.stationery_kimi.repository.IDiscountRepository;
import com.lamnguyen.stationery_kimi.service.IDiscountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DiscountServiceImpl implements IDiscountService {
    @Autowired
    private IDiscountRepository discountRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public DiscountDTO getDiscount(Long id) {
        LocalDateTime now = LocalDateTime.now();
        Discount discount = discountRepository.findByStartDateLessThanEqualAndEndDateGreaterThanAndProductsContaining(now, now, Product.builder().id(id).build());
        if (discount == null) return null;
        return convertToDTO(discount);
    }

    private DiscountDTO convertToDTO(Discount discount) {
        return modelMapper.map(discount, DiscountDTO.class);
    }
}
