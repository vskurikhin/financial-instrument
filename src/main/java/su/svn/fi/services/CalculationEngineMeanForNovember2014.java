package su.svn.fi.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import su.svn.fi.models.Instrument;

import java.time.LocalDate;
import java.time.Month;

@Service("calculationEngineMeanForNovember2014")
public class CalculationEngineMeanForNovember2014 implements CalculationEngine
{
    private static final Log LOG = LogFactory.getLog(CalculationEngineMeanForNovember2014.class);

    private double sum = 0;

    private long count = 0L;

    public CalculationEngineMeanForNovember2014()
    {
        LOG.info("Instance of " + this.getClass().getName());
    }

    boolean checkDay(LocalDate date)
    {
        return date.getYear() == 2014 && date.getMonth() == Month.NOVEMBER;
    }

    @Override
    public void apply(Instrument instrument)
    {
        if (checkDay(instrument.getDate())) {
            sum += instrument.getValue();
            count++;
        }
    }

    @Override
    public double getResult()
    {
        return sum / count;
    }
}
