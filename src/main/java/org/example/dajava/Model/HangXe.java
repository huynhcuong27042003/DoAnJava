package org.example.dajava.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HangXe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer MaHangXe;
    private String TenHang;

    // Getters and Setters
}
