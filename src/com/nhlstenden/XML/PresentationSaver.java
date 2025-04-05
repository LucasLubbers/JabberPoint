package com.nhlstenden;

import com.nhlstenden.factory_method.BitmapItem;
import com.nhlstenden.factory_method.Slide;
import com.nhlstenden.factory_method.SlideItem;
import com.nhlstenden.factory_method.TextItem;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PresentationSaver {

  public void saveFile(Presentation presentation, String filename) throws IOException {
    try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
      out.println("<?xml version=\"1.0\"?>");
      out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
      out.println("<presentation>");
      out.print("<showtitle>");
      out.print(presentation.getTitle());
      out.println("</showtitle>");
      for (int slideNumber = 0; slideNumber < presentation.getSize(); slideNumber++) {
        Slide slide = presentation.getSlide(slideNumber);
        out.println("<slide>");
        out.println("<title>" + slide.getTitle() + "</title>");
        for (SlideItem slideItem : slide.getSlideItems()) {
          out.print("<item kind=");
          if (slideItem instanceof TextItem) {
            out.print("\"text\" level=\"" + slideItem.getLevel() + "\">");
            out.print(((TextItem) slideItem).getText());
          } else if (slideItem instanceof BitmapItem) {
            out.print("\"image\" level=\"" + slideItem.getLevel() + "\">");
            out.print(((BitmapItem) slideItem).getName());
          }
          out.println("</item>");
        }
        out.println("</slide>");
      }
      out.println("</presentation>");
    }
  }
}
