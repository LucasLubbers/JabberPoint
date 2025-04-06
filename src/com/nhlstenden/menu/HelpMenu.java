package com.nhlstenden.menu;

import com.nhlstenden.AboutBox;
import java.awt.Frame;
import java.awt.Menu;

public class HelpMenu {
    private static final String HELP = "Help";
    private static final String ABOUT = "About";

    public static Menu createHelpMenu(Frame parent) {
        Menu helpMenu = new Menu(HELP);
        helpMenu.add(MenuItemFactory.createMenuItem(ABOUT, () -> AboutBox.show(parent), "A"));
        return helpMenu;
    }
}