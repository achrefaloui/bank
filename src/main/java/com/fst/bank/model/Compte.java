package com.fst.bank.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comptes")
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulaire;
    private Double solde;
    
        public Compte() {
    }

    // Constructeur avec paramètres
    public Compte(String titulaire, double solde) {
        this.titulaire = titulaire;
        this.solde = solde;
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulaire() {
        return titulaire;
    }

    public void setTitulaire(String titulaire) {
        this.titulaire = titulaire;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

  
    public void deposer(double montant) {
        solde += montant;
    }
    
    public void retirer(double montant) {
        solde -= montant;
    }
    
}