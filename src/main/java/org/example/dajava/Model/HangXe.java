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
public class HangXe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer MaHangXe;
    @NotBlank(message = "Tên hãng xe là bắt buộc")
    @Column(unique = true)
    private String TenHang;

    // Getters and Setters
}
