package com.nhlstenden.style;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import org.junit.jupiter.api.Test;

class StyleTest {

  @Test
  void testConstructorAndGetters() {
    Style style = new Style(10, Color.GREEN, 18, 12);

    assertEquals(10, style.getIndent());
    assertEquals(Color.GREEN, style.getColor());
    assertEquals(18, style.getFontSize());
    assertEquals(12, style.getLeading());

    Font font = style.getFont();
    assertNotNull(font);
    assertEquals("Helvetica", font.getName());
    assertEquals(Font.BOLD, font.getStyle());
    assertEquals(18, font.getSize());
  }

  @Test
  void testGetFontWithScale() {
    Style style = new Style(0, Color.BLACK, 20, 10);

    Font scaledFont = style.getFont(1.5f);
    assertEquals((int) (20 * 1.5f), scaledFont.getSize());
  }

  @Test
  void testToStringFormat() {
    Style style = new Style(5, Color.RED, 14, 8);
    String result = style.toString();
    assertTrue(result.contains("5"));
    assertTrue(result.contains("14"));
    assertTrue(result.contains("8"));
    assertTrue(result.contains("java.awt.Color"));
  }
}
