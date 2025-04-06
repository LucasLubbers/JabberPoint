package com.nhlstenden.menu;

import com.nhlstenden.command.Command;
import java.awt.MenuItem;

public class MenuItemFactory {
  public static MenuItem createMenuItem(String name, Command command, String shortcut) {
    MenuItem menuItem = new MenuItem(name + " (" + shortcut + ")");
    menuItem.addActionListener(e -> command.execute());
    return menuItem;
  }
}
