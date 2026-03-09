package com.example.restservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Operateur")
public class Operateur {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operateur")
    private Long idOperateur;
    
    @NotBlank
    @Column(name = "operateur", nullable = false, unique = true, length = 50)
    private String operateur;
    
    public Operateur() {}
    
    public Operateur(String operateur) {
        this.operateur = operateur;
    }
    
    public Long getIdOperateur() {
        return idOperateur;
    }
    
    public void setIdOperateur(Long idOperateur) {
        this.idOperateur = idOperateur;
    }
    
    public String getOperateur() {
        return operateur;
    }
    
    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }
}
