package org.example.dajava.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChucVu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IDChucVu;
    @NotBlank(message = "TenChucVu là bắt buộc")
    @Column(unique = true)
    private String TenChucVu;
}