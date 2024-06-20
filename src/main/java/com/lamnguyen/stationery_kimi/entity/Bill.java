package com.lamnguyen.stationery_kimi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "bills")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String email;
    @Column
    private String paymentMethod;
    @Column
    private String shippingAddress;
    @Column
    private String shippingNote;
    @Column
    private Integer shippingFee;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "bill")
    private List<BillDetail> billDetails;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private List<BillStatus> billStatuses;

    @OneToMany(mappedBy = "bill")
    private List<BillVoucher> vouchers;
}