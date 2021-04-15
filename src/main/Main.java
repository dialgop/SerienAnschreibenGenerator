package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("Please select an option \n" +
                    "(1) to save an xml file into the Fahrzeug database \n" +
                    "(2) to create a new client into the Kunde dabatase \n" +
                    "(3) to print pdf letters for all users \n" +
                    "(Q) to stop the program");
            String option = input.nextLine();
            switch (option){
                case "1":
                    VehicleControl readData = new VehicleControl();
                    System.out.println("Please provide the path of the xml file");
                    String path = input.nextLine();
                    readData.saveVehiclesinDB(path);//"/home/diego/Downloads/autos.xml"
                    break;
                case "2":
                    List<Client> dBClients = new ArrayList< Client >();;
                    int clientOption = ClientControl.selectOption();
                    if(clientOption==1)
                        ClientControl.writeClient();
                    if(clientOption==2) {
                        dBClients = ClientControl.loadClientsFromDB();
                        System.out.println("The following are our current clients:\n" +
                                "---------------------------------------------------");
                        for (Client clients : dBClients) {
                            String name = clients.getName();
                            String vorname = clients.getVorname();
                            String anschritt = clients.getAnschrift();
                            System.out.println("[name:" + name + ", vorname:" + vorname + ", anschritt:" + anschritt);
                        }
                        System.out.println("---------------------------------------------------\n");
                    }
                    break;
                case "3":
                    List<Client> clientList = ClientControl.loadClientsFromDB();
                    List<Vehicle> vehicleList = VehicleControl.loadVehiclesFromDB();
                    PDFPrinter.generatePDF(clientList,vehicleList);
                    System.out.println("Files printed");
                    break;
                case "Q":
                case "q":
                    return;
                default:
                    break;
            }
        }
    }
}



