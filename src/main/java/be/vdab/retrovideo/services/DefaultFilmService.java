package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.repositories.FilmRepository;
import be.vdab.retrovideo.repositories.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
 class DefaultFilmService implements FilmService {
    private final FilmRepository filmRepository;

    DefaultFilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public List<Film> findAll(){return filmRepository.findAll();}

    @Override
    public Optional<Film> findById(long id) {
        return filmRepository.findById(id);
    }

    @Override
    public Optional<Film> findByGenreId(long id) {
        return filmRepository.findByGenreId(id);
    }
}




