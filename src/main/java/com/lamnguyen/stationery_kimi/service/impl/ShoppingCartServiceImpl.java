package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.dto.*;
import com.lamnguyen.stationery_kimi.entity.Bill;
import com.lamnguyen.stationery_kimi.entity.BillDetail;
import com.lamnguyen.stationery_kimi.entity.ProductOption;
import com.lamnguyen.stationery_kimi.exception.ApplicationException;
import com.lamnguyen.stationery_kimi.exception.ErrorCode;
import com.lamnguyen.stationery_kimi.request.AddCartItemRequest;
import com.lamnguyen.stationery_kimi.request.DeleteCartItemRequest;
import com.lamnguyen.stationery_kimi.request.EditQuantityCartItemRequest;
import com.lamnguyen.stationery_kimi.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements IShoppingCartService {
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductOptionService productOptionService;
    @Autowired
    private IProductImageService productImageService;
    @Autowired
    private IDiscountService discountService;

    @Override
    public Integer addCartItem(HttpSession session, AddCartItemRequest request) {
        if (request.getQuantity() <= 0) throw new ApplicationException(ErrorCode.INVALID_QUANTITY);
        Cart cart = getCart(session);
        CartItem cartItem = cart.findCartItem(CartItem.getCartItemId(request.getProductId(), request.getProductOptionId()));
        if (cartItem != null) return increaseCartItemQuantity(session, EditQuantityCartItemRequest.builder()
                .cartItemId(cartItem.getCartItemId())
                .quantity(request.getQuantity())
                .build());

        ProductOptionDTO optionDTO = productOptionService.findById(request.getProductOptionId());
        if (request.getQuantity() > optionDTO.getQuantity())
            throw new ApplicationException(ErrorCode.PRODUCT_OPTION_NOT_ENOUGH);

        cartItem = CartItem.builder()
                .productId(request.getProductId())
                .productOptionId(optionDTO.getId())
                .quantity(request.getQuantity())
                .build();

        setCartItem(session, cart, cartItem);
        return cart.getCartItems().size();
    }

    @Override
    public Integer increaseCartItemQuantity(HttpSession session, EditQuantityCartItemRequest request) {
        if (request.getQuantity() <= 0) throw new ApplicationException(ErrorCode.INVALID_QUANTITY);
        Cart cart = getCart(session);
        CartItem cartItem = cart.findCartItem(request.getCartItemId());
        if (cartItem == null) return addCartItem(session, AddCartItemRequest.builder()
                .productId(cart.getProductId(request.getCartItemId()))
                .productOptionId(cart.getProductOptionId(request.getCartItemId()))
                .quantity(request.getQuantity())
                .build());

        ProductOptionDTO optionDTO = productOptionService.findById(cart.getProductOptionId(request.getCartItemId()));

        if (request.getQuantity() > optionDTO.getQuantity() - cartItem.getQuantity())
            throw new ApplicationException(ErrorCode.PRODUCT_OPTION_NOT_ENOUGH);
        cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());

        setCartItem(session, cart, cartItem);
        return cart.getCartItems().size();
    }

    @Override
    public Integer decreaseCartItemQuantity(HttpSession session, EditQuantityCartItemRequest request) {
        if (request.getQuantity() <= 0) throw new ApplicationException(ErrorCode.INVALID_QUANTITY);
        Cart cart = getCart(session);
        CartItem cartItem = cart.findCartItem(request.getCartItemId());
        if (cartItem == null) throw new ApplicationException(ErrorCode.CART_ITEM_NOT_FOUND);
        cartItem.setQuantity(cartItem.getQuantity() - request.getQuantity());
        setCartItem(session, cart, cartItem);
        return cart.getCartItems().size();
    }

    @Override
    public Integer deleteCartItem(HttpSession session, DeleteCartItemRequest request) {
        Cart cart = getCart(session);
        cart.deleteCartItem(request.getCartItemId());
        return cart.getCartItems().size();
    }

    @Override
    public List<CartItemDisplay> loadCart(HttpSession session) {
        Cart cart = getCart(session);
        var cartItemDisplays = new ArrayList<CartItemDisplay>();
        cart.getCartItems().forEach((s, cartItem) -> {
            Long productId = cartItem.getProductId();
            Long productOptionId = cartItem.getProductOptionId();
            CartItemDisplay cartItemDisplay = productService.findCartItemById(productId);
            ProductOptionDTO option = productOptionService.findById(productOptionId);
            DiscountDTO discountDTO = discountService.getDiscount(productId);
            double discountPercent = discountDTO == null ? 0.0 : discountDTO.getDiscountPercent();

            cartItemDisplay.setCartItemId(cartItem.getCartItemId());
            cartItemDisplay.setUrl(productImageService.findFirstByProductId(productId).getUrl());
            cartItemDisplay.setPercentDiscount(discountPercent);
            cartItemDisplay.setOption(option.getName());
            cartItemDisplay.setQuantity(cartItem.getQuantity());

            cartItemDisplays.add(cartItemDisplay);
        });
        return cartItemDisplays;
    }

    public List<BillDetail> getBillDetails(HttpSession session, Bill bill) {
        List<CartItemDisplay> cartItemDisplays = loadCart(session);
        List<BillDetail> billDetails = cartItemDisplays
                .stream()
                .map(item -> BillDetail.builder()
                        .price(item.getTotalPrice())
                        .quantity(item.getQuantity())
                        .productOption(ProductOption.builder()
                                .id(getCart(session).getProductOptionId(item.getCartItemId()))
                                .build())
                        .bill(bill)
                        .build())
                .toList();
        return billDetails;
    }

    public Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) cart = new Cart();
        return cart;
    }


    private void setCartItem(HttpSession session, Cart cart, CartItem cartItem) {
        cart.addCartItem(cartItem);
        session.setAttribute("cart", cart);
    }
}
