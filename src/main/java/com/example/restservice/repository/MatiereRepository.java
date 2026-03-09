package com.example.restservice.repository;

import com.example.restservice.entity.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Long> {
    
    Optional<Matiere> findByNom(String nom);
    
    List<Matiere> findByNomContainingIgnoreCase(String nom);
    
    @Query("SELECT m FROM Matiere m WHERE m.nom LIKE %:nom%")
    List<Matiere> findByNomContaining(@Param("nom") String nom);
    
    boolean existsByNom(String nom);
}
