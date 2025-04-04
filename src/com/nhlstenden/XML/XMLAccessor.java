package com.nhlstenden.XML;

import com.nhlstenden.Presentation;
import com.nhlstenden.PresentationSaver;

import java.io.IOException;

public class XMLAccessor {

  private final PresentationLoader presentationLoader;
  private final PresentationSaver presentationSaver;

  public XMLAccessor() {
    this.presentationLoader = new PresentationLoader();
    this.presentationSaver = new PresentationSaver();
  }

  public void loadFile(Presentation presentation, String filename) throws IOException {
    presentationLoader.loadFile(presentation, filename);
  }

  public void saveFile(Presentation presentation, String filename) throws IOException {
    presentationSaver.saveFile(presentation, filename);
  }
}
