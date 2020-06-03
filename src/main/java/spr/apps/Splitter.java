package spr.apps;

import spr.ex.ColsAndRows;
import spr.ex.Images;
import spr.ex.Padding;
import spr.ex.Size;

import java.awt.image.BufferedImage;
import java.io.File;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static java.lang.String.format;
import static spr.ex.Files.getBasename;
import static spr.ex.Files.getExtension;

public class Splitter {
    public static void splitFrames(File packedFile, ColsAndRows colsAndRows, Padding padding) {
        BufferedImage image = Images.openImage(packedFile);
        Size frameSize = frameSize(packedFile, colsAndRows, image);

        int index = 0;
        for (int row = 0; row < colsAndRows.rows; row++) {
            for (int col = 0; col < colsAndRows.cols; col++) {
                BufferedImage frame = new BufferedImage(
                        frameSize.width + padding.left + padding.right,
                        frameSize.height + padding.top + padding.bottom,
                        TYPE_INT_ARGB);

                frame.getGraphics().drawImage(
                        image,
                        padding.left, padding.top,
                        frameSize.width + padding.left,
                        padding.top + frameSize.height,
                        col * frameSize.width,
                        row * frameSize.height,
                        col * frameSize.width + frameSize.width,
                        row * frameSize.height + frameSize.height,
                        null);
                saveImage(frame, packedFile, index);
                index++;
            }
        }
    }

    private static void saveImage(BufferedImage frame, File packedFile, int index) {
        File parent = packedFile.getParentFile();
        File framesFolder = new File(parent, packedFile.getName() + " - frames");
        framesFolder.mkdir();

        String extension = getExtension(packedFile.getName()).orElseThrow(RuntimeException::new);
        String basename = getBasename(packedFile.getName());

        File file = new File(framesFolder, basename + "-" + index + "." + extension);
        Images.saveImage(
                frame,
                extension,
                file);
        System.out.println("Saved frame " + file.getPath());
    }

    private static Size frameSize(File file, ColsAndRows colsAndRows, BufferedImage image) {
        if (image.getWidth() % colsAndRows.cols == 0) {
            if (image.getHeight() % colsAndRows.rows == 0) {
                return new Size(image.getWidth() / colsAndRows.cols, image.getHeight() / colsAndRows.rows);
            }
            throw new RuntimeException(format("Are you sure this image has %d/%d frames?", colsAndRows.cols, colsAndRows.cols));
        }
        throw new RuntimeException(format("Image '%s' (%d/%d) can't be divided into %d cols and %d rows.",
                file.getPath(),
                image.getWidth(),
                image.getHeight(),
                colsAndRows.cols,
                colsAndRows.rows));
    }
}
