package main.java.nhlstenden;

import java.util.ArrayList;
import main.java.nhlstenden.memento.PresentationMemento;

public class Presentation {

  private String showTitle; // de titel van de presentatie
  private ArrayList<Slide> showList = null; // een ArrayList met de Slides
  private int currentSlideNumber = 0; // het slidenummer van de huidige Slide
  private SlideViewerComponent slideViewComponent = null; // de viewcomponent voor de Slides

  public Presentation() {
    slideViewComponent = null;
    clear();
  }

  public Presentation(SlideViewerComponent slideViewerComponent) {
    this.slideViewComponent = slideViewerComponent;
    clear();
  }

  public int getSize() {
    return showList.size();
  }

  public String getTitle() {
    return showTitle;
  }

  public void setTitle(String nt) {
    showTitle = nt;
  }

  public ArrayList<Slide> getShowList() {
    return this.showList;
  }

  public void setShowList(ArrayList<Slide> showList) {
    this.showList = showList;
  }

  public void setShowView(SlideViewerComponent slideViewerComponent) {
    this.slideViewComponent = slideViewerComponent;
  }

  // Geef het nummer van de huidige slide
  public int getSlideNumber() {
    return currentSlideNumber;
  }

  // Verander het huidige-slide-nummer en laat het aan het window weten.
  public void setSlideNumber(int number) {
    currentSlideNumber = number;
    if (slideViewComponent != null) {
      slideViewComponent.update(this, getCurrentSlide());
    }
  }

  // Ga naar de vorige slide tenzij je aan het begin van de presentatie bent
  public void prevSlide() {
    if (currentSlideNumber > 0) {
      setSlideNumber(currentSlideNumber - 1);
    }
  }

  // Ga naar de volgende slide tenzij je aan het einde van de presentatie bent.
  public void nextSlide() {
    if (currentSlideNumber < (showList.size() - 1)) {
      setSlideNumber(currentSlideNumber + 1);
    }
  }

  // Verwijder de presentatie, om klaar te zijn voor de volgende
  public void clear() {
    showList = new ArrayList<>();
    setSlideNumber(0);
  }

  // Voeg een slide toe aan de presentatie
  public void append(Slide slide) {
    showList.add(slide);
  }

  // Geef een slide met een bepaald slidenummer
  public Slide getSlide(int number) {
    if (number < 0) {
      return showList.isEmpty() ? null : showList.get(0);
    }
    if (number >= getSize()) {
      return null;
    }
    return showList.get(number);
  }

  // Geef de huidige Slide
  public Slide getCurrentSlide() {
    return getSlide(currentSlideNumber);
  }

  public void exit(int n) {
    System.exit(n);
  }

  // Create Memento of the current state
  public PresentationMemento createMemento() {
    return new PresentationMemento(currentSlideNumber, new ArrayList<>(showList));
  }

  // Restore state from a Memento
  public void restoreStateFromMemento(PresentationMemento memento) {
    this.currentSlideNumber = memento.getSavedSlideNumber();
    this.showList = memento.getSavedShowList();
  }
}
