package com.example.restservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Matiere")
public class Matiere {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matiere")
    private Long idMatiere;
    
    @NotBlank
    @Column(name = "nom", nullable = false, unique = true, length = 100)
    private String nom;
    
    public Matiere() {}
    
    public Matiere(String nom) {
        this.nom = nom;
    }
    
    public Long getIdMatiere() {
        return idMatiere;
    }
    
    public void setIdMatiere(Long idMatiere) {
        this.idMatiere = idMatiere;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
}
