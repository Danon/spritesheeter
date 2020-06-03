package spr.ex;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import static java.lang.String.format;

public class Files {
    public static Stream<File> listFiles(File folder) {
        File[] array = folder.listFiles();
        if (array == null) {
            throw new RuntimeException("Can't list files in folder: " + folder.getPath());
        }
        return Arrays.stream(array);
    }

    public static File copyOfNested(File file, String folder) {
        File nest = new File(file.getParent(), folder);
        nest.mkdir();
        return new File(nest, getBasename(file.getName()) + "." + getExt(file.getName()));
    }

    public static String getExt(File file) {
        return getExt(file.getName());
    }

    public static String getExt(String filename) {
        return getExtension(filename).orElseThrow(() -> new RuntimeException(format("Can't determine the format of '%s'", filename)));
    }

    public static Optional<String> getExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(file -> file.contains("."))
                .map(file -> file.substring(filename.lastIndexOf(".") + 1));
    }

    public static String getBasename(String filename) {
        return Optional.ofNullable(filename)
                .filter(file -> file.contains("."))
                .map(file -> file.substring(0, filename.lastIndexOf(".")))
                .orElse(filename);
    }
}
