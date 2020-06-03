package spr.ex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColsAndRowsFactoryTest
{
    @Test
    void shouldThrowException()
    {
        assertThrows(RuntimeException.class, () -> ColsAndRowsFactory.parseColsAndRows("0:0"));
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
    void shouldParseColsAndRowsSmallAmount()
    {
        // given
        ColsAndRows actual = ColsAndRowsFactory.parseColsAndRows("2:3");
        ColsAndRows expected = new ColsAndRows(2, 3);

        // then
        assertEquals(expected.cols, actual.cols);
        assertEquals(expected.rows, actual.rows);
    }

    @Test
    void shouldParseColsAndRowsBigAmount()
    {
        // given
        ColsAndRows actual = ColsAndRowsFactory.parseColsAndRows("123456789:123456789");
        ColsAndRows expected = new ColsAndRows(123456789, 123456789);

        // then
        assertEquals(expected.cols, actual.cols);
        assertEquals(expected.rows, actual.rows);
    }
}