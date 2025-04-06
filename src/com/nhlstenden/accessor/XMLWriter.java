package com.nhlstenden.accessor;

import com.nhlstenden.Presentation;
import com.nhlstenden.factory_method.BitmapItem;
import com.nhlstenden.factory_method.Slide;
import com.nhlstenden.factory_method.SlideItem;
import com.nhlstenden.factory_method.TextItem;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

public class XMLWriter {

  // Method to save a Presentation object to an XML file
  public void saveFile(Presentation presentation, String filename) throws IOException {
    try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
      out.println("<?xml version=\"1.0\"?>");
      out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
      out.println("<presentation>");
      out.print("<showtitle>");
      out.print(presentation.getTitle());
      out.println("</showtitle>");
      // Iterate through each slide in the presentation
      for (int slideNumber = 0; slideNumber < presentation.getSize(); slideNumber++) {
        Slide slide = presentation.getSlide(slideNumber);
        out.println("<slide>");
        out.println("<title>" + slide.getTitle() + "</title>");
        Vector<SlideItem> slideItems = slide.getSlideItems();
        // Iterate through each item in the slide
        for (SlideItem slideItem : slideItems) {
          out.print("<item kind=");
          // Determine the type of slide item and print its details
          if (slideItem instanceof TextItem) {
            out.print("\"text\" level=\"" + slideItem.getLevel() + "\">");
            out.print(((TextItem) slideItem).getText());
          } else if (slideItem instanceof BitmapItem) {
            out.print("\"image\" level=\"" + slideItem.getLevel() + "\">");
            out.print(((BitmapItem) slideItem).getName());
          } else {
            System.out.println("Ignoring " + slideItem);
          }
          out.println("</item>");
        }
        out.println("</slide>");
      }
      out.println("</presentation>");
    }
  }
}
