package com.nhlstenden.command;

import com.nhlstenden.Presentation;
import com.nhlstenden.XML.XMLAccessor;
import java.awt.Frame;
import java.io.IOException;
import javax.swing.JOptionPane;

public class SaveFileCommand implements Command {
  private Presentation presentation;
  private Frame parent;
  private static final String IOEX = "IO Exception: ";
  private static final String SAVEERR = "Save Error";
  private static final String SAVEFILE = "dump.xml";

  public SaveFileCommand(Presentation presentation, Frame parent) {
    this.presentation = presentation;
    this.parent = parent;
  }

  @Override
  public void execute() {
    try {
      new XMLAccessor().saveFile(presentation, SAVEFILE);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(parent, IOEX + e, SAVEERR, JOptionPane.ERROR_MESSAGE);
    }
  }
}
