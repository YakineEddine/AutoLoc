package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.domain.Agence;

/**
 * Annotation stéréotype @Repository :
 * Indique à Spring que cette interface appartient à la couche d'accès aux données.
 * Le conteneur Spring IoC crée automatiquement un Bean proxy implémentant JpaRepository.
 */
@Repository
public interface AgenceRepository extends JpaRepository<Agence, Long> {
}
