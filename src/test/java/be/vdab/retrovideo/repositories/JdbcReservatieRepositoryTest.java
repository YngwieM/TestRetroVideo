package be.vdab.retrovideo.repositories;


import be.vdab.retrovideo.domain.Reservatie;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(JdbcReservatieRepository.class)
@Sql({"/insertKlanten.sql", "/insertGenres.sql","/insertFilms.sql"})
 class JdbcReservatieRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final JdbcReservatieRepository repository;
    private static final String RESERVATIES = "reservaties";

    JdbcReservatieRepositoryTest(JdbcReservatieRepository repository) {
        this.repository = repository;
    }

    private long idVanTestKlant() {
        return super.jdbcTemplate.queryForObject("select id from klanten where voornaam='test'", Long.class);
    }

    private long idVanTestFilm() {
        return super.jdbcTemplate.queryForObject("select id from films where titel='test'", Long.class);
    }


    @Test
    void create() {
        var idTestKlant = idVanTestKlant();
        var idTestFilm = idVanTestFilm();
        repository.create(new Reservatie(idTestKlant, idTestFilm, LocalDate.now()));
        assertThat(this.countRowsInTableWhere(RESERVATIES, "klantid=" + idTestKlant + " and filmid=" + idTestFilm)).isOne();
    }

}



