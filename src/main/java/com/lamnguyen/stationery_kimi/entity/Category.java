package com.lamnguyen.stationery_kimi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categories")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "`lock`")
    @ColumnDefault(value = "false")
    private Boolean lock;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
