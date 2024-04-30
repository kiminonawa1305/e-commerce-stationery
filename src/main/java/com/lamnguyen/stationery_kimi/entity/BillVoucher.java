package com.lamnguyen.stationery_kimi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bill_vouchers")
public class BillVoucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;
    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;
}
