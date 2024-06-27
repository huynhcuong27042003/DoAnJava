package org.example.dajava.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class KhuyenMai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer MaiKhuyenMai;
    private String TenKhuyenMai;
    private String Code;
    private String NoiDungKhuyenMai;
    private Float PhanTramKhhuyenMai;
    private String HinhAnhKhuyenMai;
    private LocalDate NgayKhuyenMai;
    private LocalDate NgayKetThuc;

    // Getters and Setters
}
