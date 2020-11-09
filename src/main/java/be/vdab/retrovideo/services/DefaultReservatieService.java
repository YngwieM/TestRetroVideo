package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Reservatie;
import be.vdab.retrovideo.repositories.FilmRepository;
import be.vdab.retrovideo.repositories.ReservatieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
 class DefaultReservatieService implements ReservatieService {
    private final ReservatieRepository reservatieRepository;
    private final FilmRepository filmRepository;

   DefaultReservatieService(ReservatieRepository reservatieRepository, FilmRepository filmRepository){
        this.reservatieRepository = reservatieRepository;
       this.filmRepository = filmRepository;
   }

    @Override
    public long create(Reservatie reservatie) {
       filmRepository.update(reservatie.getFilmId());
        return reservatieRepository.create(reservatie);
    }
}
