package com.nhlstenden.slide_viewer;

import com.nhlstenden.Presentation;
import com.nhlstenden.factory_method.Slide;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class SlideViewerComponent extends JComponent {

  private Slide slide;
  private Font labelFont;
  private Presentation presentation;
  private JFrame frame;

  private static final long serialVersionUID = 227L;
  private static final Color BGCOLOR = Color.white;
  private static final Color COLOR = Color.black;
  private static final String FONTNAME = "Dialog";
  private static final int FONTSTYLE = Font.BOLD;
  private static final int FONTHEIGHT = 10;
  private static final int XPOS = 1100;
  private static final int YPOS = 20;

  public SlideViewerComponent(Presentation presentation, JFrame frame) {
    setBackground(BGCOLOR);
    this.presentation = presentation;
    this.labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
    this.frame = frame;
  }

  // This method is called when the component is resized.
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(Slide.WIDTH, Slide.HEIGHT);
  }

  public void update(Presentation presentation, Slide slide) {
    this.presentation = presentation;
    this.slide = slide;
    repaint();
    frame.setTitle(presentation.getTitle());
  }

  // This method is called to paint the component.
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(BGCOLOR);
    g.fillRect(0, 0, getSize().width, getSize().height);
    if (presentation.getSlideNumber() < 0 || slide == null) {
      return;
    }
    g.setFont(labelFont);
    g.setColor(COLOR);
    g.drawString(
        "Slide " + (1 + presentation.getSlideNumber()) + " of " + presentation.getSize(),
        XPOS,
        YPOS);
    Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
    slide.draw(g, area, this);
  }
}
