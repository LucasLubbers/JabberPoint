package com.nhlstenden;

import com.nhlstenden.factory_method.Slide;

import java.util.ArrayList;

/**
 * com.nhlstenden.Presentation houdt de slides in de presentatie bij.
 *
 * <p>Er is slechts één instantie van deze klasse aanwezig.
 *
 * @author Ian F. Darwin, ian@darwinsys.com
 * @author Gert Florijn
 * @author Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class Presentation {

  private String showTitle; // de titel van de presentatie
  private ArrayList<Slide> showList; // een ArrayList met de Slides
  private int currentSlideNumber; // het slidenummer van de huidige com.nhlstenden.factory_method.Slide
  private SlideViewerComponent slideViewComponent; // de viewcomponent voor de Slides

  public Presentation() {
    this.showList = new ArrayList<>(); // Initialize showList
    this.currentSlideNumber = -1;
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

  public void clear() {
    showList.clear();
    currentSlideNumber = -1;
    notifyObservers();
  }

  // Voeg een slide toe aan de presentatie
  public void append(Slide slide) {
    showList.add(slide);
    if (showList.size() == 1) { // If it's the first slide, set it as the current slide
      setSlideNumber(0);
    }
  }

  // Geef een slide met een bepaald slidenummer
  public Slide getSlide(int number) {
    if (number < 0 || number >= getSize()) {
      return null;
    }
    return showList.get(number);
  }

  public String getShowTitle()
  {
    return this.showTitle;
  }

  public void setShowTitle(String showTitle)
  {
    this.showTitle = showTitle;
  }

  public ArrayList<Slide> getShowList()
  {
    return this.showList;
  }

  public void setShowList(ArrayList<Slide> list) {
    this.showList = list;
    notifyObservers();
  }

  public int getCurrentSlideNumber()
  {
    return this.currentSlideNumber;
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

  public SlideViewerComponent getSlideViewComponent()
  {
    return this.slideViewComponent;
  }

  public void setSlideViewComponent(SlideViewerComponent slideViewComponent)
  {
    this.slideViewComponent = slideViewComponent;
  }

  public Slide getCurrentSlide() {
    if (currentSlideNumber >= 0 && currentSlideNumber < showList.size()) {
      return showList.get(currentSlideNumber);
    }
    return null;
  }

  public void exit(int n) {
    System.exit(n);
  }
}
