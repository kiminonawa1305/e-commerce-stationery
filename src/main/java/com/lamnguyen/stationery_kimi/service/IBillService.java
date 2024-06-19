package com.lamnguyen.stationery_kimi.service;

import com.lamnguyen.stationery_kimi.controller.PaymentRestController;
import com.lamnguyen.stationery_kimi.response.BillDTO;
import jakarta.servlet.http.HttpSession;

public interface IBillService {
    BillDTO createBill(HttpSession session, PaymentRestController.PaymentRequest request);
}
