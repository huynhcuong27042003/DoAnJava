package org.example.dajava.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DiaDiem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer MaDiaDiem;
    private String TenDiaDiem;

    // Getters and Setters
}
