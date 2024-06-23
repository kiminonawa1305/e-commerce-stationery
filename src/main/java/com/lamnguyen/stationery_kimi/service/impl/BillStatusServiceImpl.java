package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.dto.BillStatusDTO;
import com.lamnguyen.stationery_kimi.dto.DatatableApiRequest;
import com.lamnguyen.stationery_kimi.entity.BillStatus;
import com.lamnguyen.stationery_kimi.enums.BillStatusEnum;
import com.lamnguyen.stationery_kimi.repository.IBillStatusRepository;
import com.lamnguyen.stationery_kimi.service.IBillStatusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class BillStatusServiceImpl implements IBillStatusService {
    @Autowired
    private IBillStatusRepository iBillStatusRepository;
    @Autowired
    private ModelMapper modelMapper;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Override
    public BillStatusDTO save(BillStatus billStatus) {
        return convertToDTO(iBillStatusRepository.save(billStatus));
    }

    @Override
    public List<BillStatusDTO> findById(Long billId, DatatableApiRequest request) {
        List<BillStatusDTO> billStatusDTOS = new ArrayList<>(iBillStatusRepository.findAllByBill_Id(billId).stream().map(this::convertToDTO).toList());
        searchBillStatus(billStatusDTOS, request);
        sortBillStatus(billStatusDTOS, request);
        return billStatusDTOS;
    }

    @Override
    public BillStatusDTO update(Long billStatusId, BillStatusDTO billStatusDTO) {
        BillStatus billStatus = iBillStatusRepository.findById(billStatusId).orElse(null);
        billStatus.setStatus(BillStatusEnum.valueOf(billStatusDTO.getStatus().toUpperCase()).getStatus());
        billStatus.setDate(billStatusDTO.getDate());
        billStatus.setDescription(billStatus.getDescription());
        return save(billStatus);
    }

    @Override
    public BillStatusDTO delete(Long billStatusId) {
        BillStatus billStatus = iBillStatusRepository.findById(billStatusId).orElse(null);
        iBillStatusRepository.delete(billStatus);
        return convertToDTO(billStatus);
    }

    private BillStatusDTO convertToDTO(BillStatus billStatus) {
        return modelMapper.map(billStatus, BillStatusDTO.class);
    }

    private void sortBillStatus(List<BillStatusDTO> billStatuses, DatatableApiRequest request) {
        if (billStatuses.size() < 2) return;
        request.getOrder().forEach(order -> {
            switch (order.getName()) {
                case "id" -> {
                    switch (order.getDir()) {
                        case "asc" -> billStatuses.sort(Comparator.comparingLong(BillStatusDTO::getId));
                        case "desc" -> billStatuses.sort((c1, c2) -> Long.compare(c2.getId(), c1.getId()));
                    }
                }
                case "status" -> {
                    switch (order.getDir()) {
                        case "asc" -> billStatuses.sort(Comparator.comparing(BillStatusDTO::getStatus));
                        case "desc" -> billStatuses.sort((c1, c2) -> c2.getStatus().compareTo(c1.getStatus()));
                    }
                }
                case "date" -> {
                    switch (order.getDir()) {
                        case "asc" -> billStatuses.sort(Comparator.comparing(BillStatusDTO::getDate));
                        case "desc" -> billStatuses.sort((c1, c2) -> c2.getDate().compareTo(c1.getDate()));
                    }
                }
            }
        });
    }

    private void searchBillStatus(List<BillStatusDTO> billStatuses, DatatableApiRequest request) {
        String searchValue = request.getSearch().getValue();
        if (searchValue != null && !searchValue.isBlank())
            billStatuses.removeIf(billStatus -> !billStatus.getStatus().toLowerCase().contains(searchValue.toLowerCase()) && !billStatus.getId().toString().contains(searchValue.toLowerCase()) && !dateTimeFormatter.format(billStatus.getDate()).contains(searchValue.toLowerCase()));
    }
}
