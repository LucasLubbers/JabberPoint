package com.nhlstenden.style;

import java.awt.Color;

public class StyleFactory {

  private static Style[] styles;

  // This method creates a set of styles with different properties
  public static void createStyles() {
    styles = new Style[5];
    styles[0] = new Style(0, Color.red, 48, 20);
    styles[1] = new Style(20, Color.blue, 40, 10);
    styles[2] = new Style(50, Color.black, 36, 10);
    styles[3] = new Style(70, Color.black, 30, 10);
    styles[4] = new Style(90, Color.black, 24, 10);
  }

  // This method returns a style based on the level provided
  public static Style getStyle(int level) {
    if (level >= styles.length) {
      level = styles.length - 1;
    }
    return styles[level];
  }
}
