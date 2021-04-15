package main;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.*;

import java.io.IOException;
import java.util.List;

public class PDFPrinter {
    public static void generatePDF(List<Client> clients, List<Vehicle> vehicles) throws IOException {
        PDDocument document;
        PDPage page = new PDPage(PDRectangle.A4);
        PDPageContentStream contentStream;

        if(clients.size()<1){
            System.out.println("The database clients does not have registries, no letters will be printed");
            return;
        }
        if(vehicles.size()<1){
            System.out.println("The database vehicles does not have registries, no letters will be printed");
            return;
        }

        for(Client client: clients){

            document = new PDDocument();
            document.addPage(page);
            contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.OVERWRITE, true);

            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_ITALIC, 20);
            contentStream.setLeading(20.f);
            contentStream.newLineAtOffset(25, 800);

            String text = client.getAnschrift() + ", " + client.getPostleitzahl() + ". " + client.getStadt();
            String Line1 = "sehr geehrte(r):";
            String Line2 = client.getVorname() + " " + client.getName();
            String line3 = "Im Anhang  finden Sie die Liste der  Fahrzeuge, die  unsere Firma";
            String line4 = "Autohaus unseren aktuellen Kunden anbietet.  Wenn Sie Interesse ";
            String line5 = "an einem unserer  Modelle  haben, zögern Sie bitte  nicht uns  zu";
            String line6 = "kontaktieren.";

            contentStream.showText(text);
            contentStream.newLine();
            contentStream.newLine();
            contentStream.newLine();
            contentStream.newLine();
            contentStream.setFont(PDType1Font.TIMES_BOLD_ITALIC, 20);
            contentStream.showText(Line1);
            contentStream.newLine();
            contentStream.showText(Line2);
            contentStream.newLine();
            contentStream.newLine();
            contentStream.setFont(PDType1Font.TIMES_ITALIC, 20);
            contentStream.showText(line3);
            contentStream.newLine();
            contentStream.showText(line4);
            contentStream.newLine();
            contentStream.showText(line5);
            contentStream.newLine();
            contentStream.showText(line6);
            contentStream.newLine();
            contentStream.newLine();
            contentStream.newLine();

            for(Vehicle vehicle: vehicles){
                String lineAuto = "("+vehicle.getFahrzeugtyp()+") " + vehicle.getHeersteller() + " " +
                        vehicle.getBezeichnung() + ", " + vehicle.getKw_leistung() + " KW";
                String lineVerkaufsPreis = "Preis: "+ vehicle.getVerkaufspreis();

                contentStream.setFont(PDType1Font.TIMES_BOLD_ITALIC, 20);
                contentStream.showText(lineAuto);
                contentStream.newLine();
                contentStream.setFont(PDType1Font.TIMES_ITALIC, 20);
                contentStream.showText(lineVerkaufsPreis);
                contentStream.newLine();

            }

            String lineVerabschied1 = "Herzliche Grüße";
            String lineVerabschied2 = "Automobilhaus";

            contentStream.newLine();
            contentStream.setFont(PDType1Font.TIMES_BOLD_ITALIC, 20);
            contentStream.showText(lineVerabschied1);
            contentStream.newLine();
            contentStream.showText(lineVerabschied2);

            contentStream.close();

            String fileName =  client.getName() + "_" + client.getVorname()+ ".pdf";
            document.save(fileName);
            document.close();

        }
    }
}
