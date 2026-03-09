package com.example.restservice.controller;

import com.example.restservice.entity.Parametre;
import com.example.restservice.service.ParametreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parametres")
@CrossOrigin(origins = "*")
public class ParametreController {

    @Autowired
    private ParametreService parametreService;

    @GetMapping
    public ResponseEntity<List<Parametre>> getAllParametres() {
        List<Parametre> parametres = parametreService.findAll();
        return ResponseEntity.ok(parametres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parametre> getParametreById(@PathVariable Long id) {
        Optional<Parametre> parametre = parametreService.findById(id);
        return parametre.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Parametre> createParametre(@RequestBody Parametre parametre) {
        try {
            Parametre savedParametre = parametreService.save(parametre);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedParametre);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parametre> updateParametre(@PathVariable Long id, @RequestBody Parametre parametre) {
        try {
            Parametre updatedParametre = parametreService.update(id, parametre);
            return ResponseEntity.ok(updatedParametre);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParametre(@PathVariable Long id) {
        try {
            parametreService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/matiere/{idMatiere}")
    public ResponseEntity<List<Parametre>> getParametresByMatiere(@PathVariable Long idMatiere) {
        List<Parametre> parametres = parametreService.findByIdMatiere(idMatiere);
        return ResponseEntity.ok(parametres);
    }

    @GetMapping("/operateur/{idOperateur}")
    public ResponseEntity<List<Parametre>> getParametresByOperateur(@PathVariable Long idOperateur) {
        List<Parametre> parametres = parametreService.findByIdOperateur(idOperateur);
        return ResponseEntity.ok(parametres);
    }

    @GetMapping("/resolution/{idResolution}")
    public ResponseEntity<List<Parametre>> getParametresByResolution(@PathVariable Long idResolution) {
        List<Parametre> parametres = parametreService.findByIdResolution(idResolution);
        return ResponseEntity.ok(parametres);
    }

    @GetMapping("/matiere/{idMatiere}/operateur/{idOperateur}")
    public ResponseEntity<List<Parametre>> getParametresByMatiereAndOperateur(
            @PathVariable Long idMatiere,
            @PathVariable Long idOperateur) {
        List<Parametre> parametres = parametreService.findByMatiereAndOperateur(idMatiere, idOperateur);
        return ResponseEntity.ok(parametres);
    }

    @GetMapping("/matiere/{idMatiere}/resolution/{idResolution}")
    public ResponseEntity<List<Parametre>> getParametresByMatiereAndResolution(
            @PathVariable Long idMatiere,
            @PathVariable Long idResolution) {
        List<Parametre> parametres = parametreService.findByMatiereAndResolution(idMatiere, idResolution);
        return ResponseEntity.ok(parametres);
    }

    @GetMapping("/operateur/{idOperateur}/resolution/{idResolution}")
    public ResponseEntity<List<Parametre>> getParametresByOperateurAndResolution(
            @PathVariable Long idOperateur,
            @PathVariable Long idResolution) {
        List<Parametre> parametres = parametreService.findByOperateurAndResolution(idOperateur, idResolution);
        return ResponseEntity.ok(parametres);
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkIfExists(
            @RequestParam Long idMatiere,
            @RequestParam Long idOperateur,
            @RequestParam Long idResolution) {
        boolean exists = parametreService.existsByIdMatiereAndIdOperateurAndIdResolution(idMatiere, idOperateur, idResolution);
        return ResponseEntity.ok(exists);
    }
}
