package com.example.restservice.controller;

import com.example.restservice.entity.Matiere;
import com.example.restservice.service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matieres")
@CrossOrigin(origins = "*")
public class MatiereController {

    @Autowired
    private MatiereService matiereService;

    @GetMapping
    public ResponseEntity<List<Matiere>> getAllMatieres() {
        List<Matiere> matieres = matiereService.findAll();
        return ResponseEntity.ok(matieres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matiere> getMatiereById(@PathVariable Long id) {
        Optional<Matiere> matiere = matiereService.findById(id);
        return matiere.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Matiere> createMatiere(@RequestBody Matiere matiere) {
        try {
            Matiere savedMatiere = matiereService.save(matiere);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMatiere);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matiere> updateMatiere(@PathVariable Long id, @RequestBody Matiere matiere) {
        try {
            Matiere updatedMatiere = matiereService.update(id, matiere);
            return ResponseEntity.ok(updatedMatiere);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatiere(@PathVariable Long id) {
        try {
            matiereService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Matiere>> searchMatieres(@RequestParam String nom) {
        List<Matiere> matieres = matiereService.findByNomContaining(nom);
        return ResponseEntity.ok(matieres);
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkIfExists(@RequestParam String nom) {
        boolean exists = matiereService.existsByNom(nom);
        return ResponseEntity.ok(exists);
    }
}
