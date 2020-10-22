package be.vdab.retrovideo.domain;


public class Adres {
    private final String straatNummer;
    private final int postcode;
    private final String gemeente;

    public Adres(String straatNummer, int postcode, String gemeente) {
        this.straatNummer = straatNummer;
        this.postcode = postcode;
        this.gemeente = gemeente;
    }

    public String getStraatNummer() {
        return straatNummer;
    }

    public int getPostcode() {
        return postcode;
    }

    public String getGemeente() {
        return gemeente;
    }
}
