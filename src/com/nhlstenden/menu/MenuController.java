package com.nhlstenden.menu;

import com.nhlstenden.AboutBox;
import com.nhlstenden.Presentation;
import com.nhlstenden.slide_viewer.SlideViewerFrame;
import com.nhlstenden.command.*;
import com.nhlstenden.memento.CareTaker;
import java.awt.Frame;
import java.awt.Menu;


/**
 * De controller voor het menu
 */
public class MenuController {
    private Frame parent; // Het frame, gebruikt voor dialogs
    private Presentation presentation; // De presentatie waar commando's aan worden gegeven
    private CareTaker careTaker = new CareTaker(); // Memento CareTaker
    private SlideViewerFrame slideViewerFrame;
    private java.awt.MenuBar menuBar;

    // Menu categorien
    protected static final String FILE = "File";
    protected static final String EDIT = "Edit";
    protected static final String VIEW = "View";
    protected static final String HELP = "Help";

    // File menu items
    protected static final String OPEN = "Open";
    protected static final String NEW = "New";
    protected static final String NEW_SLIDE = "New Slide";
    protected static final String SAVE = "Save";
    protected static final String EXIT = "Exit";

    // View menu items
    protected static final String NEXT = "Next";
    protected static final String PREV = "Prev";
    protected static final String GOTO = "Go to";

    // Edit menu items
    protected static final String ADD_TEXT = "Add Text Item";
    protected static final String ADD_IMAGE = "Add Image Item";
    protected static final String SAVE_STATE = "Save State";
    protected static final String RESTORE_STATE = "Restore State";
    protected static final String DELETE_SLIDE = "Delete Slide";

    // Help menu items
    protected static final String ABOUT = "About";

    public MenuController(Frame frame, Presentation presentation, SlideViewerFrame slideViewerFrame) {
        this.parent = frame;
        this.presentation = presentation;
        this.slideViewerFrame = slideViewerFrame;
        this.menuBar = new java.awt.MenuBar();

        menuBar.add(createFileMenu());
        menuBar.add(createViewMenu());
        menuBar.add(createEditMenu());
        menuBar.add(createHelpMenu());
    }

    public java.awt.MenuBar getMenuBar() {
        return menuBar;
    }

    private Menu createFileMenu() {
        Menu fileMenu = new Menu(FILE);
        fileMenu.add(MenuItemFactory.createMenuItem(OPEN, new OpenFileCommand(presentation, parent, slideViewerFrame), "O"));
        fileMenu.add(MenuItemFactory.createMenuItem(NEW, new NewPresentationCommand(presentation, parent, slideViewerFrame), "N"));
        fileMenu.add(MenuItemFactory.createMenuItem(NEW_SLIDE, new SetNewSlideCommand(presentation, parent), "S"));
        fileMenu.add(MenuItemFactory.createMenuItem(SAVE, new SaveFileCommand(presentation, parent), "S"));
        fileMenu.addSeparator();
        fileMenu.add(MenuItemFactory.createMenuItem(EXIT, () -> presentation.exit(0), "Q"));
        return fileMenu;
    }

    private Menu createViewMenu() {
        Menu viewMenu = new Menu(VIEW);
        viewMenu.add(MenuItemFactory.createMenuItem(NEXT, presentation::nextSlide, "Page Down"));
        viewMenu.add(MenuItemFactory.createMenuItem(PREV, presentation::prevSlide, "Page Up"));
        viewMenu.add(MenuItemFactory.createMenuItem(GOTO, new GoToSlideCommand(presentation, parent), "G"));
        return viewMenu;
    }

    private Menu createEditMenu() {
        Menu editMenu = new Menu(EDIT);
        editMenu.add(MenuItemFactory.createMenuItem(ADD_TEXT, new AddTextItemCommand(presentation, parent), "T"));
        editMenu.add(MenuItemFactory.createMenuItem(ADD_IMAGE, new AddBitmapItemCommand(presentation, parent), "I"));
        editMenu.add(MenuItemFactory.createMenuItem(SAVE_STATE, new SaveStateCommand(presentation, parent, careTaker), "F5"));
        editMenu.add(MenuItemFactory.createMenuItem(RESTORE_STATE, new RestoreStateCommand(presentation, parent, careTaker), "F9"));
        editMenu.add(MenuItemFactory.createMenuItem(DELETE_SLIDE, new DeleteSlideCommand(presentation, parent), "D"));
        return editMenu;
    }

    private Menu createHelpMenu() {
        Menu helpMenu = new Menu(HELP);
        helpMenu.add(MenuItemFactory.createMenuItem(ABOUT, () -> AboutBox.show(parent), "A"));
        return helpMenu;
    }
}