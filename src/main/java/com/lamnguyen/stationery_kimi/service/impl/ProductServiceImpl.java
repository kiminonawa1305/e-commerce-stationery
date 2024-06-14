package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.dto.ProductDTO;
import com.lamnguyen.stationery_kimi.dto.ProductSeeMoreDTO;
import com.lamnguyen.stationery_kimi.dto.ProductOptionDTO;
import com.lamnguyen.stationery_kimi.entity.Product;
import com.lamnguyen.stationery_kimi.exception.ApplicationException;
import com.lamnguyen.stationery_kimi.exception.ErrorCode;
import com.lamnguyen.stationery_kimi.repository.IProductRepository;
import com.lamnguyen.stationery_kimi.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    IProductRepository productRepository;
    @Autowired(required = true)
    private ModelMapper modelMapper;
    private List<ProductDTO> database;

    {
        database = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            ProductDTO productDTO = ProductDTO.builder()
                    .name("Product " + i)
                    .id(i)
                    .description("Description " + i)
                    .type("Type " + i)
                    .quantity(100L)
                    .urlImageProducers(List.of("/images/product/demo_product.webp"))
                    .price(100 + i)
                    .discountPercent(0.2)
                    .build();
            database.add(productDTO);
        }
    }

    @Override
    public ProductDTO findProductById(Long id) {
        return null;
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
    public List<ProductDTO> findAll(Integer page) {
        List<Product> result = productRepository.findAll(Pageable.ofSize(20).withPage(page)).getContent();
        if (result.isEmpty()) throw new ApplicationException(ErrorCode.PRODUCT_NOT_FOUND);
        return convertToDTOList(result);
    }

    @Override
    public List<ProductDTO> findAllByLockFalse(Integer page) {
        List<Product> result = productRepository.findAllByLockFalse(Pageable.ofSize(20).withPage(page)).getContent();
        if (result.isEmpty()) throw new ApplicationException(ErrorCode.PRODUCT_NOT_FOUND);
        return convertToDTOList(result);
    }

    @Override
    public List<ProductDTO> findByCategory(String category) {
//        return convertToDTOList(productRepository.findByCategory(category));
        return database;
    }

    @Override
    public ProductSeeMoreDTO seeMore(Integer id) {
        List<ProductOptionDTO> options = Stream.of(1, 2, 3).map(idProductOption ->
                ProductOptionDTO.builder()
                        .id(Long.valueOf(idProductOption))
                        .name("name " + idProductOption)
                        .quantity(100)
                        .build()
        ).toList();

        return ProductSeeMoreDTO.builder()
                .name("Combo 5 Ream giấy A4 80 gsm IK Natural-02 (500 tờ) - Hàng nhập khẩu Indonesia" + id)
                .id(id)
                .quantity(100L)
                .urlImageProducers(List.of("/images/product/demo_product.webp", "/images/product/demo_product.webp", "/images/product/demo_product.webp"))
                .price(425000)
                .discountPercent(0.15)
                .productOptionDTOS(options)
                .build();
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        product.getImage().forEach(productImage ->
                productDTO.getUrlImageProducers().add(productImage.getUrl())
        );
        return productDTO;
    }

    private List<ProductDTO> convertToDTOList(List<Product> products) {
        return products.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
