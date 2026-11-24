package tn.esprit.autoloc.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IAgenceRepository;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgenceService {

    private final IAgenceRepository agenceRepository;

    @Override
    public Agence create(Agence agence) {

        // Vérifier que le nom est obligatoire
        if (agence.getNom() == null || agence.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Le nom de l'agence est obligatoire");
        }

        return agenceRepository.save(agence);
    }

    @Override
    public Agence findById(Long id) {

        return agenceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Agence", id));
    }

    @Override
    public List<Agence> findAll() {

        return agenceRepository.findAll();
    }

    @Override
    public Agence update(Long id, Agence agence) {

        Agence agenceExistante = agenceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Agence", id));

        // Vérifier que le nom est obligatoire
        if (agence.getNom() == null || agence.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Le nom de l'agence est obligatoire");
        }

        agenceExistante.setNom(agence.getNom());

        return agenceRepository.save(agenceExistante);
    }



    @Override
    public void delete(Long id) {

        Agence agence = agenceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Agence", id));

        agenceRepository.delete(agence);
    }
}