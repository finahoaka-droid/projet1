package com.example.restservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Correcteur")
public class Correcteur {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_correcteur")
    private Long idCorrecteur;
    
    @NotBlank
    @Column(name = "nom", nullable = false, length = 100)
    private String nom;
    
    @NotBlank
    @Column(name = "prenom", nullable = false, length = 100)
    private String prenom;
    
    public Correcteur() {}
    
    public Correcteur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    
    public Long getIdCorrecteur() {
        return idCorrecteur;
    }
    
    public void setIdCorrecteur(Long idCorrecteur) {
        this.idCorrecteur = idCorrecteur;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
