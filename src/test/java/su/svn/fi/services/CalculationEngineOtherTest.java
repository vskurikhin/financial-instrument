package su.svn.fi.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import su.svn.fi.models.Instrument;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CalculationEngineOtherTest
{
    CalculationEngineOther  engineMean;

    @BeforeEach
    void setUp()
    {
        engineMean = new CalculationEngineOther("INSTRUMENT4");
    }

    @Test
    void test_empty_sequence()
    {
        assertEquals(0.0, engineMean.getResult());
    }

    @Test
    void test_one_element()
    {
        Instrument test = new Instrument("INSTRUMENT4", LocalDate.of(1996, 1, 1), 1.0);
        engineMean.apply(test);
        assertEquals(1.0, engineMean.getResult());
    }

    @Test
    void test_ten_elements()
    {
        int i = 1;
        for (double d = 0; d < 10.0; d += 1.0, i++) {
            Instrument test = new Instrument("INSTRUMENT4", LocalDate.of(1996, 1, i), d);
            engineMean.apply(test);
        }
        assertEquals(45.0, engineMean.getResult());
    }

    @Test
    void test_twelve_elements()
    {
        int i = 1;
        for (double d = 0; d < 12.0; d += 1.0, i++) {
            Instrument test = new Instrument("INSTRUMENT4", LocalDate.of(1996, 1, i), d);
            engineMean.apply(test);
        }
        assertEquals(65.0, engineMean.getResult());
    }
}