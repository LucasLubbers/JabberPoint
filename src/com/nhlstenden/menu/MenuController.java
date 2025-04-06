package com.nhlstenden.menu;

import com.nhlstenden.Presentation;
import com.nhlstenden.slide_viewer.SlideViewerFrame;
import com.nhlstenden.memento.CareTaker;
import java.awt.Frame;

public class MenuController {
    private Frame parent;
    private Presentation presentation;
    private CareTaker careTaker;
    private SlideViewerFrame slideViewerFrame;
    private java.awt.MenuBar menuBar;

    public MenuController(Frame frame, Presentation presentation, SlideViewerFrame slideViewerFrame) {
        this.parent = frame;
        this.presentation = presentation;
        this.slideViewerFrame = slideViewerFrame;
        this.careTaker = new CareTaker();
        this.menuBar = new java.awt.MenuBar();

        menuBar.add(FileMenu.createFileMenu(presentation, parent, slideViewerFrame));
        menuBar.add(ViewMenu.createViewMenu(presentation, parent));
        menuBar.add(EditMenu.createEditMenu(presentation, parent, careTaker));
        menuBar.add(HelpMenu.createHelpMenu(parent));
    }

    public java.awt.MenuBar getMenuBar() {
        return menuBar;
    }
}