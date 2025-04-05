package com.nhlstenden.accessor;

import com.nhlstenden.Presentation;
import com.nhlstenden.accessor.XMLWriter;
import com.nhlstenden.factory_method.BitmapItem;
import com.nhlstenden.factory_method.Slide;
import com.nhlstenden.factory_method.TextItem;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class XMLWriterTest {

    private final String filename = "test.xml";
    private XMLWriter writer;

    @BeforeEach
    void setUp() {
        writer = new XMLWriter();
    }

    @Test
    void testSaveFile() throws IOException {
        Presentation presentation = new Presentation();
        presentation.setTitle("Writer Test");

        Slide slide = new Slide();
        slide.setTitle("Slide Title");
        slide.append(new TextItem(1, "Some text"));
        slide.append(new BitmapItem(2, "image.png"));

        presentation.append(slide);

        writer.saveFile(presentation, filename);

        List<String> lines = Files.readAllLines(new File(filename).toPath());
        assertTrue(lines.stream().anyMatch(line -> line.contains("<showtitle>Writer Test</showtitle>")));
        assertTrue(lines.stream().anyMatch(line -> line.contains("<item kind=\"text\" level=\"1\">Some text</item>")));
        assertTrue(lines.stream().anyMatch(line -> line.contains("<item kind=\"image\" level=\"2\">image.png</item>")));
    }

    @AfterEach
    void cleanUp() {
        new File(filename).delete();
    }
}
