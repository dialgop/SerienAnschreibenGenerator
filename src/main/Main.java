package main;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * @author Diego Alejandro Gómez Pardo.
     *
     * Hauptmenü: Ein Menü zur Auswahl der 3 Optionen, die sich auf die Aufgabe beziehen, wird eingesetzt:
     * 1. Hinzufügen von Fahrzeugen zur DB aus einer XML-Datei.
     * 2. Hinzufügen von Kunden zur DB oder Anzeigen von Kunden aus der DB
     * 3. Erstellen von Briefen auf der Basis von Kunden und Fahrzeugen.
     * Nachdem eine Option ausgeführt wurde, erscheint wieder das Hauptmenü
     */

    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("Bitte wählen Sie eine Option \n" +
                    "(F) um einer xml-Datei in der Fahrzeug-Datenbank zu speichern \n" +
                    "(K) um einen neuen Kunden in der Kunde-Datenbank anzulegen oder um alle Kunden aufzulisten. \n" +
                    "(D) um pdf-Briefe für alle Kunden zu erzeugen \n" +
                    "(Q) beendet das Programm");
            String option = input.nextLine();
            switch (option){
                case "F":
                case "f":
                    VehicleControl readData = new VehicleControl();
                    System.out.println("Bitte geben Sie den Pfad der xml-Datei an");
                    String path = input.nextLine();
                    readData.saveVehiclesinDB(path);//"/home/diego/Downloads/autos.xml"
                    continueMenu();
                    break;
                case "K":
                case "k":
                    ClientControl.startClientControl();
                    continueMenu();
                    break;
                case "D":
                case "d":
                    List<Client> clientList = ClientControl.loadClientsFromDB();
                    List<Vehicle> vehicleList = VehicleControl.loadVehiclesFromDB();
                    PDFPrinter.generatePDF(clientList,vehicleList);
                    continueMenu();
                    break;
                case "Q":
                case "q":
                    return;
                default:
                    break;
            }
        }
    }

    /**
     * Methode zum Anhalten des Menüs, damit der Benutzer Änderungen an den Daten sehen kann.
     */
    private static void continueMenu(){
        Scanner option = new Scanner(System.in);
        System.out.println("\nDrücken Sie die Taste M und Intro, um zurück zum Hauptmenü zu gelangen\n");
        option.nextLine();
        return;
    }
}



