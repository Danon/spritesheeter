package spr.apps;

import spr.apps.impl.Bounds;
import spr.ex.Images;
import spr.ex.Padding;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toSet;
import static spr.apps.impl.Bounds.bounds;
import static spr.ex.Files.*;
import static spr.ex.Images.openImage;
import static spr.ex.Images.saveImage;

public class Trimmer {
    public static void trim(File file) {
        if (file.isFile()) {
            trimFile(file);
        } else {
            trimDirectory(file);
        }
    }

    private static void trimFile(File file) {
        BufferedImage image = openImage(file);
        Padding bounds = bounds(image);
        System.out.println(formatBounds(file, bounds));
        saveCopy(file, trimImage(image, bounds));
    }

    private static BufferedImage trimImage(BufferedImage image, Padding innerPadding) {
        BufferedImage trimmed = new BufferedImage(
                image.getWidth() - innerPadding.left - innerPadding.right,
                image.getHeight() - innerPadding.top - innerPadding.bottom,
                TYPE_INT_ARGB
        );
        Graphics2D graphics = trimmed.createGraphics();
        graphics.drawImage(image, -innerPadding.left, -innerPadding.top, null);
        return trimmed;
    }

    private static void trimDirectory(File directory) {
        Map<File, BufferedImage> files = listFiles(directory).collect(Collectors.toMap(identity(), Images::openImage));

        Padding bounds = compositeBounds(files.values());
        System.out.println("Composite bounds: " + formatBounds(bounds));

        if (bounds.isZero()) {
            System.out.println("No trimming");
        }

        files.forEach((imageFile, value) -> saveCopy(imageFile, trimImage(value, bounds)));
    }

    private static void saveCopy(File imageFile, BufferedImage image) {
        saveImage(image, getExt(imageFile), copyOfNested(imageFile, imageFile.getParent() + ".trimmed"));
    }

    private static Padding compositeBounds(Collection<BufferedImage> bufferedImageStream) {
        return intersect(bufferedImageStream
                .stream()
                .map(Bounds::bounds)
                .collect(toSet()));
    }

    private static Padding intersect(Set<Padding> bounds) {
        return new Padding(
                min(bounds, padding -> padding.top),
                min(bounds, padding -> padding.right),
                min(bounds, padding -> padding.bottom),
                min(bounds, padding -> padding.left));
    }

    private static int min(Set<Padding> bounds, Function<Padding, Integer> mapper) {
        return bounds.stream().mapToInt(mapper::apply).min().orElseThrow(RuntimeException::new);
    }

    private static String formatBounds(File file, Padding bounds) {
        return String.format("%s - %dpx/%dpx/%dpx/%dpx (u/r/d/l)", file.getName(), bounds.top, bounds.right, bounds.bottom, bounds.left);
    }

    private static String formatBounds(Padding bounds) {
        return String.format("%dpx/%dpx/%dpx/%dpx (u/r/d/l)", bounds.top, bounds.right, bounds.bottom, bounds.left);
    }
}
