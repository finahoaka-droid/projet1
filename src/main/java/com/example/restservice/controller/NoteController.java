package com.example.restservice.controller;

import com.example.restservice.entity.Note;
import com.example.restservice.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteService.findAll();
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Optional<Note> note = noteService.findById(id);
        return note.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        try {
            Note savedNote = noteService.save(note);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedNote);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note note) {
        try {
            Note updatedNote = noteService.update(id, note);
            return ResponseEntity.ok(updatedNote);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        try {
            noteService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/candidat/{idCandidat}")
    public ResponseEntity<List<Note>> getNotesByCandidat(@PathVariable Long idCandidat) {
        List<Note> notes = noteService.findByIdCandidat(idCandidat);
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/matiere/{idMatiere}")
    public ResponseEntity<List<Note>> getNotesByMatiere(@PathVariable Long idMatiere) {
        List<Note> notes = noteService.findByIdMatiere(idMatiere);
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/correcteur/{idCorrecteur}")
    public ResponseEntity<List<Note>> getNotesByCorrecteur(@PathVariable Long idCorrecteur) {
        List<Note> notes = noteService.findByIdCorrecteur(idCorrecteur);
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/candidat/{idCandidat}/matiere/{idMatiere}")
    public ResponseEntity<List<Note>> getNotesByCandidatAndMatiere(
            @PathVariable Long idCandidat,
            @PathVariable Long idMatiere) {
        List<Note> notes = noteService.findByCandidatAndMatiere(idCandidat, idMatiere);
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/matiere/{idMatiere}/average")
    public ResponseEntity<Double> getAverageByMatiere(@PathVariable Long idMatiere) {
        Optional<Double> average = noteService.findAverageByMatiere(idMatiere);
        return average.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/candidat/{idCandidat}/average")
    public ResponseEntity<Double> getAverageByCandidat(@PathVariable Long idCandidat) {
        Optional<Double> average = noteService.findAverageByCandidat(idCandidat);
        return average.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/range")
    public ResponseEntity<List<Note>> getNotesByRange(
            @RequestParam Double min,
            @RequestParam Double max) {
        List<Note> notes = noteService.findByNoteRange(min, max);
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkIfExists(
            @RequestParam Long idCandidat,
            @RequestParam Long idMatiere,
            @RequestParam Long idCorrecteur) {
        boolean exists = noteService.existsByIdCandidatAndIdMatiereAndIdCorrecteur(idCandidat, idMatiere, idCorrecteur);
        return ResponseEntity.ok(exists);
    }
}
