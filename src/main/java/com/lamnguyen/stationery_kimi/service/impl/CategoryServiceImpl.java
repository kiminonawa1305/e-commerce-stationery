package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.dto.CategoryDTO;
import com.lamnguyen.stationery_kimi.dto.DatatableApiRequest;
import com.lamnguyen.stationery_kimi.entity.Category;
import com.lamnguyen.stationery_kimi.repository.ICategoryRepository;
import com.lamnguyen.stationery_kimi.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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

    @Override
    public List<CategoryDTO> findAll(DatatableApiRequest request) {
        List<CategoryDTO> categories = new ArrayList<>(findAll());
        if (categories.size() > 1) {
            request.getOrder().forEach(order -> {
                switch (order.getName()) {
                    case "id" -> {
                        switch (order.getDir()) {
                            case "asc" -> categories.sort(Comparator.comparingLong(CategoryDTO::getId));
                            case "desc" -> categories.sort((c1, c2) -> Long.compare(c2.getId(), c1.getId()));
                        }
                    }

                    case "name" -> {
                        switch (order.getDir()) {
                            case "asc" -> categories.sort(Comparator.comparing(CategoryDTO::getName));
                            case "desc" -> categories.sort((c1, c2) -> c2.getName().compareTo(c1.getName()));
                        }
                    }
                }
            });
        }

        String searchValue = request.getSearch().getValue();
        if (searchValue != null && !searchValue.isBlank())
            categories.removeIf(category -> !category.getName().toLowerCase().contains(searchValue.toLowerCase()) && !category.getId().toString().contains(searchValue.toLowerCase()));

        return categories.stream().skip(request.getStart()).limit(request.getLength()).toList();
    }

    private CategoryDTO convertToDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }
}
