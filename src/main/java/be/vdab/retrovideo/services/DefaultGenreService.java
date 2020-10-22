package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Genre;
import be.vdab.retrovideo.repositories.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
class DefaultGenreService implements GenreService {
    private final GenreRepository genreRepository;

   DefaultGenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }


    @Override
    public List<Genre> findAll() {
        return  genreRepository.findAll();

    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Genre> findById(long id) {
        return genreRepository.findById(id);
    }
}
