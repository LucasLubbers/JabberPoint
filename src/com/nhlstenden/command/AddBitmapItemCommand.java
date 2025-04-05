package com.nhlstenden.command;

import com.nhlstenden.Presentation;
import com.nhlstenden.factory_method.Slide;
import java.awt.Frame;
import javax.swing.JOptionPane;

public class AddBitmapItemCommand implements Command {
  private Presentation presentation;
  private Frame parent;

  public AddBitmapItemCommand(Presentation presentation, Frame parent) {
    this.presentation = presentation;
    this.parent = parent;
  }

  @Override
  public void execute() {
    ensureSlideExists();

    String levelInput = JOptionPane.showInputDialog("Enter level (0-5):");
    int level;
    try {
      level = Integer.parseInt(levelInput);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(
          parent, "Invalid level! Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    String imageName = JOptionPane.showInputDialog("Enter the file path for the bitmap:");
    if (imageName != null && !imageName.trim().isEmpty()) {
      try {
        presentation.getCurrentSlide().appendBitmapItem(level, imageName);
        parent.repaint();
      } catch (Exception e) {
        JOptionPane.showMessageDialog(
            parent, "Failed to add bitmap: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private void ensureSlideExists() {
    if (presentation.getCurrentSlide() == null) {
      Slide newSlide = new Slide();
      presentation.append(newSlide);
      presentation.setSlideNumber(presentation.getSize() - 1);
    }
  }
}
