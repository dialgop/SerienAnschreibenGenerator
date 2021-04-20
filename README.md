# SerienAnschreibenGenerator

Der SerienAnschreibenGenerator ist ein Anwendungsprogramm der Firma Automobilhaus zur Erstellung von automatischen Briefen für alle Kunden. Jeder Kunde erhält einen Brief mit allen Fahrzeugen, die das Unternehmen anbietet.

Die Software enthält 3 Module:

- Modul "Fahrzeuge": In diesem Modul können die Mitarbeiter des Automobilhauses auf Basis einer XML-Datei verschiedene Fahrzeugtypen hinzufügen. (Ein Beispiel für diese Datei finden Sie in sample/autos.xml)

- Modul Kunden:In diesem Modul ist es möglich, einen Kunden über die Konsole hinzuzufügen, wobei nach und nach die Daten des Benutzers eingegeben werden.  Es ist auch möglich, alle in die Datenbank eingefügten Clients anzuzeigen

- Modul Drucken: Basierend auf den Kunden und Fahrzeugen, die in den Datenbanken gespeichert sind, wird ein Briefset (einer pro Kunde) im Ordner Anschreiben/ erstellt. Jeder Brief enthält das Angebot aller Fahrzeuge, die die Firma hat.

### Tutorials:

Das folgende Klassendiagramm enthält eine einfache Erklärung, wie jede Klasse miteinander verbunden ist und mit anderen interagiert.

![ClassDiagram](https://user-images.githubusercontent.com/11840801/115403069-5bd56e80-a1ec-11eb-9369-918228de8f37.png)

Weitere Informationen zu den einzelnen Methoden finden Sie im Ordner Javadoc. Wo sich die Java-Dokumentation dazu befindet.

### Programm-Installation

1. Klonen Sie dieses Repository
2. Installieren Sie mysql
3. Geben Sie nach der Installation von mysql den folgenden Befehl ein: 
   - sudo mysql -u root -e "create database automobilhaus"; 
4. Geben Sie dem Befehl sudo mysql -u root -p automobilhaus < <b>$/Pfad/zum/repository$</b>/SerienAnschreibenGenerator/Datenbank/tables.sql
5. Dieses Programm funktioniert mit Java 11.0.10, wenn Sie es nicht haben, installieren Sie es bitte
6. Binden Sie die folgenden Bibliotheken in das Repository ein (Wenn Sie das Programm unter IntelliJ ausführen, gehen Sie zu Datei/Projekt_Struktur und fügen Sie in Project_Settings/Libraries hinzu):
   - mysql:mysql-connector-java:8.0.18
   - org.apache.pdfbox:pdfbox:2.0.4
7. Bei Intellij könnten auch die folgenden Plugins verwendet werden:
   - Junit: Testen der Validierungsklasse (auf Project/Test/java/main/ValidationTest)
   - Graphviz (sudo apt install graphviz) "PlantUML integration" und "Sketch it" zur Visualisierung des Klassendiagramms (im Ordner class diagram)

### Ausführen des Programms

Sie haben 2 Möglichkeiten, das Programm auszuführen:

- Öffnen Sie das Projekt auf Intellij, wählen Sie main.Main als die auszuführende Klasse und führen Sie es aus
- java -jar /pfad/von/Projekt/SerienAnschreibenGenerator/out/artifacts/SerienAnschreibenGenerator_jar/SerienAnschreibenGenerator.jar 
