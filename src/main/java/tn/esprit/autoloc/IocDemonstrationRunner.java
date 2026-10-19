package tn.esprit.autoloc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.*;

import java.math.BigDecimal;

/**
 * Composant de démonstration au démarrage :
 * Valide le bon fonctionnement de l'IoC, de la DI et des Portées (Scopes).
 */
@Component
public class IocDemonstrationRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(IocDemonstrationRunner.class);

    private final IVehiculeService vehiculeService;
    private final DiDemonstrationService diDemonstrationService;
    private final ApplicationContext applicationContext;

    @Autowired
    public IocDemonstrationRunner(IVehiculeService vehiculeService,
                                  DiDemonstrationService diDemonstrationService,
                                  ApplicationContext applicationContext) {
        this.vehiculeService = vehiculeService;
        this.diDemonstrationService = diDemonstrationService;
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) {
        log.info("==================================================================================");
        log.info(">>> DEMONSTRATION DU CHAPITRE : INVERSION OF CONTROL (IoC) & DEPENDENCY INJECTION");
        log.info("==================================================================================");

        // 1. Validation de l'Injection de Dépendances (Constructeur, Setter, Attribut)
        boolean diOk = diDemonstrationService.testerInjections();
        log.info("[1] Validation des 3 modes d'injection (Constructeur, Setter, Attribut) : {}", diOk ? "SUCCÈS" : "ÉCHEC");

        // 2. Démonstration des Scopes (Singleton vs Prototype)
        ApplicationInfoService single1 = applicationContext.getBean(ApplicationInfoService.class);
        ApplicationInfoService single2 = applicationContext.getBean(ApplicationInfoService.class);
        log.info("[2.A] Scope Singleton : Instance 1 HashCode = {} | Instance 2 HashCode = {} -> (Même Bean ? {})",
                System.identityHashCode(single1), System.identityHashCode(single2), single1 == single2);

        AuditLogService proto1 = applicationContext.getBean(AuditLogService.class);
        AuditLogService proto2 = applicationContext.getBean(AuditLogService.class);
        log.info("[2.B] Scope Prototype : Instance 1 HashCode = {} | Instance 2 HashCode = {} -> (Nouvelle instance ? {})",
                System.identityHashCode(proto1), System.identityHashCode(proto2), proto1 != proto2);

        // 3. Test de service injecté (Couplage Faible)
        if (vehiculeService.recupererTousVehicules().isEmpty()) {
            Vehicule v = new Vehicule();
            v.setImmatriculation("250-TN-1000");
            v.setMarque("Renault");
            v.setModele("Clio 5");
            v.setCategorie(CategorieVehicule.CITADINE);
            v.setStatut(StatutVehicule.DISPONIBLE);
            v.setTarifJournalier(new BigDecimal("90.00"));
            vehiculeService.ajouterVehicule(v);
            log.info("[3] Véhicule inséré via IVehiculeService injecté : {}", v.getImmatriculation());
        }

        log.info("==================================================================================");
    }
}
