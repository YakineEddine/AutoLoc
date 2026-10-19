package tn.esprit.autoloc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.EquipementRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;

/**
 * Classe pédagogique illustrant les 3 techniques d'injection de dépendances abordées en cours :
 * 1. Injection par Constructeur (recommandé pour dépendances obligatoires)
 * 2. Injection par Setter (adapté pour dépendances optionnelles)
 * 3. Injection par Attribut / Champ avec @Autowired
 */
@Service
public class DiDemonstrationService {

    // 1. Dépendance par Constructeur (Obligatoire & final)
    private final VehiculeRepository vehiculeRepository;

    // 2. Dépendance par Setter (Optionnelle)
    private AgenceRepository agenceRepository;

    // 3. Dépendance par Champ / Field (Directe via @Autowired)
    @Autowired
    private EquipementRepository equipementRepository;

    @Autowired
    public DiDemonstrationService(VehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }

    @Autowired
    public void setAgenceRepository(AgenceRepository agenceRepository) {
        this.agenceRepository = agenceRepository;
    }

    public boolean testerInjections() {
        return (vehiculeRepository != null) && (agenceRepository != null) && (equipementRepository != null);
    }
}
