package su.svn.fi.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import su.svn.fi.models.Instrument;

import java.util.ArrayDeque;
import java.util.Deque;

public class CalculationEngineOther implements CalculationEngine
{
    private static final Log LOG = LogFactory.getLog(CalculationEngineOther.class);

    private final String name;

    private Deque<Double> deque = new ArrayDeque<>();

    public CalculationEngineOther(String name)
    {
        this.name = name;
        LOG.info("Instance of " + this.getClass().getName() + " name: " + name);
    }

    @Override
    public void apply(Instrument instrument)
    {
        deque.addLast(instrument.getValue());
        if (deque.size() > 10) {
            deque.removeFirst();
        }
    }

    @Override
    public double getResult()
    {
        return deque.stream().reduce(0.0, (d1, d2) -> d1 + d2);
    }
}
