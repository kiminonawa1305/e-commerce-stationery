package com.lamnguyen.stationery_kimi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String avatar;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String role;

    @OneToMany(mappedBy = "user")
    private List<VoucherUser> vouchers;

    @OneToMany(mappedBy = "user")
    private List<Bill> bills;
}
