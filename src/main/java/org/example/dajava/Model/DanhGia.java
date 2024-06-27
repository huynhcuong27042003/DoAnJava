package org.example.dajava.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity

public class DanhGia {
    @Id
    private String BienSoXe;
    @Id
    private String Email;
    private Integer SoSao;
    private String NhanXet;

    @ManyToOne
    private Xe xe;

    @ManyToOne
    private TaiKhoan taiKhoan;

    // Getters and Setters
}
