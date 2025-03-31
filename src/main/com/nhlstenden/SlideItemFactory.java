package main.com.nhlstenden;

import com.nhlstenden.factory_method.TextItem;
public abstract class SlideItemFactory {

  public static SlideItem createSlideItem(String type, int level, String content) {
    switch (type.toLowerCase()) {
      case "text":
        return new com.nhlstenden.factory_method.TextItem(level, content);
      case "bitmap":
        return new BitmapItem();
      default:
        throw new IllegalArgumentException("Unknown SlideItem type: " + type);
    }
  }
}
