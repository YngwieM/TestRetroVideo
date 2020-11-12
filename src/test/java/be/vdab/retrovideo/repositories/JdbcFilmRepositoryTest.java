package be.vdab.retrovideo.repositories;


import be.vdab.retrovideo.domain.Film;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(JdbcFilmRepository.class)
@Sql("/insertFilms.sql")
public class JdbcFilmRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    private final JdbcFilmRepository repository;

    JdbcFilmRepositoryTest(JdbcFilmRepository repository) {
        this.repository = repository;
    }

    private long idVanTestFilm() {
        return super.jdbcTemplate.queryForObject(
                "select id from films where titel='test'", Long.class);
    }

    private long idVanTestFilm2() {
        return super.jdbcTemplate.queryForObject(
                "select id from films where titel='test2'", Long.class);
    }
    @Test
    void findById() {
        assertThat(repository.findById(idVanTestFilm()).get().getTitel()).isEqualTo("test");
    }
    @Test
    void findByOnbestaandeIdVindtGeenFilm() {
        assertThat(repository.findById(-1)).isNotPresent();
    }

    @Test
    void update() {
        var id = idVanTestFilm();
        repository.update(id);
        assertThat(super.jdbcTemplate.queryForObject(
                "select gereserveerd from films where id=?", BigDecimal.class, id)).isOne();
    }
    @Test
    void findByIds() {
        long id1 = idVanTestFilm();
        long id2 = idVanTestFilm2();
        assertThat(repository.findByIds(Set.of(id1, id2)))
                .extracting(film->film.getId()).containsOnly(id1, id2).isSorted();
    }




}


//    List<Film> findAll();
//    List<Film> findByGenreId(long id);

