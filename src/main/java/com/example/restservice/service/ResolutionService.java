package com.example.restservice.service;

import com.example.restservice.entity.Resolution;
import com.example.restservice.repository.ResolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResolutionService {

    @Autowired
    private ResolutionRepository resolutionRepository;

    public List<Resolution> findAll() {
        return resolutionRepository.findAll();
    }

    public Optional<Resolution> findById(Long id) {
        return resolutionRepository.findById(id);
    }

    public Resolution save(Resolution resolution) {
        return resolutionRepository.save(resolution);
    }

    public Resolution update(Long id, Resolution resolution) {
        if (resolutionRepository.existsById(id)) {
            resolution.setIdResolution(id);
            return resolutionRepository.save(resolution);
        }
        throw new RuntimeException("Résolution non trouvée avec l'ID: " + id);
    }

    public void deleteById(Long id) {
        if (resolutionRepository.existsById(id)) {
            resolutionRepository.deleteById(id);
        } else {
            throw new RuntimeException("Résolution non trouvée avec l'ID: " + id);
        }
    }

    public Optional<Resolution> findByNom(String nom) {
        return resolutionRepository.findByNom(nom);
    }

    public List<Resolution> findByNomContaining(String nom) {
        return resolutionRepository.findByNomContainingIgnoreCase(nom);
    }

    public List<Resolution> findByNomContainingQuery(String nom) {
        return resolutionRepository.findByNomContaining(nom);
    }

    public boolean existsByNom(String nom) {
        return resolutionRepository.existsByNom(nom);
    }

    public boolean existsById(Long id) {
        return resolutionRepository.existsById(id);
    }
}
