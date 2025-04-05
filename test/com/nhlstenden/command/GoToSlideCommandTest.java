package com.nhlstenden.command;

import static org.mockito.Mockito.*;

import com.nhlstenden.Presentation;
import java.awt.Frame;
import javax.swing.JOptionPane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class GoToSlideCommandTest {
  private Presentation presentation;
  private Frame parent;
  private GoToSlideCommand command;

  @BeforeEach
  void setUp() {
    presentation = mock(Presentation.class);
    parent = mock(Frame.class);
    command = new GoToSlideCommand(presentation, parent);
  }

  @Test
  void testExecuteWithValidInput() {
    try (MockedStatic<JOptionPane> dialog = mockStatic(JOptionPane.class)) {
      dialog.when(() -> JOptionPane.showInputDialog("Page number?")).thenReturn("3");
      command.execute();
      verify(presentation).setSlideNumber(2);
    }
  }

  @Test
  void testExecuteWithInvalidInput() {
    try (MockedStatic<JOptionPane> dialog = mockStatic(JOptionPane.class)) {
      dialog.when(() -> JOptionPane.showInputDialog("Page number?")).thenReturn("abc");
      command.execute();
      dialog.verify(
          () ->
              JOptionPane.showMessageDialog(
                  parent, "Invalid page number!", "Error", JOptionPane.ERROR_MESSAGE));
    }
  }
}
