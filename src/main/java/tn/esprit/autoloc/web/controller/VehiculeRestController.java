package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

/**
 * Integration SpringDoc OpenAPI :
 * @Tag : Regroupe et decrit l'ensemble des endpoints de cette ressource dans Swagger UI
 */
@Tag(name = "Gestion des vehicules", description = "API pour la gestion des vehicules du parc AutoLoc")
@RestController
@RequestMapping("/api/vehicules")
public class VehiculeRestController {

    private final IVehiculeService vehiculeService;

    @Autowired
    public VehiculeRestController(IVehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @Operation(description = "Recuperer la liste de tous les vehicules de la base de donnees")
    @GetMapping
    public List<Vehicule> getTousLesVehicules() {
        return vehiculeService.recupererTousVehicules();
    }

    @Operation(description = "Recuperer un vehicule par son identifiant unique ID")
    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> getVehiculeParId(@PathVariable Long id) {
        return vehiculeService.recupererVehiculeParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(description = "Ajouter un nouveau vehicule dans le systeme")
    @PostMapping
    public ResponseEntity<Vehicule> ajouterVehicule(@RequestBody Vehicule vehicule) {
        Vehicule nouveau = vehiculeService.ajouterVehicule(vehicule);
        return new ResponseEntity<>(nouveau, HttpStatus.CREATED);
    }

    @Operation(description = "Mettre a jour les informations d'un vehicule existant")
    @PutMapping
    public ResponseEntity<Vehicule> modifierVehicule(@RequestBody Vehicule vehicule) {
        return ResponseEntity.ok(vehiculeService.modifierVehicule(vehicule));
    }

    @Operation(description = "Supprimer un vehicule par son identifiant")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerVehicule(@PathVariable Long id) {
        vehiculeService.supprimerVehicule(id);
        return ResponseEntity.noContent().build();
    }
}
