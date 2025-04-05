package com.nhlstenden;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.awt.*;
import org.junit.jupiter.api.Test;

class AboutBoxTest {

  @Test
  void testShowDoesNotThrowException() {
    Frame dummyFrame = new Frame();
    assertDoesNotThrow(() -> AboutBox.show(dummyFrame));
  }
}
