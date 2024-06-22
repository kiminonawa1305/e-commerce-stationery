package com.lamnguyen.stationery_kimi.service;

import com.lamnguyen.stationery_kimi.dto.CategoryDTO;
import com.lamnguyen.stationery_kimi.dto.DatatableApiRequest;
import com.lamnguyen.stationery_kimi.entity.Category;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> findAll();

    CategoryDTO findCategoryById(Long id);

    CategoryDTO addCategory(Category category);

    CategoryDTO updateCategory(Category category);

    CategoryDTO lockCategoryById(Category category);

    List<CategoryDTO> findAll(DatatableApiRequest request);
}
