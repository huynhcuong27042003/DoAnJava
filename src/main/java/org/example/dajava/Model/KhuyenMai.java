package org.example.dajava.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KhuyenMai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer MaKhuyenMai;

    @NotBlank(message = "Tên khuyến mãi là bắt buộc")
    private String TenKhuyenMai;

    @Pattern(regexp = "^[A-Z]{6}$", message = "Code phải đủ 6 ký tự và ghi hoa hết")
    private String Code;

    @NotBlank(message = "Nội dung khuyến mãi là bắt buộc")
    private String NoiDungKhuyenMai;

    @Min(value = 1, message = "Phần trăm khuyến mãi phải lớn hơn hoặc bằng 1")
    @Max(value = 100, message = "Phần trăm khuyến mãi phải nhỏ hơn hoặc bằng 100")
    private Float PhanTramKhuyenMai;

    private String HinhAnhKhuyenMai;

    @NotNull(message = "Ngày khuyến mãi là bắt buộc")
    private Date NgayKhuyenMai;

    @NotNull(message = "Ngày kết thúc là bắt buộc")
    @Future(message = "Ngày kết thúc phải lớn hơn ngày hiện tại")
    private Date NgayKetThuc;

    @PrePersist
    @PreUpdate
    private void validateDates() {
        if (NgayKhuyenMai != null && NgayKetThuc != null && !NgayKetThuc.after(NgayKhuyenMai)) {
            throw new IllegalArgumentException("Ngày kết thúc phải lớn hơn ngày khuyến mãi");
        }
    }
}
