package com.example.restservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Resolution")
public class Resolution {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resolution")
    private Long idResolution;
    
    @NotBlank
    @Column(name = "nom", nullable = false, length = 100)
    private String nom;
    
    public Resolution() {}
    
    public Resolution(String nom) {
        this.nom = nom;
    }
    
    public Long getIdResolution() {
        return idResolution;
    }
    
    public void setIdResolution(Long idResolution) {
        this.idResolution = idResolution;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
}
