package be.vdab.retrovideo.domain;

public class Klant {
    private final long id;
    private final String voornaam;
    private final String familienaam;
    private final Adres adres;

    public Klant(long id, String voornaam, String familienaam, Adres adres) {
        this.id = id;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.adres = adres;
    }

    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public Adres getAdres() {
        return adres;
    }
    public String getNaam() {
        return voornaam + ' ' + familienaam;
    }
}
