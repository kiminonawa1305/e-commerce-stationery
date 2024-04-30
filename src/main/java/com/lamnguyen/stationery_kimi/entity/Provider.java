package com.lamnguyen.stationery_kimi.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "providers")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String phone;

    @OneToMany(mappedBy = "provider")
    private List<Product> products;
}
