package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    @Test
    void testName() {
        boolean empty = main.Validation.testSentence("");
        boolean nameDigits = main.Validation.testSentence("Jem4n0");
        boolean nameSymbols = main.Validation.testSentence("J*m@nd");
        boolean correct = main.Validation.testSentence("Jemand");

        assertFalse(empty);
        assertFalse(nameDigits);
        assertFalse(nameSymbols);
        assertTrue(correct);

    }

    @Test
    void testAddress() {
        boolean empty = main.Validation.testSentence("");
        boolean addressymbols = main.Validation.testSentence("H@uptBahnhof $tra§e");
        boolean correct = main.Validation.testSentence("Hermann-Wandersleb Ring 6");

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