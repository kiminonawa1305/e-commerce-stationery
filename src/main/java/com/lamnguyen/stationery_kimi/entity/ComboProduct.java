package com.lamnguyen.stationery_kimi.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "combo_products")
public class ComboProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Integer price;
    @Column
    private String description;
    @Column
    private String image;
    @Column
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "comboProduct")
    private List<ComboProductDetail> comboProduct;


    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;
}
