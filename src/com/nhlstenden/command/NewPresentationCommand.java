package com.nhlstenden.command;

import com.nhlstenden.Presentation;
import java.awt.Frame;

public class NewPresentationCommand implements Command {
  private Presentation presentation;
  private Frame parent;

  public NewPresentationCommand(Presentation presentation, Frame parent) {
    this.presentation = presentation;
    this.parent = parent;
  }

  @Override
  public void execute() {
    presentation.clear();
    parent.repaint();
  }
}
