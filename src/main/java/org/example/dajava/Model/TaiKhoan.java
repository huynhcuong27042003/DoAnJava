package org.example.dajava.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaiKhoan {
    @Id
    @Email(message = "Email phải đúng định dạng")
    private String email;
    private String TenNguoiDung;
    @Column(name = "password", length = 250)
    @NotBlank(message = "Password là bắt buộc")
    private String MatKhau;
    @Pattern(regexp = "^0\\d{10}$", message = "SDT phải bắt đầu bằng 0 và đủ 11 ký tự")
    private String SDT;
    private String DiaChi;
    private Date NgaySinh;
    @Pattern(regexp = "^\\d{12}$", message = "GPLX phải đủ 12 ký tự và luôn là chữ số")
    private String GPLX;
    private String Avarta;

    @ManyToOne
    @JoinColumn(name = "idChucVu", nullable = false)
    private ChucVu chucVu;

    @OneToMany(mappedBy = "taiKhoan")
    private List<Xe> xes;

    @OneToMany(mappedBy = "taiKhoan")
    private List<YeuCauDatXe> yeuCauDatXes;
    @PrePersist
    @PreUpdate
    private void validateAge() {
        if (NgaySinh != null) {
            LocalDate birthDate = new java.sql.Date(NgaySinh.getTime()).toLocalDate();
            LocalDate today = LocalDate.now();
            Period age = Period.between(birthDate, today);
            if (age.getYears() < 18) {
                throw new IllegalArgumentException("Người dùng phải đủ 18 tuổi");
            }
        }
    }
    // Getters and Setters
}