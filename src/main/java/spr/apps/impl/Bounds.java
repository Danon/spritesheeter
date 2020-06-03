package spr.apps.impl;

import spr.ex.Padding;

import java.awt.image.BufferedImage;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class Bounds {

    public static Padding bounds(BufferedImage image) {
        int left = MAX_VALUE;
        int right = MIN_VALUE;
        int top = MAX_VALUE;
        int bottom = MIN_VALUE;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int alpha = (image.getRGB(x, y) >> 24) & 0xFF;
                if (alpha != 0) {
                    left = min(x, left);
                    right = max(x, right);
                    top = min(y, top);
                    bottom = max(y, bottom);
                }
            }
        }
        return new Padding(top, image.getWidth() - right - 1, image.getHeight() - bottom - 1, left);
    }
}
