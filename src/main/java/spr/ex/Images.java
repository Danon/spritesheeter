package spr.ex;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.String.format;
import static javax.imageio.ImageIO.read;

public class Images {
    public static BufferedImage openImage(File file) {
        try {
            System.out.println(format("Reading '%s'", file.getPath()));
            return read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveImage(BufferedImage image, String format, File file) {
        try {
            ImageIO.write(image, format, file);
            System.out.println(format("Saved '%s'", file.getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
