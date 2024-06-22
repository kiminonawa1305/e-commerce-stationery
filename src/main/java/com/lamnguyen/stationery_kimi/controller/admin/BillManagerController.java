package com.lamnguyen.stationery_kimi.controller.admin;

import com.lamnguyen.stationery_kimi.dto.ApiResponse;
import com.lamnguyen.stationery_kimi.dto.DatatableApiRequest;
import com.lamnguyen.stationery_kimi.dto.DatatableApiResponse;
import com.lamnguyen.stationery_kimi.dto.UserDTO;
import com.lamnguyen.stationery_kimi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/bills")
public class BillManagerController {
    @Autowired
    private IUserService iUserService;

    @GetMapping("/get")
    public DatatableApiResponse<List<UserDTO>> get(@RequestParam(required = false) Map<String, Object> query) {
        DatatableApiRequest request = DatatableApiRequest.newInstance(query);
        List<UserDTO> userDTOs = iUserService.findAll(request);

        return DatatableApiResponse.<List<UserDTO>>builder()
                .data(userDTOs)
                .draw(request.getDraw())
                .recordsTotal(userDTOs.size())
                .recordsFiltered(userDTOs.size())
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

    @PostMapping("/lock/{id}")
    public ApiResponse<UserDTO> lockProduct(@PathVariable("id") Long id) {
        UserDTO result = iUserService.lock(id);
        return ApiResponse.<UserDTO>builder()
                .message("Thành công!")
                .data(result)
                .build();
    }
}
