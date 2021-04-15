package main;

import java.io.File;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class VehicleControl {

    public static List<Vehicle> loadVehiclesFromDB(){

        List<Vehicle> vehicleList = new ArrayList < Vehicle > ();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/automobilhaus?serverTimezone=Europe/Berlin","diego","[myP4ssw0rd]");
            Statement stmt=con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Fahrzeug");

            while(rs.next())
                vehicleList.add(loadVehicle(rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6)));

            con.close();
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return vehicleList;
    }

    private static Vehicle loadVehicle(String fahrzeugtypLD, String bezeichnungLD, String heerstellerLD, int kw_leistungLD,
                                int verkaufspreisLD){
        Vehicle auto = new Vehicle();
        auto.setFahrzeugtyp(fahrzeugtypLD);
        auto.setBezeichnung(bezeichnungLD);
        auto.setHeersteller(heerstellerLD);
        auto.setKw_leistung(kw_leistungLD);
        auto.setVerkaufspreis(verkaufspreisLD);

        return auto;
    }

    public void saveVehiclesinDB(String pathXML){
        if(!Validation.testXMLPAth(pathXML))
            return;

        List<Vehicle> vehicleList = readXML(pathXML);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/automobilhaus?serverTimezone=Europe/Berlin", "diego", "[myP4ssw0rd]");
            Statement stmt = con.createStatement();

            //lets print User list information
            for (Vehicle car: vehicleList) {

                String fahrzeugtypDB = car.getFahrzeugtyp();
                String bezeichnungDB = car.getBezeichnung();
                String heerstellerDB = car.getHeersteller();
                String kw_leistungDB = String.valueOf(car.getKw_leistung());
                String verkaufspreisDB = String.valueOf(car.getVerkaufspreis());

                stmt.executeUpdate("INSERT INTO Fahrzeug (FahrzeugTyp,Bezeichnung,Heersteller,LeistungKW,Verkaufspreis) " +
                        "VALUES ('" + fahrzeugtypDB + "','" + bezeichnungDB + "','" + heerstellerDB + "'," + kw_leistungDB +
                        "," + verkaufspreisDB + ")");
                System.out.println("Saved data:["+fahrzeugtypDB+","+bezeichnungDB+","+heerstellerDB+","+kw_leistungDB+"]");
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    private List<Vehicle> readXML(String pathXML){
        File xmlFile = new File(pathXML);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        List < Vehicle > vehiclesList = new ArrayList < Vehicle > ();

        try{
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("fahrzeug");
            // now XML is loaded as Document in memory, lets convert it to Object List

            for (int i = 0; i < nodeList.getLength(); i++) {
                vehiclesList.add(getVehicle(nodeList.item(i)));
            }

        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
        return vehiclesList;
    }

    private Vehicle getVehicle(Node node) {
        // XMLReaderDOM domReader = new XMLReaderDOM();
        Vehicle auto = new Vehicle();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            auto.setFahrzeugtyp(getTagValue("fahrzeugtyp", element));
            auto.setBezeichnung(getTagValue("bezeichnung", element));
            auto.setHeersteller(getTagValue("heersteller", element));
            auto.setKw_leistung(Integer.parseInt(getTagValue("kw_leistung", element)));
            auto.setVerkaufspreis(Integer.parseInt(getTagValue("verkaufspreis", element)));
        }
        return auto;
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

}
