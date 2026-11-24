package tn.esprit.autoloc.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IEquipementRepository;

@Service
@RequiredArgsConstructor
public class EquipementServiceImpl implements IEquipementService {

    private final IEquipementRepository equipementRepository;

    @Override
    public Equipement create(Equipement equipement) {

        if (equipement.getLibelle() == null ||
                equipement.getLibelle().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Le libellé de l'équipement est obligatoire");
        }

        if (equipement.getQuantite() < 0) {

            throw new IllegalArgumentException(
                    "La quantité ne peut pas être négative");
        }

        return equipementRepository.save(equipement);
    }

    @Override
    public Equipement findById(Long id) {

        return equipementRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Equipement", id));
    }

    @Override
    public List<Equipement> findAll() {

        return equipementRepository.findAll();
    }

    @Override
    public Equipement update(Long id, Equipement equipement) {

        Equipement equipementExistant = equipementRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Equipement", id));

        if (equipement.getLibelle() == null ||
                equipement.getLibelle().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Le libellé de l'équipement est obligatoire");
        }

        if (equipement.getQuantite() < 0) {

            throw new IllegalArgumentException(
                    "La quantité ne peut pas être négative");
        }

        equipementExistant.setLibelle(equipement.getLibelle());
        equipementExistant.setQuantite(equipement.getQuantite());

        return equipementRepository.save(equipementExistant);
    }

    @Override
    public void deleteById(Long id) {

        Equipement equipement = equipementRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Equipement", id));

        equipementRepository.delete(equipement);
    }
}