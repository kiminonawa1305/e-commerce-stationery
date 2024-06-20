package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.controller.PaymentRestController;
import com.lamnguyen.stationery_kimi.entity.Bill;
import com.lamnguyen.stationery_kimi.entity.BillDetail;
import com.lamnguyen.stationery_kimi.entity.BillStatus;
import com.lamnguyen.stationery_kimi.enums.BillStatusEnum;
import com.lamnguyen.stationery_kimi.repository.IBillDetailRepository;
import com.lamnguyen.stationery_kimi.repository.IBillRepository;
import com.lamnguyen.stationery_kimi.response.BillDTO;
import com.lamnguyen.stationery_kimi.service.IBillService;
import com.lamnguyen.stationery_kimi.service.IBillStatusService;
import com.lamnguyen.stationery_kimi.service.IProductOptionService;
import com.lamnguyen.stationery_kimi.service.IShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillServiceImpl implements IBillService {
    @Autowired
    private IBillRepository iBillRepository;
    @Autowired
    private IBillDetailRepository iBillDetailRepository;
    @Autowired
    private IShoppingCartService iShoppingCartService;
    @Autowired
    private IProductOptionService iProductOptionService;
    @Autowired
    private IBillStatusService iBillStatusService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BillDTO createBill(HttpSession session, PaymentRestController.PaymentRequest request) {
        Bill bill = Bill.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .paymentMethod(request.getPayment())
                .shippingAddress(request.getFullAddress())
                .shippingFee(10000)
                .shippingNote(request.getNote())
                .build();
        bill = iBillRepository.save(bill);
        List<BillDetail> billDetailList = iShoppingCartService.getBillDetails(session, bill);
        billDetailList = iBillDetailRepository.saveAllAndFlush(billDetailList);
        billDetailList.forEach(billDetail -> iProductOptionService.buy(billDetail.getId(), billDetail.getQuantity()));
        BillStatus billStatus = BillStatus.builder()
                .bill(bill)
                .status(BillStatusEnum.ORDERED.getStatus())
                .data(LocalDateTime.now())
                .build();
        iBillStatusService.save(billStatus);
        return convertToDTO(bill);
    }

    private BillDTO convertToDTO(Bill bill) {
        return modelMapper.map(bill, BillDTO.class);
    }
}
