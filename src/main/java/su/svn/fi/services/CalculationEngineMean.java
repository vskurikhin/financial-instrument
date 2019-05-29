package su.svn.fi.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import su.svn.fi.models.Instrument;

@Service("calculationEngineMean")
public class CalculationEngineMean implements CalculationEngine
{
    private static final Log LOG = LogFactory.getLog(CalculationEngineMean.class);

    private double sum = 0;

    private long count = 0L;

    public CalculationEngineMean()
    {
        LOG.info("Instance of " + this.getClass().getName());
    }

    @Override
    public void apply(Instrument instrument)
    {
        sum += instrument.getValue();
        count++;
    }

    @Override
    public double getResult()
    {
        return sum / count;
    }
}
