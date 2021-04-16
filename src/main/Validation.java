package main;

public class Validation {
    public static boolean testSentence(String name){
        return (testNullString(name) && testDigitString(name) && testSpecialCharsString(name));
    }

    public static boolean testAddress(String address){
        return (testNullString(address) && testSpecialCharsString(address));
    }

    public static boolean testPostalCode(String postalCode){
        return (testNullString(postalCode) && testOnlyDigits(postalCode) && test5Digits(postalCode));
    }

    public static boolean testOnlyDigits(String code){
        String regex = "\\d+";
        boolean out = code.matches(regex);
        if(out)
            return true;
        else{
            System.out.println("Eine Postleitzahl enthält nur Ziffern");
            return false;
        }
    }

    public static boolean test5Digits(String code){
        if(code.length()==5)
            return true;
        else{
            System.out.println("Deutsche Postleitzahlen haben nur 5 Ziffern");
            return false;
        }
    }

    public static boolean testXMLPAth(String path){
        if(path.length()<5 || !path.endsWith(".xml")){
            System.out.println("Der Pfad enthält keine xml-Datei");
            return false;
        }
        return true;
    }

    private static boolean testNullString(String string) {
        if (string.trim().isEmpty()) {
            System.out.println("Die Eingabe ist leer");
            return false;
        }
        return true;
    }

    private static boolean testDigitString(String string){
        if(string.matches(".*\\d.*")){
            System.out.println("Die Eingabe enthält Ziffern");
            return false;
        }
        return true;
    }

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
