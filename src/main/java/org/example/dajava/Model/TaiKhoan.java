package org.example.dajava.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class TaiKhoan {
    @Id
    private String Email;
    private String TenNguoiDung;
    private String MatKhau;
    private String SDT;
    private String DiaChi;
    private LocalDateTime NgaySinh;
    private String GPLX;
    private String Avarta;
    private Integer IDChucVu;

    @ManyToOne
    private ChucVu chucVu;

    // Getters and Setters
}