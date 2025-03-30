import com.nhlstenden.slide_items.TextItem; // Adjust the package name as needed
import com.nhlstenden.slide_items.BitmapItem; // Adjust the package name as needed

public abstract class SlideItemFactory {

  public static SlideItem createSlideItem(String type, int level, String content) {
    switch (type.toLowerCase()) {
      case "text":
        return new TextItem(level, content);
      case "bitmap":
        return new BitmapItem(level, content);
      default:
        throw new IllegalArgumentException("Unknown SlideItem type: " + type);
    }
  }
}
