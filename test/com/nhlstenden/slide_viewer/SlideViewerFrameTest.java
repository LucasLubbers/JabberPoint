package com.nhlstenden.slide_viewer;

import com.nhlstenden.Presentation;
import com.nhlstenden.factory_method.Slide;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class SlideViewerFrameTest {

    private Presentation presentation;
    private Slide slide;
    private SlideViewerFrame frame;

    @BeforeEach
    void setUp() {
        presentation = new Presentation();
        slide = new Slide();
        slide.setTitle("Slide 1");
        presentation.append(slide);
        presentation.setTitle("Presentation");
        frame = new SlideViewerFrame("Test", presentation);
    }

    @Test
    void testFrameInitialTitle() {
        assertEquals("Jabberpoint 1.6 - OU", frame.getTitle());
    }

    @Test
    void testFrameSizeIsCorrect() {
        assertEquals(1200, frame.getWidth());
        assertEquals(800, frame.getHeight());
    }

    @Test
    void testUpdateSetsCorrectTitle() {
        frame.update(presentation, slide);
        assertTrue(frame.getTitle().contains("Jabberpoint 1.6 - OU - Presentation - Slide 1"));
    }

    @AfterEach
    void tearDown() {
        frame.dispose();
    }
}
