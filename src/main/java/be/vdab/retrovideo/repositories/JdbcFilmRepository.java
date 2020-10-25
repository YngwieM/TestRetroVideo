package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.domain.Genre;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
 class JdbcFilmRepository implements FilmRepository{
    private final JdbcTemplate template;
    JdbcFilmRepository(JdbcTemplate template) {

        this.template = template;
    }

    @Override
    public List<Film> findAll() {
        var sql = "select id,genreid, titel, voorraad, gereserveerd,prijs from films order by id ";
        return template.query(sql,filmRowMapper);
    }

    private final RowMapper<Film> filmRowMapper = (result, rowNum) ->
            new Film(result.getLong("id"), result.getLong("genreid"),
                    result.getString("titel"),result.getInt("voorraad"),
                    result.getInt("gereserveerd"),result.getBigDecimal("prijs"));

    @Override
    public Optional<Film> findById(long id) {
        try {
            var sql ="select id,genreid, titel, voorraad, gereserveerd,prijs from films where id = ?";
            return Optional.of(template.queryForObject(sql, filmRowMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }}

    @Override
    public Optional<Film> findByGenreId(long id) {
        try {
            var sql ="select id,genreid, titel, voorraad, gereserveerd,prijs from films where genreid = ?";
            return Optional.of(template.queryForObject(sql, filmRowMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }}

    @Override
    public List<Film> findByIds(Set<Long> ids) {
        if (ids.isEmpty()) {
            return List.of();
        }
        var sql = "select id,genreid, titel, voorraad, gereserveerd,prijs from films where id in (" + "?,".repeat(ids.size() - 1) +"?) order by id";
        return template.query(sql, ids.toArray(), filmRowMapper);
    }
}
