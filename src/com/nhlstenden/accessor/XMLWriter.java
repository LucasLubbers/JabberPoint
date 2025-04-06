package com.nhlstenden.accessor;

import com.nhlstenden.Presentation;
import com.nhlstenden.factory_method.BitmapItem;
import com.nhlstenden.factory_method.Slide;
import com.nhlstenden.factory_method.SlideItem;
import com.nhlstenden.factory_method.TextItem;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import java.util.logging.Logger;

public class XMLWriter {
    private static final Logger logger = Logger.getLogger(XMLWriter.class.getName());

    public void saveFile(Presentation presentation, String filename) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            out.println("<?xml version=\"1.0\"?>");
            out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
            out.println("<presentation>");
            out.print("<showtitle>");
            out.print(presentation.getTitle());
            out.println("</showtitle>");

            logger.info("Saving presentation: " + presentation.getTitle());
            logger.info("Number of slides: " + presentation.getSize());

            for (int slideNumber = 0; slideNumber < presentation.getSize(); slideNumber++) {
                Slide slide = presentation.getSlide(slideNumber);
                out.println("<slide>");
                out.println("<title>" + slide.getTitle() + "</title>");

                logger.info("Saving slide: " + slide.getTitle());
                Vector<SlideItem> slideItems = slide.getSlideItems();
                logger.info("Number of items in slide: " + slideItems.size());

                for (SlideItem slideItem : slideItems) {
                    out.print("<item kind=");
                    if (slideItem instanceof TextItem) {
                        out.print("\"text\" level=\"" + slideItem.getLevel() + "\">");
                        out.print(((TextItem) slideItem).getText());
                        logger.info("Saving text item: " + ((TextItem) slideItem).getText());
                    } else if (slideItem instanceof BitmapItem) {
                        out.print("\"image\" level=\"" + slideItem.getLevel() + "\">");
                        out.print(((BitmapItem) slideItem).getName());
                        logger.info("Saving image item: " + ((BitmapItem) slideItem).getName());
                    } else {
                        logger.warning("Ignoring " + slideItem);
                    }
                    out.println("</item>");
                }
                out.println("</slide>");
            }
            out.println("</presentation>");
            logger.info("Presentation saved to " + filename);
        }
    }
}