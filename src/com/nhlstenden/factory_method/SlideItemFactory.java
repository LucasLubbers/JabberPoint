package com.nhlstenden.factory_method;

public abstract class SlideItemFactory {
  public abstract SlideItem createSlideItem(int level, String message);
}
