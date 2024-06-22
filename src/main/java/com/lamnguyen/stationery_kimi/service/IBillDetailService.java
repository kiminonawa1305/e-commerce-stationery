package com.lamnguyen.stationery_kimi.service;

import com.lamnguyen.stationery_kimi.dto.BillDetailDTO;

import java.util.List;

public interface IBillDetailService {
    List<BillDetailDTO> findById(Long billId);
}
