package com.example.restservice.repository;

import com.example.restservice.entity.Operateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OperateurRepository extends JpaRepository<Operateur, Long> {
    
    Optional<Operateur> findByOperateur(String operateur);
    
    List<Operateur> findByOperateurContainingIgnoreCase(String operateur);
    
    @Query("SELECT o FROM Operateur o WHERE o.operateur LIKE %:operateur%")
    List<Operateur> findByOperateurContaining(@Param("operateur") String operateur);
    
    boolean existsByOperateur(String operateur);
}
