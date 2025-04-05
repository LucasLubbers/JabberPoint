package com.nhlstenden.accessor;

import com.nhlstenden.Presentation;
import java.io.IOException;

public class XMLAccessor extends Accessor {

  private final XMLReader reader = new XMLReader();
  private final XMLWriter writer = new XMLWriter();

  // Method to load a file using XMLReader
  @Override
  public void loadFile(Presentation presentation, String filename) throws IOException {
    reader.loadFile(presentation, filename);
  }

  // Method to save a file using XMLWriter
  @Override
  public void saveFile(Presentation presentation, String filename) throws IOException {
    writer.saveFile(presentation, filename);
  }
}