package com.nhlstenden.command;

import com.nhlstenden.Presentation;
import com.nhlstenden.memento.CareTaker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.swing.JOptionPane;
import java.awt.Frame;

import static org.mockito.Mockito.*;

class RestoreStateCommandTest {
    private Presentation presentation;
    private Frame parent;
    private CareTaker careTaker;
    private RestoreStateCommand command;

    @BeforeEach
    void setUp() {
        presentation = mock(Presentation.class);
        parent = mock(Frame.class);
        careTaker = mock(CareTaker.class);
        command = new RestoreStateCommand(presentation, parent, careTaker);
    }

    @Test
    void testExecuteWithSavedState() {
        when(careTaker.hasSavedState()).thenReturn(true);
        try (MockedStatic<JOptionPane> mocked = mockStatic(JOptionPane.class)) {
            command.execute();
            verify(careTaker).restoreState(presentation);
            verify(parent).repaint();
            mocked.verify(() -> JOptionPane.showMessageDialog(parent, "Presentation state restored!", "Success", JOptionPane.INFORMATION_MESSAGE));
        }
    }

    @Test
    void testExecuteWithoutSavedState() {
        when(careTaker.hasSavedState()).thenReturn(false);
        try (MockedStatic<JOptionPane> mocked = mockStatic(JOptionPane.class)) {
            command.execute();
            mocked.verify(() -> JOptionPane.showMessageDialog(parent, "No saved state to restore!", "Error", JOptionPane.ERROR_MESSAGE));
        }
    }
}
