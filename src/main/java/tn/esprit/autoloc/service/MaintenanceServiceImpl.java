package tn.esprit.autoloc.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IMaintenanceRepository;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final IMaintenanceRepository maintenanceRepository;

    @Override
    public Maintenance create(Maintenance maintenance) {

        if (maintenance.getDateDebut() != null &&
                maintenance.getDateFin() != null &&
                maintenance.getDateFin().isBefore(maintenance.getDateDebut())) {

            throw new IllegalArgumentException(
                    "La date de fin ne peut pas être antérieure à la date de début");
        }

        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance findById(Long id) {

        return maintenanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Maintenance", id));
    }

    @Override
    public List<Maintenance> findAll() {

        return maintenanceRepository.findAll();
    }

    @Override
    public Maintenance update(Long id, Maintenance maintenance) {

        Maintenance maintenanceExistante = maintenanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Maintenance", id));

        if (maintenance.getDateDebut() != null &&
                maintenance.getDateFin() != null &&
                maintenance.getDateFin().isBefore(maintenance.getDateDebut())) {

            throw new IllegalArgumentException(
                    "La date de fin ne peut pas être antérieure à la date de début");
        }

        maintenanceExistante.setDateDebut(maintenance.getDateDebut());
        maintenanceExistante.setDateFin(maintenance.getDateFin());
        maintenanceExistante.setDescription(maintenance.getDescription());
        maintenanceExistante.setVehicule(maintenance.getVehicule());

        return maintenanceRepository.save(maintenanceExistante);
    }

    @Override
    public void delete(Long id) {

        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Maintenance", id));

        maintenanceRepository.delete(maintenance);
    }
}