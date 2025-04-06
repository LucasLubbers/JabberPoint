package com.nhlstenden.command;

import com.nhlstenden.Presentation;
import java.awt.Frame;
import javax.swing.JOptionPane;

public class DeleteSlideCommand implements Command {
  private Presentation presentation;
  private Frame parent;

  public DeleteSlideCommand(Presentation presentation, Frame parent) {
    this.presentation = presentation;
    this.parent = parent;
  }

  // This method is called when the command is executed.
  @Override
  public void execute() {
    String input = JOptionPane.showInputDialog(parent, "Enter the slide number to delete:");
    if (input != null && !input.trim().isEmpty()) {
      try {
        int slideNumber = Integer.parseInt(input) - 1; // Convert to zero-based index
        presentation.deleteSlide(slideNumber);
        parent.repaint();
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(
            parent, "Invalid slide number.", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}
