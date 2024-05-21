package com.lamnguyen.stationery_kimi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.BitSet;
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
    @NotNull(message = "Tên không được để trống!")
    @NotBlank(message = "Tên không được để trống!")
    private String name;
    @Column
    @NotNull(message = "Số lượng không được để trống!")
    @NotBlank(message = "Số lượng không được để trống!")
    private Long quantity;
    @Column
    @NotNull(message = "Loại không được để trống!")
    @NotBlank(message = "Loại không được để trống!")
    private String type;
    @Column
    @NotNull(message = "Mô tả không được để trống!")
    @NotBlank(message = "Mô tả không được để trống!")
    private String description;
    @Column
    @NotNull(message = "Giá không được để trống!")
    @NotBlank(message = "Giá không được để trống!")
    private Integer price;

    @Column(name = "`lock`")
    @ColumnDefault("0")
    private Boolean lock;


    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "Danh mục không được để trống!")
    @NotBlank(message = "Danh mục không được để trống!")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    @NotNull(message = "Nhà cung cấp không được để trống!")
    @NotBlank(message = "Nhà cung cấp không được để trống!")
    private Provider provider;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> image;

    @OneToMany(mappedBy = "product")
    private List<ComboProductDetail> comboProducts;

    @OneToMany(mappedBy = "product")
    private List<BillDetail> billDetails;
}
