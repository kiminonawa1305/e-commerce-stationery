package com.lamnguyen.stationery_kimi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "bill_statuses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;
}
