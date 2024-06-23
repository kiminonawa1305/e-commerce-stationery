package com.lamnguyen.stationery_kimi.controller.admin;

import com.lamnguyen.stationery_kimi.dto.BillStatusDTO;
import com.lamnguyen.stationery_kimi.dto.DatatableApiRequest;
import com.lamnguyen.stationery_kimi.dto.DatatableApiResponse;
import com.lamnguyen.stationery_kimi.dto.EditDataTableResponse;
import com.lamnguyen.stationery_kimi.entity.Bill;
import com.lamnguyen.stationery_kimi.entity.BillStatus;
import com.lamnguyen.stationery_kimi.enums.BillStatusEnum;
import com.lamnguyen.stationery_kimi.service.IBillStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/bill-statuses")
public class BillStatusManagerController {
    @Autowired
    private IBillStatusService iBillStatusService;

    @GetMapping("/get/{billId}")
    public DatatableApiResponse<List<BillStatusDTO>> get(@PathVariable("billId") Long billId, @RequestParam(required = false) Map<String, Object> query) {
        DatatableApiRequest request = DatatableApiRequest.newInstance(query);
        List<BillStatusDTO> billStatusDTOs = iBillStatusService.findById(billId, request);
        return DatatableApiResponse.<List<BillStatusDTO>>builder()
                .data(billStatusDTOs)
                .draw(request.getDraw())
                .recordsTotal(billStatusDTOs.size())
                .recordsFiltered(billStatusDTOs.size())
                .build();
    }

    @PostMapping("/create/{billId}")
    public EditDataTableResponse<BillStatusDTO> create(@PathVariable("billId") Long billId, @RequestBody BillStatus billStatus) {
        String status = BillStatusEnum.valueOf(billStatus.getStatus().toUpperCase()).getStatus();
        billStatus.setStatus(status);
        billStatus.setBill(Bill.builder().id(billId).build());
        return EditDataTableResponse.<BillStatusDTO>builder()
                .data(iBillStatusService.save(billStatus))
                .build();
    }

    @PutMapping("/edit/{billStatusId}")
    public EditDataTableResponse<BillStatusDTO> update(@PathVariable("billStatusId") Long billStatusId, @RequestBody BillStatusDTO billStatusDTO) {
        return EditDataTableResponse.<BillStatusDTO>builder()
                .data(iBillStatusService.update(billStatusId, billStatusDTO))
                .build();
    }

    @DeleteMapping("/delete/{billStatusId}")
    public EditDataTableResponse<BillStatusDTO> delete(@PathVariable("billStatusId") Long billStatusId) {
        return EditDataTableResponse.<BillStatusDTO>builder()
                .data(iBillStatusService.delete(billStatusId))
                .build();
    }
}
