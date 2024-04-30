package com.lamnguyen.stationery_kimi.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Long quantity;
    @Column
    private String type;
    @Column
    private String description;
    @Column
    private Integer price;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @OneToMany(mappedBy = "product")
    private List<ComboProductDetail> comboProducts;

    @OneToMany(mappedBy = "product")
    private List<BillDetail> billDetails;
}
