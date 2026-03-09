package com.example.restservice.repository;

import com.example.restservice.entity.Resolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResolutionRepository extends JpaRepository<Resolution, Long> {
    
    Optional<Resolution> findByNom(String nom);
    
    List<Resolution> findByNomContainingIgnoreCase(String nom);
    
    @Query("SELECT r FROM Resolution r WHERE LOWER(r.nom) LIKE LOWER(CONCAT('%', :nom, '%'))")
    List<Resolution> findByNomContaining(@Param("nom") String nom);
    
    boolean existsByNom(String nom);
}
