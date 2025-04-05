package com.nhlstenden.command;

import static org.mockito.Mockito.*;

import com.nhlstenden.Presentation;
import java.awt.Frame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NewPresentationCommandTest {
  private Presentation presentation;
  private Frame parent;
  private NewPresentationCommand command;

  @BeforeEach
  void setUp() {
    presentation = mock(Presentation.class);
    parent = mock(Frame.class);
    command = new NewPresentationCommand(presentation, parent);
  }

  @Test
  void testExecuteClearsAndRepaints() {
    command.execute();
    verify(presentation).clear();
    verify(parent).repaint();
  }
}
