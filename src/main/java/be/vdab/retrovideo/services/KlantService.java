package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Klant;

import java.util.List;
import java.util.Optional;

public interface KlantService {
    Optional<Klant> findById(long id);
    List<Klant> findByBeginNaam(String beginNaam);
}
