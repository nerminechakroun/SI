package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Vehicule")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicule {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicule;
   private String immatriculation;
   private String modele;
   private String marque;
   private BigDecimal tarifJournalier;
   @Enumerated(EnumType.STRING)
   private StatutVehicule statut;
   @Enumerated(EnumType.STRING)
   private CategorieVehicule categorie;
   @OneToMany(mappedBy = "vehicule" , fetch = FetchType.LAZY)
   private List<Reservation> reservations = new ArrayList<>();
   @ManyToMany
   Set<Equipement> equipements =new HashSet<>();
   @ManyToOne(fetch = FetchType.LAZY)
   private Agence agence;

}
