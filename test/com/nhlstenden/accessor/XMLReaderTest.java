package com.nhlstenden.accessor;

import static org.junit.jupiter.api.Assertions.*;

import com.nhlstenden.Presentation;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.*;

class XMLReaderTest {

  private final String filename = "test.xml";
  private XMLReader reader;

  @BeforeEach
  void setUp() throws IOException {
    reader = new XMLReader();

    try (FileWriter writer = new FileWriter(filename)) {
      writer.write(
          """
          <?xml version="1.0"?>
          <!DOCTYPE presentation SYSTEM "jabberpoint.dtd">
          <presentation>
            <showtitle>Reader Test</showtitle>
            <slide>
              <title>Slide 1</title>
              <item kind="text" level="1">Text Item 1</item>
              <item kind="image" level="2">image1.png</item>
            </slide>
          </presentation>
          """);
    }
  }

  @Test
  void testLoadFile() throws IOException {
    Presentation presentation = new Presentation();
    reader.loadFile(presentation, filename);

    assertEquals("Reader Test", presentation.getTitle());
    assertEquals(1, presentation.getSize());
    assertEquals("Slide 1", presentation.getSlide(0).getTitle());
    assertEquals(2, presentation.getSlide(0).getSlideItems().size());
  }

  @AfterEach
  void tearDown() {
    new File(filename).delete();
  }
}
