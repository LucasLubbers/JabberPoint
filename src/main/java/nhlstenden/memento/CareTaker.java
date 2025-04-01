package main.java.nhlstenden.memento;

import main.java.nhlstenden.Presentation;

import java.util.ArrayList;
import java.util.Stack;

public class CareTaker {
    private final Stack<PresentationMemento> mementoStack;

    public CareTaker() {
        this.mementoStack = new Stack<>();
    }

    public void saveState(Presentation presentation) {
        PresentationMemento memento = new PresentationMemento(
                presentation.getSlideNumber(),
                new ArrayList<>(presentation.getShowList())
        );
        mementoStack.push(memento);
    }

    public void restoreState(Presentation presentation) {
        if (!mementoStack.isEmpty()) {
            PresentationMemento memento = mementoStack.pop();
            presentation.setSlideNumber(memento.getSavedSlideNumber());
            presentation.setShowList(new ArrayList<>(memento.getSavedShowList()));
        } else {
            presentation.clear();
        }
    }

    public boolean hasSavedState() {
        return !mementoStack.isEmpty();
    }

    public void clearHistory() {
        mementoStack.clear();
    }
}