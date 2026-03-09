package com.example.restservice.controller;

import com.example.restservice.entity.Resolution;
import com.example.restservice.service.ResolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/resolutions")
@CrossOrigin(origins = "*")
public class ResolutionController {

    @Autowired
    private ResolutionService resolutionService;

    @GetMapping
    public ResponseEntity<List<Resolution>> getAllResolutions() {
        List<Resolution> resolutions = resolutionService.findAll();
        return ResponseEntity.ok(resolutions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resolution> getResolutionById(@PathVariable Long id) {
        Optional<Resolution> resolution = resolutionService.findById(id);
        return resolution.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Resolution> createResolution(@RequestBody Resolution resolution) {
        try {
            Resolution savedResolution = resolutionService.save(resolution);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedResolution);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resolution> updateResolution(@PathVariable Long id, @RequestBody Resolution resolution) {
        try {
            Resolution updatedResolution = resolutionService.update(id, resolution);
            return ResponseEntity.ok(updatedResolution);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResolution(@PathVariable Long id) {
        try {
            resolutionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Resolution>> searchResolutions(@RequestParam String nom) {
        List<Resolution> resolutions = resolutionService.findByNomContaining(nom);
        return ResponseEntity.ok(resolutions);
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkIfExists(@RequestParam String nom) {
        boolean exists = resolutionService.existsByNom(nom);
        return ResponseEntity.ok(exists);
    }
}
