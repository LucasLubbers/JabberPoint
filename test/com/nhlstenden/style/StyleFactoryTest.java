package com.nhlstenden.style;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import org.junit.jupiter.api.*;

class StyleFactoryTest {

  @BeforeEach
  void setUp() {
    StyleFactory.createStyles();
  }

  @Test
  void testStylesArrayInitialization() {
    for (int i = 0; i < 5; i++) {
      Style style = StyleFactory.getStyle(i);
      assertNotNull(style, "Style at level " + i + " should not be null");
    }
  }

  @Test
  void testGetStyleWithinBounds() {
    Style style = StyleFactory.getStyle(2);
    assertEquals(36, style.getFontSize());
    assertEquals(50, style.getIndent());
    assertEquals(Color.BLACK, style.getColor());
  }

  @Test
  void testGetStyleOutOfBounds() {
    Style lastStyle = StyleFactory.getStyle(4);
    Style outOfBounds = StyleFactory.getStyle(10);

    assertEquals(lastStyle.getFontSize(), outOfBounds.getFontSize());
    assertEquals(lastStyle.getIndent(), outOfBounds.getIndent());
  }
}
