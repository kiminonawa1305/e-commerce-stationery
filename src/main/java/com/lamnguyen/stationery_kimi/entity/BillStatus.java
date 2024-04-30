package com.lamnguyen.stationery_kimi.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bill_status")
public class BillStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String status;
    @Column
    private String description;
    @Column
    private LocalDateTime data;
    @Column
    private String address;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;
}
