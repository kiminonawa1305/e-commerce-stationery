package com.lamnguyen.stationery_kimi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "verify_email_status")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyEmailStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String code;
    @Column
    private LocalDateTime expiredAt;
    @Column
    private String type;

    @OneToOne(mappedBy = "verifyEmailStatus", fetch = FetchType.LAZY)
    private User user;
}
