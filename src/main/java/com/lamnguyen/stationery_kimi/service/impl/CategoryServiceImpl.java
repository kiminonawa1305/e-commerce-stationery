package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.dto.CategoryDTO;
import com.lamnguyen.stationery_kimi.entity.Category;
import com.lamnguyen.stationery_kimi.repository.ICategoryRepository;
import com.lamnguyen.stationery_kimi.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private ICategoryRepository iCategoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryDTO> findAll() {
        return iCategoryRepository.findAll().stream().map(this::convertToDTO).toList();
    }

    @Override
    public CategoryDTO findCategoryById(Long id) {
        return iCategoryRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    @Override
    public CategoryDTO addCategory(Category category) {
        return null;
    }

    @Override
    public CategoryDTO updateCategory(Category category) {
        return null;
    }

    @Override
    public CategoryDTO lockCategoryById(Category category) {
        return null;
    }

    private CategoryDTO convertToDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }
}
