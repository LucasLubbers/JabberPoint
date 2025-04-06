package com.nhlstenden.menu;

import com.nhlstenden.Presentation;
import com.nhlstenden.command.GoToSlideCommand;
import java.awt.Frame;
import java.awt.Menu;

public class ViewMenu {
    private static final String VIEW = "View";
    private static final String NEXT = "Next";
    private static final String PREV = "Prev";
    private static final String GOTO = "Go to";

    public static Menu createViewMenu(Presentation presentation, Frame parent) {
        return getMenu(presentation, parent, VIEW, NEXT, PREV, GOTO);
    }

    static Menu getMenu(Presentation presentation, Frame parent, String view, String next, String prev, String aGoto)
    {
        Menu viewMenu = new Menu(view);
        viewMenu.add(MenuItemFactory.createMenuItem(next, presentation::nextSlide, "Page Down"));
        viewMenu.add(MenuItemFactory.createMenuItem(prev, presentation::prevSlide, "Page Up"));
        viewMenu.add(MenuItemFactory.createMenuItem(aGoto, new GoToSlideCommand(presentation, parent), "G"));
        return viewMenu;
    }
}