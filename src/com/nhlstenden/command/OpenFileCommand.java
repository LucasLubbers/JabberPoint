package com.nhlstenden.command;

import com.nhlstenden.Presentation;
import com.nhlstenden.XMLAccessor;
import javax.swing.JOptionPane;
import java.awt.Frame;
import java.io.IOException;

public class OpenFileCommand implements Command {
    private Presentation presentation;
    private Frame parent;
    private static final String IOEX = "IO Exception: ";
    private static final String LOADERR = "Load Error";
    private static final String TESTFILE = "test.xml";

    public OpenFileCommand(Presentation presentation, Frame parent) {
        this.presentation = presentation;
        this.parent = parent;
    }

    @Override
    public void execute() {
        presentation.clear();
        try {
            new XMLAccessor().loadFile(presentation, TESTFILE);
            presentation.setSlideNumber(0);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(parent, IOEX + e, LOADERR, JOptionPane.ERROR_MESSAGE);
        }
        parent.repaint();
    }
}