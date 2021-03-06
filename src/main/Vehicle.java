package main;

/**
 * Eine Klassenentität, die einen Fahrzeug darstellt. Diese Klasse wird zum Setzen oder Abrufen der verschiedenen
 * Attribute der Klasse verwendet.
 */

public class Vehicle extends TransportMean {

    private String fahrzeugtyp;
    private String bezeichnung;
    private String heersteller;
    private int kw_leistung;
    private int verkaufspreis;

    public String getFahrzeugtyp() {
        return fahrzeugtyp;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public String getHeersteller() {
        return heersteller;
    }

    public int getKw_leistung() {
        return kw_leistung;
    }

    public int getVerkaufspreis() {
        return verkaufspreis;
    }

    public void setFahrzeugtyp(String fahrzeugtyp) {
        this.fahrzeugtyp = fahrzeugtyp;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public void setHeersteller(String heersteller) {
        this.heersteller = heersteller;
    }

    public void setKw_leistung(int kw_leistung) {
        this.kw_leistung = kw_leistung;
    }

    public void setVerkaufspreis(int verkaufspreis) {
        this.verkaufspreis = verkaufspreis;
    }

    public void setIdTransportMean(int idForTransportMean){this.idTransportMean = idForTransportMean;}
}
