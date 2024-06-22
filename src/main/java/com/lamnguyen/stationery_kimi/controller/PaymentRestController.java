package com.lamnguyen.stationery_kimi.controller;

import com.lamnguyen.stationery_kimi.dto.CartItemDisplay;
import com.lamnguyen.stationery_kimi.service.IBillService;
import com.lamnguyen.stationery_kimi.service.IShoppingCartService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class PaymentRestController {
    @Autowired
    private IBillService iBillService;
    @Autowired
    private IShoppingCartService iShoppingCartService;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @PostMapping("/pay")
    public String payment(HttpSession session, @ModelAttribute PaymentRequest request, Model model) {
        request.setPayment(request.getPayment().equals("qr") ? "Chuyển khoản qua QR Code" : "Thanh toán khi nhận hàng");
        List<CartItemDisplay> cart = iShoppingCartService.loadCart(session);
        model.addAttribute("bill", iBillService.createBill(session, request));
        model.addAttribute("totalPrice", cart.stream().mapToInt(CartItemDisplay::getTotalPay).sum());
        model.addAttribute("time", dateTimeFormatter.format(java.time.LocalDateTime.now()));
        iShoppingCartService.clearCart(session);
        return "payment-success";
    }

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaymentRequest {
        String name,
                phone,
                email,
                note,
                province,
                district,
                commune,
                fullAddress,
                payment;
    }


}
