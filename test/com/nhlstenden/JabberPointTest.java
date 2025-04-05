package com.nhlstenden;

import com.nhlstenden.style.StyleFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class JabberPointTest {

    private static final String TEST_XML_CONTENT = """
            <?xml version="1.0"?>
            <presentation>
                <showtitle>Test Presentation</showtitle>
                <slide>
                    <title>Test Slide</title>
                    <item kind="text" level="1">Test Content</item>
                </slide>
            </presentation>
            """;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        // Reset Swing state before each test
        JOptionPane.setRootFrame(null);
    }

    @AfterEach
    void tearDown() {
        // Clean up Swing state after each test
        JOptionPane.setRootFrame(null);
    }

    @Test
    void testMainWithoutArguments() {
        // Test that main runs without arguments (should not throw exceptions)
        assertDoesNotThrow(() -> JabberPoint.main(new String[]{}));

        // Verify styles were created
        assertNotNull(StyleFactory.getStyle(0));
    }

    @Test
    void testMainWithValidXMLFile() throws IOException {
        // Create a temporary XML file
        Path xmlFile = tempDir.resolve("test.xml");
        Files.writeString(xmlFile, TEST_XML_CONTENT);

        // Test that main runs with a valid XML file
        assertDoesNotThrow(() -> JabberPoint.main(new String[]{xmlFile.toString()}));
    }

    @Test
    void testMainWithNonExistentFile() {
        // Test that main handles non-existent file gracefully
        String nonExistentFile = "nonexistent.xml";
        assertDoesNotThrow(() -> JabberPoint.main(new String[]{nonExistentFile}));

    }

    @Test
    void testMainWithInvalidXMLFile() throws IOException {
        // Create a temporary invalid XML file
        Path xmlFile = tempDir.resolve("invalid.xml");
        Files.writeString(xmlFile, "This is not valid XML");

        assertDoesNotThrow(() -> JabberPoint.main(new String[]{xmlFile.toString()}));
    }

    @Test
    void testShowErrorDialog() {
        // Test the error dialog display
        assertDoesNotThrow(() -> JabberPoint.showErrorDialog("Test error message"));
    }

    @Test
    void testStyleCreation() {
        // Verify that styles are created during initialization
        StyleFactory.createStyles();
        assertNotNull(StyleFactory.getStyle(0));
        assertNotNull(StyleFactory.getStyle(1));
    }
}