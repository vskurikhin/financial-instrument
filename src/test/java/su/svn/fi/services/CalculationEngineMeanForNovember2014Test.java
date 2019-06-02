package su.svn.fi.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import su.svn.fi.models.Instrument;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class CalculationEngineMeanForNovember2014Test
{
    CalculationEngineMeanForNovember2014 engineMean;

    @BeforeEach
    void setUp()
    {
        engineMean = new CalculationEngineMeanForNovember2014();
    }

    @Test
    void test_empty_sequence()
    {
        assertEquals(Double.NaN, engineMean.getResult());
    }

    @Test
    void test_one_element()
    {
        Instrument test = new Instrument("INSTRUMENT2", 0, LocalDate.of(2014, Month.NOVEMBER, 1), 1.0);
        engineMean.apply(test);
        assertEquals(1.0, engineMean.getResult());
    }

    @Test
    void test_discaded_one_element()
    {
        Instrument test = new Instrument("INSTRUMENT2", 0, LocalDate.of(1996, 1, 1), 1.0);
        engineMean.apply(test);
        assertEquals(Double.NaN, engineMean.getResult());
    }

    @Test
    void test_three_elements()
    {
        Instrument test1 = new Instrument("INSTRUMENT2", 0, LocalDate.of(1996, 1, 1), 1.0);
        Instrument test2 = new Instrument("INSTRUMENT2", 0, LocalDate.of(2014, Month.NOVEMBER, 2), 2.0);
        Instrument test3 = new Instrument("INSTRUMENT2", 0, LocalDate.of(2014, Month.NOVEMBER, 3), 3.0);
        engineMean.apply(test1);
        engineMean.apply(test2);
        engineMean.apply(test3);
        assertEquals(2.5, engineMean.getResult());
    }
}