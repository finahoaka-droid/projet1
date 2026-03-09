package com.example.restservice.service;

import com.example.restservice.entity.Note;
import com.example.restservice.entity.Parametre;
import com.example.restservice.entity.Resolution;
import com.example.restservice.entity.Operateur;
import com.example.restservice.repository.NoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.function.BiPredicate;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private ParametreService parametreService;

    @Autowired
    private ResolutionService resolutionService;

    @Autowired
    private OperateurService operateurService;

    private static final Map<String, BiPredicate<Double, Double>> OPERATEURS = Map.of(
        "<",  (a, b) -> a < b,
        "<=", (a, b) -> a <= b,
        ">",  (a, b) -> a > b,
        ">=", (a, b) -> a >= b
    );

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public Optional<Note> findById(Long id) {
        return noteRepository.findById(id);
    }

    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public Note update(Long id, Note note) {
        if (noteRepository.existsById(id)) {
            note.setIdNote(id);
            return noteRepository.save(note);
        }
        throw new RuntimeException("Note non trouvée avec l'ID: " + id);
    }

    public void deleteById(Long id) {
        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Note non trouvée avec l'ID: " + id);
        }
    }

    public List<Note> findByIdCandidat(Long idCandidat) {
        return noteRepository.findByIdCandidat(idCandidat);
    }

    public List<Note> findByIdMatiere(Long idMatiere) {
        return noteRepository.findByIdMatiere(idMatiere);
    }

    public List<Note> findByIdCorrecteur(Long idCorrecteur) {
        return noteRepository.findByIdCorrecteur(idCorrecteur);
    }

    public List<Note> findByCandidatAndMatiere(Long idCandidat, Long idMatiere) {
        return noteRepository.findByCandidatAndMatiere(idCandidat, idMatiere);
    }

    public Optional<Double> findAverageByMatiere(Long idMatiere) {
        return noteRepository.findAverageByMatiere(idMatiere);
    }

    public Optional<Double> findAverageByCandidat(Long idCandidat) {
        return noteRepository.findAverageByCandidat(idCandidat);
    }

    public List<Note> findByNoteRange(Double min, Double max) {
        return noteRepository.findByNoteRange(min, max);
    }

    public boolean existsByIdCandidatAndIdMatiereAndIdCorrecteur(Long idCandidat, Long idMatiere, Long idCorrecteur) {
        return noteRepository.existsByIdCandidatAndIdMatiereAndIdCorrecteur(idCandidat, idMatiere, idCorrecteur);
    }

    public boolean existsById(Long id) {
        return noteRepository.existsById(id);
    }

    public double getMinNote(Long idCandidat, Long idMatiere) {

        List<Note> notes = findByCandidatAndMatiere(idCandidat, idMatiere);

        if (notes.isEmpty()) {
            return 0.0;
        }

        return notes.stream()
                .mapToDouble(note -> note.getNote().doubleValue())
                .min()
                .orElse(0.0);
    }

    public double getMaxNote(Long idCandidat, Long idMatiere) {

        List<Note> notes = findByCandidatAndMatiere(idCandidat, idMatiere);

        if (notes.isEmpty()) {
            return 0.0;
        }

        return notes.stream()
                .mapToDouble(note -> note.getNote().doubleValue())
                .max()
                .orElse(0.0);
    }

    public double calculateDifference(Long idCandidat, Long idMatiere) {

        double maxNote = getMaxNote(idCandidat, idMatiere);
        double minNote = getMinNote(idCandidat, idMatiere);

        return maxNote - minNote;
    }

    public double calculateAverage(Long idCandidat, Long idMatiere) {

        List<Note> notes = findByCandidatAndMatiere(idCandidat, idMatiere);

        if (notes.isEmpty()) {
            return 0.0;
        }

        return notes.stream()
                .mapToDouble(note -> note.getNote().doubleValue())
                .average()
                .orElse(0.0);
    }

    public double calculateDifferenceSomme(Long idCandidat, Long idMatiere) {

        List<Note> notes = findByCandidatAndMatiere(idCandidat, idMatiere);

        if (notes.isEmpty()) {
            return 0.0;
        }

        if (notes.size() == 2) {
            return calculateDifference(idCandidat, idMatiere);
        }

        double[] noteValues = notes.stream()
                .mapToDouble(note -> note.getNote().doubleValue())
                .toArray();

        double totalDifference = 0.0;

        for (int i = 0; i < noteValues.length; i++) {
            for (int j = i + 1; j < noteValues.length; j++) {
                totalDifference += Math.abs(noteValues[i] - noteValues[j]);
            }
        }

        return totalDifference;
    }

    public Optional<Resolution> findResolutionByDifferenceComparison(Long idCandidat, Long idMatiere) {

        double calculatedDifference = calculateDifferenceSomme(idCandidat, idMatiere);

        if (calculatedDifference == 0.0) {
            return resolutionService.findByNom("Petite");
        }

        List<Parametre> parametres = parametreService.findByIdMatiere(idMatiere);

        for (Parametre parametre : parametres) {

            Optional<Resolution> resolution = checkParametre(parametre, calculatedDifference);

            if (resolution.isPresent()) {
                return resolution;
            }
        }

        return Optional.empty();
    }

    private Optional<Resolution> checkParametre(Parametre parametre, double calculatedDifference) {

        Optional<Operateur> operateurOpt = operateurService.findById(parametre.getIdOperateur());

        if (operateurOpt.isEmpty()) {
            return Optional.empty();
        }

        String operateur = operateurOpt.get().getOperateur();
        double parametreDifference = parametre.getDifference().doubleValue();

        // if (compare(parametreDifference, calculatedDifference, operateur)) {
        //     return resolutionService.findById(parametre.getIdResolution());
        // }

        if (compare(calculatedDifference, parametreDifference, operateur)) {
            return resolutionService.findById(parametre.getIdResolution());
        }

        return Optional.empty();
    }

    private boolean compare(double value1, double value2, String operateur) {

        BiPredicate<Double, Double> operation = OPERATEURS.get(operateur);

        if (operation == null) {
            return false;
        }

        return operation.test(value1, value2);
    }

    public double getNoteFinale(Long idCandidat, Long idMatiere, Long idResolution) {

        Optional<Resolution> resolutionOpt = resolutionService.findById(idResolution);

        if (!resolutionOpt.isPresent()) {
            return 0.0;
        }

        String nomResolution = resolutionOpt.get().getNom();

        switch (nomResolution) {

            case "Petite":
                return getMinNote(idCandidat, idMatiere);

            case "Grande":
                return getMaxNote(idCandidat, idMatiere);

            case "Moyenne":
                return calculateAverage(idCandidat, idMatiere);

            default:
                return 0.0;
        }
    }

    public double getNoteFinaleWithZeroDifference(Long idCandidat, Long idMatiere) {

        double calculatedDifference = calculateDifferenceSomme(idCandidat, idMatiere);

        if (calculatedDifference == 0.0) {

            List<Note> notes = findByCandidatAndMatiere(idCandidat, idMatiere);

            if (!notes.isEmpty()) {
                return notes.get(0).getNote().doubleValue();
            }

            return 0.0;
        }

        Optional<Resolution> resolutionOpt = findResolutionByDifferenceComparison(idCandidat, idMatiere);

        if (resolutionOpt.isPresent()) {
            return getNoteFinale(idCandidat, idMatiere, resolutionOpt.get().getIdResolution());
        }

        return calculateAverage(idCandidat, idMatiere);
    }
}

