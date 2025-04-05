package com.nhlstenden;

import com.nhlstenden.XML.XMLAccessor;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * com.nhlstenden.JabberPoint Main Programma
 *
 * <p>Dit programma is een presentatie-tool waarmee gebruikers verschillende soorten dia-items
 * kunnen toevoegen, zoals tekst en afbeeldingen.
 *
 * @author Ian F. Darwin
 * @author Gert Florijn
 * @author Sylvia Stuurman
 * @version 1.7 2025/03/31 Toegevoegd ondersteuning voor meerdere itemtypes
 */
public class JabberPoint {

  protected static final String IOERR = "IO Error: ";
  protected static final String JABERR = "Jabberpoint Error ";
  protected static final String JABVERSION = "Jabberpoint 1.7 - Enhanced Version";

  /** Het Main Programma */
  public static void main(String[] argv) {

    Style.createStyles();
    Presentation presentation = new Presentation();
    new SlideViewerFrame(JABVERSION, presentation);

    try {
      if (argv.length == 0) { // Laad een demo-presentatie
        Accessor.getDemoAccessor().loadFile(presentation, "");
      } else {
        new XMLAccessor().loadFile(presentation, argv[0]);
      }
      presentation.setSlideNumber(0);
    } catch (IOException ex) {
      JOptionPane.showMessageDialog(null, IOERR + ex, JABERR, JOptionPane.ERROR_MESSAGE);
    }
  }
}
