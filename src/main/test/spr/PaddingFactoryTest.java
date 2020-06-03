package spr;

import org.junit.jupiter.api.Test;
import spr.ex.Padding;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static spr.ex.PaddingFactory.*;

public class PaddingFactoryTest {
    @Test
    public void shouldGetEqual() {
        // when
        assertPaddingEquals(parsePadding("0"), 0, 0, 0, 0);
        assertPaddingEquals(parsePadding("0:0"), 0, 0, 0, 0);
        assertPaddingEquals(parsePadding("0:0:0"), 0, 0, 0, 0);
        assertPaddingEquals(parsePadding("0:0:0:0"), 0, 0, 0, 0);
        assertPaddingEquals(parsePadding("-0"), 0, 0, 0, 0);

        assertPaddingEquals(parsePadding("11"), 11, 11, 11, 11);
        assertPaddingEquals(parsePadding("11:22"), 11, 22, 11, 22);
        assertPaddingEquals(parsePadding("11:22:33"), 11, 22, 33, 22);
        assertPaddingEquals(parsePadding("11:22:33:44"), 11, 22, 33, 44);
    }

    @Test
    public void shouldThrow() {
        // when
        assertThrows(IllegalArgumentException.class, () -> parsePadding("-"));
        assertThrows(IllegalArgumentException.class, () -> parsePadding("11:22:33:"));
        assertThrows(IllegalArgumentException.class, () -> parsePadding(":11:22:33"));
        assertThrows(IllegalArgumentException.class, () -> parsePadding(":"));
        assertThrows(IllegalArgumentException.class, () -> parsePadding("::"));
        assertThrows(IllegalArgumentException.class, () -> parsePadding("a:b:c"));
    }

    private void assertPaddingEquals(Padding padding, int top, int right, int bottom, int left) {
        assertEquals(padding.top, top);
        assertEquals(padding.right, right);
        assertEquals(padding.bottom, bottom);
        assertEquals(padding.left, left);
    }
}
