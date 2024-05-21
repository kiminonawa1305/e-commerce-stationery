package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.dto.ProductCartDetail;
import com.lamnguyen.stationery_kimi.dto.ProductDTO;
import com.lamnguyen.stationery_kimi.exception.ApplicationException;
import com.lamnguyen.stationery_kimi.exception.ErrorCode;
import com.lamnguyen.stationery_kimi.service.IProductService;
import com.lamnguyen.stationery_kimi.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ShoppingCartServiceImpl implements IShoppingCartService {
    @Autowired
    private IProductService productService;

    private Map<Long, ProductCartDetail> cart;

    public ShoppingCartServiceImpl() {
        cart = new HashMap<>();
    }

    @Override
    public boolean addProduct(Long id, Integer quantity) {
        ProductDTO product = productService.findProductById(id);
        if (product == null) throw new ApplicationException(ErrorCode.PRODUCT_NOT_FOUND);
        ProductCartDetail productCartDetail;
        if (cart.containsKey(product.getId())) {
            productCartDetail = cart.get(product.getId());
            if (product.getQuantity() < quantity + productCartDetail.getQuantity())
                throw new ApplicationException(ErrorCode.PRODUCT_NOT_ENOUGH);
            productCartDetail.setQuantity(productCartDetail.getQuantity() + quantity);
            return true;
        }
        if (product.getQuantity() < quantity) throw new ApplicationException(ErrorCode.PRODUCT_NOT_ENOUGH);
        cart.put(product.getId(), ProductCartDetail.builder().product(product).quantity(quantity).build());
        return true;
    }
}
