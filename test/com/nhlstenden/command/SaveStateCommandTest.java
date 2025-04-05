package com.nhlstenden.command;

import static org.junit.jupiter.api.Assertions.*;

import com.nhlstenden.Presentation;
import com.nhlstenden.factory_method.Slide;
import com.nhlstenden.memento.CareTaker;
import java.awt.Frame;
import javax.swing.JOptionPane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.mockStatic;

class SaveStateCommandTest {
    private SaveStateCommand command;
    private Presentation presentation;
    private Frame parent;
    private CareTaker careTaker;

    @BeforeEach
    void setUp() {
        presentation = new Presentation();
        parent = new Frame();
        careTaker = new CareTaker();
        command = new SaveStateCommand(presentation, parent, careTaker);

        Slide slide = new Slide();
        presentation.append(slide);
        presentation.setSlideNumber(0);
    }

    @Test
    void testExecuteSavesState() {
        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {
            command.execute();
            assertTrue(careTaker.hasSavedState());
        }
    }

    @Test
    void testExecuteWithMultipleSlides() {
        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {
            Slide slide2 = new Slide();
            presentation.append(slide2);
            command.execute();
            assertTrue(careTaker.hasSavedState());
        }
    }

    @Test
    void testExecuteMultipleTimes() {
        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {
            command.execute();
            command.execute();
            assertTrue(careTaker.hasSavedState());
        }
    }

    @Test
    void testExecuteWithNullPresentation() {
        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {
            command = new SaveStateCommand(null, parent, careTaker);
            assertThrows(NullPointerException.class, () -> command.execute());
        }
    }

    @Test
    void testExecuteWithNullCareTaker() {
        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {
            command = new SaveStateCommand(presentation, parent, null);
            assertThrows(NullPointerException.class, () -> command.execute());
        }
    }
}