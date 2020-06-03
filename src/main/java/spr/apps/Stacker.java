package spr.apps;

import spr.ex.Images;
import spr.ex.Padding;
import spr.ex.Size;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;
import static spr.ex.Files.listFiles;
import static spr.ex.Images.saveImage;

public class Stacker {
    public static void stackFrames(File folder, String format, boolean flip, Padding padding) {
        List<BufferedImage> images = mapToImages(folder, format);
        Size size = size(images);

        BufferedImage outputImage = new BufferedImage(size.width, size.height, TYPE_INT_ARGB);
        int offset = flip ? size.width : 0;

        for (BufferedImage image : images) {
            outputImage.getGraphics().drawImage(image, offset - (flip ? image.getWidth() : 0), 0, null);
            offset = flip ? offset - image.getWidth() : offset + image.getWidth();
        }
        storeSpriteSheet(folder, format, images, outputImage);
    }

    private static void storeSpriteSheet(File folder, String format, List<BufferedImage> images, BufferedImage outputImage) {
        saveImage(outputImage, format, new File(formatFilename(folder, format, images.size(), new Size(images.get(0)))));
    }

    private static String formatFilename(File folder, String imageFormat, int frames, Size size) {
        return format("%s_spritesheet_%s-%d.%s", folder.getName(), size, frames, imageFormat);
    }

    private static Size size(List<BufferedImage> images) {
        validateSize(images);
        return new Size(
                images.stream().mapToInt(BufferedImage::getWidth).sum(),
                images.stream().mapToInt(BufferedImage::getHeight).max().orElseThrow(IllegalArgumentException::new));
    }

    private static void validateSize(List<BufferedImage> images) {
        BufferedImage firstImage = images.get(0);
        for (BufferedImage image : images) {
            if (firstImage.getWidth() == image.getWidth()) {
                if (firstImage.getHeight() == image.getHeight()) {
                    continue;
                }
            }
            throw new RuntimeException("Images sizes differ");
        }
    }

    private static List<BufferedImage> mapToImages(File folder, String format) {
        return listFiles(folder)
                .sorted(comparingInt(file -> {
                    String name = file.getName();
                    if (!name.endsWith(format)) {
                        throw new RuntimeException(format("File '%s' was supposed to have '%s' extension", name, format));
                    }
                    Matcher matcher = Pattern.compile("(\\d+)\\." + format + "$").matcher(name);
                    if (matcher.find()) {
                        return parseInt(matcher.group(1));
                    }
                    throw new RuntimeException(format("Failed to assert that '%s' ends with a sprite number", name));
                }))
                .map(Images::openImage)
                .collect(toList());
    }
}
