package spr;

import spr.apps.Splitter;
import spr.apps.Stacker;
import spr.apps.Trimmer;
import spr.args.Task;
import spr.ex.ColsAndRows;
import spr.ex.Padding;

import java.io.File;

import static java.lang.String.format;
import static spr.args.ArgumentParser.parse;
import static spr.args.RenderFormat.FRAMES;
import static spr.args.RenderFormat.SINGLE_IMAGE;
import static spr.args.RenderFormat.STACKED;

public class Application {
    public static void main(String[] args) {
        Task task = parse(args);

        if (task.getFormat() == STACKED) {
            stack(task.getFile(), "png", task.isFlip());
        } else if (task.getFormat() == FRAMES) {
            split(task.getFile(), task.getCells(), task.getPadding());
        } else if (task.getFormat() == SINGLE_IMAGE) {
            if (!task.isTrim()) {
                throw new RuntimeException("Trimming without trim is not supported");
            }
            trim(task.getFile());
        }
    }

    private static void stack(File folder, String format, boolean flip) {
        if (folder.isDirectory()) {
            Stacker.stackFrames(folder, format, flip, new Padding(0));
        } else {
            System.err.println(format("File '%s' is not directory", folder.getPath()));
        }
    }

    private static void split(File file, ColsAndRows colsAndRows, Padding padding) {
        if (file.isFile()) {
            Splitter.splitFrames(file, colsAndRows, padding);
        } else {
            System.err.println(format("File '%s' is not a file", file.getPath()));
        }
    }

    private static void trim(File file) {
        if (file.isFile() || file.isDirectory()) {
            Trimmer.trim(file);
        } else {
            System.err.println(format("File '%s' doesn't exist or is invalid", file.getPath()));
        }
    }
}
