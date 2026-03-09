package com.example.restservice.controller;

import com.example.restservice.entity.Candidat;
import com.example.restservice.service.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/candidats")
@CrossOrigin(origins = "*")
public class CandidatController {

    @Autowired
    private CandidatService candidatService;

    @GetMapping
    public ResponseEntity<List<Candidat>> getAllCandidats() {
        List<Candidat> candidats = candidatService.findAll();
        return ResponseEntity.ok(candidats);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidat> getCandidatById(@PathVariable Long id) {
        Optional<Candidat> candidat = candidatService.findById(id);
        return candidat.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Candidat> createCandidat(@RequestBody Candidat candidat) {
        try {
            Candidat savedCandidat = candidatService.save(candidat);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCandidat);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidat> updateCandidat(@PathVariable Long id, @RequestBody Candidat candidat) {
        try {
            Candidat updatedCandidat = candidatService.update(id, candidat);
            return ResponseEntity.ok(updatedCandidat);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidat(@PathVariable Long id) {
        try {
            candidatService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Candidat>> searchCandidats(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String prenom) {
        
        if (nom != null && prenom != null) {
            List<Candidat> candidats = candidatService.findByNomAndPrenomContaining(nom, prenom);
            return ResponseEntity.ok(candidats);
        } else if (nom != null) {
            List<Candidat> candidats = candidatService.findByNomContaining(nom);
            return ResponseEntity.ok(candidats);
        } else if (prenom != null) {
            List<Candidat> candidats = candidatService.findByPrenomContaining(prenom);
            return ResponseEntity.ok(candidats);
        } else {
            List<Candidat> candidats = candidatService.findAll();
            return ResponseEntity.ok(candidats);
        }
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkIfExists(
            @RequestParam String nom,
            @RequestParam String prenom) {
        boolean exists = candidatService.existsByNomAndPrenom(nom, prenom);
        return ResponseEntity.ok(exists);
    }
}
