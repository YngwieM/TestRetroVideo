package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Klant;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
 class JdbcKlantRepository implements KlantRepository {
    private final JdbcTemplate template;
    JdbcKlantRepository(JdbcTemplate template) {
        this.template = template;

    }

    private final RowMapper<Klant> klantRowMapper = (result, rowNum) -> new Klant(
            result.getLong("id"), result.getString("voornaam"),
            result.getString("familienaam"), result.getString("straatNummer"),
            result.getString("postcode"), result.getString("gemeente"));

    @Override
    public Optional<Klant> findById(long id) {
        try {
            var sql = "select id, familienaam, voornaam, straatnummer, postcode, gemeente from klanten id = ?";
            return Optional.of(template.queryForObject(sql, klantRowMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }
    @Override
    public List<Klant> findByBeginNaam(String beginNaam) {
        var sql = "select  id, familienaam, voornaam, straatnummer, postcode, gemeente from klanten where familienaam like ? order by voornaam";
        return template.query(sql,klantRowMapper,beginNaam + '%');
    }
}
