package main;

public class Client {

    private String name;
    private String vorname;
    private String anschrift;
    private int postleitzahl;
    private String stadt;

    public Client(){
        name = "";
        vorname="";
        anschrift="";
        postleitzahl = -1;
        stadt = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getAnschrift() {
        return anschrift;
    }

    public void setAnschrift(String anschrift) {
        this.anschrift = anschrift;
    }

    public int getPostleitzahl() {return postleitzahl;}

    public void setPostleitzahl(int postleitzahl) {this.postleitzahl = postleitzahl;}

    public String getStadt() {return stadt;}

    public void setStadt(String stadt) {this.stadt = stadt;}
}
