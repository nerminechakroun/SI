package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="Reservation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
     @Id
     @GeneratedValue(strategy= GenerationType.IDENTITY)
     private Long idReservation;
     private LocalDate dateDebut;
     private LocalDate dateFin;
     @Enumerated(EnumType.STRING)
     private StatutReservation statut;
     @OneToOne(mappedBy = "reservation",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
     private Contrat contrat;
     @ManyToOne(fetch = FetchType.LAZY)
     private Client client;
     @ManyToOne(fetch = FetchType.LAZY)
     private Vehicule vehicule;
}
