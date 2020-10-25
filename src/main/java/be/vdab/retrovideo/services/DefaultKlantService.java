package be.vdab.retrovideo.services;


import be.vdab.retrovideo.domain.Klant;
import be.vdab.retrovideo.repositories.KlantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
 class DefaultKlantService implements KlantService {
    private final KlantRepository klantRepository;

    DefaultKlantService(KlantRepository klantRepository) {
        this.klantRepository = klantRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Klant> findById(long id) {
        return klantRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Klant> findByBeginNaam(String beginNaam) {
        return klantRepository.findByBeginNaam(beginNaam);
    }
}
