package be.vdab.retrovideo.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class Reservatie {
    @NotNull
    private final int klantId;
    @NotNull
    private final int filmId;
    @NotNull
    @DateTimeFormat(style = "S-")
    private final LocalDate reservatie;

    public Reservatie(@NotNull int klantId, @NotNull int filmId, @NotNull LocalDate reservatie) {
        this.klantId = klantId;
        this.filmId = filmId;
        this.reservatie = reservatie;
    }

    public int getKlantId() {
        return klantId;
    }

    public int getFilmId() {
        return filmId;
    }

    public LocalDate getReservatie() {
        return reservatie;
    }
}
