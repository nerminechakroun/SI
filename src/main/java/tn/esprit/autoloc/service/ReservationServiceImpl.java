package tn.esprit.autoloc.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IReservationRepository;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;

    @Override
    public Reservation create(Reservation reservation) {

        if (reservation.getDateDebut() != null &&
                reservation.getDateFin() != null &&
                reservation.getDateFin().isBefore(reservation.getDateDebut())) {

            throw new IllegalArgumentException(
                    "La date de fin ne peut pas être antérieure à la date de début");
        }

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation findById(Long id) {

        return reservationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reservation", id));
    }

    @Override
    public List<Reservation> findAll() {

        return reservationRepository.findAll();
    }

    @Override
    public Reservation update(Long id, Reservation reservation) {

        Reservation reservationExistante = reservationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reservation", id));

        if (reservation.getDateDebut() != null &&
                reservation.getDateFin() != null &&
                reservation.getDateFin().isBefore(reservation.getDateDebut())) {

            throw new IllegalArgumentException(
                    "La date de fin ne peut pas être antérieure à la date de début");
        }

        reservationExistante.setDateDebut(reservation.getDateDebut());
        reservationExistante.setDateFin(reservation.getDateFin());
        reservationExistante.setStatut(reservation.getStatut());
        reservationExistante.setClient(reservation.getClient());
        reservationExistante.setVehicule(reservation.getVehicule());

        return reservationRepository.save(reservationExistante);
    }

    @Override
    public void delete(Long id) {

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reservation", id));

        reservationRepository.delete(reservation);
    }
}