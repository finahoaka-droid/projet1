package com.example.restservice.service;

import com.example.restservice.entity.Operateur;
import com.example.restservice.repository.OperateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperateurService {

    @Autowired
    private OperateurRepository operateurRepository;

    public List<Operateur> findAll() {
        return operateurRepository.findAll();
    }

    public Optional<Operateur> findById(Long id) {
        return operateurRepository.findById(id);
    }

    public Operateur save(Operateur operateur) {
        return operateurRepository.save(operateur);
    }

    public Operateur update(Long id, Operateur operateur) {
        if (operateurRepository.existsById(id)) {
            operateur.setIdOperateur(id);
            return operateurRepository.save(operateur);
        }
        throw new RuntimeException("Opérateur non trouvé avec l'ID: " + id);
    }

    public void deleteById(Long id) {
        if (operateurRepository.existsById(id)) {
            operateurRepository.deleteById(id);
        } else {
            throw new RuntimeException("Opérateur non trouvé avec l'ID: " + id);
        }
    }

    public Optional<Operateur> findByOperateur(String operateur) {
        return operateurRepository.findByOperateur(operateur);
    }

    public List<Operateur> findByOperateurContaining(String operateur) {
        return operateurRepository.findByOperateurContainingIgnoreCase(operateur);
    }

    public List<Operateur> findByOperateurContainingQuery(String operateur) {
        return operateurRepository.findByOperateurContaining(operateur);
    }

    public boolean existsByOperateur(String operateur) {
        return operateurRepository.existsByOperateur(operateur);
    }

    public boolean existsById(Long id) {
        return operateurRepository.existsById(id);
    }
}
