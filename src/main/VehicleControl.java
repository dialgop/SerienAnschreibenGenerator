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

/**
 * Klasse zur Ausführung aller Methoden, die mit dem Speichern von Fahrzeugen in der DB und dem Laden von Fahrzeugen
 * aus der DB zusammenhängen
 */

public class VehicleControl {

    /**
     * Methode, die alle Fahrzeuge direkt aus der Datenbank lädt und jedes einzelne in einem Vehicle-Objekt speichert,
     * das dann einer Liste von Vehicle-Objekten hinzugefügt wird.
     * @return vehicleList: Liste mit Objekten von "Type Vehicle" aus der DB.
     */
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

    /**
     * Methode, die während loadVehiclesFromDB() aufgerufen wird. Es speichert jedes Tupel von der DB-Query in einem
     * Vehicle-Objekt.
     *
     * @param fahrzeugtypLD
     * @param bezeichnungLD
     * @param heerstellerLD
     * @param kw_leistungLD
     * @param verkaufspreisLD
     * @return auto
     */
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

    /**
     * Diese Methode erhält eine Liste von Objekten Vehicle, danach werden die Attribute jedes Objekts aus der Liste
     * verwendet, um neue Tupel in der Tabelle Fahrzeug zu erstellen.
     * @param pathXML - Pfad, in dem sich die XML-Datei befindet
     */
    public void saveVehiclesinDB(String pathXML){

        List<Vehicle> vehicleList = readXML(pathXML);
        if (vehicleList.size()<1)
            return;

        try {

            // DB Verbindung.
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

    /**
     * Diese Methode validiert die Richtigkeit einer XML-Datei und nach erfolgreicher Validierung, konvertiert das
     * Programm jeden XML-Datensatz in ein Vehicle-Objekt, das einer Liste von Objekten desselben Type hinzugefügt wird.
     * @param pathXML - Pfad der xml-Datei
     * @return vehiclesList
     */
    private List<Vehicle> readXML(String pathXML){

        List < Vehicle > vehiclesList = new ArrayList < Vehicle > ();
        File xmlFile = new File(pathXML);

        if(!Validation.testXMLPath(pathXML))
            return vehiclesList;

        //Erstellung von Objekten zum Lesen und Konvertieren der XML-Datei in ein Objekt Fahrzeug
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try{
            // Nimmt jedes Element (Tag) des Dokuments (XML-Datei) und fügt es in eine NodeList ein. Die Daten jedes
            // Node der NodeList werden iterativ verwendet, um Objekte Vehicle zu erstellen
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("fahrzeug");
            // Jetzt ist die XML Datei als Dokument in Memory geladen, jetzt muss es in eine Objektliste konvertiert
            // werden

            for (int i = 0; i < nodeList.getLength(); i++) {
                vehiclesList.add(getVehicle(nodeList.item(i)));
            }

        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
        return vehiclesList;
    }

    /**
     * Diese Methode holt die Werte der einzelnen Tags eines Nodes und fügt sie in ein Objekt Vehicle ein.
     *
     * @param node - Node from a Nodelist (Document)
     * @return auto - Object Vehicle with xml data from each Tag of the node
     */
    private Vehicle getVehicle(Node node) {

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

    /**
     * Diese Methode Liest den Wert eines Knoten-Elements basierend auf seinem Tag
     * @param tag -> Tag, der vom Element aus durchsucht werden soll
     * @param element -> Alle enthaltenen Elemente aus dem Node.
     * @return Wert vom Element-Tag.
     */
    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

}
