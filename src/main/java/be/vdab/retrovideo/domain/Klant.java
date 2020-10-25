package be.vdab.retrovideo.domain;

public class Klant {
    private final long id;
    private final String voornaam;
    private final String familienaam;
//    private final Adres adres;
    private final String straatNummer;
    private final String postcode;
    private final String gemeente;

    public Klant(long id, String voornaam, String familienaam, String straatNummer, String postcode, String gemeente) {
        this.id = id;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.straatNummer = straatNummer;
        this.postcode = postcode;
        this.gemeente = gemeente;
//        this.adres = adres;
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

    public String getAdres() {
        return straatNummer + ' ' + postcode + ' ' + gemeente;
    }
    public String getNaam() {
        return voornaam + ' ' + familienaam;
    }
}
