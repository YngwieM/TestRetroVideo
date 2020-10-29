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
@Sql("/insertReservaties.sql")
 class JdbcReservatieRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final JdbcReservatieRepository repository;
    private static final String RESERVATIES = "reservaties";

    JdbcReservatieRepositoryTest(JdbcReservatieRepository repository) {
        this.repository = repository;
    }

    @Test
    void create() {
        var id = repository.create(new Reservatie(0,10,LocalDate.now()));
        assertThat(id).isPositive();
        assertThat(super.countRowsInTableWhere(RESERVATIES, "klantid=" + id)).isOne();
    }
}
//test fails nog


//long create (Reservatie reservatie);