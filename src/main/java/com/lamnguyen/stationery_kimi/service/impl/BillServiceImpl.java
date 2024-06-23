package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.controller.PaymentRestController;
import com.lamnguyen.stationery_kimi.dto.*;
import com.lamnguyen.stationery_kimi.entity.Bill;
import com.lamnguyen.stationery_kimi.entity.BillDetail;
import com.lamnguyen.stationery_kimi.entity.BillStatus;
import com.lamnguyen.stationery_kimi.entity.User;
import com.lamnguyen.stationery_kimi.enums.BillStatusEnum;
import com.lamnguyen.stationery_kimi.repository.IBillDetailRepository;
import com.lamnguyen.stationery_kimi.repository.IBillRepository;
import com.lamnguyen.stationery_kimi.service.IBillService;
import com.lamnguyen.stationery_kimi.service.IBillStatusService;
import com.lamnguyen.stationery_kimi.service.IProductOptionService;
import com.lamnguyen.stationery_kimi.service.IShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Override
    public BillDTO createBill(HttpSession session, PaymentRestController.PaymentRequest request) {
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        Bill bill = Bill.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .paymentMethod(request.getPayment())
                .shippingAddress(request.getFullAddress())
                .shippingFee(10000)
                .user(User.builder()
                        .id(userDTO.getId())
                        .build())
                .shippingNote(request.getNote())
                .build();
        bill = iBillRepository.save(bill);
        List<BillDetail> billDetailList = iShoppingCartService.getBillDetails(session, bill);
        billDetailList = iBillDetailRepository.saveAllAndFlush(billDetailList);
        billDetailList.forEach(billDetail -> iProductOptionService.buy(billDetail.getProductOption().getId(), billDetail.getQuantity()));
        BillStatus billStatus = BillStatus.builder()
                .bill(bill)
                .status(BillStatusEnum.ORDERED.getStatus())
                .date(LocalDateTime.now())
                .build();
        iBillStatusService.save(billStatus);
        return convertToDTO(bill);
    }

    @Override
    public List<BillDisplay> getBillByStatusAndUserId(Long id, String status) {
        List<Bill> bills = iBillRepository.findByUser_Id(id);
        bills = filterBillByStatus(bills, status);
        return bills.stream().map(this::convertToBillDisplay).toList();
    }

    @Override
    public List<BillManager> fillAll(String status, DatatableApiRequest request) {
        List<Bill> bills = iBillRepository.findAll();
        bills = filterBillByStatus(bills, status);
        List<BillManager> billManagers = new ArrayList<>(bills.stream().map(this::convertToBillManager).toList());
        searchBill(billManagers, request);
        sortBill(billManagers, request);
        return billManagers;
    }

    @Override
    public BillStatusDTO cancelBill(Long id, Long billId) {
        List<Bill> bills = iBillRepository.findByUser_Id(id);
        Bill bill = bills.stream().filter(b -> b.getId().equals(billId)).findFirst().orElse(null);
        if (bill == null) return null;
        BillStatus billStatus = BillStatus.builder()
                .bill(bill)
                .status(BillStatusEnum.CANCELED.getStatus())
                .date(LocalDateTime.now())
                .build();
        return iBillStatusService.save(billStatus);
    }

    @Override
    public Map<Integer, Integer> getRevenueByYear(int year) {
        List<Bill> bills = iBillRepository.findAll();
        bills = bills.stream().filter(bill -> bill.getBillStatuses().stream().anyMatch(billStatus -> billStatus.getStatus().equals(BillStatusEnum.DELIVERED.getStatus()))).toList();
        bills = bills.stream().filter(bill -> {
            LocalDateTime date = bill.getBillStatuses().stream().filter(billStatus -> billStatus.getStatus().equals(BillStatusEnum.DELIVERED.getStatus())).findFirst().orElse(null).getDate();
            return date.getYear() == year;
        }).toList();

        Map<Integer, Integer> revenue = bills.stream().collect(Collectors.toMap(bill -> {
            LocalDateTime date = bill.getBillStatuses().stream().filter(billStatus -> billStatus.getStatus().equals(BillStatusEnum.DELIVERED.getStatus())).findFirst().orElse(null).getDate();
            return date.getMonthValue();
        }, bill -> {
            int total = bill.getBillDetails().stream().mapToInt(billDetail -> billDetail.getQuantity() * billDetail.getPrice()).sum();
            return total;
        }, Integer::sum, TreeMap::new));

        for (int i = 1; i <= 12; i++)
            revenue.putIfAbsent(i, 0);
        return revenue;
    }

    private BillDTO convertToDTO(Bill bill) {
        return modelMapper.map(bill, BillDTO.class);
    }

    private BillDisplay convertToBillDisplay(Bill bill) {
        BillDisplay billDisplay = modelMapper.map(bill, BillDisplay.class);
        billDisplay.setDate(dateTimeFormatter.format(getBillStatusOrder(bill).getDate()));
        billDisplay.setTotalPay(bill.getBillDetails().stream().mapToInt(billDetail -> billDetail.getQuantity() * billDetail.getPrice()).sum() + bill.getShippingFee() - bill.getBillDetails().stream().mapToInt(BillDetail::getDiscount).sum());
        billDisplay.setTotalDiscount(bill.getBillDetails().stream().mapToInt(BillDetail::getDiscount).sum());
        return billDisplay;
    }

    private BillManager convertToBillManager(Bill bill) {
        BillDisplay billDisplay = convertToBillDisplay(bill);
        BillManager billManager = modelMapper.map(billDisplay, BillManager.class);
        billManager.setUserId(bill.getUser().getId());
        billManager.setCancel(bill.getBillStatuses().stream().anyMatch(billStatus -> billStatus.getStatus().equals(BillStatusEnum.CANCELED.getStatus())));
        billManager.setSuccess(bill.getBillStatuses().stream().anyMatch(billStatus -> billStatus.getStatus().equals(BillStatusEnum.DELIVERED.getStatus())));
        return billManager;
    }

    private BillStatus getBillStatusOrder(Bill bill) {
        return bill.getBillStatuses().stream().filter(billStatus -> billStatus.getStatus().equals(BillStatusEnum.ORDERED.getStatus())).findFirst().orElse(null);
    }

    private List<Bill> filterBillByStatus(List<Bill> bills, String status) {
        if (!status.equalsIgnoreCase(BillStatusEnum.ALL.name().toLowerCase()))
            if (status.equalsIgnoreCase(BillStatusEnum.DELIVERED.name().toLowerCase()))
                return bills.stream().filter(bill -> bill.getBillStatuses().stream().anyMatch(billStatus -> billStatus.getStatus().equals(BillStatusEnum.DELIVERED.getStatus()))).toList();
            else if (status.equalsIgnoreCase(BillStatusEnum.CANCELED.name().toLowerCase()))
                return bills.stream().filter(bill -> bill.getBillStatuses().stream().anyMatch(billStatus -> billStatus.getStatus().equals(BillStatusEnum.CANCELED.getStatus()))).toList();
            else
                return bills.stream().filter(bill -> bill.getBillStatuses().stream().noneMatch(billStatus -> billStatus.getStatus().equals(BillStatusEnum.DELIVERED.getStatus()) || billStatus.getStatus().equals(BillStatusEnum.CANCELED.getStatus()))).toList();
        else return bills;
    }

    private void sortBill(List<BillManager> bills, DatatableApiRequest request) {
        if (bills.size() > 1) {
            request.getOrder().forEach(order -> {
                switch (order.getName()) {
                    case "id" -> {
                        switch (order.getDir()) {
                            case "asc" -> bills.sort(Comparator.comparingLong(BillManager::getId));
                            case "desc" -> bills.sort((c1, c2) -> Long.compare(c2.getId(), c1.getId()));
                        }
                    }
                    case "email" -> {
                        switch (order.getDir()) {
                            case "asc" -> bills.sort(Comparator.comparing(BillManager::getEmail));
                            case "desc" -> bills.sort((c1, c2) -> c2.getEmail().compareTo(c1.getEmail()));
                        }
                    }
                    case "name" -> {
                        switch (order.getDir()) {
                            case "asc" -> bills.sort(Comparator.comparing(BillManager::getName));
                            case "desc" -> bills.sort((c1, c2) -> c2.getName().compareTo(c1.getName()));
                        }
                    }
                    case "date" -> {
                        switch (order.getDir()) {
                            case "asc" -> bills.sort(Comparator.comparing(BillManager::getDate));
                            case "desc" -> bills.sort((c1, c2) -> c2.getDate().compareTo(c1.getDate()));
                        }
                    }
                    case "phone" -> {
                        switch (order.getDir()) {
                            case "asc" -> bills.sort(Comparator.comparing(BillManager::getPhone));
                            case "desc" -> bills.sort((c1, c2) -> c2.getPhone().compareTo(c1.getPhone()));
                        }
                    }
                    case "userId" -> {
                        switch (order.getDir()) {
                            case "asc" -> bills.sort(Comparator.comparing(BillManager::getUserId));
                            case "desc" -> bills.sort((c1, c2) -> c2.getUserId().compareTo(c1.getUserId()));
                        }
                    }
                    case "totalPay" -> {
                        switch (order.getDir()) {
                            case "asc" -> bills.sort(Comparator.comparing(BillManager::getTotalPay));
                            case "desc" -> bills.sort((c1, c2) -> c2.getTotalPay().compareTo(c1.getTotalPay()));
                        }
                    }
                    case "totalDiscount" -> {
                        switch (order.getDir()) {
                            case "asc" -> bills.sort(Comparator.comparing(BillManager::getTotalDiscount));
                            case "desc" ->
                                    bills.sort((c1, c2) -> c2.getTotalDiscount().compareTo(c1.getTotalDiscount()));
                        }
                    }
                }
            });
        }
    }

    private void searchBill(List<BillManager> bills, DatatableApiRequest request) {
        String searchValue = request.getSearch().getValue();
        if (searchValue != null && !searchValue.isBlank())
            bills.removeIf(bill -> !bill.getPhone().toLowerCase().contains(searchValue.toLowerCase())
                    && !bill.getId().toString().contains(searchValue.toLowerCase())
                    && !bill.getEmail().toLowerCase().contains(searchValue.toLowerCase())
                    && !bill.getUserId().toString().contains(searchValue.toLowerCase())
                    && !bill.getName().toLowerCase().contains(searchValue.toLowerCase())
                    && !bill.getShippingAddress().toLowerCase().contains(searchValue.toLowerCase())
                    && !bill.getPaymentMethod().toLowerCase().contains(searchValue.toLowerCase())
                    && !bill.getDate().contains(searchValue.toLowerCase())
            );
    }
}
