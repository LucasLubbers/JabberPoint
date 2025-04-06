package com.nhlstenden.command;

import com.nhlstenden.Presentation;
import com.nhlstenden.factory_method.Slide;
import java.awt.Frame;

public class SetNewSlideCommand implements Command {
  private Presentation presentation;
  private Frame parent;

  public SetNewSlideCommand(Presentation presentation, Frame parent) {
    this.presentation = presentation;
    this.parent = parent;
  }

  // This method is called when the command is executed.
  @Override
  public void execute() {
    presentation.append(new Slide());
    parent.repaint();
  }
}
