package com.nhlstenden;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class AboutBoxTest {

    @Test
    void testShowDoesNotThrowException() {
        Frame dummyFrame = new Frame();
        assertDoesNotThrow(() -> AboutBox.show(dummyFrame));
    }
}
