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

class SaveFileCommandTest {
    private Presentation presentation;
    private Frame parent;
    private SaveFileCommand command;

    @BeforeEach
    void setUp() {
        presentation = mock(Presentation.class);
        parent = mock(Frame.class);
        command = new SaveFileCommand(presentation, parent);
    }

    @Test
    void testExecuteSuccessfulSave() throws Exception {
        try (MockedConstruction<XMLAccessor> mocked = mockConstruction(XMLAccessor.class,
                (mock, context) -> doNothing().when(mock).saveFile(presentation, "dump.xml"))) {
            command.execute();
            verify(mocked.constructed().get(0)).saveFile(presentation, "dump.xml");
        }
    }

    @Test
    void testExecuteIOException() throws Exception {
        try (
                MockedConstruction<XMLAccessor> mocked = mockConstruction(XMLAccessor.class,
                        (mock, context) -> doThrow(new IOException("fail")).when(mock).saveFile(presentation, "dump.xml"));
                MockedStatic<JOptionPane> mockedDialog = mockStatic(JOptionPane.class)
        ) {
            command.execute();
            mockedDialog.verify(() -> JOptionPane.showMessageDialog(any(), contains("fail"), eq("Save Error"), eq(JOptionPane.ERROR_MESSAGE)));
        }
    }
}
