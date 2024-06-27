package org.example.dajava.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer MaHoaDon;
    private LocalDateTime NgayLap;
    private BigDecimal TongDonGia;
    private BigDecimal TienCoc;
    private Integer MaPhuongThuc;
    private String Email;
    private String BienSoXe;
    private Integer MaYeuCau;

    @ManyToOne
    private PhuongThucThanhToan phuongThucThanhToan;

    @ManyToOne
    private TaiKhoan taiKhoan;

    @ManyToOne
    private YeuCauDatXe yeuCauDatXe;

    // Getters and Setters
}