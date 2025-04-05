package com.nhlstenden;

import com.nhlstenden.factory_method.Slide;
import com.nhlstenden.slide_viewer.SlideViewerComponent;
import java.util.ArrayList;
import java.util.List;

public class Presentation {

  private String showTitle;
  private List<Slide> showList;
  private int currentSlideNumber;
  private SlideViewerComponent slideViewComponent;

  public Presentation() {
    this.showList = new ArrayList<>();
    this.currentSlideNumber = -1;
  }

  public Presentation(SlideViewerComponent slideViewerComponent) {
    this();
    this.slideViewComponent = slideViewerComponent;
  }

  public int getSize() {
    return showList.size();
  }

  public String getTitle() {
    return showTitle;
  }

  public void setTitle(String title) {
    this.showTitle = title;
  }

  public void setShowView(SlideViewerComponent slideViewerComponent) {
    this.slideViewComponent = slideViewerComponent;
  }

  public int getSlideNumber() {
    return currentSlideNumber;
  }

  public void setSlideNumber(int number) {
    if (number >= 0 && number < showList.size()) {
      this.currentSlideNumber = number;
      notifyObservers();
    }
  }

  public void deleteSlide(int index) {
    if (index >= 0 && index < showList.size()) {
      showList.remove(index);
    }
  }

  public void prevSlide() {
    if (currentSlideNumber > 0) {
      setSlideNumber(currentSlideNumber - 1);
    }
  }

  public void nextSlide() {
    if (currentSlideNumber < showList.size() - 1) {
      setSlideNumber(currentSlideNumber + 1);
    }
  }

  public void clear() {
    showList.clear();
    currentSlideNumber = -1;
    notifyObservers();
  }

  public void append(Slide slide) {
    showList.add(slide);
    if (showList.size() == 1) {
      setSlideNumber(0);
    }
  }

  public Slide getSlide(int number) {
    if (number >= 0 && number < showList.size()) {
      return showList.get(number);
    }
    return null;
  }

  public Slide getCurrentSlide() {
    return getSlide(currentSlideNumber);
  }

  public int getCurrentSlideNumber() {
    return currentSlideNumber;
  }

  public List<Slide> getShowList() {
    return showList;
  }

  public void setShowList(List<Slide> showList) {
    this.showList = showList;
  }

  public void setCurrentSlideNumber(int number) {
    this.currentSlideNumber = number;
    notifyObservers();
  }

  private void notifyObservers() {
    if (slideViewComponent != null) {
      slideViewComponent.update(this, getCurrentSlide());
    }
  }

  public void exit(int status) {
    System.exit(status);
  }
}
