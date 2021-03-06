package be.vdab.retrovideo.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(JdbcGenreRepository.class)
@Sql("/insertGenres.sql")
class JdbcGenreRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
private final JdbcGenreRepository repository;

    JdbcGenreRepositoryTest(JdbcGenreRepository repository) {
        this.repository = repository;
    }

    private long idVanTestGenre() {
        return super.jdbcTemplate.queryForObject(
                "select id from genres where naam='test'", Long.class);
    }
    @Test
    void findById() {
        assertThat(repository.findById(idVanTestGenre()).get().getNaam()).isEqualTo("test");
    }
    @Test
    void findByOnbestaandeIdVindtGeenGenre() {
        assertThat(repository.findById(-1)).isNotPresent();
    }
}