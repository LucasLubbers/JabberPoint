package com.nhlstenden.command;

import com.nhlstenden.Presentation;
import com.nhlstenden.factory_method.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.swing.JOptionPane;
import java.awt.Frame;

import static org.mockito.Mockito.*;

class AddBitmapItemCommandTest {
    private Presentation presentation;
    private Frame parent;
    private AddBitmapItemCommand command;

    @BeforeEach
    void setUp() {
        presentation = mock(Presentation.class);
        parent = mock(Frame.class);
        command = new AddBitmapItemCommand(presentation, parent);
    }

    @Test
    void testExecuteAddsBitmapItem() throws Exception {
        Slide slide = mock(Slide.class);
        when(presentation.getCurrentSlide()).thenReturn(slide);
        try (MockedStatic<JOptionPane> dialog = mockStatic(JOptionPane.class)) {
            dialog.when(() -> JOptionPane.showInputDialog("Enter level (0-5):")).thenReturn("2");
            dialog.when(() -> JOptionPane.showInputDialog("Enter the file path for the bitmap:")).thenReturn("image.png");
            command.execute();
            verify(slide).appendBitmapItem(2, "image.png");
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
    void testExecuteWithAppendBitmapFailure() throws Exception {
        Slide slide = mock(Slide.class);
        when(presentation.getCurrentSlide()).thenReturn(null).thenReturn(slide);
        when(presentation.getSize()).thenReturn(1); // for setSlideNumber

        doThrow(new RuntimeException("oops")).when(slide).appendBitmapItem(anyInt(), anyString());

        try (MockedStatic<JOptionPane> dialog = mockStatic(JOptionPane.class)) {
            dialog.when(() -> JOptionPane.showInputDialog("Enter level (0-5):")).thenReturn("1");
            dialog.when(() -> JOptionPane.showInputDialog("Enter the file path for the bitmap:")).thenReturn("img.png");

            command.execute();

            dialog.verify(() -> JOptionPane.showMessageDialog(
                    eq(parent),
                    argThat(msg -> msg.toString().contains("oops")),
                    eq("Error"),
                    eq(JOptionPane.ERROR_MESSAGE)
            ));
        }
    }
}
