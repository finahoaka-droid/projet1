package com.example.restservice.service;

import com.example.restservice.entity.Candidat;
import com.example.restservice.repository.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatService {

    @Autowired
    private CandidatRepository candidatRepository;

    public List<Candidat> findAll() {
        return candidatRepository.findAll();
    }

    public Optional<Candidat> findById(Long id) {
        return candidatRepository.findById(id);
    }

    public Candidat save(Candidat candidat) {
        return candidatRepository.save(candidat);
    }

    public Candidat update(Long id, Candidat candidat) {
        if (candidatRepository.existsById(id)) {
            candidat.setIdCandidat(id);
            return candidatRepository.save(candidat);
        }
        throw new RuntimeException("Candidat non trouvé avec l'ID: " + id);
    }

    public void deleteById(Long id) {
        if (candidatRepository.existsById(id)) {
            candidatRepository.deleteById(id);
        } else {
            throw new RuntimeException("Candidat non trouvé avec l'ID: " + id);
        }
    }

    public Optional<Candidat> findByNomAndPrenom(String nom, String prenom) {
        return candidatRepository.findByNomAndPrenom(nom, prenom);
    }

    public List<Candidat> findByNomContaining(String nom) {
        return candidatRepository.findByNomContainingIgnoreCase(nom);
    }

    public List<Candidat> findByPrenomContaining(String prenom) {
        return candidatRepository.findByPrenomContainingIgnoreCase(prenom);
    }

    public List<Candidat> findByNomAndPrenomContaining(String nom, String prenom) {
        return candidatRepository.findByNomAndPrenomContaining(nom, prenom);
    }

    public boolean existsByNomAndPrenom(String nom, String prenom) {
        return candidatRepository.existsByNomAndPrenom(nom, prenom);
    }

    public boolean existsById(Long id) {
        return candidatRepository.existsById(id);
    }
}
