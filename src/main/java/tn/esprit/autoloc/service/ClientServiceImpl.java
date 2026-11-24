package tn.esprit.autoloc.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IClientRepository;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;

    @Override
    public Client create(Client client) {

        if (client.getNom() == null ||
                client.getNom().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Le nom du client est obligatoire");
        }

        if (client.getEmail() == null ||
                client.getEmail().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "L'email du client est obligatoire");
        }

        return clientRepository.save(client);
    }

    @Override
    public Client findById(Long id) {

        return clientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Client", id));
    }

    @Override
    public List<Client> findAll() {

        return clientRepository.findAll();
    }

    @Override
    public Client update(Long id, Client client) {

        Client clientExistant = clientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Client", id));

        if (client.getNom() == null ||
                client.getNom().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Le nom du client est obligatoire");
        }

        if (client.getEmail() == null ||
                client.getEmail().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "L'email du client est obligatoire");
        }

        clientExistant.setNom(client.getNom());
        clientExistant.setEmail(client.getEmail());

        return clientRepository.save(clientExistant);
    }

    @Override
    public void deleteById(Long id) {

        Client client = clientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Client", id));

        clientRepository.delete(client);
    }
}