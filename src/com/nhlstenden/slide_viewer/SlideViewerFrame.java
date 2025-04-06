package com.nhlstenden.slide_viewer;

import com.nhlstenden.Presentation;
import com.nhlstenden.command.KeyCommandMapper;
import com.nhlstenden.command.KeyController;
import com.nhlstenden.factory_method.Slide;
import com.nhlstenden.menu.MenuController;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class SlideViewerFrame extends JFrame {

  private static final long serialVersionUID = 3227L;
  private static final String JABTITLE = "Jabberpoint 1.6 - OU";
  public static final int WIDTH = 1200;
  public static final int HEIGHT = 800;

  public SlideViewerFrame(String title, Presentation presentation) {
    super(title);
    SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, this);
    presentation.setShowView(slideViewerComponent);
    setupWindow(slideViewerComponent, presentation);
  }

  // This method sets up the window with the given SlideViewerComponent and Presentation.
  private void setupWindow(SlideViewerComponent slideViewerComponent, Presentation presentation) {
    setTitle(JABTITLE);
    addWindowListener(
        new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e) {
            System.exit(0);
          }
        });
    getContentPane().add(slideViewerComponent);
    KeyCommandMapper keyCommandMapper = new KeyCommandMapper();
    addKeyListener(new KeyController(keyCommandMapper));
    setMenuBar(new MenuController(this, presentation, this).getMenuBar());
    setSize(new Dimension(WIDTH, HEIGHT));
    setVisible(true);
  }

  // This method updates the title of the window and repaints the content.
  public void update(Presentation presentation, Slide currentSlide) {
    getContentPane().repaint();
    setTitle(JABTITLE + " - " + presentation.getTitle() + " - " + currentSlide.getTitle());
  }
}
