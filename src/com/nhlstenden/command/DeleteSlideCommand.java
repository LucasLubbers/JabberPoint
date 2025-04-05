package com.nhlstenden.command;

import com.nhlstenden.Presentation;
import javax.swing.JOptionPane;
import java.awt.Frame;

public class DeleteSlideCommand implements Command {
    private Presentation presentation;
    private Frame parent;

    public DeleteSlideCommand(Presentation presentation, Frame parent) {
        this.presentation = presentation;
        this.parent = parent;
    }

    @Override
    public void execute() {
        String input = JOptionPane.showInputDialog(parent, "Enter the slide number to delete:");
        if (input != null && !input.trim().isEmpty()) {
            try {
                int slideNumber = Integer.parseInt(input) - 1; // Convert to zero-based index
                presentation.deleteSlide(slideNumber);
                parent.repaint();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(parent, "Invalid slide number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}