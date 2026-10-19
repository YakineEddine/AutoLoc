package tn.esprit.autoloc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientRestController {

    private final IClientService clientService;

    @Autowired
    public ClientRestController(IClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getTousLesClients() {
        return clientService.recupererTousClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientParId(@PathVariable Long id) {
        return clientService.recupererClientParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Client> ajouterClient(@RequestBody Client client) {
        Client nouveau = clientService.ajouterClient(client);
        return new ResponseEntity<>(nouveau, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Client> modifierClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.modifierClient(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerClient(@PathVariable Long id) {
        clientService.supprimerClient(id);
        return ResponseEntity.noContent().build();
    }
}
