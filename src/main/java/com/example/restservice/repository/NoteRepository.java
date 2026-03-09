package com.example.restservice.repository;

import com.example.restservice.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    
    List<Note> findByIdCandidat(Long idCandidat);
    
    List<Note> findByIdMatiere(Long idMatiere);
    
    List<Note> findByIdCorrecteur(Long idCorrecteur);
    
    Optional<Note> findByIdCandidatAndIdMatiereAndIdCorrecteur(Long idCandidat, Long idMatiere, Long idCorrecteur);
    
    @Query("SELECT n FROM Note n WHERE n.idCandidat = :idCandidat AND n.idMatiere = :idMatiere")
    List<Note> findByCandidatAndMatiere(@Param("idCandidat") Long idCandidat, @Param("idMatiere") Long idMatiere);
    
    @Query("SELECT AVG(n.note) FROM Note n WHERE n.idMatiere = :idMatiere")
    Optional<Double> findAverageByMatiere(@Param("idMatiere") Long idMatiere);
    
    @Query("SELECT AVG(n.note) FROM Note n WHERE n.idCandidat = :idCandidat")
    Optional<Double> findAverageByCandidat(@Param("idCandidat") Long idCandidat);
    
    @Query("SELECT n FROM Note n WHERE n.note BETWEEN :min AND :max")
    List<Note> findByNoteRange(@Param("min") Double min, @Param("max") Double max);
    
    boolean existsByIdCandidatAndIdMatiereAndIdCorrecteur(Long idCandidat, Long idMatiere, Long idCorrecteur);
}
