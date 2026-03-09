package com.example.restservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "parametre")
public class Parametre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parametre")
    private Long idParametre;

    @NotNull
    @Column(name = "id_matiere", nullable = false)
    private Long idMatiere;

    @NotNull
    @Column(name = "difference", nullable = false, precision = 5, scale = 2)
    private BigDecimal difference;

    @NotNull
    @Column(name = "id_operateur", nullable = false)
    private Long idOperateur;

    @NotNull
    @Column(name = "id_resolution", nullable = false)
    private Long idResolution;

    public Parametre() {}

    public Parametre(Long idMatiere, BigDecimal difference, Long idOperateur, Long idResolution) {
        this.idMatiere = idMatiere;
        this.difference = difference;
        this.idOperateur = idOperateur;
        this.idResolution = idResolution;
    }

    public Long getIdParametre() {
        return idParametre;
    }

    public void setIdParametre(Long idParametre) {
        this.idParametre = idParametre;
    }

    public Long getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(Long idMatiere) {
        this.idMatiere = idMatiere;
    }

    public BigDecimal getDifference() {
        return difference;
    }

    public void setDifference(BigDecimal difference) {
        this.difference = difference;
    }

    public Long getIdOperateur() {
        return idOperateur;
    }

    public void setIdOperateur(Long idOperateur) {
        this.idOperateur = idOperateur;
    }

    public Long getIdResolution() {
        return idResolution;
    }

    public void setIdResolution(Long idResolution) {
        this.idResolution = idResolution;
    }
}