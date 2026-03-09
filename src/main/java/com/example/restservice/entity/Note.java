package com.example.restservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_note")
    private Long idNote;

    @NotNull
    @Column(name = "id_candidat", nullable = false)
    private Long idCandidat;

    @NotNull
    @Column(name = "id_matiere", nullable = false)
    private Long idMatiere;

    @NotNull
    @Column(name = "id_correcteur", nullable = false)
    private Long idCorrecteur;

    @NotNull
    @DecimalMin(value = "0.0", message = "La note doit être >= 0")
    @Digits(integer = 3, fraction = 2)
    @Column(name = "note", nullable = false, precision = 5, scale = 2)
    private BigDecimal note;

    public Note() {}

    public Note(Long idCandidat, Long idMatiere, Long idCorrecteur, BigDecimal note) {
        this.idCandidat = idCandidat;
        this.idMatiere = idMatiere;
        this.idCorrecteur = idCorrecteur;
        this.note = note;
    }

    public Long getIdNote() {
        return idNote;
    }

    public void setIdNote(Long idNote) {
        this.idNote = idNote;
    }

    public Long getIdCandidat() {
        return idCandidat;
    }

    public void setIdCandidat(Long idCandidat) {
        this.idCandidat = idCandidat;
    }

    public Long getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(Long idMatiere) {
        this.idMatiere = idMatiere;
    }

    public Long getIdCorrecteur() {
        return idCorrecteur;
    }

    public void setIdCorrecteur(Long idCorrecteur) {
        this.idCorrecteur = idCorrecteur;
    }

    public BigDecimal getNote() {
        return note;
    }

    public void setNote(BigDecimal note) {
        this.note = note;
    }
}