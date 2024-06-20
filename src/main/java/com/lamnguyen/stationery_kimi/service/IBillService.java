package com.lamnguyen.stationery_kimi.service;

import com.lamnguyen.stationery_kimi.controller.PaymentRestController;
import com.lamnguyen.stationery_kimi.dto.BillDTO;
import com.lamnguyen.stationery_kimi.dto.BillDisplay;
import com.lamnguyen.stationery_kimi.dto.BillStatusDTO;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface IBillService {
    BillDTO createBill(HttpSession session, PaymentRestController.PaymentRequest request);

    List<BillDisplay> getBillByStatus(Long id, String status);

    BillStatusDTO cancelBill(Long id, Long billId);
}
