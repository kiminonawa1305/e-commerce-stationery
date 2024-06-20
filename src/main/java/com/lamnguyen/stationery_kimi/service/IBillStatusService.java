package com.lamnguyen.stationery_kimi.service;

import com.lamnguyen.stationery_kimi.dto.BillStatusDTO;
import com.lamnguyen.stationery_kimi.entity.BillStatus;

public interface IBillStatusService {
    BillStatusDTO save(BillStatus billStatus);
}
