package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

/**
 * Integration SpringDoc OpenAPI :
 * @Tag : Regroupe et decrit l'ensemble des endpoints relatifs aux clients
 */
@Tag(name = "Gestion des clients", description = "API pour la gestion des clients et locataires AutoLoc")
@RestController
@RequestMapping("/api/clients")
public class ClientRestController {

    private final IClientService clientService;

    @Autowired
    public ClientRestController(IClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(description = "Recuperer tous les clients enregistres")
    @GetMapping
    public List<Client> getTousLesClients() {
        return clientService.recupererTousClients();
    }

    @Operation(description = "Recuperer un client par son identifiant unique ID")
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientParId(@PathVariable Long id) {
        return clientService.recupererClientParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(description = "Enregistrer un nouveau client dans la base de donnees")
    @PostMapping
    public ResponseEntity<Client> ajouterClient(@RequestBody Client client) {
        Client nouveau = clientService.ajouterClient(client);
        return new ResponseEntity<>(nouveau, HttpStatus.CREATED);
    }

    @Operation(description = "Mettre a jour les donnees d'un client existant")
    @PutMapping
    public ResponseEntity<Client> modifierClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.modifierClient(client));
    }

    @Operation(description = "Supprimer un client a partir de son identifiant")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerClient(@PathVariable Long id) {
        clientService.supprimerClient(id);
        return ResponseEntity.noContent().build();
    }
}
