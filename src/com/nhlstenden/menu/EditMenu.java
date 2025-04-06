package com.nhlstenden.menu;

import com.nhlstenden.Presentation;
import com.nhlstenden.command.*;
import com.nhlstenden.memento.CareTaker;
import java.awt.Frame;
import java.awt.Menu;

public class EditMenu {
    private static final String EDIT = "Edit";
    private static final String ADD_TEXT = "Add Text Item";
    private static final String ADD_IMAGE = "Add Image Item";
    private static final String SAVE_STATE = "Save State";
    private static final String RESTORE_STATE = "Restore State";
    private static final String DELETE_SLIDE = "Delete Slide";

    public static Menu createEditMenu(Presentation presentation, Frame parent, CareTaker careTaker) {
        return getMenu(presentation, parent, careTaker, EDIT, ADD_TEXT, ADD_IMAGE, SAVE_STATE, RESTORE_STATE, DELETE_SLIDE);
    }

    static Menu getMenu(Presentation presentation, Frame parent, CareTaker careTaker, String edit, String addText, String addImage, String saveState, String restoreState, String deleteSlide)
    {
        Menu editMenu = new Menu(edit);
        editMenu.add(MenuItemFactory.createMenuItem(addText, new AddTextItemCommand(presentation, parent), "T"));
        editMenu.add(MenuItemFactory.createMenuItem(addImage, new AddBitmapItemCommand(presentation, parent), "I"));
        editMenu.add(MenuItemFactory.createMenuItem(saveState, new SaveStateCommand(presentation, parent, careTaker), "F5"));
        editMenu.add(MenuItemFactory.createMenuItem(restoreState, new RestoreStateCommand(presentation, parent, careTaker), "F9"));
        editMenu.add(MenuItemFactory.createMenuItem(deleteSlide, new DeleteSlideCommand(presentation, parent), "D"));
        return editMenu;
    }
}