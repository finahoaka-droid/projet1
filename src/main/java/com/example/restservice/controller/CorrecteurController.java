package com.example.restservice.controller;

import com.example.restservice.entity.Correcteur;
import com.example.restservice.service.CorrecteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/correcteurs")
@CrossOrigin(origins = "*")
public class CorrecteurController {

    @Autowired
    private CorrecteurService correcteurService;

    @GetMapping
    public ResponseEntity<List<Correcteur>> getAllCorrecteurs() {
        List<Correcteur> correcteurs = correcteurService.findAll();
        return ResponseEntity.ok(correcteurs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Correcteur> getCorrecteurById(@PathVariable Long id) {
        Optional<Correcteur> correcteur = correcteurService.findById(id);
        return correcteur.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Correcteur> createCorrecteur(@RequestBody Correcteur correcteur) {
        try {
            Correcteur savedCorrecteur = correcteurService.save(correcteur);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCorrecteur);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Correcteur> updateCorrecteur(@PathVariable Long id, @RequestBody Correcteur correcteur) {
        try {
            Correcteur updatedCorrecteur = correcteurService.update(id, correcteur);
            return ResponseEntity.ok(updatedCorrecteur);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCorrecteur(@PathVariable Long id) {
        try {
            correcteurService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Correcteur>> searchCorrecteurs(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String prenom) {
        
        if (nom != null && prenom != null) {
            List<Correcteur> correcteurs = correcteurService.findByNomAndPrenomContaining(nom, prenom);
            return ResponseEntity.ok(correcteurs);
        } else if (nom != null) {
            List<Correcteur> correcteurs = correcteurService.findByNomContaining(nom);
            return ResponseEntity.ok(correcteurs);
        } else if (prenom != null) {
            List<Correcteur> correcteurs = correcteurService.findByPrenomContaining(prenom);
            return ResponseEntity.ok(correcteurs);
        } else {
            List<Correcteur> correcteurs = correcteurService.findAll();
            return ResponseEntity.ok(correcteurs);
        }
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkIfExists(
            @RequestParam String nom,
            @RequestParam String prenom) {
        boolean exists = correcteurService.existsByNomAndPrenom(nom, prenom);
        return ResponseEntity.ok(exists);
    }
}
