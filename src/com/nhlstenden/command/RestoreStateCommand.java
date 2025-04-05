package com.nhlstenden.command;

import com.nhlstenden.Presentation;
import com.nhlstenden.memento.CareTaker;
import java.awt.Frame;
import javax.swing.JOptionPane;

public class RestoreStateCommand implements Command {
  private Presentation presentation;
  private Frame parent;
  private CareTaker careTaker;

  public RestoreStateCommand(Presentation presentation, Frame parent, CareTaker careTaker) {
    this.presentation = presentation;
    this.parent = parent;
    this.careTaker = careTaker;
  }

  // This method is called when the command is executed.
  @Override
  public void execute() {
    if (careTaker.hasSavedState()) {
      careTaker.restoreState(presentation);
      parent.repaint();
      JOptionPane.showMessageDialog(
          parent, "Presentation state restored!", "Success", JOptionPane.INFORMATION_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(
          parent, "No saved state to restore!", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
}
