package com.example.restservice.service;

import com.example.restservice.entity.Matiere;
import com.example.restservice.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatiereService {

    @Autowired
    private MatiereRepository matiereRepository;

    public List<Matiere> findAll() {
        return matiereRepository.findAll();
    }

    public Optional<Matiere> findById(Long id) {
        return matiereRepository.findById(id);
    }

    public Matiere save(Matiere matiere) {
        return matiereRepository.save(matiere);
    }

    public Matiere update(Long id, Matiere matiere) {
        if (matiereRepository.existsById(id)) {
            matiere.setIdMatiere(id);
            return matiereRepository.save(matiere);
        }
        throw new RuntimeException("Matière non trouvée avec l'ID: " + id);
    }

    public void deleteById(Long id) {
        if (matiereRepository.existsById(id)) {
            matiereRepository.deleteById(id);
        } else {
            throw new RuntimeException("Matière non trouvée avec l'ID: " + id);
        }
    }

    public Optional<Matiere> findByNom(String nom) {
        return matiereRepository.findByNom(nom);
    }

    public List<Matiere> findByNomContaining(String nom) {
        return matiereRepository.findByNomContainingIgnoreCase(nom);
    }

    public List<Matiere> findByNomContainingQuery(String nom) {
        return matiereRepository.findByNomContaining(nom);
    }

    public boolean existsByNom(String nom) {
        return matiereRepository.existsByNom(nom);
    }

    public boolean existsById(Long id) {
        return matiereRepository.existsById(id);
    }
}
