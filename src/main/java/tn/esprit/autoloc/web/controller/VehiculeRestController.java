package tn.esprit.autoloc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

/**
 * Annotation stéréotype @RestController :
 * Spécialisation de @Component pour les services web RESTful.
 * Combine @Controller et @ResponseBody.
 *
 * Couplage Faible (DI par constructeur) :
 * Le contrôleur dépend de l'INTERFACE "IVehiculeService" et non de l'implémentation concrète.
 */
@RestController
@RequestMapping("/api/vehicules")
public class VehiculeRestController {

    private final IVehiculeService vehiculeService;

    @Autowired
    public VehiculeRestController(IVehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @GetMapping
    public List<Vehicule> getTousLesVehicules() {
        return vehiculeService.recupererTousVehicules();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> getVehiculeParId(@PathVariable Long id) {
        return vehiculeService.recupererVehiculeParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Vehicule> ajouterVehicule(@RequestBody Vehicule vehicule) {
        Vehicule nouveau = vehiculeService.ajouterVehicule(vehicule);
        return new ResponseEntity<>(nouveau, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Vehicule> modifierVehicule(@RequestBody Vehicule vehicule) {
        return ResponseEntity.ok(vehiculeService.modifierVehicule(vehicule));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerVehicule(@PathVariable Long id) {
        vehiculeService.supprimerVehicule(id);
        return ResponseEntity.noContent().build();
    }
}
