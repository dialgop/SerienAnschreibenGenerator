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
            System.out.println("A postal code only have Digits");
            return false;
        }
    }

    public static boolean test5Digits(String code){
        if(code.length()==5)
            return true;
        else{
            System.out.println("German PostalCodes only have 5 Digits");
            return false;
        }
    }

    public static boolean testXMLPAth(String path){
        if(path.length()<5 || !path.endsWith("xml")){
            System.out.println("the path does not contain an xml file");
            return false;
        }
        return true;
    }

    private static boolean testNullString(String string) {
        if (string.trim().isEmpty()) {
            System.out.println("The input written is empty");
            return false;
        }
        return true;
    }

    private static boolean testDigitString(String string){
        if(string.matches(".*\\d.*")){
            System.out.println("The input written contains digits");
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
                    System.out.println("The input written special characters");
                    return false;
                }
            }
        }
        return true;
    }
}
