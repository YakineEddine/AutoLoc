package tn.esprit.autoloc.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Démonstration de Scope Singleton (par défaut dans Spring) :
 * Une seule instance unique est créée et partagée dans tout le conteneur IoC.
 */
@Component
@Scope("singleton")
public class ApplicationInfoService {
    private final long creationTimestamp = System.currentTimeMillis();

    public String getInfo() {
        return "AutoLoc API v1.0 - Portée Singleton (Créé à: " + creationTimestamp + ")";
    }
}
