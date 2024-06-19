package com.lamnguyen.stationery_kimi.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "combo_product_details")
public class ComboProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "combo_product_id")
    private ComboProduct comboProduct;

    @OneToMany(mappedBy = "comboProductDetail")
    private List<BillDetail> billDetails;
}
