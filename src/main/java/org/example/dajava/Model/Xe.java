package org.example.dajava.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Xe {
    @Id
    private String BienSoXe;
    private Integer MaDiaDiem;
    private Integer MaHangXe;
    private Integer MaLoai;
    @NotBlank(message = "Tên xe là bắt buộc")
    private String TenXe;
    private String NamSanXuat;
    private BigDecimal GiaThue;
    private String NhienLieu;
    private String MaLuc_PhanKhoi;
    private String MoTa;
    private Boolean TrangThai;
    private Boolean Hide;
    private String Email;
    @NotEmpty(message = "Hình ảnh chính này không được bỏ trống")
    @Lob
    private String HinhAnh1;
    @Lob
    private String HinhAnh2;
    @Lob
    private String HinhAnh3;
    @Lob
    private String HinhAnh4;

    @ManyToOne
    private DiaDiem diaDiem;

    @ManyToOne
    private HangXe hangXe;

    @ManyToOne
    private LoaiXe loaiXe;

    @ManyToOne
    private TaiKhoan taiKhoan;

}