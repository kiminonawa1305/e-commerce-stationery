package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.dto.Cart;
import com.lamnguyen.stationery_kimi.dto.CartItem;
import com.lamnguyen.stationery_kimi.dto.ProductOptionDTO;
import com.lamnguyen.stationery_kimi.exception.ApplicationException;
import com.lamnguyen.stationery_kimi.exception.ErrorCode;
import com.lamnguyen.stationery_kimi.request.AddCartItemRequest;
import com.lamnguyen.stationery_kimi.request.DeleteCartItemRequest;
import com.lamnguyen.stationery_kimi.request.EditQuantityCartItemRequest;
import com.lamnguyen.stationery_kimi.service.IProductOptionService;
import com.lamnguyen.stationery_kimi.service.IProductService;
import com.lamnguyen.stationery_kimi.service.IShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements IShoppingCartService {
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductOptionService productOptionService;

    @Override
    public boolean addCartItem(HttpSession session, AddCartItemRequest request) {
        Cart cart = getCart(session);
        if (cart == null) cart = new Cart();

        CartItem cartItem = cart.findCartItem(CartItem.getCartItemId(request.getProductId(), request.getProductOptionId()));
        if (cartItem != null) return increaseCartItemQuantity(session, EditQuantityCartItemRequest.builder()
                .cartItemId(cartItem.getCartItemId())
                .quantity(request.getQuantity())
                .build());

        ProductOptionDTO optionDTO = productOptionService.findById(request.getProductOptionId());
        if (request.getQuantity() > optionDTO.getQuantity())
            throw new ApplicationException(ErrorCode.PRODUCT_OPTION_NOT_ENOUGH);

        cartItem = CartItem.builder()
                .productOptionId(optionDTO.getId())
                .quantity(request.getQuantity())
                .build();

        setCartItem(session, cart, cartItem);
        return true;
    }

    @Override
    public boolean increaseCartItemQuantity(HttpSession session, EditQuantityCartItemRequest request) {
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
        return true;
    }

    @Override
    public boolean decreaseCartItemQuantity(HttpSession session, EditQuantityCartItemRequest request) {
        if (request.getQuantity() <= 0) return false;
        Cart cart = getCart(session);
        CartItem cartItem = cart.findCartItem(request.getCartItemId());
        if (cartItem == null) throw new ApplicationException(ErrorCode.CART_ITEM_NOT_FOUND);
        cartItem.setQuantity(cartItem.getQuantity() - request.getQuantity());
        setCartItem(session, cart, cartItem);
        return true;
    }

    @Override
    public boolean deleteCartItem(HttpSession session, DeleteCartItemRequest request) {
        Cart cart = getCart(session);
        cart.deleteCartItem(request.getCartItemId());
        return true;
    }

    private Cart getCart(HttpSession session) {
        return (Cart) session.getAttribute("cart");
    }

    private void setCart(HttpSession session, Cart cart) {
        session.setAttribute("cart", cart);
    }

    private void setCartItem(HttpSession session, Cart cart, CartItem cartItem) {
        cart.addCartItem(cartItem);
        session.setAttribute("cart", cart);
    }
}
