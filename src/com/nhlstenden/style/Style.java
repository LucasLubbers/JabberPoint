package com.nhlstenden.style;

import java.awt.Color;
import java.awt.Font;

public class Style {

  private static final String FONTNAME = "Helvetica";
  private int indent;
  private Color color;
  private Font font;
  private int fontSize;
  private int leading;

  public Style(int indent, Color color, int points, int leading) {
    this.indent = indent;
    this.color = color;
    this.font = new Font(FONTNAME, Font.BOLD, points);
    this.fontSize = points;
    this.leading = leading;
  }

  public int getIndent() {
    return indent;
  }

  public Color getColor() {
    return color;
  }

  public Font getFont() {
    return font;
  }

  public int getFontSize() {
    return fontSize;
  }

  public int getLeading() {
    return leading;
  }

  public Font getFont(float scale) {
    return font.deriveFont(fontSize * scale);
  }

  @Override
  public String toString() {
    return "[" + indent + "," + color + "; " + fontSize + " on " + leading + "]";
  }
}
