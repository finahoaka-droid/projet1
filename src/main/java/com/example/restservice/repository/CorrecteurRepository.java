package com.example.restservice.repository;

import com.example.restservice.entity.Correcteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CorrecteurRepository extends JpaRepository<Correcteur, Long> {
    
    Optional<Correcteur> findByNomAndPrenom(String nom, String prenom);
    
    List<Correcteur> findByNomContainingIgnoreCase(String nom);
    
    List<Correcteur> findByPrenomContainingIgnoreCase(String prenom);
    
    @Query("SELECT c FROM Correcteur c WHERE c.nom LIKE %:nom% AND c.prenom LIKE %:prenom%")
    List<Correcteur> findByNomAndPrenomContaining(@Param("nom") String nom, @Param("prenom") String prenom);
    
    boolean existsByNomAndPrenom(String nom, String prenom);
}
