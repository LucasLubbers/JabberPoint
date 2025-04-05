package com.nhlstenden.command;

import com.nhlstenden.Presentation;
import com.nhlstenden.XML.XMLAccessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;

import javax.swing.JOptionPane;
import java.awt.Frame;
import java.io.IOException;

import static org.mockito.Mockito.*;

class OpenFileCommandTest {
    private Presentation presentation;
    private Frame parent;
    private OpenFileCommand command;

    @BeforeEach
    void setUp() {
        presentation = mock(Presentation.class);
        parent = mock(Frame.class);
        command = new OpenFileCommand(presentation, parent);
    }

    @Test
    void testExecuteSuccessfulLoad() throws Exception {
        try (MockedConstruction<XMLAccessor> mocked = mockConstruction(XMLAccessor.class,
                (mock, context) -> doNothing().when(mock).loadFile(presentation, "test.xml"))) {
            command.execute();
            verify(presentation).clear();
            verify(presentation).setSlideNumber(0);
            verify(parent).repaint();
        }
    }

    @Test
    void testExecuteIOException() throws Exception {
        try (
                MockedConstruction<XMLAccessor> mocked = mockConstruction(XMLAccessor.class,
                        (mock, context) -> doThrow(new IOException("fail")).when(mock).loadFile(presentation, "test.xml"));
                MockedStatic<JOptionPane> dialog = mockStatic(JOptionPane.class)
        ) {
            command.execute();
            verify(presentation).clear();
            verify(parent).repaint();
            dialog.verify(() -> JOptionPane.showMessageDialog(any(), contains("fail"), eq("Load Error"), eq(JOptionPane.ERROR_MESSAGE)));
        }
    }
}
