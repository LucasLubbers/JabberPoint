package com.nhlstenden.command;

import com.nhlstenden.Presentation;
import javax.swing.JOptionPane;
import java.awt.Frame;

public class GoToSlideCommand implements Command {
    private Presentation presentation;
    private Frame parent;
    private static final String PAGENR = "Page number?";

    public GoToSlideCommand(Presentation presentation, Frame parent) {
        this.presentation = presentation;
        this.parent = parent;
    }

    @Override
    public void execute() {
        String pageNumberStr = JOptionPane.showInputDialog(PAGENR);
        try {
            int pageNumber = Integer.parseInt(pageNumberStr);
            presentation.setSlideNumber(pageNumber - 1);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parent, "Invalid page number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}