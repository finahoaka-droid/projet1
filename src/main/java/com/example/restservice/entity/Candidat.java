package com.example.restservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Candidat")
public class Candidat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_candidat")
    private Long idCandidat;
    
    @NotBlank
    @Column(name = "nom", nullable = false, length = 100)
    private String nom;
    
    @NotBlank
    @Column(name = "prenom", nullable = false, length = 100)
    private String prenom;
    
    public Candidat() {}
    
    public Candidat(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    
    public Long getIdCandidat() {
        return idCandidat;
    }
    
    public void setIdCandidat(Long idCandidat) {
        this.idCandidat = idCandidat;
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
