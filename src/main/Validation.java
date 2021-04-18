package main;

/**
 * Eine Klasse zur Validierung des Pfades einer XML-Datei oder von Client-Daten.
 */

public class Validation {

    /**
     * Prüft die Richtigkeit bei der Eingabe von Vornamen, Nachnamen, Wohnorten und jeder Eingabe, die nur auf
     * Buchstaben basiert
     * @param word - Input von ClientControl.
     * @return true/false
     */
    public static boolean testSentence(String word){
        return (testNullString(word) && testDigitString(word) && testSpecialCharsString(word));
    }

    /**
     * Prüft die Richtigkeit bei der Eingabe von Adressen
     * @param address - Input von ClientControl.
     * @return true/false
     */
    public static boolean testAddress(String address){
        return (testNullString(address) && testSpecialCharsString(address));
    }

    /**
     * Prüft die Richtigkeit bei der Eingabe von Postleitzahlen
     * @param postalCode - Input von ClientControl.
     * @return true/false
     */
    public static boolean testPostalCode(String postalCode){
        return (testNullString(postalCode) && testOnlyDigits(postalCode) && testLength5(postalCode));
    }

    /**
     * Prüft dass einen richtigen Input-Pfad ein XML Datei specifiziert ist.
     * @param path - Input von Main() Methode.
     * @return true/false
     */
    public static boolean testXMLPath(String path){
        if(path.length()<5 || !path.endsWith(".xml")){
            System.out.println("Der Pfad enthält keine xml-Datei");
            return false;
        }
        return true;
    }

    /**
     * Prüft dass ein Input-Postleitzahlt nur Ziffern enthält
     * @param code - Input von testPostalCode() Methode.
     * @return true/false
     */
    private static boolean testOnlyDigits(String code){
        String regex = "\\d+";
        boolean out = code.matches(regex);
        if(out)
            return true;
        else{
            System.out.println("Eine Postleitzahl enthält nur Ziffern");
            return false;
        }
    }

    /**
     * Prüft dass ein String Size=5 hat.
     * @param code - Input von testPostalCode() Methode.
     * @return true/false
     */
    private static boolean testLength5(String code){
        if(code.length()==5)
            return true;
        else{
            System.out.println("Deutsche Postleitzahlen haben nur 5 Ziffern");
            return false;
        }
    }

    /**
     * Prüft dass jede Input nicht leer ist.
     * @param string - Input von testSentence(), testAddress() oder testPostalCode() Methode.
     * @return true/false
     */
    private static boolean testNullString(String string) {
        if (string.trim().isEmpty()) {
            System.out.println("Die Eingabe ist leer");
            return false;
        }
        return true;
    }

    /**
     * Prüft dass jede Input der nur mit Worte eingefühlt sein sollte, keine Ziffern hat.
     * @param string - Input von testSentence() Methode.
     * @return true/false
     */
    private static boolean testDigitString(String string){
        if(string.matches(".*\\d.*")){
            System.out.println("Die Eingabe enthält Ziffern");
            return false;
        }
        return true;
    }

    /**
     * Prüft dass jede Input der nur mit Worte eingefühlt sein sollte, keine Sonderzeichen hat.
     * @param string - Input von testSentence() Methode.
     * @return true/false
     */
    private static boolean testSpecialCharsString(String string){
        char[] chars = string.toCharArray();
        char[] specialCh = {'!','@',']','#','$','%','^','&','*','_','['};

        for (Character str : chars){
            for (Character spc : specialCh) {
                if (str == spc){
                    System.out.println("Die geschriebene Eingabe enthält Sonderzeichen");
                    return false;
                }
            }
        }
        return true;
    }
}
