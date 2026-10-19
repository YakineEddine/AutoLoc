package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Reservation;
import java.util.List;
import java.util.Optional;

public interface IReservationService {
    Reservation creerReservation(Reservation reservation);
    List<Reservation> recupererToutesReservations();
    Optional<Reservation> recupererReservationParId(Long id);
    void annulerReservation(Long id);
}
