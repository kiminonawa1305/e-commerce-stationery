package com.lamnguyen.stationery_kimi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Table(name = "providers")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Địa chỉ không được để trống!")
    @NotNull(message = "Tên không được để trống!")
    private String name;

    @Column
    @NotBlank(message = "Địa chỉ không được để trống!")
    @NotNull(message = "Địa chỉ không được để trống!")
    private String address;

    @Column
    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b", message = "Số điện thoại không hợp lệ!")
    @NotNull(message = "Số điện thoại không được để trống!")
    private String phone;

    @Column
    @Email
    @NotNull(message = "Email không được để trống!")
    @NotBlank(message = "Email không được để trống!")
    private String email;

    @Column(name = "`lock`")
    @ColumnDefault("0")
    private Boolean lock;

    @OneToMany(mappedBy = "provider")
    private List<Product> products;
}
