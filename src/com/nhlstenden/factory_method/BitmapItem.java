package com.nhlstenden.factory_method;

import com.nhlstenden.style.Style;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * De klasse voor een Bitmap item
 *
 * <p>Bitmap items hebben de verantwoordelijkheid om zichzelf te tekenen.
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
public class BitmapItem extends SlideItem {

  private BufferedImage bufferedImage;
  private String imageName;

  protected static final String FILE = "Bestand ";
  protected static final String NOTFOUND = " niet gevonden";

  // Level is for the indentation of the text, name is the name of the image
  public BitmapItem(int level, String name) {
    super(level);
    imageName = name;
    try {
      bufferedImage = ImageIO.read(new File(imageName));
      if (bufferedImage == null) {
        throw new IOException("Invalid image file format.");
      }
    } catch (IOException e) {
      System.err.println(FILE + imageName + NOTFOUND);
      bufferedImage = null; // Ensure the object is in a safe state
    }
  }

  // An empty constructor for the factory method
  public BitmapItem() {
    this(0, null);
  }

  // Give the image name
  public String getName() {
    return imageName;
  }

  // Give the boundingbox of the image
  public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style) {
    return new Rectangle(
        (int) (style.getIndent() * scale),
        0,
        (int) (bufferedImage.getWidth(observer) * scale),
        ((int) (style.getLeading() * scale)) + (int) (bufferedImage.getHeight(observer) * scale));
  }

  // Draw the image at the specified location with the given scale
  public void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer) {
    if (bufferedImage == null) {
      g.drawString(FILE + imageName + NOTFOUND, x, y);
      return;
    }
    int width = x + (int) (style.getIndent() * scale);
    int height = y + (int) (style.getLeading() * scale);
    g.drawImage(
        bufferedImage,
        width,
        height,
        (int) (bufferedImage.getWidth(observer) * scale),
        (int) (bufferedImage.getHeight(observer) * scale),
        observer);
  }

  @Override
  public String toString() {
    return "com.nhlstenden.factory_method.BitmapItem[" + getLevel() + "," + imageName + "]";
  }
}
