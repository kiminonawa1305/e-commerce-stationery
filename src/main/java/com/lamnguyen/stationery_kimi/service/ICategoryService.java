package com.lamnguyen.stationery_kimi.service;

import com.lamnguyen.stationery_kimi.dto.CategoryDTO;
import com.lamnguyen.stationery_kimi.dto.CategoryManager;
import com.lamnguyen.stationery_kimi.dto.DatatableApiRequest;
import com.lamnguyen.stationery_kimi.entity.Category;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> findAll();

    CategoryDTO findCategoryById(Long id);

    CategoryManager addCategory(Category category);

    CategoryManager updateCategory(Category category);


    List<CategoryManager> findAll(DatatableApiRequest request);

    CategoryManager lock(Long id);
}
