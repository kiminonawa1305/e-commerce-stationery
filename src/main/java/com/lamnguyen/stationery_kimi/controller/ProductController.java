package com.lamnguyen.stationery_kimi.controller;

import com.lamnguyen.stationery_kimi.dto.ProductDetailDTO;
import com.lamnguyen.stationery_kimi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/{id}")
    public String productDetail(Model model, @PathVariable("id") Long id) {
        ProductDetailDTO productDetailDTO = productService.findProductDetailById(id);
        model.addAttribute("productDetail", productDetailDTO);
        return "product_detail";
    }
}
