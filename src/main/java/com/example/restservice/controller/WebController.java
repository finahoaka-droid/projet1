package com.example.restservice.controller;

import com.example.restservice.entity.Candidat;
import com.example.restservice.entity.Correcteur;
import com.example.restservice.entity.Matiere;
import com.example.restservice.entity.Note;
import com.example.restservice.entity.Resolution;
import com.example.restservice.entity.Operateur;
import com.example.restservice.entity.Parametre;
import com.example.restservice.service.CandidatService;
import com.example.restservice.service.CorrecteurService;
import com.example.restservice.service.MatiereService;
import com.example.restservice.service.NoteService;
import com.example.restservice.service.ResolutionService;
import com.example.restservice.service.OperateurService;
import com.example.restservice.service.ParametreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private CandidatService candidatService;
    
    @Autowired
    private CorrecteurService correcteurService;
    
    @Autowired
    private MatiereService matiereService;
    
    @Autowired
    private NoteService noteService;
    
    @Autowired
    private ResolutionService resolutionService;
    
    @Autowired
    private OperateurService operateurService;
    
    @Autowired
    private ParametreService parametreService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/candidats")
    public String candidats(Model model) {
        List<Candidat> candidats = candidatService.findAll();
        model.addAttribute("candidats", candidats);
        return "candidats";
    }

    @GetMapping("/candidats/add")
    public String addCandidatForm() {
        return "candidats_add";
    }

    @PostMapping("/candidats/save")
    public String saveCandidat(@RequestParam String nom, @RequestParam String prenom, 
                               RedirectAttributes redirectAttributes) {
        try {
            Candidat candidat = new Candidat(nom, prenom);
            candidatService.save(candidat);
            redirectAttributes.addFlashAttribute("success", "Candidat ajouté avec succès!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de l'ajout du candidat");
        }
        return "redirect:/candidats";
    }

    @GetMapping("/candidats/edit/{id}")
    public String editCandidatForm(@PathVariable Long id, Model model) {
        Candidat candidat = candidatService.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidat non trouvé"));
        model.addAttribute("candidat", candidat);
        return "candidats_edit";
    }

    @PostMapping("/candidats/update")
    public String updateCandidat(@RequestParam Long id, @RequestParam String nom, @RequestParam String prenom,
                               RedirectAttributes redirectAttributes) {
        try {
            Candidat candidat = new Candidat(nom, prenom);
            candidat.setIdCandidat(id);
            candidatService.save(candidat);
            redirectAttributes.addFlashAttribute("success", "Candidat modifié avec succès!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la modification du candidat");
        }
        return "redirect:/candidats";
    }

    @GetMapping("/candidats/delete/{id}")
    public String deleteCandidat(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            candidatService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Candidat supprimé avec succès!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression du candidat");
        }
        return "redirect:/candidats";
    }

    @GetMapping("/correcteurs")
    public String correcteurs(Model model) {
        List<Correcteur> correcteurs = correcteurService.findAll();
        model.addAttribute("correcteurs", correcteurs);
        return "correcteurs";
    }

    @GetMapping("/correcteurs/add")
    public String addCorrecteurForm() {
        return "correcteurs_add";
    }

    @PostMapping("/correcteurs/save")
    public String saveCorrecteur(@RequestParam String nom, @RequestParam String prenom,
                               RedirectAttributes redirectAttributes) {
        try {
            Correcteur correcteur = new Correcteur(nom, prenom);
            correcteurService.save(correcteur);
            redirectAttributes.addFlashAttribute("success", "Correcteur ajouté avec succès!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de l'ajout du correcteur");
        }
        return "redirect:/correcteurs";
    }

    @GetMapping("/correcteurs/edit/{id}")
    public String editCorrecteurForm(@PathVariable Long id, Model model) {
        Correcteur correcteur = correcteurService.findById(id)
                .orElseThrow(() -> new RuntimeException("Correcteur non trouvé"));
        model.addAttribute("correcteur", correcteur);
        return "correcteurs_edit";
    }

    @PostMapping("/correcteurs/update")
    public String updateCorrecteur(@RequestParam Long id, @RequestParam String nom, @RequestParam String prenom,
                                 RedirectAttributes redirectAttributes) {
        try {
            Correcteur correcteur = new Correcteur(nom, prenom);
            correcteur.setIdCorrecteur(id);
            correcteurService.save(correcteur);
            redirectAttributes.addFlashAttribute("success", "Correcteur modifié avec succès!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la modification du correcteur");
        }
        return "redirect:/correcteurs";
    }

    @GetMapping("/correcteurs/delete/{id}")
    public String deleteCorrecteur(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            correcteurService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Correcteur supprimé avec succès!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression du correcteur");
        }
        return "redirect:/correcteurs";
    }

    @GetMapping("/matieres")
    public String matieres(Model model) {
        List<Matiere> matieres = matiereService.findAll();
        model.addAttribute("matieres", matieres);
        return "matieres";
    }

    @GetMapping("/matieres/add")
    public String addMatiereForm() {
        return "matieres_add";
    }

    @PostMapping("/matieres/save")
    public String saveMatiere(@RequestParam String nom, RedirectAttributes redirectAttributes) {
        try {
            Matiere matiere = new Matiere(nom);
            matiereService.save(matiere);
            redirectAttributes.addFlashAttribute("success", "Matière ajoutée avec succès!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de l'ajout de la matière");
        }
        return "redirect:/matieres";
    }

    @GetMapping("/matieres/edit/{id}")
    public String editMatiereForm(@PathVariable Long id, Model model) {
        Matiere matiere = matiereService.findById(id)
                .orElseThrow(() -> new RuntimeException("Matière non trouvée"));
        model.addAttribute("matiere", matiere);
        return "matieres_edit";
    }

    @PostMapping("/matieres/update")
    public String updateMatiere(@RequestParam Long id, @RequestParam String nom,
                               RedirectAttributes redirectAttributes) {
        try {
            Matiere matiere = new Matiere(nom);
            matiere.setIdMatiere(id);
            matiereService.save(matiere);
            redirectAttributes.addFlashAttribute("success", "Matière modifiée avec succès!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la modification de la matière");
        }
        return "redirect:/matieres";
    }

    @GetMapping("/matieres/delete/{id}")
    public String deleteMatiere(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            matiereService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Matière supprimée avec succès!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression de la matière");
        }
        return "redirect:/matieres";
    }

    @GetMapping("/notes")
    public String notes(Model model) {
        List<Note> notes = noteService.findAll();
        model.addAttribute("notes", notes);
        return "notes";
    }

    @GetMapping("/notes/add")
    public String addNoteForm() {
        return "notes_add";
    }

    @PostMapping("/notes/save")
    public String saveNote(@RequestParam Long idCandidat, @RequestParam Long idMatiere, 
                          @RequestParam Long idCorrecteur, @RequestParam BigDecimal note,
                          RedirectAttributes redirectAttributes) {
        try {
            Note noteEntity = new Note(idCandidat, idMatiere, idCorrecteur, note);
            noteService.save(noteEntity);
            redirectAttributes.addFlashAttribute("success", "Note ajoutée avec succès!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de l'ajout de la note");
        }
        return "redirect:/notes";
    }

    @GetMapping("/notes/edit/{id}")
    public String editNoteForm(@PathVariable Long id, Model model) {
        Note note = noteService.findById(id)
                .orElseThrow(() -> new RuntimeException("Note non trouvée"));
        model.addAttribute("note", note);
        return "notes_edit";
    }

    @PostMapping("/notes/update")
    public String updateNote(@RequestParam Long id, @RequestParam Long idCandidat, 
                           @RequestParam Long idMatiere, @RequestParam Long idCorrecteur, 
                           @RequestParam BigDecimal note, RedirectAttributes redirectAttributes) {
        try {
            Note noteEntity = new Note(idCandidat, idMatiere, idCorrecteur, note);
            noteEntity.setIdNote(id);
            noteService.save(noteEntity);
            redirectAttributes.addFlashAttribute("success", "Note modifiée avec succès!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la modification de la note");
        }
        return "redirect:/notes";
    }

    @GetMapping("/notes/delete/{id}")
    public String deleteNote(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            noteService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Note supprimée avec succès!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression de la note");
        }
        return "redirect:/notes";
    }

    @GetMapping("/resolutions")
    public String resolutions(Model model) {
        List<Resolution> resolutions = resolutionService.findAll();
        model.addAttribute("resolutions", resolutions);
        return "resolutions";
    }

    @GetMapping("/resolutions/add")
    public String addResolutionForm() {
        return "resolutions_add";
    }

    @PostMapping("/resolutions/save")
    public String saveResolution(@RequestParam String nom, RedirectAttributes redirectAttributes) {
        try {
            Resolution resolution = new Resolution(nom);
            resolutionService.save(resolution);
            redirectAttributes.addFlashAttribute("success", "Résolution ajoutée avec succès!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de l'ajout de la résolution");
        }
        return "redirect:/resolutions";
    }

    @GetMapping("/resolutions/edit/{id}")
    public String editResolutionForm(@PathVariable Long id, Model model) {
        Resolution resolution = resolutionService.findById(id)
                .orElseThrow(() -> new RuntimeException("Résolution non trouvée"));
        model.addAttribute("resolution", resolution);
        return "resolutions_edit";
    }

    @PostMapping("/resolutions/update")
    public String updateResolution(@RequestParam Long id, @RequestParam String nom,
                                RedirectAttributes redirectAttributes) {
        try {
            Resolution resolution = new Resolution(nom);
            resolution.setIdResolution(id);
            resolutionService.save(resolution);
            redirectAttributes.addFlashAttribute("success", "Résolution modifiée avec succès!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la modification de la résolution");
        }
        return "redirect:/resolutions";
    }

    @GetMapping("/resolutions/delete/{id}")
    public String deleteResolution(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            resolutionService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Résolution supprimée avec succès!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression de la résolution");
        }
        return "redirect:/resolutions";
    }

    @GetMapping("/operateurs")
    public String operateurs(Model model) {
        List<Operateur> operateurs = operateurService.findAll();
        model.addAttribute("operateurs", operateurs);
        return "operateurs";
    }

    @GetMapping("/operateurs/add")
    public String addOperateurForm() {
        return "operateurs_add";
    }

    @PostMapping("/operateurs/save")
    public String saveOperateur(@RequestParam String operateur, RedirectAttributes redirectAttributes) {
        try {
            Operateur operateurEntity = new Operateur(operateur);
            operateurService.save(operateurEntity);
            redirectAttributes.addFlashAttribute("success", "Opérateur ajouté avec succès!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de l'ajout de l'opérateur");
        }
        return "redirect:/operateurs";
    }

    @GetMapping("/operateurs/edit/{id}")
    public String editOperateurForm(@PathVariable Long id, Model model) {
        Operateur operateur = operateurService.findById(id)
                .orElseThrow(() -> new RuntimeException("Opérateur non trouvé"));
        model.addAttribute("operateur", operateur);
        return "operateurs_edit";
    }

    @PostMapping("/operateurs/update")
    public String updateOperateur(@RequestParam Long id, @RequestParam String operateur,
                                RedirectAttributes redirectAttributes) {
        try {
            Operateur operateurEntity = new Operateur(operateur);
            operateurEntity.setIdOperateur(id);
            operateurService.save(operateurEntity);
            redirectAttributes.addFlashAttribute("success", "Opérateur modifié avec succès!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la modification de l'opérateur");
        }
        return "redirect:/operateurs";
    }

    @GetMapping("/operateurs/delete/{id}")
    public String deleteOperateur(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            operateurService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Opérateur supprimé avec succès!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression de l'opérateur");
        }
        return "redirect:/operateurs";
    }

    @GetMapping("/parametres")
    public String parametres(Model model) {
        List<Parametre> parametres = parametreService.findAll();
        model.addAttribute("parametres", parametres);
        return "parametres";
    }

    @GetMapping("/parametres/add")
    public String addParametreForm() {
        return "parametres_add";
    }

    @PostMapping("/parametres/save")
    public String saveParametre(@RequestParam Long idMatiere, @RequestParam BigDecimal difference,
                               @RequestParam Long idOperateur, @RequestParam Long idResolution,
                               RedirectAttributes redirectAttributes) {
        try {
            Parametre parametre = new Parametre(idMatiere, difference, idOperateur, idResolution);
            parametreService.save(parametre);
            redirectAttributes.addFlashAttribute("success", "Paramètre ajouté avec succès!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de l'ajout du paramètre");
        }
        return "redirect:/parametres";
    }

    @GetMapping("/parametres/edit/{id}")
    public String editParametreForm(@PathVariable Long id, Model model) {
        Parametre parametre = parametreService.findById(id)
                .orElseThrow(() -> new RuntimeException("Paramètre non trouvé"));
        model.addAttribute("parametre", parametre);
        return "parametres_edit";
    }

    @PostMapping("/parametres/update")
    public String updateParametre(@RequestParam Long id, @RequestParam Long idMatiere, 
                                @RequestParam BigDecimal difference, @RequestParam Long idOperateur, 
                                @RequestParam Long idResolution, RedirectAttributes redirectAttributes) {
        try {
            Parametre parametre = new Parametre(idMatiere, difference, idOperateur, idResolution);
            parametre.setIdParametre(id);
            parametreService.save(parametre);
            redirectAttributes.addFlashAttribute("success", "Paramètre modifié avec succès!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la modification du paramètre");
        }
        return "redirect:/parametres";
    }

    @GetMapping("/parametres/delete/{id}")
    public String deleteParametre(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            parametreService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Paramètre supprimé avec succès!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression du paramètre");
        }
        return "redirect:/parametres";
    }

    @GetMapping("/search/notes")
    public String searchNotes(@RequestParam(required = false) Long idCandidat, 
                             @RequestParam(required = false) Long idMatiere, 
                             Model model) {
        if (idCandidat != null && idMatiere != null) {
            try {
                List<Note> notes = noteService.findByCandidatAndMatiere(idCandidat, idMatiere);
                model.addAttribute("notes", notes);
                
                // Calculer la différence en utilisant le service
                // double difference = noteService.calculateDifference(idCandidat, idMatiere);
                // model.addAttribute("difference", String.format("%.2f", difference));

                double difference = noteService.calculateDifferenceSomme(idCandidat, idMatiere);
                model.addAttribute("difference", String.format("%.2f", difference));

                
                // Calculer la moyenne en utilisant le service
                double average = noteService.calculateAverage(idCandidat, idMatiere);
                model.addAttribute("average", String.format("%.2f", average));
                
                // Trouver la résolution basée sur la comparaison et calculer la note finale
                double noteFinale = noteService.getNoteFinaleWithZeroDifference(idCandidat, idMatiere);
                model.addAttribute("noteFinale", String.format("%.2f", noteFinale));
                
                Optional<Resolution> resolutionOpt = noteService.findResolutionByDifferenceComparison(idCandidat, idMatiere);
                if (resolutionOpt.isPresent()) {
                    model.addAttribute("resolution", resolutionOpt.get().getNom());
                }
            } catch (Exception e) {
                model.addAttribute("error", "Erreur lors de la recherche des notes");
            }
        }
        
        // Ajouter les paramètres de recherche pour le formulaire
        model.addAttribute("param", new java.util.HashMap<String, Long>() {{
            if (idCandidat != null) put("idCandidat", idCandidat);
            if (idMatiere != null) put("idMatiere", idMatiere);
        }});
        
        return "search_notes";
    }
}
