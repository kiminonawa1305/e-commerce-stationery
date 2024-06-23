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
@RequestMapping("/admin/api/dashboard")
public class DashBoardManagerController {
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
}
