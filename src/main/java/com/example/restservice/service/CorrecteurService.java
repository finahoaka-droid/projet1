package com.example.restservice.service;

import com.example.restservice.entity.Correcteur;
import com.example.restservice.repository.CorrecteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CorrecteurService {

    @Autowired
    private CorrecteurRepository correcteurRepository;

    public List<Correcteur> findAll() {
        return correcteurRepository.findAll();
    }

    public Optional<Correcteur> findById(Long id) {
        return correcteurRepository.findById(id);
    }

    public Correcteur save(Correcteur correcteur) {
        return correcteurRepository.save(correcteur);
    }

    public Correcteur update(Long id, Correcteur correcteur) {
        if (correcteurRepository.existsById(id)) {
            correcteur.setIdCorrecteur(id);
            return correcteurRepository.save(correcteur);
        }
        throw new RuntimeException("Correcteur non trouvé avec l'ID: " + id);
    }

    public void deleteById(Long id) {
        if (correcteurRepository.existsById(id)) {
            correcteurRepository.deleteById(id);
        } else {
            throw new RuntimeException("Correcteur non trouvé avec l'ID: " + id);
        }
    }

    public Optional<Correcteur> findByNomAndPrenom(String nom, String prenom) {
        return correcteurRepository.findByNomAndPrenom(nom, prenom);
    }

    public List<Correcteur> findByNomContaining(String nom) {
        return correcteurRepository.findByNomContainingIgnoreCase(nom);
    }

    public List<Correcteur> findByPrenomContaining(String prenom) {
        return correcteurRepository.findByPrenomContainingIgnoreCase(prenom);
    }

    public List<Correcteur> findByNomAndPrenomContaining(String nom, String prenom) {
        return correcteurRepository.findByNomAndPrenomContaining(nom, prenom);
    }

    public boolean existsByNomAndPrenom(String nom, String prenom) {
        return correcteurRepository.existsByNomAndPrenom(nom, prenom);
    }

    public boolean existsById(Long id) {
        return correcteurRepository.existsById(id);
    }
}
