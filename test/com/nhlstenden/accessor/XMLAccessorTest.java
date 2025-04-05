package com.nhlstenden.accessor;

import com.nhlstenden.Presentation;
import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class XMLAccessorTest {

    private XMLAccessor accessor;
    private Presentation presentation;
    private final String testFile = "test_presentation.xml";

    @BeforeEach
    void setUp() {
        accessor = new XMLAccessor();
        presentation = new Presentation();
    }

    @Test
    void testSaveAndLoadFile() throws IOException {
        presentation.setTitle("Test Title");

        accessor.saveFile(presentation, testFile);
        assertTrue(new File(testFile).exists());

        Presentation loaded = new Presentation();
        accessor.loadFile(loaded, testFile);

        assertEquals("Test Title", loaded.getTitle());
    }

    @AfterEach
    void cleanUp() {
        new File(testFile).delete();
    }
}
