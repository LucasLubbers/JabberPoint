package main.java.nhlstenden.memento;

import main.java.nhlstenden.Slide;

import java.util.ArrayList;

public class PresentationMemento {

    public int savedSlideNumber;
    public ArrayList<Slide> savedShowList;

    public PresentationMemento(int savedSlideNumber, ArrayList<Slide> savedShowList)
    {
        this.savedSlideNumber = savedSlideNumber;
        this.savedShowList = savedShowList;
    }

    public int getSavedSlideNumber()
    {
        return this.savedSlideNumber;
    }

    public void setSavedSlideNumber(int savedSlideNumber)
    {
        this.savedSlideNumber = savedSlideNumber;
    }

    public ArrayList<Slide> getSavedShowList()
    {
        return this.savedShowList;
    }

    public void setSavedShowList(ArrayList<Slide> savedShowList)
    {
        this.savedShowList = savedShowList;
    }
}