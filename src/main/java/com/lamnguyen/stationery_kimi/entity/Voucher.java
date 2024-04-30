package com.lamnguyen.stationery_kimi.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Table(name = "vouchers")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String code;
    @Column
    private String name;
    @Column
    private String type;
    @Column
    private String description;
    @Column
    @ColumnDefault("0")
    private Double discount;

    @OneToMany(mappedBy = "voucher")
    private List<VoucherUser> users;
}
