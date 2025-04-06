package com.nhlstenden.accessor;

import com.nhlstenden.Presentation;
import java.io.IOException;

public abstract class Accessor {
  // Abstract method to load a file into a Presentation object
  public abstract void loadFile(Presentation presentation, String filename) throws IOException;

  // Abstract method to save a Presentation object to a file
  public abstract void saveFile(Presentation presentation, String filename) throws IOException;
}
