package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.dto.*;
import com.lamnguyen.stationery_kimi.entity.Product;
import com.lamnguyen.stationery_kimi.exception.ApplicationException;
import com.lamnguyen.stationery_kimi.exception.ErrorCode;
import com.lamnguyen.stationery_kimi.repository.IProductRepository;
import com.lamnguyen.stationery_kimi.service.IDiscountService;
import com.lamnguyen.stationery_kimi.service.IProductImageService;
import com.lamnguyen.stationery_kimi.service.IProductOptionService;
import com.lamnguyen.stationery_kimi.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    IProductRepository productRepository;
    @Autowired
    private IProductOptionService productOptionService;
    @Autowired
    private IProductImageService imageService;
    @Autowired
    private IDiscountService discountService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.PRODUCT_NOT_FOUND));
        return convertToDTO(product);
    }

    @Override
    public ProductDTO addProduct(Product product) {
        Product productEntity = productRepository.saveAndFlush(product);
        return convertToDTO(productEntity);
    }

    @Override
    public ProductDTO updateProduct(Product product) {
        if (product.getId() == null) throw new ApplicationException(ErrorCode.NULL_ID_PRODUCT);
        Product productEntity = productRepository.saveAndFlush(product);
        return convertToDTO(productEntity);
    }

    @Override
    public ProductDTO lockProductById(Product product) {
        if (product.getId() == null) throw new ApplicationException(ErrorCode.NULL_ID_PRODUCT);
        productRepository.lockProductById(product.getId(), product.getLock());
        return convertToDTO(product);
    }

    @Override
    public List<ProductDisplayDTO> findAllByLockFalse(Integer limit, Integer page) {
        List<Product> result = productRepository.findAllByLockFalse(Pageable.ofSize(limit).withPage(page)).getContent();
        if (result.isEmpty()) throw new ApplicationException(ErrorCode.PRODUCT_NOT_FOUND);
        return convertToDisplayDTO(result);
    }

    @Override
    public List<ProductDisplayDTO> findByCategory(Long id, Integer limit, Integer page) {
        List<Product> products = productRepository.findAllByLockFalseAndCategory_Id(id, Pageable.ofSize(limit).withPage(page))
                .stream()
                .filter(product -> product.getProductOptions()
                        .stream().anyMatch(productOption -> productOption.getQuantity() > 0)
                ).toList();
        return convertToDisplayDTO(products);
    }

    @Override
    public ProductSeeMoreDTO seeMore(Long productId) {
        ProductDTO productDTO = findProductById(productId);
        List<ProductOptionDTO> options = productOptionService.findByProductId(productId);
        List<ProductImageDTO> productImageDTOS = imageService.findByProductId(productId);

        return ProductSeeMoreDTO.builder()
                .name(productDTO.getName())
                .id(productDTO.getId())
                .productImageDTOS(productImageDTOS)
                .price(productDTO.getPrice())
                .discountPercent(productDTO.getDiscountPercent())
                .productOptionDTOS(options)
                .build();
    }

    @Override
    public CartItemDisplay findCartItemById(Long id) {
        ProductDTO productDTO = findProductById(id);
        return CartItemDisplay.builder()
                .price(productDTO.getPrice())
                .name(productDTO.getName())
                .build();
    }

    @Override
    public ProductDetailDTO findProductDetailById(Long id) {
        ProductSeeMoreDTO seeMoreDTO = seeMore(id);
        Product product = productRepository.findById(id).orElse(Product.builder().build());
        ProductDetailDTO productDetailDTO = modelMapper.map(seeMoreDTO, ProductDetailDTO.class);
        productDetailDTO.setDescription(product.getDescription());
        return productDetailDTO;
    }

    @Override
    public List<String> findBrandsByCategoryId(Long categoryId) {
        return productRepository.findBrandsByCategory_Id(categoryId);
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        DiscountDTO discountDTO = discountService.getDiscount(product.getId());
        if (discountDTO == null) productDTO.setDiscountPercent(0.0);
        else productDTO.setDiscountPercent(discountDTO.getDiscountPercent());
        return productDTO;
    }

    private ProductDisplayDTO convertToDisplayDTO(Product product) {
        ProductDisplayDTO productDisplayDTO = modelMapper.map(product, ProductDisplayDTO.class);
        productDisplayDTO.setProductImageDTO(imageService.findFirstByProductId(product.getId()));
        DiscountDTO discountDTO = discountService.getDiscount(product.getId());
        if (discountDTO == null) productDisplayDTO.setDiscountPercent(0.0);
        else productDisplayDTO.setDiscountPercent(discountDTO.getDiscountPercent());
        return productDisplayDTO;
    }

    private List<ProductDisplayDTO> convertToDisplayDTO(List<Product> products) {
        return products.stream()
                .map(this::convertToDisplayDTO)
                .toList();
    }

    private List<ProductDTO> convertToDTOList(List<Product> products) {
        return products.stream()
                .map(this::convertToDTO)
                .toList();
    }


}
