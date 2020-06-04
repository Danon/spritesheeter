package spr.ex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static spr.ex.ColsAndRowsFactory.parseColsAndRows;

class ColsAndRowsFactoryTest {
    @Test
    void shouldParse() {
        // when
        ColsAndRows actual = parseColsAndRows("123:456");

        // then
        assertEquals(123, actual.cols);
        assertEquals(456, actual.rows);
    }

    @Test
    void shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> parseColsAndRows(""));
        assertThrows(IllegalArgumentException.class, () -> parseColsAndRows("."));
        assertThrows(IllegalArgumentException.class, () -> parseColsAndRows(":"));
        assertThrows(IllegalArgumentException.class, () -> parseColsAndRows("10:"));
        assertThrows(IllegalArgumentException.class, () -> parseColsAndRows(":10"));
        assertThrows(IllegalArgumentException.class, () -> parseColsAndRows("2: 7"));
        assertThrows(IllegalArgumentException.class, () -> parseColsAndRows("4::4"));
        assertThrows(IllegalArgumentException.class, () -> parseColsAndRows("  31:1"));
        assertThrows(IllegalArgumentException.class, () -> parseColsAndRows("22;22"));
        assertThrows(IllegalArgumentException.class, () -> parseColsAndRows("4:d"));
        assertThrows(IllegalArgumentException.class, () -> parseColsAndRows("a:a"));
        assertThrows(IllegalArgumentException.class, () -> parseColsAndRows("1:1a"));
        assertThrows(IllegalArgumentException.class, () -> parseColsAndRows("asd"));
    }

    @Test
    void shouldThrowZeroDimensionExceptionCols() {
        assertThrows(ZeroDimensionException.class, () -> parseColsAndRows("0:1"));
    }

    @Test
    void shouldThrowZeroDimensionExceptionRows() {
        assertThrows(ZeroDimensionException.class, () -> parseColsAndRows("1:0"));
    }
}
