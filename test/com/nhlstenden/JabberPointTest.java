package com.nhlstenden;

import com.nhlstenden.style.StyleFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

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

    @BeforeAll
    static void checkHeadless() {
        // Only skip ALL tests if we're in headless mode AND they require GUI
        // We'll control this at individual test level instead
    }

    @Test
    void testMainWithoutArguments() {
        // This test doesn't require GUI
        assertDoesNotThrow(() -> JabberPoint.main(new String[]{}));
        assertNotNull(StyleFactory.getStyle(0));
    }

    @Test
    void testMainWithValidXMLFile() throws IOException {
        // This test doesn't require GUI
        Path xmlFile = tempDir.resolve("test.xml");
        Files.writeString(xmlFile, TEST_XML_CONTENT);
        assertDoesNotThrow(() -> JabberPoint.main(new String[]{xmlFile.toString()}));
    }

    @Test
    void testMainWithNonExistentFile() {
        // This test doesn't require GUI
        assertDoesNotThrow(() -> JabberPoint.main(new String[]{"nonexistent.xml"}));
    }

    @Test
    void testMainWithInvalidXMLFile() throws IOException {
        // This test doesn't require GUI
        Path xmlFile = tempDir.resolve("invalid.xml");
        Files.writeString(xmlFile, "This is not valid XML");
        assertDoesNotThrow(() -> JabberPoint.main(new String[]{xmlFile.toString()}));
    }

    @Test
    void testShowErrorDialog() {
        // Only skip this specific test in headless mode
        assumeFalse(GraphicsEnvironment.isHeadless(),
                "Skipping GUI test in headless environment");

        assertDoesNotThrow(() -> JabberPoint.showErrorDialog("Test error message"));
    }

    @Test
    void testStyleCreation() {
        // This test doesn't require GUI
        StyleFactory.createStyles();
        assertNotNull(StyleFactory.getStyle(0));
        assertNotNull(StyleFactory.getStyle(1));
    }
}