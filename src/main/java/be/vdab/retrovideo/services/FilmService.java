package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Film;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FilmService {
    List<Film> findAll();
  Optional<Film> findById(long id);
    List<Film> findByGenreId(long id);
    List<Film> findByIds(Set<Long> ids);
    void update(long id);
    void controle (int voorraad, int gereserveerd);
}
