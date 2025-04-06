package com.nhlstenden;

import com.nhlstenden.accessor.XMLAccessor;
import com.nhlstenden.command.CommandRegistry;
import com.nhlstenden.command.KeyController;
import com.nhlstenden.memento.CareTaker;
import com.nhlstenden.slide_viewer.SlideViewerFrame;
import com.nhlstenden.style.StyleFactory;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * com.nhlstenden.JabberPoint Main Program
 *
 * <p>This program is a presentation tool that allows users to add various types of slide items,
 * such as text and images.
 *
 * @version 1.7 2025/03/31 Added support for multiple item types
 */
public class JabberPoint {

  private static final String IOERR = "IO Error: ";
  private static final String JABERR = "Jabberpoint Error ";
  private static final String JABVERSION = "Jabberpoint 1.7 - Enhanced Version";

  /** The Main Program */
  public static void main(String[] argv) {
    StyleFactory.createStyles();
    Presentation presentation = new Presentation();
    SlideViewerFrame slideViewerFrame = new SlideViewerFrame(JABVERSION, presentation);
    CareTaker careTaker = new CareTaker();

    CommandRegistry commandRegistry =
        new CommandRegistry(presentation, slideViewerFrame, slideViewerFrame, careTaker);
    KeyController keyController = new KeyController(commandRegistry.getKeyCommandMapper());
    slideViewerFrame.addKeyListener(keyController);

    try {
      if (argv.length > 0) {
        new XMLAccessor().loadFile(presentation, argv[0]);
        presentation.setSlideNumber(0);
      }
    } catch (IOException ex) {
      showErrorDialog(IOERR + ex);
    }
  }

  /**
   * Shows an error dialog with the specified message.
   *
   * @param message the error message to display
   */
  static void showErrorDialog(String message) {
    JOptionPane.showMessageDialog(null, message, JABERR, JOptionPane.ERROR_MESSAGE);
  }
}
