package com.nhlstenden.memento;

import com.nhlstenden.Presentation;
import com.nhlstenden.factory_method.Slide;
import java.util.ArrayList;
import java.util.Stack;

public class CareTaker {
  private final Stack<PresentationMemento> mementoStack = new Stack<>();

  public void saveState(Presentation presentation) {
    System.out.println("=== Saving State ===");
    System.out.println("Current slide number: " + presentation.getCurrentSlideNumber());
    System.out.println("Show list size: " + presentation.getShowList().size());
    System.out.println("Stack size before save: " + mementoStack.size());

    mementoStack.push(
        new PresentationMemento(presentation.getCurrentSlideNumber(), presentation.getShowList()));

    System.out.println("Stack size after save: " + mementoStack.size());
  }

  public void restoreState(Presentation presentation) {
    System.out.println("=== Restoring State ===");
    System.out.println("Stack size before restore: " + mementoStack.size());

    if (!mementoStack.isEmpty()) {
      PresentationMemento memento = mementoStack.pop();
      System.out.println("Restoring to slide number: " + memento.getSavedSlideNumber());
      System.out.println("Restoring show list size: " + memento.getSavedShowList().size());

      System.out.println("Current slide content before restore:");
      Slide currentSlide = presentation.getCurrentSlide();
      if (currentSlide != null) {
        System.out.println(currentSlide.toString());
      }

      presentation.clear();
      System.out.println("After clear - current slide: " + presentation.getCurrentSlideNumber());

      presentation.setShowList(new ArrayList<>(memento.getSavedShowList()));
      System.out.println(
          "After setShowList - show list size: " + presentation.getShowList().size());

      presentation.setCurrentSlideNumber(memento.getSavedSlideNumber());
      System.out.println(
          "After setCurrentSlideNumber - current slide: " + presentation.getCurrentSlideNumber());

      System.out.println("Current slide content after restore:");
      Slide restoredSlide = presentation.getCurrentSlide();
      if (restoredSlide != null) {
        System.out.println(restoredSlide.toString());
      }

      System.out.println("Stack size after restore: " + mementoStack.size());
    } else {
      System.out.println("No states to restore - stack is empty");
      presentation.clear();
      presentation.setCurrentSlideNumber(0); // Ensure the slide number is reset to 0
    }
  }

  public boolean hasSavedState() {
    return !mementoStack.isEmpty();
  }

  public void clearHistory() {
    mementoStack.clear();
  }
}
