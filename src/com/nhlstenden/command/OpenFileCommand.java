package com.nhlstenden.command;

import com.nhlstenden.Presentation;
import com.nhlstenden.accessor.XMLAccessor;
import com.nhlstenden.slide_viewer.SlideViewerFrame;
import java.awt.Frame;
import java.io.IOException;
import java.util.Map;
import javax.swing.JOptionPane;

public class OpenFileCommand implements Command {
  private Presentation presentation;
  private Frame parent;
  private SlideViewerFrame slideViewerFrame;
  private static final String IOEX = "IO Exception: ";
  private static final String LOADERR = "Load Error";

  public OpenFileCommand(
      Presentation presentation, Frame parent, SlideViewerFrame slideViewerFrame) {
    this.presentation = presentation;
    this.parent = parent;
    this.slideViewerFrame = slideViewerFrame;
  }

  @Override
  public void execute() {
    Map<String, String> savedPresentations = NewPresentationCommand.getSavedPresentations();
    if (savedPresentations.isEmpty()) {
      JOptionPane.showMessageDialog(
          parent, "No saved presentations available.", LOADERR, JOptionPane.ERROR_MESSAGE);
      return;
    }

    String[] names = savedPresentations.keySet().toArray(new String[0]);
    String selectedName =
        (String)
            JOptionPane.showInputDialog(
                parent,
                "Select a presentation to open:",
                "Open Presentation",
                JOptionPane.QUESTION_MESSAGE,
                null,
                names,
                names[0]);
    if (selectedName != null) {
      String filename = savedPresentations.get(selectedName);
      presentation.clear();
      try {
        new XMLAccessor().loadFile(presentation, filename);
        presentation.setSlideNumber(0);
        slideViewerFrame.update(presentation, presentation.getCurrentSlide());
      } catch (IOException e) {
        JOptionPane.showMessageDialog(parent, IOEX + e, LOADERR, JOptionPane.ERROR_MESSAGE);
      }
      parent.repaint();
    }
  }
}
