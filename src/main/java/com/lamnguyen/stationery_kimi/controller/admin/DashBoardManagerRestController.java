package com.lamnguyen.stationery_kimi.controller.admin;

import com.lamnguyen.stationery_kimi.dto.ApiResponse;
import com.lamnguyen.stationery_kimi.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin/api/dashboard")
public class DashBoardManagerRestController {
    @Autowired
    private IBillService iBillService;

    @GetMapping("/revenue/{year}")
    public ApiResponse<Map<Integer, Integer>> revenueByYear(@PathVariable("year") int year) {
        Map<Integer, Integer> revenue = iBillService.getRevenueByYear(year);
        return ApiResponse.<Map<Integer, Integer>>builder()
                .data(revenue)
                .message("Revenue by year")
                .build();
    }
}
