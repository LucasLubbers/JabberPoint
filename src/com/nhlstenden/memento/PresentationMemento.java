package com.nhlstenden.memento;

import com.nhlstenden.factory_method.Slide;
import java.util.ArrayList;
import java.util.List;

public class PresentationMemento {
  private final int savedSlideNumber;
  private final List<Slide> savedShowList;

  public PresentationMemento(int slideNumber, List<Slide> showList) {
    this.savedSlideNumber = slideNumber;
    this.savedShowList = new ArrayList<>();
    for (Slide slide : showList) {
      this.savedShowList.add(slide.clone());
    }
  }

  public int getSavedSlideNumber() {
    return savedSlideNumber;
  }

  public List<Slide> getSavedShowList() {
    return savedShowList;
  }
}
