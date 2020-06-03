package spr.ex;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class PaddingFactory {
    public static Padding parsePadding(String padding) {
        List<Integer> values = parse(padding);
        if (values.size() == 1) {
            return new Padding(values.get(0));
        }
        if (values.size() == 2) {
            return new Padding(values.get(0), values.get(1));
        }
        if (values.size() == 3) {
            return new Padding(values.get(0), values.get(1), values.get(2));
        }
        if (values.size() == 4) {
            return new Padding(values.get(0), values.get(1), values.get(2), values.get(3));
        }
        throw new IllegalArgumentException();
    }

    private static List<Integer> parse(String padding) {
        return Stream
                .of(padding.split(":", -1))
                .map(Integer::parseInt)
                .collect(toList());
    }
}
