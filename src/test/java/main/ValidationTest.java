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
    void testPostalCode() {
        boolean empty = main.Validation.testXMLPath("");
        boolean mixedLettersDigits = main.Validation.testXMLPath("53lll");
        boolean moreDigits = main.Validation.testXMLPath("1234567");
        boolean lessDigits = main.Validation.testXMLPath("12");
        boolean correct = main.Validation.testXMLPath("/home/diego/Downloads/autos.xml");

        assertFalse(empty);
        assertFalse(mixedLettersDigits);
        assertFalse(moreDigits);
        assertFalse(lessDigits);
        assertTrue(correct);
    }

    @Test
    void testXMLPath() {
        boolean empty = main.Validation.testXMLPath("");
        boolean onlyPath = main.Validation.testXMLPath("/home/Diego/Downloads/");
        boolean anotherFile = main.Validation.testXMLPath("/home/Diego/Downloads/autos.xls");
        boolean correct = main.Validation.testXMLPath("/home/Diego/Downloads/autos.xml");

        assertFalse(empty);
        assertFalse(onlyPath);
        assertFalse(anotherFile);
        assertTrue(correct);
    }


}