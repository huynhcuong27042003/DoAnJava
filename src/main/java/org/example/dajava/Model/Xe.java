package org.example.dajava.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
@Entity
public class Xe {
    @Id
    private String BienSoXe;
    private Integer MaDiaDiem;
    private Integer MaHangXe;
    private Integer MaLoai;
    private String TenXe;
    private String NamSanXuat;
    private BigDecimal GiaThue;
    private String NhienLieu;
    private String MaLuc_PhanKhoi;
    private String MoTa;
    private Boolean TrangThai;
    private Boolean Hide;
    private String Email;
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

    // Getters and Setters
}