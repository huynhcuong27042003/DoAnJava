package org.example.dajava.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LoaiXe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer MaLoai;
    private String TenLoai;

    // Getters and Setters
}