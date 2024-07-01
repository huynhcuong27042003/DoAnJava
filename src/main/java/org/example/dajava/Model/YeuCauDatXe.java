package org.example.dajava.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class YeuCauDatXe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maYeuCau;

    private Date ngayNhan;
    private Date ngayTra;
    private int soNgayThue;
    private boolean trangThaiChapNhan;
    private boolean hide;

    @ManyToOne
    @JoinColumn(name = "email", nullable = false)
    private TaiKhoan taiKhoan;

    @ManyToOne
    @JoinColumn(name = "bienSoXe", nullable = false)
    private Xe xe;

    @OneToMany(mappedBy = "yeuCauDatXe")
    private List<HoaDon> hoaDons;

    // Getters and Setters
}
