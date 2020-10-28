package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Reservatie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
 class JdbcReservatieRepository implements ReservatieRepository {
    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;

    JdbcReservatieRepository(JdbcTemplate template) {
        this.template = template;
        this.insert = new SimpleJdbcInsert(template)
                .withTableName("reservaties")
                .usingColumns("klantid");

    }

    @Override
    public long create(Reservatie reservatie) {
        var kolomWaarden = Map.of("klantid", reservatie.getKlantId(),
                "filmId", reservatie.getFilmId(),
                "reservatie", reservatie.getReservatie());
        var id = insert.executeAndReturnKey(kolomWaarden);
        return id.longValue();
    }


}
