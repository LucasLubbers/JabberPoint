package com.nhlstenden.accessor;

import com.nhlstenden.Presentation;
import com.nhlstenden.factory_method.BitmapItem;
import com.nhlstenden.factory_method.Slide;
import com.nhlstenden.factory_method.TextItem;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReader {

  // Constants for XML tags and error messages
  private static final String SHOWTITLE = "showtitle";
  private static final String SLIDETITLE = "title";
  private static final String SLIDE = "slide";
  private static final String ITEM = "item";
  private static final String LEVEL = "level";
  private static final String KIND = "kind";
  private static final String TEXT = "text";
  private static final String IMAGE = "image";
  private static final String PCE = "Parser Configuration Exception";
  private static final String UNKNOWNTYPE = "Unknown Element type";
  private static final String NFE = "Number Format Exception";

  // Method to load an XML file and populate the Presentation object
  public void loadFile(Presentation presentation, String filename) throws IOException {
    try {
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document document = builder.parse(new File(filename));
      Element doc = document.getDocumentElement();
      presentation.setTitle(getTitle(doc, SHOWTITLE));

      NodeList slides = doc.getElementsByTagName(SLIDE);
      for (int slideNumber = 0; slideNumber < slides.getLength(); slideNumber++) {
        Element xmlSlide = (Element) slides.item(slideNumber);
        Slide slide = new Slide();
        slide.setTitle(getTitle(xmlSlide, SLIDETITLE));
        presentation.append(slide);

        NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);
        for (int itemNumber = 0; itemNumber < slideItems.getLength(); itemNumber++) {
          Element item = (Element) slideItems.item(itemNumber);
          loadSlideItem(slide, item);
        }
      }
    } catch (IOException | SAXException | ParserConfigurationException e) {
      System.err.println(e.getMessage());
    }
  }

  // Helper method to get the title from an XML element
  private String getTitle(Element element, String tagName) {
    NodeList titles = element.getElementsByTagName(tagName);
    return titles.item(0).getTextContent();
  }

  // Helper method to load a slide item from an XML element
  private void loadSlideItem(Slide slide, Element item) {
    int level = 1;
    NamedNodeMap attributes = item.getAttributes();
    String levelText = attributes.getNamedItem(LEVEL).getTextContent();
    if (levelText != null) {
      try {
        level = Integer.parseInt(levelText);
      } catch (NumberFormatException e) {
        System.err.println(NFE);
      }
    }

    // Determine the type of slide item (text or image)
    String type = attributes.getNamedItem(KIND).getTextContent();
    if (TEXT.equals(type)) {
      slide.append(new TextItem(level, item.getTextContent()));
    } else if (IMAGE.equals(type)) {
      slide.append(new BitmapItem(level, item.getTextContent()));
    } else {
      System.err.println(UNKNOWNTYPE);
    }
  }
}
