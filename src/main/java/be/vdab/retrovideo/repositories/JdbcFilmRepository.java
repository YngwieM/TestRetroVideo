package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.exceptions.FilmUitVoorraadException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.*;

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
            Optional<Film> films = Optional.of(template.queryForObject(sql, filmRowMapper, id));
            return films;
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }}

    @Override
    public List<Film> findByGenreId(long id) {
        try {
            var sql ="select id,genreid, titel, voorraad, gereserveerd,prijs from films where genreid = ?";
            List<Film> films = template.query(sql, filmRowMapper, id);

            return films;
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Collections.emptyList();
        }}

    @Override
    public List<Film> findByIds(Set<Long> ids) {
        if (ids.isEmpty()) {
            return List.of();
        }
        var sql = "select id,genreid, titel, voorraad, gereserveerd,prijs from films where id in (" + "?,".repeat(ids.size() - 1) +"?) order by id";
        return template.query(sql, ids.toArray(), filmRowMapper);
    }

    @Override
    public void update(long id) {
        var sql = "update films set voorraad = voorraad - 1, gereserveerd = gereserveerd + 1 where id = ?";
        template.update(sql,id);


    }
}
