package com.nhlstenden.slide_viewer;

import com.nhlstenden.Presentation;
import com.nhlstenden.factory_method.Slide;
import com.nhlstenden.style.StyleFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class SlideViewerComponentTest {

    private Presentation presentation;
    private Slide slide;
    private SlideViewerComponent component;
    private JFrame frame;

    @BeforeEach
    void setUp() {
        presentation = new Presentation();
        slide = new Slide();
        slide.setTitle("Test Slide");
        presentation.append(slide);
        frame = new JFrame();
        component = new SlideViewerComponent(presentation, frame);
    }

    @Test
    void testPreferredSizeMatchesSlideDimensions() {
        Dimension preferredSize = component.getPreferredSize();
        assertEquals(Slide.WIDTH, preferredSize.width);
        assertEquals(Slide.HEIGHT, preferredSize.height);
    }

    @Test
    void testUpdateChangesFrameTitle() {
        presentation.setTitle("Presentation Title");
        component.update(presentation, slide);

        assertEquals("Presentation Title", frame.getTitle());
    }

    @Test
    void testPaintComponentDoesNotCrash() {
        StyleFactory.createStyles();
        presentation.append(slide);
        presentation.setSlideNumber(0);

        assertDoesNotThrow(() -> component.paintComponent(new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB).getGraphics()));
    }

    @Test
    void testPaintComponentReturnsWhenSlideIsNull() {
        StyleFactory.createStyles();
        presentation.setSlideNumber(0);
        component.update(presentation, null);

        Graphics g = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB).getGraphics();
        assertDoesNotThrow(() -> component.paintComponent(g));
    }
}
