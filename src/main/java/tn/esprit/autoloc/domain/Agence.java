package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name="Agence")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;
    private String nom;
    private String ville;
    private String adresse;
    private String telephone;
    @OneToMany(mappedBy = "agence",fetch = FetchType.LAZY)
    private List<Vehicule> vehicules =new ArrayList<>();
    @OneToMany(mappedBy = "agence",fetch = FetchType.LAZY)
    private List<Equipement> equipements =new ArrayList<>();
}
