package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientControl {

    /***
     * Displays the option related to the client menu
     * @return result: Selected option marked via console
     */
    public static int selectOption(){
        Scanner input = new Scanner(System.in);
        int result;
        while(true){
            System.out.println("Write (1) if you want to create a new client\n" +
                    "or (2) if you want to see a list with all the clients.\n" +
                    "Any other Number will stop this module.");
            String option = input.nextLine();
            try{
                result = Integer.parseInt(option);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please choose only a numerical option.\n\n");
            }
        }
        return result;
    }

    public static void writeClient(){
        Client clientToCreate = new Client();

        Scanner input = new Scanner(System.in);
        while(clientToCreate.getVorname().length()==0){
            System.out.println("Enter your name");
            String firstNameInput = input.nextLine();
            if(Validation.testName(firstNameInput)) {
                clientToCreate.setVorname(firstNameInput);
            }
            else
                clientToCreate.setVorname("");
        }

        while(clientToCreate.getName().length()==0){
            System.out.println("Enter your last name");
            String lastNameInput = input.nextLine();
            if(Validation.testName(lastNameInput)) {
                clientToCreate.setName(lastNameInput);
            }
            else
                clientToCreate.setName("");
        }

        while(clientToCreate.getAnschrift().length()==0){
            System.out.println("Enter your address");
            String addressInput = input.nextLine();
            if(Validation.testAddress(addressInput)) {
                clientToCreate.setAnschrift(addressInput);
            }
            else
                clientToCreate.setAnschrift("");
        }

        System.out.println("Are you sure, that you want to save the data" +
                " ("+clientToCreate.getName()+","+clientToCreate.getVorname()+","+clientToCreate.getAnschrift()+")\n" +
                "(Y/y) will save the data, other key will return to the main menu");
        String yesNoClient = input.nextLine();
        if(yesNoClient.equals("y") || yesNoClient.equals("Y"))
            SaveClientInDB(clientToCreate);
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

            stmt.executeUpdate("INSERT INTO Kunde (name,vorname,anschrift) " +
                    "VALUES ('" + nameDB + "','" + vornameDB + "','" + anschrittDB + "')");
            System.out.println("Saved data:["+nameDB+","+vornameDB+","+anschrittDB+"]");

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Client> loadClientsFromDB(){
        List<Client> clientsList = new ArrayList < Client > ();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/automobilhaus?serverTimezone=Europe/Berlin","diego","[myP4ssw0rd]");
            Statement stmt=con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Kunde");

            while(rs.next())
                clientsList.add(loadClient(rs.getString(2),rs.getString(3),rs.getString(4)));

            con.close();
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return clientsList;
    }

    private static Client loadClient(String name, String vorname, String anschrift){
        Client client = new Client();
        client.setName(name);
        client.setVorname(vorname);
        client.setAnschrift(anschrift);

        return client;
    }
}