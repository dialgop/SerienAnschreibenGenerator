package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    @Test
    void testName() {
        boolean empty = main.Validation.testName("");
        boolean nameDigits = main.Validation.testName("Jem4n0");
        boolean nameSymbols = main.Validation.testName("J*m@nd");
        boolean correct = main.Validation.testName("Jemand");

        assertFalse(empty);
        assertFalse(nameDigits);
        assertFalse(nameSymbols);
        assertTrue(correct);

    }

    @Test
    void testAddress() {
        boolean empty = main.Validation.testName("");
        boolean addressymbols = main.Validation.testName("H@uptBahnhof $tra§e");
        boolean correct = main.Validation.testName("Hermann-Wandersleb Ring 6");

        assertFalse(empty);
        assertFalse(addressymbols);

    }

    @Test
    void testXMLPAth() {
        boolean empty = main.Validation.testXMLPAth("");
        boolean onlyPath = main.Validation.testXMLPAth("/home/Diego/Downloads/");
        boolean anotherFile = main.Validation.testXMLPAth("/home/Diego/Downloads/autos.xls");
        boolean correct = main.Validation.testXMLPAth("/home/Diego/Downloads/autos.xml");

        assertFalse(empty);
        assertFalse(onlyPath);
        assertFalse(anotherFile);
        assertTrue(correct);
    }
}