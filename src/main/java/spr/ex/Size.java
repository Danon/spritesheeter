package spr.ex;

import java.awt.image.BufferedImage;

import static java.lang.String.format;

public class Size {
    public final int width, height;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Size(BufferedImage image) {
        this(image.getWidth(), image.getHeight());
    }

    @Override
    public String toString() {
        return format("%dx%d", width, height);
    }
}
