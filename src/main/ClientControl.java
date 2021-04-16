package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientControl {

    public static void startClientControl(){
        Scanner mainInput = new Scanner(System.in);
        List<Client> dBClients;
        int clientOption = selectOption();
        if(clientOption==1)
            writeClient();
        if(clientOption==2) {
            dBClients = loadClientsFromDB();
            System.out.println("Die folgende Liste enthält unsere aktuellen Kunden:\n" +
                    "---------------------------------------------------");
            for (Client clients : dBClients) {
                String name = clients.getName();
                String vorname = clients.getVorname();
                String anschritt = clients.getAnschrift();
                System.out.println("[name:" + name + ", vorname:" + vorname + ", anschritt:" + anschritt);
            }
            System.out.println("---------------------------------------------------\n");
            System.out.println("Drücken Sie eine beliebige Taste, um zurück zum Hauptmenü zu gelangen:\n");
            mainInput.nextLine();
        }
    }

    /***
     * Displays the option related to the client menu
     * @return result: Selected option marked via console
     */
    public static int selectOption(){
        Scanner input = new Scanner(System.in);
        int result;
        while(true){
            System.out.println("Drücken Sie die Taste (1), wenn Sie einen neuen Kunde anlegen wollen\n" +
                    "oder (2) wenn Sie eine Liste mit allen Kunden sehen möchten. Jede\n" +
                    "andere Option wird dieses Modul stoppen.");
            String option = input.nextLine();
            try{
                result = Integer.parseInt(option);
                break;
            } catch (NumberFormatException e) {
                System.out.println(".\n\n");
            }
        }
        return result;
    }

    public static void writeClient(){
        Client clientToCreate = new Client();

        Scanner input = new Scanner(System.in);
        while(clientToCreate.getVorname().length()==0){
            System.out.println("Geben Sie Ihren Vornamen ein");
            String firstNameInput = input.nextLine();
            if(Validation.testSentence(firstNameInput)) {
                clientToCreate.setVorname(firstNameInput);
            }
            else
                clientToCreate.setVorname("");
        }

        while(clientToCreate.getName().length()==0){
            System.out.println("Geben Sie Ihren Namen ein");
            String lastNameInput = input.nextLine();
            if(Validation.testSentence(lastNameInput)) {
                clientToCreate.setName(lastNameInput);
            }
            else
                clientToCreate.setName("");
        }

        while(clientToCreate.getPostleitzahl()==-1){
            System.out.println("Geben Sie Ihre Postleitzahl ein");
            String postalCodeInput = input.nextLine();
            if(Validation.testPostalCode(postalCodeInput))
                clientToCreate.setPostleitzahl(Integer.parseInt(postalCodeInput));
            else
                clientToCreate.setPostleitzahl(-1);
        }

        while(clientToCreate.getStadt().length()==0){
            System.out.println("Geben Sie Ihre Stadt ein");
            String cityInput = input.nextLine();
            if(Validation.testSentence(cityInput)) {
                clientToCreate.setStadt(cityInput);
            }
            else
                clientToCreate.setStadt("");
        }

        while(clientToCreate.getAnschrift().length()==0){
            System.out.println("Geben Sie Ihre Adresse ein");
            String addressInput = input.nextLine();
            if(Validation.testAddress(addressInput)) {
                clientToCreate.setAnschrift(addressInput);
            }
            else
                clientToCreate.setAnschrift("");
        }

        System.out.println("Sind Sie sicher, dass Sie die folgende Daten speichern wollen?" +
                " ("+clientToCreate.getName()+","+clientToCreate.getVorname()+","+clientToCreate.getAnschrift()+","+
                clientToCreate.getPostleitzahl()+","+clientToCreate.getStadt()+")\n"+
                "(Y) speichert die Daten, andere Taste kehrt zum Hauptmenü zurück");
        String yesNoClient = input.nextLine();
        if(yesNoClient.equals("y") || yesNoClient.equals("Y"))
            SaveClientInDB(clientToCreate);
            System.out.println("Drücken Sie eine beliebige Taste, um zurück zum Hauptmenü zu gelangen:\n");
            input.nextLine();
    }

    private static void SaveClientInDB(Client client){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/automobilhaus?serverTimezone=Europe/Berlin", "diego", "[myP4ssw0rd]");
            Statement stmt = con.createStatement();

            String nameDB = client.getName();
            String vornameDB = client.getVorname();
            String anschrittDB = client.getAnschrift();
            int postleitzahlDB = client.getPostleitzahl();
            String stadtDB = client.getStadt();

            stmt.executeUpdate("INSERT INTO Kunde (name,vorname,anschrift,postleitzahl,stadt) " +
                    "VALUES ('" + nameDB + "','" + vornameDB + "','" + anschrittDB + "'," + postleitzahlDB + ",'" + stadtDB +"')");
            System.out.println("Saved data:["+nameDB+","+vornameDB+","+anschrittDB+"]");

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Client> loadClientsFromDB(){
        List<Client> clientsList = new ArrayList<>();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/automobilhaus?serverTimezone=Europe/Berlin","diego","[myP4ssw0rd]");
            Statement stmt=con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Kunde");

            while(rs.next())
                clientsList.add(loadClient(rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6)));

            con.close();
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return clientsList;
    }

    private static Client loadClient(String name, String vorname, String anschrift, int postleitzahl, String stadt){
        Client client = new Client();
        client.setName(name);
        client.setVorname(vorname);
        client.setAnschrift(anschrift);
        client.setPostleitzahl(postleitzahl);
        client.setStadt(stadt);

        return client;
    }
}