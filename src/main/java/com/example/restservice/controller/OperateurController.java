package com.example.restservice.controller;

import com.example.restservice.entity.Operateur;
import com.example.restservice.service.OperateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/operateurs")
@CrossOrigin(origins = "*")
public class OperateurController {

    @Autowired
    private OperateurService operateurService;

    @GetMapping
    public ResponseEntity<List<Operateur>> getAllOperateurs() {
        List<Operateur> operateurs = operateurService.findAll();
        return ResponseEntity.ok(operateurs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operateur> getOperateurById(@PathVariable Long id) {
        Optional<Operateur> operateur = operateurService.findById(id);
        return operateur.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Operateur> createOperateur(@RequestBody Operateur operateur) {
        try {
            Operateur savedOperateur = operateurService.save(operateur);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedOperateur);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Operateur> updateOperateur(@PathVariable Long id, @RequestBody Operateur operateur) {
        try {
            Operateur updatedOperateur = operateurService.update(id, operateur);
            return ResponseEntity.ok(updatedOperateur);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperateur(@PathVariable Long id) {
        try {
            operateurService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Operateur>> searchOperateurs(@RequestParam String operateur) {
        List<Operateur> operateurs = operateurService.findByOperateurContaining(operateur);
        return ResponseEntity.ok(operateurs);
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkIfExists(@RequestParam String operateur) {
        boolean exists = operateurService.existsByOperateur(operateur);
        return ResponseEntity.ok(exists);
    }
}
