package com.nhlstenden.factory_method;

import com.nhlstenden.style.Style;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class BitmapItemTest {

    @TempDir
    Path tempDir;

    // Helper method to create a standard Style for testing
    private Style createTestStyle(int indent) {
        return new Style(indent, Color.BLACK, 12, 10); // indent, color, points, leading
    }

    @Test
    void constructorWithValidImage() throws IOException {
        // Create a temporary image file
        File imageFile = tempDir.resolve("test.png").toFile();
        BufferedImage dummyImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        // Fill with non-transparent color
        Graphics g = dummyImage.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 100, 100);
        g.dispose();

        ImageIO.write(dummyImage, "png", imageFile);

        BitmapItem item = new BitmapItem(1, imageFile.getPath());

        assertEquals(1, item.getLevel());
        assertEquals(imageFile.getPath(), item.getName());
        assertNotNull(item.toString());
    }

    @Test
    void getBoundingBoxWithValidImage() throws IOException {
        // Create a temporary image file
        File imageFile = tempDir.resolve("test.png").toFile();
        BufferedImage dummyImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        ImageIO.write(dummyImage, "png", imageFile);

        BitmapItem item = new BitmapItem(1, imageFile.getPath());
        Style style = createTestStyle(20); // indent=20, leading=10
        Graphics g = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB).getGraphics();

        Rectangle bounds = item.getBoundingBox(g, null, 1.0f, style);

        assertEquals(20, bounds.x); // indent
        assertEquals(0, bounds.y);
        assertEquals(100, bounds.width); // image width
        assertEquals(110, bounds.height); // leading (10) + image height (100)
    }

    @Test
    void drawWithValidImage() throws IOException {
        // Create a temporary image file
        File imageFile = tempDir.resolve("test.png").toFile();
        BufferedImage dummyImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        // Fill with non-transparent color
        Graphics imgGraphics = dummyImage.getGraphics();
        imgGraphics.setColor(Color.RED);
        imgGraphics.fillRect(0, 0, 100, 100);
        imgGraphics.dispose();
        ImageIO.write(dummyImage, "png", imageFile);

        BitmapItem item = new BitmapItem(1, imageFile.getPath());
        Style style = createTestStyle(20); // indent=20, leading=10
        BufferedImage canvas = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        // Fill canvas with known color
        Graphics canvasGraphics = canvas.getGraphics();
        canvasGraphics.setColor(Color.BLUE);
        canvasGraphics.fillRect(0, 0, 500, 500);
        canvasGraphics.dispose();

        Graphics g = canvas.getGraphics();
        item.draw(10, 20, 1.0f, g, style, null);
        g.dispose();

        // Check if something was drawn at the expected position
        // The image should be drawn at:
        // x = 10 (position) + 20 (indent) = 30
        // y = 20 (position) + 10 (leading) = 30
        // We check a pixel inside the image area
        assertNotEquals(Color.BLUE.getRGB(), canvas.getRGB(50, 50));
    }

    @Test
    void testToString() {
        BitmapItem item = new BitmapItem(2, "test.png");
        String expected = "com.nhlstenden.factory_method.BitmapItem[2,test.png]";
        assertEquals(expected, item.toString());
    }

}