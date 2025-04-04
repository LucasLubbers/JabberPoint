package com.nhlstenden.XML;

import com.nhlstenden.Presentation;
import com.nhlstenden.factory_method.Slide;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class PresentationLoader {

    private XMLSlideParser slideParser;

    public PresentationLoader() {
        slideParser = new XMLSlideParser();
    }

    public void loadFile(Presentation presentation, String filename) throws IOException {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new File(filename));
            Element doc = document.getDocumentElement();
            presentation.setTitle(getTitle(doc, "showtitle"));

            NodeList slides = doc.getElementsByTagName("slide");
            for (int slideNumber = 0; slideNumber < slides.getLength(); slideNumber++) {
                Element xmlSlide = (Element) slides.item(slideNumber);
                Slide slide = slideParser.parseSlide(xmlSlide);
                presentation.append(slide);
            }
        } catch (IOException | SAXException | ParserConfigurationException e) {
            System.err.println(e.getMessage());
        }
    }

    private String getTitle(Element element, String tagName) {
        NodeList titles = element.getElementsByTagName(tagName);
        return titles.item(0).getTextContent();
    }
}
