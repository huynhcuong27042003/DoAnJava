package org.example.dajava.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PhuongThucThanhToan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer MaPhuongThuc;
    @NotBlank(message = "Tên là bắt buộc")
    @Column(unique = true)
    private String TenPhuongThuc;

    // Getters and Setters
}

