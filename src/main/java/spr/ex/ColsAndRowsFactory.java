package spr.ex;

import java.util.regex.Matcher;

import static java.lang.Integer.parseInt;
import static java.util.regex.Pattern.compile;

public class ColsAndRowsFactory {
    public static ColsAndRows parseColsAndRows(String rowsAndCols) {
        Matcher matcher = compile("^(\\d+):(\\d+)$").matcher(rowsAndCols);
        if (matcher.find()) {
            int cols = parseInt(matcher.group(1));
            int rows = parseInt(matcher.group(2));
            if (cols == 0 || rows == 0) {
                throw new RuntimeException("Can't use 0 for cols or rows");
            }
            return new ColsAndRows(cols, rows);
        }
        throw new IllegalArgumentException();
    }
}
