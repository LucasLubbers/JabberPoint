package com.nhlstenden.command;

import com.nhlstenden.Presentation;
import com.nhlstenden.accessor.XMLAccessor;
import com.nhlstenden.slide_viewer.SlideViewerFrame;
import javax.swing.JOptionPane;
import java.awt.Frame;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NewPresentationCommand implements Command {
    private Presentation presentation;
    private Frame parent;
    private SlideViewerFrame slideViewerFrame;
    private static final String SAVEERR = "Save Error";
    private static final String IOEX = "IO Exception: ";
    private static final Map<String, String> savedPresentations = new HashMap<>();

    public NewPresentationCommand(Presentation presentation, Frame parent, SlideViewerFrame slideViewerFrame) {
        this.presentation = presentation;
        this.parent = parent;
        this.slideViewerFrame = slideViewerFrame;
    }

    @Override
    public void execute() {
        String name = JOptionPane.showInputDialog(parent, "Enter the name of the new presentation:");
        if (name != null && !name.trim().isEmpty()) {
            presentation.clear();
            presentation.setTitle(name);
            String filename = name + ".xml";
            savedPresentations.put(name, filename);
            try {
                new XMLAccessor().saveFile(presentation, filename);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(parent, IOEX + e, SAVEERR, JOptionPane.ERROR_MESSAGE);
            }
            presentation.setSlideNumber(0);
            slideViewerFrame.update(presentation, presentation.getCurrentSlide());
            parent.repaint();
        }
    }

    public static Map<String, String> getSavedPresentations() {
        return savedPresentations;
    }
}