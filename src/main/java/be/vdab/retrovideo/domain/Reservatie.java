package be.vdab.retrovideo.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class Reservatie {
    @NotNull
    private final long klantId;
    @NotNull
    private final long filmId;
    @NotNull
    private final LocalDate reservatie;

    public Reservatie(@NotNull long klantId, @NotNull long filmId, @NotNull LocalDate reservatie) {
        this.klantId = klantId;
        this.filmId = filmId;
        this.reservatie = reservatie;
    }

    public long getKlantId() {
        return klantId;
    }

    public long getFilmId() {
        return filmId;
    }

    public LocalDate getReservatie() {
        return reservatie;
    }
}
