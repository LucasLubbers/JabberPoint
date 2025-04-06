package com.nhlstenden.menu;

import com.nhlstenden.Presentation;
import com.nhlstenden.command.*;
import com.nhlstenden.slide_viewer.SlideViewerFrame;
import java.awt.Frame;
import java.awt.Menu;

public class FileMenu {
    private static final String FILE = "File";
    private static final String OPEN = "Open";
    private static final String NEW = "New";
    private static final String NEW_SLIDE = "New Slide";
    private static final String SAVE = "Save";
    private static final String EXIT = "Exit";

    public static Menu createFileMenu(Presentation presentation, Frame parent, SlideViewerFrame slideViewerFrame) {
        return getMenu(presentation, parent, slideViewerFrame, FILE, OPEN, NEW, NEW_SLIDE, SAVE, EXIT);
    }

    static Menu getMenu(Presentation presentation, Frame parent, SlideViewerFrame slideViewerFrame, String file, String open, String aNew, String newSlide, String save, String exit)
    {
        Menu fileMenu = new Menu(file);
        fileMenu.add(MenuItemFactory.createMenuItem(open, new OpenFileCommand(presentation, parent, slideViewerFrame), "O"));
        fileMenu.add(MenuItemFactory.createMenuItem(aNew, new NewPresentationCommand(presentation, parent, slideViewerFrame), "N"));
        fileMenu.add(MenuItemFactory.createMenuItem(newSlide, new SetNewSlideCommand(presentation, parent), "S"));
        fileMenu.add(MenuItemFactory.createMenuItem(save, new SaveFileCommand(presentation, parent), "S"));
        fileMenu.addSeparator();
        fileMenu.add(MenuItemFactory.createMenuItem(exit, () -> presentation.exit(0), "Q"));
        return fileMenu;
    }
}