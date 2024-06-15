package com.lamnguyen.stationery_kimi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Integer price;

    @Column(name = "`new`")
    private Boolean productNew;

    @Column(name = "`lock`")
    @ColumnDefault("0")
    private Boolean lock;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductOption> productOptions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductImage> image;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ComboProductDetail> comboProducts;

    @OneToMany(mappedBy = "product")
    private List<BillDetail> billDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<TimeGoldPrice> timeGoldPrices;
}
