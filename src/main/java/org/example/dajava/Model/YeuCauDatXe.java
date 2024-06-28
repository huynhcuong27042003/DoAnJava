package org.example.dajava.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class YeuCauDatXe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer MaYeuCau;
    private String Email;
    private String BienSoXe;
    private LocalDateTime NgayNhan;
    private LocalDateTime NgayTra;
    private Integer SoNgayThue;
    private Boolean TrangThaiChapNhan;
    private Boolean Hide;

    @ManyToOne
    private Xe xe;

    @ManyToOne
    private TaiKhoan taiKhoan;

    // Getters and Setters
}
