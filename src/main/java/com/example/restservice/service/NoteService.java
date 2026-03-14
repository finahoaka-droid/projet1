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
import java.util.Arrays;
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
        if (!noteRepository.existsById(id)) {
            throw new RuntimeException("Note non trouvée avec l'ID: " + id);
        }
        note.setIdNote(id);
        return noteRepository.save(note);
    }

    public void deleteById(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new RuntimeException("Note non trouvée avec l'ID: " + id);
        }
        noteRepository.deleteById(id);
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

    private double[] getNoteValues(Long idCandidat, Long idMatiere) {
        return findByCandidatAndMatiere(idCandidat, idMatiere)
                .stream()
                .mapToDouble(n -> n.getNote().doubleValue())
                .toArray();
    }

    public double getMinNote(Long idCandidat, Long idMatiere) {
        return Arrays.stream(getNoteValues(idCandidat, idMatiere))
                .min()
                .orElse(0.0);
    }

    public double getMaxNote(Long idCandidat, Long idMatiere) {
        return Arrays.stream(getNoteValues(idCandidat, idMatiere))
                .max()
                .orElse(0.0);
    }

    public double calculateDifference(Long idCandidat, Long idMatiere) {
        return getMaxNote(idCandidat, idMatiere) - getMinNote(idCandidat, idMatiere);
    }

    public double calculateAverage(Long idCandidat, Long idMatiere) {
        return Arrays.stream(getNoteValues(idCandidat, idMatiere))
                .average()
                .orElse(0.0);
    }

    public double calculateDifferenceSomme(Long idCandidat, Long idMatiere) {
        double[] notes = getNoteValues(idCandidat, idMatiere);

        if (notes.length == 0) {
            return 0.0;
        }

        if (notes.length == 2) {
            return Math.abs(notes[0] - notes[1]);
        }

        double total = 0.0;

        for (int i = 0; i < notes.length; i++) {
            for (int j = i + 1; j < notes.length; j++) {
                total += Math.abs(notes[i] - notes[j]);
            }
        }

        return total;
    }

    private boolean compare(double value1, double value2, String operateur) {
        return Optional.ofNullable(OPERATEURS.get(operateur))
                .map(op -> op.test(value1, value2))
                .orElse(false);
    }

    private Optional<Resolution> checkParametre(Parametre parametre, double calculatedDifference) {

        Optional<Operateur> operateurOpt = operateurService.findById(parametre.getIdOperateur());

        if (operateurOpt.isEmpty()) {
            return Optional.empty();
        }

        String operateur = operateurOpt.get().getOperateur();
        double parametreDifference = parametre.getDifference().doubleValue();

        if (compare(calculatedDifference, parametreDifference, operateur)) {
            return resolutionService.findById(parametre.getIdResolution());
        }

        return Optional.empty();
    }

    public Optional<Resolution> findResolutionByDifferenceComparison(Long idCandidat, Long idMatiere) {

        double difference = calculateDifferenceSomme(idCandidat, idMatiere);

        if (difference == 0.0) {
            return resolutionService.findByNom("Petite");
        }

        List<Parametre> compatibles = findParametresCompatibles(idCandidat, idMatiere);

        if (compatibles.isEmpty()) {
            return Optional.empty();
        }

        if (compatibles.size() == 1) {
            return resolutionService.findById(compatibles.get(0).getIdResolution());
        }

        Optional<Parametre> parametreProche = findParametreLePlusProche(idCandidat, idMatiere);

        return parametreProche.flatMap(p -> resolutionService.findById(p.getIdResolution()));
    }

    public double getNoteFinale(Long idCandidat, Long idMatiere, Long idResolution) {

        Optional<Resolution> resolutionOpt = resolutionService.findById(idResolution);

        if (resolutionOpt.isEmpty()) {
            return 0.0;
        }

        return switch (resolutionOpt.get().getNom()) {
            case "Petite" -> getMinNote(idCandidat, idMatiere);
            case "Grande" -> getMaxNote(idCandidat, idMatiere);
            case "Moyenne" -> calculateAverage(idCandidat, idMatiere);
            default -> 0.0;
        };
    }

    public double getNoteFinaleWithZeroDifference(Long idCandidat, Long idMatiere) {

        double difference = calculateDifferenceSomme(idCandidat, idMatiere);

        if (difference == 0.0) {
            return Arrays.stream(getNoteValues(idCandidat, idMatiere))
                    .findFirst()
                    .orElse(0.0);
        }

        return findResolutionByDifferenceComparison(idCandidat, idMatiere)
                .map(r -> getNoteFinale(idCandidat, idMatiere, r.getIdResolution()))
                .orElse(calculateAverage(idCandidat, idMatiere));
    }

    public List<Parametre> findParametresCompatibles(Long idCandidat, Long idMatiere) {

        double difference = calculateDifferenceSomme(idCandidat, idMatiere);

        List<Parametre> compatibles = new java.util.ArrayList<>();

        for (Parametre p : parametreService.findByIdMatiere(idMatiere)) {

            Optional<Resolution> resolution = checkParametre(p, difference);

            if (resolution.isPresent()) {
                compatibles.add(p);
            }
        }

        return compatibles;
    }

    public Optional<Parametre> findParametreLePlusProche(Long idCandidat, Long idMatiere) {

        double difference = calculateDifferenceSomme(idCandidat, idMatiere);

        List<Parametre> compatibles = findParametresCompatibles(idCandidat, idMatiere);

        Parametre plusProche = null;
        double ecartMin = Double.MAX_VALUE;

        for (Parametre p : compatibles) {

            double diffParametre = p.getDifference().doubleValue();
            double ecart = Math.abs(difference - diffParametre);

            if (ecart < ecartMin) {
                ecartMin = ecart;
                plusProche = p;
            } else if (ecart == ecartMin) {
                // Si écart identique → prendre le plus petit paramètre
                if (diffParametre < plusProche.getDifference().doubleValue()) {
                    plusProche = p;
                }
            }
        }

        return Optional.ofNullable(plusProche);
    }
}