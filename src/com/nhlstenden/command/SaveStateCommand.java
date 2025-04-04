package com.nhlstenden.command;

import com.nhlstenden.Presentation;
import com.nhlstenden.memento.CareTaker;
import javax.swing.JOptionPane;
import java.awt.Frame;

public class SaveStateCommand implements Command {
    private Presentation presentation;
    private Frame parent;
    private CareTaker careTaker;

    public SaveStateCommand(Presentation presentation, Frame parent, CareTaker careTaker) {
        this.presentation = presentation;
        this.parent = parent;
        this.careTaker = careTaker;
    }

    @Override
    public void execute() {
        careTaker.saveState(presentation);
        JOptionPane.showMessageDialog(parent, "Presentation state saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}