package com.nhlstenden.command;

import static org.junit.jupiter.api.Assertions.*;

import com.nhlstenden.Presentation;
import com.nhlstenden.factory_method.Slide;
import java.awt.Frame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SetNewSlideCommandTest {
  private SetNewSlideCommand command;
  private Presentation presentation;
  private Frame parent;

  @BeforeEach
  void setUp() {
    presentation = new Presentation();
    parent = new Frame();
    command = new SetNewSlideCommand(presentation, parent);
  }

  @Test
  void testExecuteAddsSingleSlide() {
    assertEquals(0, presentation.getSize());
    command.execute();
    assertEquals(1, presentation.getSize());
  }

  @Test
  void testExecuteAddsMultipleSlides() {
    command.execute();
    command.execute();
    command.execute();
    assertEquals(3, presentation.getSize());
  }

  @Test
  void testExecuteWithExistingSlides() {
    Slide slide = new Slide();
    presentation.append(slide);
    assertEquals(1, presentation.getSize());

    command.execute();
    assertEquals(2, presentation.getSize());
  }

  @Test
  void testExecuteWithNullPresentation() {
    command = new SetNewSlideCommand(null, parent);
    assertThrows(NullPointerException.class, () -> command.execute());
  }

  @Test
  void testExecuteWithNullFrame() {
    command = new SetNewSlideCommand(presentation, null);
    assertThrows(NullPointerException.class, () -> command.execute());
  }
}
