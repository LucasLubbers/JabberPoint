package com.nhlstenden;

import com.nhlstenden.memento.CareTaker;
import com.nhlstenden.factory_method.Slide;

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * De controller voor het menu
 *
 * @author Ian F. Darwin
 * @author Gert Florijn
 * @author Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends MenuBar {

    private Frame parent; // Het frame, gebruikt voor dialogs
    private Presentation presentation; // De presentatie waar commando's aan worden gegeven
    private static final long serialVersionUID = 227L;
    private CareTaker careTaker = new CareTaker(); // Memento CareTaker

    protected static final String FILE = "File";
    protected static final String EDIT = "Edit";
    protected static final String VIEW = "View";
    protected static final String HELP = "Help";

    protected static final String OPEN = "Open";
    protected static final String NEW = "New";
    protected static final String NEW_SLIDE = "New Slide";
    protected static final String SAVE = "Save";
    protected static final String EXIT = "Exit";

    protected static final String NEXT = "Next";
    protected static final String PREV = "Prev";
    protected static final String GOTO = "Go to";

    protected static final String ADD_TEXT = "Add Text Item";
    protected static final String ADD_IMAGE = "Add Image Item";

    protected static final String SAVE_STATE = "Save State";
    protected static final String RESTORE_STATE = "Restore State";

    protected static final String ABOUT = "About";
    protected static final String TESTFILE = "test.xml";
    protected static final String SAVEFILE = "dump.xml";

    protected static final String IOEX = "IO Exception: ";
    protected static final String LOADERR = "Load Error";
    protected static final String SAVEERR = "Save Error";
    protected static final String PAGENR = "Page number?";

    public MenuController(Frame frame, Presentation pres) {
        parent = frame;
        presentation = pres;

        add(createFileMenu());
        add(createViewMenu());
        add(createEditMenu());
        add(createHelpMenu());
    }

    private Menu createFileMenu() {
        Menu fileMenu = new Menu(FILE);
        MenuItem menuItem;

        fileMenu.add(menuItem = mkMenuItem(OPEN));
        menuItem.addActionListener(e -> openFile());

        fileMenu.add(menuItem = mkMenuItem(NEW));
        menuItem.addActionListener(e -> newPresentation());

        fileMenu.add(menuItem = mkMenuItem(NEW_SLIDE));
        menuItem.addActionListener(e -> setNewSlide());

        fileMenu.add(menuItem = mkMenuItem(SAVE));
        menuItem.addActionListener(e -> saveFile());

        fileMenu.addSeparator();
        fileMenu.add(menuItem = mkMenuItem(EXIT));
        menuItem.addActionListener(e -> presentation.exit(0));

        return fileMenu;
    }

    private Menu createViewMenu() {
        Menu viewMenu = new Menu(VIEW);
        MenuItem menuItem;

        viewMenu.add(menuItem = mkMenuItem(NEXT));
        menuItem.addActionListener(e -> presentation.nextSlide());

        viewMenu.add(menuItem = mkMenuItem(PREV));
        menuItem.addActionListener(e -> presentation.prevSlide());

        viewMenu.add(menuItem = mkMenuItem(GOTO));
        menuItem.addActionListener(e -> goToSlide());

        return viewMenu;
    }

    private Menu createEditMenu() {
        Menu editMenu = new Menu(EDIT);
        MenuItem menuItem;

        editMenu.add(menuItem = mkMenuItem(ADD_TEXT));
        menuItem.addActionListener(e -> addTextItem());

        editMenu.add(menuItem = mkMenuItem(ADD_IMAGE));
        menuItem.addActionListener(e -> addBitmapItem());

        editMenu.add(menuItem = mkMenuItem(SAVE_STATE));
        menuItem.addActionListener(e -> saveState());

        editMenu.add(menuItem = mkMenuItem(RESTORE_STATE));
        menuItem.addActionListener(e -> restoreState());
        
        return editMenu;
    }

    private Menu createHelpMenu() {
        Menu helpMenu = new Menu(HELP);
        MenuItem menuItem;

        helpMenu.add(menuItem = mkMenuItem(ABOUT));
        menuItem.addActionListener(e -> AboutBox.show(parent));

        setHelpMenu(helpMenu);
        return helpMenu;
    }

    private void openFile() {
        presentation.clear();
        try {
            new XMLAccessor().loadFile(presentation, TESTFILE);
            presentation.setSlideNumber(0);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(parent, IOEX + e, LOADERR, JOptionPane.ERROR_MESSAGE);
        }
        parent.repaint();
    }

    private void newPresentation() {
        presentation.clear();
        parent.repaint();
    }

    private void setNewSlide() {
        presentation.append(new Slide());
        parent.repaint();
    }

    private void saveFile() {
        try {
            new XMLAccessor().saveFile(presentation, SAVEFILE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(parent, IOEX + e, SAVEERR, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goToSlide() {
        String pageNumberStr = JOptionPane.showInputDialog(PAGENR);
        try {
            int pageNumber = Integer.parseInt(pageNumberStr);
            presentation.setSlideNumber(pageNumber - 1);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parent, "Invalid page number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ensureSlideExists() {
        if (presentation.getCurrentSlide() == null) {
            Slide newSlide = new Slide(); // Create a new slide
            presentation.append(newSlide); // Append the slide to the presentation
            presentation.setSlideNumber(presentation.getSize() - 1); // Set the new slide as the current slide
        }
    }

    private void addTextItem() {
        // Vraag de gebruiker om een level (moet een getal zijn)
        ensureSlideExists();

        String levelInput = JOptionPane.showInputDialog("Enter level (0-5):");

        int level;
        try {
            level = Integer.parseInt(levelInput);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parent, "Invalid level! Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop als de invoer geen getal is
        }

        // Vraag de gebruiker om de tekst
        String text = JOptionPane.showInputDialog("Enter text for the new item:");

        if (text != null && !text.trim().isEmpty()) {
            presentation.getCurrentSlide().appendTextItem(level, text);
            parent.repaint();
        }
    }

    private void addBitmapItem() {
        ensureSlideExists(); // Ensure a slide exists before adding a bitmap item

        String levelInput = JOptionPane.showInputDialog("Enter level (0-5):");
        int level;
        try {
            level = Integer.parseInt(levelInput);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parent, "Invalid level! Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String imageName = JOptionPane.showInputDialog("Enter the file path for the bitmap:");
        if (imageName != null && !imageName.trim().isEmpty()) {
            try {
                presentation.getCurrentSlide().appendBitmapItem(level, imageName);
                parent.repaint();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(parent, "Failed to add bitmap: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public MenuItem mkMenuItem(String name) {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }

    private void saveState() {
        careTaker.saveState(presentation);
        JOptionPane.showMessageDialog(parent, "Presentation state saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void restoreState() {
        if (careTaker.hasSavedState()) {
            careTaker.restoreState(presentation);
            parent.repaint();
            JOptionPane.showMessageDialog(parent, "Presentation state restored!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(parent, "No saved state to restore!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
