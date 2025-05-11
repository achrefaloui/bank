package com.fst.bank.controllers;
import com.fst.bank.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import com.fst.bank.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class BankController {
    
    @Autowired
    private CompteRepository compteRepository;
    
    /*
    @GetMapping("/")
    public String index() {
        return "redirect:/comptes";
    }
    */
    @GetMapping("/")
    public String redirectToComptes() {
        return "redirect:/comptes";
    }
    
    @GetMapping("/comptes")
    public String listeComptes(Model model) {
        model.addAttribute("comptes", compteRepository.findAll());
        return "listeComptes";
    }

    
    @GetMapping("/ajouter")
    public String formAjout(Model model) {
        model.addAttribute("compte", new Compte());
        return "ajouterCompte";
    }

    @PostMapping("/ajouter")
    public String ajouter(@ModelAttribute Compte compte) {
        compteRepository.save(compte);
        return "redirect:/comptes";
    }

    @GetMapping("/compte/{id}")
    public String details(@PathVariable int id, Model model) {
        Compte c = compteRepository.findById(id).orElse(null);
        model.addAttribute("compte", c);
        return "detailsCompte";
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable int id) {
        compteRepository.deleteById(id);
        return "redirect:/comptes";
    }

    @PostMapping("/depot/{id}")
    public String depot(@PathVariable int id, @RequestParam double montant) {
        Compte c = compteRepository.findById(id).orElse(null);
        if (c != null) {
            c.deposer(montant);
            compteRepository.save(c);
        }
        return "redirect:/compte/" + id;
    }

    @PostMapping("/retrait/{id}")
    public String retrait(@PathVariable int id, @RequestParam double montant) {
        Compte c = compteRepository.findById(id).orElse(null);
        if (c != null) {
            c.retirer(montant);
            compteRepository.save(c);
        }
        return "redirect:/compte/" + id;
    }
}