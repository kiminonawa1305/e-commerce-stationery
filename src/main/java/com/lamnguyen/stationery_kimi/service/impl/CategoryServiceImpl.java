package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.dto.CategoryDTO;
import com.lamnguyen.stationery_kimi.dto.CategoryManager;
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
    public CategoryManager addCategory(Category category) {
        return convertToManager(iCategoryRepository.save(category));
    }

    @Override
    public CategoryManager updateCategory(Category category) {
        return convertToManager(iCategoryRepository.save(category));
    }

    @Override
    public List<CategoryManager> findAll(DatatableApiRequest request) {
        List<CategoryManager> categories = new ArrayList<>(iCategoryRepository.findAll().stream().map(this::convertToManager).toList());

        searchCategoryManager(categories, request);
        sortCategoryManager(categories, request);

        return categories.stream().skip(request.getStart()).limit(request.getLength()).toList();
    }

    @Override
    public CategoryManager lock(Long id) {
        Category category = iCategoryRepository.findById(id).orElse(null);
        if (category != null) {
            category.setLock(!category.getLock());
            return convertToManager(iCategoryRepository.save(category));
        }
        return null;
    }

    private CategoryDTO convertToDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    private CategoryManager convertToManager(Category category) {
        CategoryManager categoryManager = modelMapper.map(category, CategoryManager.class);
        categoryManager.setTotalProduct(category.getProducts() == null ? 0 : category.getProducts().size());
        return categoryManager;
    }

    private void searchCategoryManager(List<CategoryManager> categories, DatatableApiRequest request) {
        String searchValue = request.getSearch().getValue();
        if (searchValue != null && !searchValue.isBlank())
            categories.removeIf(category -> !category.getName().toLowerCase().contains(searchValue.toLowerCase()) && !category.getId().toString().contains(searchValue.toLowerCase()));
    }

    private void sortCategoryManager(List<CategoryManager> categories, DatatableApiRequest request) {
        if (categories.size() > 1) {
            request.getOrder().forEach(order -> {
                switch (order.getName()) {
                    case "id" -> {
                        switch (order.getDir()) {
                            case "asc" -> categories.sort(Comparator.comparingLong(CategoryManager::getId));
                            case "desc" -> categories.sort((c1, c2) -> Long.compare(c2.getId(), c1.getId()));
                        }
                    }
                    case "name" -> {
                        switch (order.getDir()) {
                            case "asc" -> categories.sort(Comparator.comparing(CategoryManager::getName));
                            case "desc" -> categories.sort((c1, c2) -> c2.getName().compareTo(c1.getName()));
                        }
                    }
                    case "totalProduct" -> {
                        switch (order.getDir()) {
                            case "asc" -> categories.sort(Comparator.comparing(CategoryManager::getTotalProduct));
                            case "desc" ->
                                    categories.sort((c1, c2) -> c2.getTotalProduct().compareTo(c1.getTotalProduct()));
                        }
                    }
                }
            });
        }
    }
}
