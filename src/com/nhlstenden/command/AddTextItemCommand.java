package com.nhlstenden.command;

import com.nhlstenden.Presentation;
import com.nhlstenden.factory_method.Slide;
import java.awt.Frame;
import javax.swing.JOptionPane;

public class AddTextItemCommand implements Command {
  private Presentation presentation;
  private Frame parent;

  public AddTextItemCommand(Presentation presentation, Frame parent) {
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

    String text = JOptionPane.showInputDialog("Enter text for the new item:");
    if (text != null && !text.trim().isEmpty()) {
      presentation.getCurrentSlide().appendTextItem(level, text);
      parent.repaint();
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
