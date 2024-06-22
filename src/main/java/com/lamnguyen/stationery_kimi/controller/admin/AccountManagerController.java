package com.lamnguyen.stationery_kimi.controller.admin;

import com.lamnguyen.stationery_kimi.dto.DatatableApiRequest;
import com.lamnguyen.stationery_kimi.dto.DatatableApiResponse;
import com.lamnguyen.stationery_kimi.dto.UserDTO;
import com.lamnguyen.stationery_kimi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/account")
public class AccountManagerController {
    @Autowired
    private IUserService iUserService;

    @GetMapping("/get")
    public DatatableApiResponse<List<UserDTO>> get(@RequestParam(required = false) Map<String, Object> query) {
        DatatableApiRequest request = DatatableApiRequest.newInstance(query);
        List<UserDTO> categories = iUserService.findAll(request);

        return DatatableApiResponse.<List<UserDTO>>builder()
                .data(categories)
                .draw(request.getDraw())
                .recordsTotal(categories.size())
                .recordsFiltered(categories.size())
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

    /*@PutMapping("/lock")
    public ApiResponse<ProductDTO> lockProduct(@ModelAttribute Product product) {
        ProductDTO result = productService.lockProductById(product);
        return ApiResponse.<ProductDTO>builder()
                .message("Khóa sản phẩm thành công!")
                .data(result)
                .build();
    }*/
}
