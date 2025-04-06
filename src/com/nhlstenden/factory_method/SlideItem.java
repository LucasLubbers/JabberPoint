package com.nhlstenden.factory_method;

import com.nhlstenden.style.Style;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

/**
 * De abstracte klasse voor een item op een com.nhlstenden.factory_method.Slide
 *
 * <p>
 *
 * <p>Alle SlideItems hebben tekenfunctionaliteit.
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public abstract class SlideItem implements Cloneable {

  private int level = 0; // Level of the slide item
  private String text;

  public SlideItem(int lev) {
    level = lev;
  }

  public SlideItem() {
    this(0);
  }

  public int getLevel() {
    return level;
  }

  public abstract Rectangle getBoundingBox(
      Graphics g, ImageObserver observer, float scale, Style style);

  // Draw the item
  public abstract void draw(
      int x, int y, float scale, Graphics g, Style style, ImageObserver observer);

  // Clone method to create a copy of the SlideItem
  @Override
  public SlideItem clone() {
    try {
      return (SlideItem) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(); // Should never happen
    }
  }

  @Override
  public String toString() {
    return "SlideItem[" + level + "," + text + "]";
  }
}
