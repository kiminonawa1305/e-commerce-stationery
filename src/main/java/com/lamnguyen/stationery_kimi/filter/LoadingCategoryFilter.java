package com.lamnguyen.stationery_kimi.filter;


import com.lamnguyen.stationery_kimi.dto.CategoryDTO;
import com.lamnguyen.stationery_kimi.service.ICategoryService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Order(1)
public class LoadingCategoryFilter implements Filter {
    @Autowired
    private ICategoryService categoryService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        List<CategoryDTO> categories = categoryService.findAll();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.getSession().setAttribute("categories", categories);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
