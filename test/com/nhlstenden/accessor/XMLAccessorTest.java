package com.nhlstenden.accessor;

import static org.junit.jupiter.api.Assertions.*;

import com.nhlstenden.Presentation;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.*;

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
