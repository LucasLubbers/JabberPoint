import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Vector;

/**
 * Een slide. Deze klasse heeft tekenfunctionaliteit.
 *
 * @author Ian F. Darwin, ian@darwinsys.com
 * @author Gert Florijn
 * @author Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class Slide {

  public static final int WIDTH = 1200;
  public static final int HEIGHT = 800;
  protected String title; // de titel wordt apart bewaard
  protected Vector<SlideItem> items; // de slide-items worden in een Vector bewaard

  public Slide() {
    items = new Vector<SlideItem>();
  }

  // Voeg een SlideItem toe
  public void append(SlideItem anItem) {
    items.addElement(anItem);
  }

  // Geef de titel van de slide
  public String getTitle() {
    return title;
  }

  // Verander de titel van de slide
  public void setTitle(String newTitle) {
    title = newTitle;
  }

  // Maak een TextItem van String, en voeg het TextItem toe
  public void append(int level, String message) {
    append(new TextItem(level, message));
  }

  // Geef het betreffende SlideItem
  public SlideItem getSlideItem(int number) {
    return items.elementAt(number);
  }

  // Geef alle SlideItems in een Vector
  public Vector<SlideItem> getSlideItems() {
    return items;
  }

  // Geef de afmeting van de Slide
  public int getSize() {
    return items.size();
  }

  // Teken de slide
  public void draw(Graphics g, Rectangle area, ImageObserver view) {
    float scale = getScale(area);
    int y = area.y;
    // De titel wordt apart behandeld
    SlideItem slideItem = new TextItem(0, getTitle());
    Style style = Style.getStyle(slideItem.getLevel());
    slideItem.draw(area.x, y, scale, g, style, view);
    y += slideItem.getBoundingBox(g, view, scale, style).height;
    for (int number = 0; number < getSize(); number++) {
      slideItem = getSlideItem(number);
      style = Style.getStyle(slideItem.getLevel());
      slideItem.draw(area.x, y, scale, g, style, view);
      y += slideItem.getBoundingBox(g, view, scale, style).height;
    }
  }

  // Geef de schaal om de slide te kunnen tekenen
  private float getScale(Rectangle area) {
    return Math.min(((float) area.width) / WIDTH, ((float) area.height) / HEIGHT);
  }
}
