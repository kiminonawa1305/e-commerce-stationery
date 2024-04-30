package com.lamnguyen.stationery_kimi.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String description;
    @Column
    private Double discount;
    @Column
    private Double total;
    @Column
    private String paymentMethod;
    @Column
    private String shippingAddress;
    @Column
    private String shippingDate;
    @Column
    private String shippingNote;
    @Column
    private String shippingFee;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "bill")
    private List<BillDetail> billDetails;

    @OneToMany(mappedBy = "bill")
    private List<BillStatus> billStatuses;

    @OneToMany(mappedBy = "bill")
    private List<BillVoucher> vouchers;
}