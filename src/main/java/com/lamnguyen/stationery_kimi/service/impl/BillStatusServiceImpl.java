package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.dto.BillStatusDTO;
import com.lamnguyen.stationery_kimi.entity.BillStatus;
import com.lamnguyen.stationery_kimi.repository.IBillRepository;
import com.lamnguyen.stationery_kimi.repository.IBillStatusRepository;
import com.lamnguyen.stationery_kimi.service.IBillStatusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillStatusServiceImpl implements IBillStatusService {
    @Autowired
    private IBillStatusRepository iBillStatusRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BillStatusDTO save(BillStatus billStatus) {
        return convertToDTO(iBillStatusRepository.save(billStatus));
    }

    private BillStatusDTO convertToDTO(BillStatus billStatus) {
        return modelMapper.map(billStatus, BillStatusDTO.class);
    }
}
