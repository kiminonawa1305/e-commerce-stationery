package com.lamnguyen.stationery_kimi.controller.admin;

import com.lamnguyen.stationery_kimi.dto.*;
import com.lamnguyen.stationery_kimi.service.IBillDetailService;
import com.lamnguyen.stationery_kimi.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/bills")
public class BillManagerController {
    @Autowired
    private IBillService iBillService;

    @Autowired
    private IBillDetailService iBillDetailService;

    @GetMapping("/get")
    public DatatableApiResponse<List<BillManager>> get(@RequestParam(required = false) Map<String, Object> query) {
        DatatableApiRequest request = DatatableApiRequest.newInstance(query);
        List<BillManager> bills = iBillService.getBillManager("all", request);

        return DatatableApiResponse.<List<BillManager>>builder()
                .data(bills)
                .draw(request.getDraw())
                .recordsTotal(bills.size())
                .recordsFiltered(bills.size())
                .build();
    }

    @GetMapping("/bill-detail/{billId}")
    public ApiResponse<List<BillDetailDTO>> getBillDetail(@PathVariable("billId") Long billId) {
        List<BillDetailDTO> billdetails = iBillDetailService.findById(billId);

        return ApiResponse.<List<BillDetailDTO>>builder()
                .data(billdetails)
                .message("Thành công!")
                .build();
    }


    /*@PutMapping("/update")
    public ApiResponse<ProductDTO> updateProduct(@ModelAttribute Product product) {
        ProductDTO productDTO = productService.updateProduct(product);
        return ApiResponse.<ProductDTO>builder()
                .message("Cập nhật sản phẩm thành công!")
                .data(productDTO)
                .build();
    }*/
}
