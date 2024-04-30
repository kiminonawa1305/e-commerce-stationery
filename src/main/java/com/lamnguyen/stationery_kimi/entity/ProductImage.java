package com.lamnguyen.stationery_kimi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_images")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String type;
    @Column
    private String url;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
