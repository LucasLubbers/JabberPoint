package com.nhlstenden.factory_method;

import com.nhlstenden.style.Style;
import com.nhlstenden.style.StyleFactory;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Vector;

/** A slide. This class has drawing functionality. */
public class Slide implements Cloneable {

  public static final int WIDTH = 1200;
  public static final int HEIGHT = 800;
  protected String title;
  protected Vector<SlideItem> items;

  public Slide() {
    items = new Vector<>();
  }

  public void append(SlideItem anItem) {
    items.add(anItem);
  }

  // This method is used to add a text item to the slide.
  public void appendTextItem(int level, String message) {
    append(new TextItemFactory().createSlideItem(level, message));
  }

  // This method is used to add a bitmap item to the slide.
  public void appendBitmapItem(int level, String message) {
    append(new BitmapItemFactory().createSlideItem(level, message));
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String newTitle) {
    title = newTitle;
  }

  public SlideItem getSlideItem(int number) {
    return items.get(number);
  }

  public Vector<SlideItem> getSlideItems() {
    return items;
  }

  public int getSize() {
    return items.size();
  }

  // Draws the slide on the given graphics context.
  public void draw(Graphics g, Rectangle area, ImageObserver view) {
    float scale = getScale(area);
    int y = area.y;

    if (title != null && !title.isEmpty()) {
      SlideItem titleItem = new TextItem(0, title);
      Style style = StyleFactory.getStyle(titleItem.getLevel());
      titleItem.draw(area.x, y, scale, g, style, view);
      y += titleItem.getBoundingBox(g, view, scale, style).height;
    }

    for (SlideItem slideItem : items) {
      Style style = StyleFactory.getStyle(slideItem.getLevel());
      slideItem.draw(area.x, y, scale, g, style, view);
      y += slideItem.getBoundingBox(g, view, scale, style).height;
    }
  }

  private float getScale(Rectangle area) {
    return Math.min((float) area.width / WIDTH, (float) area.height / HEIGHT);
  }

  // Clone method to create a deep copy of the Slide object.
  @Override
  public Slide clone() {
    try {
      Slide cloned = (Slide) super.clone();
      cloned.items = new Vector<>(items.size());
      for (SlideItem item : items) {
        cloned.items.add(item.clone());
      }
      return cloned;
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }

  // Get the string representation of the slide.
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Slide{title='").append(title).append("', items=[");
    for (SlideItem item : items) {
      sb.append(item.toString()).append(", ");
    }
    sb.append("]}");
    return sb.toString();
  }
}
