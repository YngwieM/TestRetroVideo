package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.repositories.FilmRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public List<Film> findByGenreId(long id) {
        return filmRepository.findByGenreId(id);
    }

    @Override
    public List<Film> findByIds(Set<Long> ids) {
        return filmRepository.findByIds(ids);
    }

    @Override
    public void update(long id) {filmRepository.update(id);}
}




