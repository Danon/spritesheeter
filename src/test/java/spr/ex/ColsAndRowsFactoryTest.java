package spr.ex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColsAndRowsFactoryTest {
    @Test
    void shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ColsAndRowsFactory.parseColsAndRows(":"));
        assertThrows(IllegalArgumentException.class, () -> ColsAndRowsFactory.parseColsAndRows("4::4"));
        assertThrows(IllegalArgumentException.class, () -> ColsAndRowsFactory.parseColsAndRows("asd"));
        assertThrows(IllegalArgumentException.class, () -> ColsAndRowsFactory.parseColsAndRows("4:d"));
        assertThrows(IllegalArgumentException.class, () -> ColsAndRowsFactory.parseColsAndRows("1:1a"));
        assertThrows(IllegalArgumentException.class, () -> ColsAndRowsFactory.parseColsAndRows("2: 7"));
        assertThrows(IllegalArgumentException.class, () -> ColsAndRowsFactory.parseColsAndRows("  31:1"));
        assertThrows(IllegalArgumentException.class, () -> ColsAndRowsFactory.parseColsAndRows("a:a"));
        assertThrows(IllegalArgumentException.class, () -> ColsAndRowsFactory.parseColsAndRows("10:"));
        assertThrows(IllegalArgumentException.class, () -> ColsAndRowsFactory.parseColsAndRows(":10"));
        assertThrows(IllegalArgumentException.class, () -> ColsAndRowsFactory.parseColsAndRows(""));
        assertThrows(IllegalArgumentException.class, () -> ColsAndRowsFactory.parseColsAndRows("22;22"));
        assertThrows(IllegalArgumentException.class, () -> ColsAndRowsFactory.parseColsAndRows("."));
    }

    @Test
    void shouldThrowZeroDimensionException() {
        assertThrows(ZeroDimensionException.class, () -> ColsAndRowsFactory.parseColsAndRows("0:0"));
    }

    @Test
    void shouldParseColsAndRows_smallAmount() {
        // when
        ColsAndRows actual = ColsAndRowsFactory.parseColsAndRows("1:2");

        // then
        assertEquals(1, actual.cols);
        assertEquals(2, actual.rows);
    }

    @Test
    void shouldParseColsAndRows_bigAmount() {
        // when
        ColsAndRows actual = ColsAndRowsFactory.parseColsAndRows("9999999:8888888");

        // then
        assertEquals(9999999, actual.cols);
        assertEquals(8888888, actual.rows);
    }
}