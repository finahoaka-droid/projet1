package com.example.restservice.service;

import com.example.restservice.entity.Parametre;
import com.example.restservice.repository.ParametreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParametreService {

    @Autowired
    private ParametreRepository parametreRepository;

    public List<Parametre> findAll() {
        return parametreRepository.findAll();
    }

    public Optional<Parametre> findById(Long id) {
        return parametreRepository.findById(id);
    }

    public Parametre save(Parametre parametre) {
        return parametreRepository.save(parametre);
    }

    public Parametre update(Long id, Parametre parametre) {
        if (parametreRepository.existsById(id)) {
            parametre.setIdParametre(id);
            return parametreRepository.save(parametre);
        }
        throw new RuntimeException("Paramètre non trouvé avec l'ID: " + id);
    }

    public void deleteById(Long id) {
        if (parametreRepository.existsById(id)) {
            parametreRepository.deleteById(id);
        } else {
            throw new RuntimeException("Paramètre non trouvé avec l'ID: " + id);
        }
    }

    public List<Parametre> findByIdMatiere(Long idMatiere) {
        return parametreRepository.findByIdMatiere(idMatiere);
    }

    public List<Parametre> findByIdOperateur(Long idOperateur) {
        return parametreRepository.findByIdOperateur(idOperateur);
    }

    public List<Parametre> findByIdResolution(Long idResolution) {
        return parametreRepository.findByIdResolution(idResolution);
    }

    public Optional<Parametre> findByIdMatiereAndIdOperateurAndIdResolution(Long idMatiere, Long idOperateur, Long idResolution) {
        return parametreRepository.findByIdMatiereAndIdOperateurAndIdResolution(idMatiere, idOperateur, idResolution);
    }

    public List<Parametre> findByMatiereAndOperateur(Long idMatiere, Long idOperateur) {
        return parametreRepository.findByMatiereAndOperateur(idMatiere, idOperateur);
    }

    public List<Parametre> findByMatiereAndResolution(Long idMatiere, Long idResolution) {
        return parametreRepository.findByMatiereAndResolution(idMatiere, idResolution);
    }

    public List<Parametre> findByOperateurAndResolution(Long idOperateur, Long idResolution) {
        return parametreRepository.findByOperateurAndResolution(idOperateur, idResolution);
    }

    public List<Parametre> findByDifferenceRange(Double min, Double max) {
        return parametreRepository.findByDifferenceRange(min, max);
    }

    public boolean existsByIdMatiereAndIdOperateurAndIdResolution(Long idMatiere, Long idOperateur, Long idResolution) {
        return parametreRepository.existsByIdMatiereAndIdOperateurAndIdResolution(idMatiere, idOperateur, idResolution);
    }

    public boolean existsById(Long id) {
        return parametreRepository.existsById(id);
    }
}
