package com.nhlstenden;

import com.nhlstenden.style.StyleFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.swing.*;
import java.awt.*;
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
        // Skip GUI tests in headless environment
        Assumptions.assumeFalse(
                GraphicsEnvironment.isHeadless(),
                "Skipping GUI test in headless environment"
        );
        JOptionPane.setRootFrame(null);
    }

    @AfterEach
    void tearDown() {
        if (!GraphicsEnvironment.isHeadless()) {
            JOptionPane.setRootFrame(null);
        }
    }

    @Test
    void testMainWithoutArguments() {
        assertDoesNotThrow(() -> JabberPoint.main(new String[]{}));
        assertNotNull(StyleFactory.getStyle(0));
    }

    @Test
    void testMainWithValidXMLFile() throws IOException {
        Path xmlFile = tempDir.resolve("test.xml");
        Files.writeString(xmlFile, TEST_XML_CONTENT);
        assertDoesNotThrow(() -> JabberPoint.main(new String[]{xmlFile.toString()}));
    }

    @Test
    void testMainWithNonExistentFile() {
        String nonExistentFile = "nonexistent.xml";
        assertDoesNotThrow(() -> JabberPoint.main(new String[]{nonExistentFile}));
    }

    @Test
    void testMainWithInvalidXMLFile() throws IOException {
        Path xmlFile = tempDir.resolve("invalid.xml");
        Files.writeString(xmlFile, "This is not valid XML");
        assertDoesNotThrow(() -> JabberPoint.main(new String[]{xmlFile.toString()}));
    }

    @Test
    void testShowErrorDialog() {
        // This will be skipped in headless mode
        assertDoesNotThrow(() -> JabberPoint.showErrorDialog("Test error message"));
    }

    @Test
    void testStyleCreation() {
        StyleFactory.createStyles();
        assertNotNull(StyleFactory.getStyle(0));
        assertNotNull(StyleFactory.getStyle(1));
    }
}