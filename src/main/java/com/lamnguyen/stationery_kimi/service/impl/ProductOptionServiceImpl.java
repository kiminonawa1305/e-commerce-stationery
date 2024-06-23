package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.dto.DatatableApiRequest;
import com.lamnguyen.stationery_kimi.dto.ProductOptionDTO;
import com.lamnguyen.stationery_kimi.entity.Product;
import com.lamnguyen.stationery_kimi.entity.ProductOption;
import com.lamnguyen.stationery_kimi.exception.ApplicationException;
import com.lamnguyen.stationery_kimi.exception.ErrorCode;
import com.lamnguyen.stationery_kimi.repository.IProductOptionRepository;
import com.lamnguyen.stationery_kimi.service.IProductOptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductOptionServiceImpl implements IProductOptionService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IProductOptionRepository productOptionRepository;

    @Override
    public List<ProductOptionDTO> findByProductId(Long productId) {
        List<ProductOption> productOption = productOptionRepository.findByQuantityGreaterThanEqualAndProduct_Id(1, productId);
        return convertToDTO(productOption);
    }

    @Override
    public List<ProductOptionDTO> findAllByProductId(Long productId, DatatableApiRequest request) {
        List<ProductOption> productOption = productOptionRepository.findAllByProduct_Id(productId);
        List<ProductOptionDTO> productOptionDTOs = new ArrayList<>(convertToDTO(productOption));
        String valueSearch = request.getSearch().getValue();
        if (!valueSearch.isEmpty())
            productOptionDTOs.removeIf(productOptionDTO -> !productOptionDTO.getName().toLowerCase().contains(valueSearch.toLowerCase()) && !productOptionDTO.getQuantity().toString().contains(valueSearch) && !productOptionDTO.getId().toString().contains(valueSearch));

        if (productOptionDTOs.size() > 1) request.getOrder().forEach(order -> {
            String name = order.getName();
            switch (name) {
                case "id" -> {
                    switch (order.getDir()) {
                        case "asc" -> productOptionDTOs.sort(Comparator.comparing(ProductOptionDTO::getId));
                        case "desc" -> productOptionDTOs.sort((o1, o2) -> o2.getId().compareTo(o1.getId()));
                    }
                }
                case "name" -> {
                    switch (order.getDir()) {
                        case "asc" -> productOptionDTOs.sort(Comparator.comparing(ProductOptionDTO::getName));
                        case "desc" -> productOptionDTOs.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
                    }
                }
                case "quantity" -> {
                    switch (order.getDir()) {
                        case "asc" -> productOptionDTOs.sort(Comparator.comparing(ProductOptionDTO::getQuantity));
                        case "desc" -> productOptionDTOs.sort((o1, o2) -> o2.getQuantity().compareTo(o1.getQuantity()));
                    }
                }
            }
        });
        return productOptionDTOs;
    }

    @Override
    public ProductOptionDTO findById(Long id) {
        ProductOption productOption = productOptionRepository.findById(id).orElse(ProductOption.builder().id(-1L).name("Mặc định").quantity(0).build());
        return convertToDTO(productOption);
    }

    @Override
    public ProductOptionDTO buy(Long id, Integer quantity) {
        ProductOption productOption = productOptionRepository.findById(id).orElse(null);
        if (productOption == null) throw new ApplicationException(ErrorCode.PRODUCT_OPTION_NOT_FOUND);
        productOption.setQuantity(productOption.getQuantity() - quantity);
        return convertToDTO(productOptionRepository.save(productOption));
    }

    @Override
    public ProductOptionDTO delete(Long id) {
        ProductOption option = productOptionRepository.findById(id).orElse(null);
        if (option == null) throw new ApplicationException(ErrorCode.PRODUCT_OPTION_NOT_FOUND);
        productOptionRepository.delete(option);
        return convertToDTO(option);
    }

    @Override
    public ProductOptionDTO update(Long id, ProductOption productOption) {
        ProductOption option = productOptionRepository.findById(id).orElse(null);
        if (option == null) throw new ApplicationException(ErrorCode.PRODUCT_OPTION_NOT_FOUND);
        option.setName(productOption.getName());
        option.setQuantity(productOption.getQuantity());
        return convertToDTO(productOptionRepository.save(option));
    }

    @Override
    public ProductOptionDTO add(Long productId, ProductOption productOption) {
        productOption.setProduct(Product.builder().id(productId).build());
        return convertToDTO(productOptionRepository.save(productOption));
    }

    private ProductOptionDTO convertToDTO(ProductOption productOption) {
        return modelMapper.map(productOption, ProductOptionDTO.class);
    }

    private List<ProductOptionDTO> convertToDTO(List<ProductOption> productOptions) {
        return productOptions.stream().map(this::convertToDTO).toList();
    }
}
