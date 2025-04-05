package com.nhlstenden.command;

import com.nhlstenden.Presentation;
import com.nhlstenden.factory_method.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.swing.JOptionPane;
import java.awt.Frame;

import static org.mockito.Mockito.*;

class AddTextItemCommandTest {
    private Presentation presentation;
    private Frame parent;
    private AddTextItemCommand command;

    @BeforeEach
    void setUp() {
        presentation = mock(Presentation.class);
        parent = mock(Frame.class);
        command = new AddTextItemCommand(presentation, parent);
    }

    @Test
    void testExecuteAddsTextItem() {
        Slide slide = mock(Slide.class);
        when(presentation.getCurrentSlide()).thenReturn(slide);
        try (MockedStatic<JOptionPane> dialog = mockStatic(JOptionPane.class)) {
            dialog.when(() -> JOptionPane.showInputDialog("Enter level (0-5):")).thenReturn("1");
            dialog.when(() -> JOptionPane.showInputDialog("Enter text for the new item:")).thenReturn("Hello World");
            command.execute();
            verify(slide).appendTextItem(1, "Hello World");
            verify(parent).repaint();
        }
    }

    @Test
    void testExecuteWithInvalidLevel() {
        when(presentation.getCurrentSlide()).thenReturn(mock(Slide.class));
        try (MockedStatic<JOptionPane> dialog = mockStatic(JOptionPane.class)) {
            dialog.when(() -> JOptionPane.showInputDialog("Enter level (0-5):")).thenReturn("abc");
            command.execute();
            dialog.verify(() -> JOptionPane.showMessageDialog(parent, "Invalid level! Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE));
        }
    }

    @Test
    void testExecuteWithBlankTextInput() {
        Slide slide = mock(Slide.class);
        when(presentation.getCurrentSlide()).thenReturn(slide);

        try (MockedStatic<JOptionPane> dialog = mockStatic(JOptionPane.class)) {
            dialog.when(() -> JOptionPane.showInputDialog("Enter level (0-5):")).thenReturn("2");
            dialog.when(() -> JOptionPane.showInputDialog("Enter text for the new item:")).thenReturn("   "); // blank text

            command.execute();

            verify(slide, never()).appendTextItem(anyInt(), anyString());
            verify(parent, never()).repaint();
        }
    }

}
