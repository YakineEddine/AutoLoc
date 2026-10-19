package tn.esprit.autoloc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.List;
import java.util.Optional;

/**
 * Annotation stéréotype @Service :
 * Indique à Spring qu'il s'agit d'un composant de la couche métier (Bean Spring géré par l'IoC).
 *
 * Injection de Dépendances par Constructeur (Méthode la plus recommandée) :
 * - Dépendance déclarée en "private final" pour garantir l'immuabilité.
 * - Aucune dépendance cachée, facilite grandement les tests unitaires avec Mockito.
 * - Depuis Spring 4.3, l'annotation @Autowired sur le constructeur unique est implicite.
 */
@Service
public class VehiculeServiceImpl implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;

    // Injection par constructeur (recommandée par Spring & UP ASI)
    @Autowired
    public VehiculeServiceImpl(VehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }

    @Override
    public Vehicule ajouterVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule modifierVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public List<Vehicule> recupererTousVehicules() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Optional<Vehicule> recupererVehiculeParId(Long id) {
        return vehiculeRepository.findById(id);
    }

    @Override
    public void supprimerVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }
}
