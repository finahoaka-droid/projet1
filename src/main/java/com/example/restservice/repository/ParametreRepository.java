package com.example.restservice.repository;

import com.example.restservice.entity.Parametre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParametreRepository extends JpaRepository<Parametre, Long> {
    
    List<Parametre> findByIdMatiere(Long idMatiere);
    
    List<Parametre> findByIdOperateur(Long idOperateur);
    
    List<Parametre> findByIdResolution(Long idResolution);
    
    Optional<Parametre> findByIdMatiereAndIdOperateurAndIdResolution(Long idMatiere, Long idOperateur, Long idResolution);
    
    @Query("SELECT p FROM Parametre p WHERE p.idMatiere = :idMatiere AND p.idOperateur = :idOperateur")
    List<Parametre> findByMatiereAndOperateur(@Param("idMatiere") Long idMatiere, @Param("idOperateur") Long idOperateur);
    
    @Query("SELECT p FROM Parametre p WHERE p.idMatiere = :idMatiere AND p.idResolution = :idResolution")
    List<Parametre> findByMatiereAndResolution(@Param("idMatiere") Long idMatiere, @Param("idResolution") Long idResolution);
    
    @Query("SELECT p FROM Parametre p WHERE p.idOperateur = :idOperateur AND p.idResolution = :idResolution")
    List<Parametre> findByOperateurAndResolution(@Param("idOperateur") Long idOperateur, @Param("idResolution") Long idResolution);
    
    @Query("SELECT p FROM Parametre p WHERE p.difference BETWEEN :min AND :max")
    List<Parametre> findByDifferenceRange(@Param("min") Double min, @Param("max") Double max);
    
    boolean existsByIdMatiereAndIdOperateurAndIdResolution(Long idMatiere, Long idOperateur, Long idResolution);
}
