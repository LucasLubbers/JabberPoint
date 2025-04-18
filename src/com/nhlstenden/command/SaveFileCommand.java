package com.nhlstenden.command;

import com.nhlstenden.Presentation;
import com.nhlstenden.accessor.XMLAccessor;
import java.awt.Frame;
import java.io.IOException;
import javax.swing.JOptionPane;

public class SaveFileCommand implements Command {
  private Presentation presentation;
  private Frame parent;
  private static final String IOEX = "IO Exception: ";
  private static final String SAVEERR = "Save Error";

  public SaveFileCommand(Presentation presentation, Frame parent) {
    this.presentation = presentation;
    this.parent = parent;
  }

  @Override
  public void execute() {
    String title = presentation.getTitle();
    String filename;

    if (title != null && !title.trim().isEmpty()) {
      filename = title.replaceAll("\\s+", "_") + ".xml"; // avoid spaces in filenames
    } else {
      filename = "untitled_" + System.currentTimeMillis() + ".xml"; // fallback
    }

    try {
      new XMLAccessor().saveFile(presentation, filename);
      System.out.println("Saved to: " + filename);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(parent, IOEX + e, SAVEERR, JOptionPane.ERROR_MESSAGE);
    }
  }
}
