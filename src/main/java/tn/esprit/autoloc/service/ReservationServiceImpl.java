package tn.esprit.autoloc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.repository.ReservationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation creerReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> recupererToutesReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> recupererReservationParId(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public void annulerReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
