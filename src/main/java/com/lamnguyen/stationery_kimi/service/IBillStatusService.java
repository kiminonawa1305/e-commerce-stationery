package com.lamnguyen.stationery_kimi.service;

import com.lamnguyen.stationery_kimi.dto.BillStatusDTO;
import com.lamnguyen.stationery_kimi.dto.DatatableApiRequest;
import com.lamnguyen.stationery_kimi.entity.BillStatus;

import java.util.List;

public interface IBillStatusService {
    BillStatusDTO save(BillStatus billStatus);

    List<BillStatusDTO> findAllByBillId(Long billId, DatatableApiRequest request);

    BillStatusDTO update(Long billStatusId, BillStatusDTO billStatusDTO);

    BillStatusDTO delete(Long billStatusId);
}
