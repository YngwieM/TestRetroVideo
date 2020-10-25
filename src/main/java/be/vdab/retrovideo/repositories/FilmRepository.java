package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.domain.Genre;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FilmRepository {
    List<Film> findAll();
    Optional<Film> findById(long id);
    Optional<Film> findByGenreId(long id);
    List<Film> findByIds(Set<Long> ids);
}
