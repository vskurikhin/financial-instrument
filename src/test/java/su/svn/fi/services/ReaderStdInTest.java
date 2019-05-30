package su.svn.fi.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReaderStdInTest
{
    ReaderStdIn readerStdIn;

    @Test
    void test_empty_case1()
    {
        ByteArrayInputStream in = new ByteArrayInputStream("".getBytes());
        readerStdIn = new ReaderStdIn(new Scanner(in));
        readerStdIn.read(lines -> Assertions.fail("no input"));

    }

    @Test
    void test_empty_case2()
    {
        ByteArrayInputStream in = new ByteArrayInputStream(System.lineSeparator().getBytes());
        readerStdIn = new ReaderStdIn(new Scanner(in));
        readerStdIn.read(lines -> assertEquals(Collections.emptyList(), lines));
    }

    String goodCase1 = "INSTRUMENT1,01-Jan-1996,2.4655";
    @Test
    void test_good_case1()
    {
        ByteArrayInputStream in = new ByteArrayInputStream(goodCase1.getBytes());
        readerStdIn = new ReaderStdIn(new Scanner(in));
        readerStdIn.read(lines -> {
            assertEquals(1, lines.size());
            assertTrue(lines.contains(goodCase1));
        });
    }

    String goodCase21 = "INSTRUMENT1,01-Jan-1996,2.4655";
    String goodCase22 = "INSTRUMENT1,02-Jan-1996,2.4685";
    String goodCase23 = "INSTRUMENT1,03-Jan-1996,2.473";
    String goodCase2 = goodCase21 + System.lineSeparator()
        + goodCase22 + System.lineSeparator() + goodCase23;
    @Test
    void test_good_case2()
    {
        ByteArrayInputStream in = new ByteArrayInputStream(goodCase1.getBytes());
        readerStdIn = new ReaderStdIn(new Scanner(in));
        readerStdIn.read(lines -> {
            assertEquals(1, lines.size());
            assertTrue(lines.contains(goodCase1));
        });
    }
}