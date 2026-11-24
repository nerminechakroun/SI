package tn.esprit.autoloc.service;

import java.math.BigDecimal;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IVehiculeRepository;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;

    @Override
    public Vehicule create(Vehicule vehicule) {

        if (vehicule.getTarifJournalier() == null ||
                vehicule.getTarifJournalier().compareTo(BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "Le tarif journalier ne peut pas être négatif");
        }

        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule findById(Long id) {

        return vehiculeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vehicule", id));
    }

    @Override
    public List<Vehicule> findAll() {

        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule update(Long id, Vehicule vehicule) {

        Vehicule vehiculeExistant = vehiculeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vehicule", id));

        if (vehicule.getTarifJournalier() == null ||
                vehicule.getTarifJournalier().compareTo(BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "Le tarif journalier ne peut pas être négatif");
        }

        vehiculeExistant.setImmatriculation(vehicule.getImmatriculation());
        vehiculeExistant.setModele(vehicule.getModele());
        vehiculeExistant.setMarque(vehicule.getMarque());
        vehiculeExistant.setTarifJournalier(vehicule.getTarifJournalier());
        vehiculeExistant.setStatut(vehicule.getStatut());
        vehiculeExistant.setCategorie(vehicule.getCategorie());
        vehiculeExistant.setAgence(vehicule.getAgence());

        return vehiculeRepository.save(vehiculeExistant);
    }
    @Override
    public void delete(Long id) {

        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vehicule", id));

        vehiculeRepository.delete(vehicule);
    }
}