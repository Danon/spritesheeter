package spr;

import spr.apps.Splitter;
import spr.apps.Stacker;
import spr.apps.Trimmer;
import spr.ex.ColsAndRows;
import spr.ex.Padding;

import java.io.File;

import static java.lang.String.format;
import static spr.ex.ColsAndRowsFactory.parseColsAndRows;
import static spr.ex.PaddingFactory.parsePadding;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            return;
        }

        if (args[0].equals("stack")) {
            if (args.length == 3) {
                stack(new File(args[1]), args[2], false);
                return;
            }
            if (args.length == 4 && args[3].equals("flip")) {
                stack(new File(args[1]), args[2], true);
                return;
            }
        }

        if (args[0].equals("split")) {
            if (args.length == 3) {
                split(new File(args[1]), parseColsAndRows(args[2]), new Padding());
                return;
            }
            if (args.length == 4) {
                split(new File(args[1]), parseColsAndRows(args[2]), parsePadding(args[3]));
                return;
            }
        }

        if (args[0].equals("trim")) {
            if (args.length == 2) {
                trim(new File(args[1]));
                return;
            }
        }

        printUsage();
    }

    private static void stack(File folder, String format, boolean flip) {
        if (folder.exists() && folder.isDirectory()) {
            Stacker.stackFrames(folder, format, flip, new Padding(0));
        } else {
            System.err.println(format("File '%s' is not directory", folder.getPath()));
        }
    }

    private static void split(File file, ColsAndRows colsAndRows, Padding padding) {
        if (file.exists() && file.isFile()) {
            Splitter.splitFrames(file, colsAndRows, padding);
        } else {
            System.err.println(format("File '%s' is not a file", file.getPath()));
        }
    }

    private static void trim(File file) {
        if (file.exists() && (file.isFile() || file.isDirectory())) {
            Trimmer.trim(file);
        } else {
            System.err.println(format("File '%s' doesn't exist or is invalid", file.getPath()));
        }
    }

    private static void printUsage() {
        System.err.println("Usage:");
        System.err.println("  stack <folder> <format> <flip?>");
        System.err.println("  split <file> <cols:rows> <css-padding?>");
        System.err.println("  trim <file|folder>");
        System.out.println();
        System.out.println("Description:");
        System.out.println(" > stack - joins frames from <folder> horizontally");
        System.out.println("           and saves it in a new file.");
        System.out.println(" > split - cuts the image by <cols> and <rows>, and saves");
        System.out.println("           each file in a new, separate file.");
        System.out.println(" > trim  - removes transparent padding from a frame");
    }
}
