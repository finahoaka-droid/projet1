package com.example.restservice.repository;

import com.example.restservice.entity.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, Long> {
    
    Optional<Candidat> findByNomAndPrenom(String nom, String prenom);
    
    List<Candidat> findByNomContainingIgnoreCase(String nom);
    
    List<Candidat> findByPrenomContainingIgnoreCase(String prenom);
    
    @Query("SELECT c FROM Candidat c WHERE c.nom LIKE %:nom% AND c.prenom LIKE %:prenom%")
    List<Candidat> findByNomAndPrenomContaining(@Param("nom") String nom, @Param("prenom") String prenom);
    
    boolean existsByNomAndPrenom(String nom, String prenom);
}
