package su.svn.fi.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import su.svn.fi.models.Instrument;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CalculationEngineMeanTest
{
    CalculationEngineMean engineMean;

    @BeforeEach
    void setUp()
    {
        engineMean = new CalculationEngineMean();
    }

    @Test
    void test_empty_sequence()
    {
        assertEquals(Double.NaN, engineMean.getResult());
    }

    @Test
    void test_one_element()
    {
        Instrument test = new Instrument("INSTRUMENT1", 0, LocalDate.of(1996, 1, 1), 1.0);
        engineMean.apply(test);
        assertEquals(1.0, engineMean.getResult());
    }

    @Test
    void test_three_elements()
    {
        Instrument test1 = new Instrument("INSTRUMENT1", 0, LocalDate.of(1996, 1, 1), 1.0);
        Instrument test2 = new Instrument("INSTRUMENT1", 0, LocalDate.of(1996, 1, 1), 2.0);
        Instrument test3 = new Instrument("INSTRUMENT1", 0, LocalDate.of(1996, 1, 1), 3.0);
        engineMean.apply(test1);
        engineMean.apply(test2);
        engineMean.apply(test3);
        assertEquals(2.0, engineMean.getResult());
    }
}