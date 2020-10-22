package be.vdab.retrovideo.domain;

import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

public class Film {
    private final long id;
    private final Genre genre;
    private final String titel;
    private final int voorraad;
    private final int gereserveerd;
    @NumberFormat(pattern = "0,00") private final BigDecimal prijs;

    public Film(long id, Genre genre, String titel, int voorraad, int gereserveerd, BigDecimal prijs) {
        this.id = id;
        this.genre = genre;
        this.titel = titel;
        this.voorraad = voorraad;
        this.gereserveerd = gereserveerd;
        this.prijs = prijs;
    }

    public long getId() {
        return id;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getTitel() {
        return titel;
    }

    public int getVoorraad() {
        return voorraad;
    }

    public int getGereserveerd() {
        return gereserveerd;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }
}