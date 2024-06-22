package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.dto.BillDetailDTO;
import com.lamnguyen.stationery_kimi.entity.BillDetail;
import com.lamnguyen.stationery_kimi.repository.IBillDetailRepository;
import com.lamnguyen.stationery_kimi.service.IBillDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillDetailServiceImpl implements IBillDetailService {
    @Autowired
    private IBillDetailRepository iBillDetailRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<BillDetailDTO> findById(Long billId) {
        return iBillDetailRepository.findAllByBill_Id(billId).stream().map(this::convertToDTO).toList();
    }

    private BillDetailDTO convertToDTO(BillDetail billDetail) {
        BillDetailDTO billDetailDTO = modelMapper.map(billDetail, BillDetailDTO.class);
        billDetailDTO.setName(billDetail.getProductOption().getName());
        return billDetailDTO;
    }
}
