package com.nhlstenden.XML;

import com.nhlstenden.factory_method.BitmapItem;
import com.nhlstenden.factory_method.Slide;
import com.nhlstenden.factory_method.SlideItem;
import com.nhlstenden.factory_method.TextItem;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

public class XMLSlideParser {

  private static final String LEVEL = "level";
  private static final String KIND = "kind";
  private static final String TEXT = "text";
  private static final String IMAGE = "image";

  public Slide parseSlide(Element xmlSlide) {
    Slide slide = new Slide();
    slide.setTitle(getTitle(xmlSlide, "title"));

    NodeList slideItems = xmlSlide.getElementsByTagName("item");
    for (int itemNumber = 0; itemNumber < slideItems.getLength(); itemNumber++) {
      Element item = (Element) slideItems.item(itemNumber);
      slide.append(parseSlideItem(item));
    }
    return slide;
  }

  private SlideItem parseSlideItem(Element item) {
    int level = 1; // default
    NamedNodeMap attributes = item.getAttributes();
    String levelText = attributes.getNamedItem(LEVEL).getTextContent();
    if (levelText != null) {
      try {
        level = Integer.parseInt(levelText);
      } catch (NumberFormatException x) {
        System.err.println("Number Format Exception");
      }
    }

    String type = attributes.getNamedItem(KIND).getTextContent();
    if (TEXT.equals(type)) {
      return new TextItem(level, item.getTextContent());
    } else if (IMAGE.equals(type)) {
      return new BitmapItem(level, item.getTextContent());
    } else {
      System.err.println("Unknown Element type");
      return null;
    }
  }

  private String getTitle(Element element, String tagName) {
    NodeList titles = element.getElementsByTagName(tagName);
    return titles.item(0).getTextContent();
  }
}
