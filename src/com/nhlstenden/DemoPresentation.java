package com.nhlstenden;

import com.nhlstenden.factory_method.Slide;

/**
 * Een ingebouwde demo-presentatie
 *
 * @author Ian F. Darwin, ian@darwinsys.com
 * @author Gert Florijn
 * @author Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
class DemoPresentation extends Accessor {

  @Override
  public void loadFile(Presentation presentation, String unusedFilename) {
    presentation.setTitle("Demo com.nhlstenden.Presentation");

    Slide slide = new Slide();
    slide.setTitle("com.nhlstenden.JabberPoint");
    slide.appendTextItem(1, "Het Java Presentatie Tool");
    slide.appendTextItem(2, "Copyright (c) 1996-2000: Ian Darwin");
    slide.appendTextItem(2, "Copyright (c) 2000-now:");
    slide.appendTextItem(2, "Gert Florijn en Sylvia Stuurman");
    slide.appendTextItem(4, "com.nhlstenden.JabberPoint aanroepen zonder bestandsnaam");
    slide.appendTextItem(4, "laat deze presentatie zien");
    slide.appendTextItem(1, "Navigeren:");
    slide.appendTextItem(3, "Volgende slide: PgDn of Enter");
    slide.appendTextItem(3, "Vorige slide: PgUp of up-arrow");
    slide.appendTextItem(3, "Stoppen: q or Q");
    presentation.append(slide);

    slide = new Slide();
    slide.setTitle("Demonstratie van levels en stijlen");
    slide.appendTextItem(1, "Level 1");
    slide.appendTextItem(2, "Level 2");
    slide.appendTextItem(1, "Nogmaals level 1");
    slide.appendTextItem(1, "Level 1 heeft stijl nummer 1");
    slide.appendTextItem(2, "Level 2 heeft stijl nummer 2");
    slide.appendTextItem(3, "Zo ziet level 3 er uit");
    slide.appendTextItem(4, "En dit is level 4");
    presentation.append(slide);

    slide = new Slide();
    slide.setTitle("De derde slide");
    slide.appendTextItem(1, "Om een nieuwe presentatie te openen,");
    slide.appendTextItem(2, "gebruik File->Open uit het menu.");
    slide.appendTextItem(1, " ");
    slide.appendTextItem(1, "Dit is het einde van de presentatie.");
    slide.appendBitmapItem(1, "com.nhlstenden.JabberPoint.jpg");
    presentation.append(slide);
  }

  @Override
  public void saveFile(Presentation presentation, String unusedFilename) {
    throw new IllegalStateException("Save As->Demo! aangeroepen");
  }
}
