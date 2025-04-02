package com.nhlstenden.memento;

import static org.junit.jupiter.api.Assertions.*;

import com.nhlstenden.Presentation;
import com.nhlstenden.factory_method.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CareTakerTest {

  private CareTaker careTaker;
  private Presentation presentation;

  @BeforeEach
  void setUp() {
    careTaker = new CareTaker();
    presentation = new Presentation();

    Slide slide = new Slide();
    presentation.append(slide);
    presentation.setSlideNumber(0);
  }

  @Test
  void testSaveState() {
    careTaker.saveState(presentation);
    assertTrue(careTaker.hasSavedState());
  }

  @Test
  void testRestoreState() {
    careTaker.saveState(presentation);
    presentation.setSlideNumber(5);
    careTaker.restoreState(presentation);
    assertEquals(0, presentation.getSlideNumber());
  }

  @Test
  void testClearHistory() {
    careTaker.saveState(presentation);
    careTaker.clearHistory();
    assertFalse(careTaker.hasSavedState());
  }

  @Test
  void testRestoreStateWhenEmpty() {
    Presentation emptyPresentation = new Presentation();
    careTaker.restoreState(emptyPresentation);
    assertEquals(0, emptyPresentation.getSlideNumber());
    assertEquals(0, emptyPresentation.getShowList().size());
  }

  @Test
  void testSaveMultipleStatesAndRestore() {
    presentation.setSlideNumber(1);
    careTaker.saveState(presentation);

    presentation.setSlideNumber(2);
    careTaker.saveState(presentation);

    presentation.setSlideNumber(3);
    careTaker.saveState(presentation);

    careTaker.restoreState(presentation);
    assertEquals(3, presentation.getSlideNumber());

    careTaker.restoreState(presentation);
    assertEquals(2, presentation.getSlideNumber());

    careTaker.restoreState(presentation);
    assertEquals(1, presentation.getSlideNumber());

    careTaker.restoreState(presentation);
    assertEquals(0, presentation.getSlideNumber());

    assertFalse(careTaker.hasSavedState());
  }

  @Test
  void testClearHistoryWhenEmpty() {
    careTaker.clearHistory();
    assertFalse(careTaker.hasSavedState());
  }

  @Test
  void testSaveStateWithNullPresentation() {
    assertThrows(NullPointerException.class, () -> careTaker.saveState(null));
  }

  @Test
  void testRestoreStateWithNullPresentation() {
    assertThrows(NullPointerException.class, () -> careTaker.restoreState(null));
  }

  @Test
  void testRestoreBeyondEarliestState() {
    careTaker.saveState(presentation);
    careTaker.restoreState(presentation);
    careTaker.restoreState(presentation);
    assertEquals(0, presentation.getSlideNumber());
  }
}
