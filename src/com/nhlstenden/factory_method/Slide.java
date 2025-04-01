package com.nhlstenden.factory_method;

import com.nhlstenden.Style;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Vector;

/**
 * Een slide. Deze klasse heeft tekenfunctionaliteit.
 *
 * @author Ian F. Darwin
 * @author Gert Florijn
 * @author Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class Slide {

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

  public void appendTextItem(int level, String message) {
    append(new TextItemFactory().createSlideItem(level, message));
  }

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

  public void draw(Graphics g, Rectangle area, ImageObserver view) {
    float scale = getScale(area);
    int y = area.y;

    if (title != null && !title.isEmpty()) {
      SlideItem titleItem = new TextItem(0, title);
      Style style = Style.getStyle(titleItem.getLevel());
      titleItem.draw(area.x, y, scale, g, style, view);
      y += titleItem.getBoundingBox(g, view, scale, style).height;
    }

    for (SlideItem slideItem : items) {
      Style style = Style.getStyle(slideItem.getLevel());
      slideItem.draw(area.x, y, scale, g, style, view);
      y += slideItem.getBoundingBox(g, view, scale, style).height;
    }
  }

  private float getScale(Rectangle area) {
    return Math.min((float) area.width / WIDTH, (float) area.height / HEIGHT);
  }
}
