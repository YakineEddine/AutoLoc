package tn.esprit.autoloc.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Démonstration de Scope Prototype :
 * Une NOUVELLE instance est créée par le conteneur Spring à chaque demande / injection.
 */
@Component
@Scope("prototype")
public class AuditLogService {
    private final long instanceId = System.nanoTime();

    public long getInstanceId() {
        return instanceId;
    }
}
