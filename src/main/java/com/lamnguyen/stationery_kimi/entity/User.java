package com.lamnguyen.stationery_kimi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String avatar;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String phone;
    @Column
    @Email(message = "Email không hợp lệ!")
    private String email;

    @Column
    private String password;

    @Column(columnDefinition = "enum")
    @ColumnDefault("'user'")
    private String role;

    @Column(name = "`lock`")
    @ColumnDefault(value = "false")
    private Boolean lock;

    @OneToMany(mappedBy = "user")
    private List<Bill> bills;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "verify_id", referencedColumnName = "id")
    private VerifyEmailStatus verifyEmailStatus;
}
