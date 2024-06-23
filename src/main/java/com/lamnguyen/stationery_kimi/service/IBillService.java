package com.lamnguyen.stationery_kimi.service;

import com.lamnguyen.stationery_kimi.controller.PaymentRestController;
import com.lamnguyen.stationery_kimi.dto.*;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface IBillService {
    BillDTO createBill(HttpSession session, PaymentRestController.PaymentRequest request);

    List<BillDisplay> getBillByStatusAndUserId(Long id, String status);

    List<BillManager> fillAll(String status, DatatableApiRequest request);

    BillStatusDTO cancelBill(Long id, Long billId);
}
