package main;

public class Validation {
    public static boolean testName(String name){
        return (testNullString(name) && testDigitString(name) && testSpecialCharsString(name));
    }

    public static boolean testAddress(String address){
        return (testNullString(address) && testSpecialCharsString(address));
    }

    public static boolean testXMLPAth(String path){
        if(path.length()<5 || !path.substring(path.length()-3).equals("xml")){
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
