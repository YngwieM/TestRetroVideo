package be.vdab.retrovideo.repositories;


import be.vdab.retrovideo.domain.Klant;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(JdbcKlantRepository.class)
@Sql("/insertKlanten.sql")
public class JdbcKlantRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final JdbcKlantRepository repository;

    JdbcKlantRepositoryTest(JdbcKlantRepository repository) {
        this.repository = repository;
    }

    private long idVanTestKlant() {
        return super.jdbcTemplate.queryForObject(
                "select id from klanten where familienaam='test'", Long.class);
    }

    @Test
    void findById() {
        assertThat(repository.findById(idVanTestKlant()).get().getFamilienaam()).isEqualTo("test");
    }
    @Test
    void findByOnbestaandeIdVindtGeenKlant() {
        assertThat(repository.findById(-1)).isNotPresent();
    }
    @Test
    void findByBeginNaam() {
        assertThat(repository.findByBeginNaam("t"))
                .hasSize(super.jdbcTemplate.queryForObject(
                        "select count(*) from klanten where familienaam like 't%'", Integer.class))
                .extracting(klant -> klant.getNaam().toLowerCase())
                .allSatisfy(naam -> assertThat(naam.startsWith("t")))
                .isSorted();
    }
}

