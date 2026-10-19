package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Client;
import java.util.List;
import java.util.Optional;

public interface IClientService {
    Client ajouterClient(Client client);
    Client modifierClient(Client client);
    List<Client> recupererTousClients();
    Optional<Client> recupererClientParId(Long id);
    void supprimerClient(Long id);
}
