package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;
import java.util.List;
import java.util.Optional;

/**
 * Couplage Faible (Loose Coupling) :
 * Définition du contrat d'interface métier sans dépendre de l'implémentation concrète.
 */
public interface IVehiculeService {
    Vehicule ajouterVehicule(Vehicule vehicule);
    Vehicule modifierVehicule(Vehicule vehicule);
    List<Vehicule> recupererTousVehicules();
    Optional<Vehicule> recupererVehiculeParId(Long id);
    void supprimerVehicule(Long id);
}
