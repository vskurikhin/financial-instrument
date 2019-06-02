package su.svn.fi.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import su.svn.fi.models.Instrument;
import su.svn.fi.models.OrderedValue;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public class CalculationEngineOther implements CalculationEngine, Runnable
{
    private static final Log LOG = LogFactory.getLog(CalculationEngineOther.class);

    private final String name;

    private final Comparator<OrderedValue> comparator = Comparator.comparingLong(OrderedValue::getNumber);
     // TODO read from property initialCapacity
    private final PriorityBlockingQueue<OrderedValue> queue = new PriorityBlockingQueue<>(100, comparator);
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public CalculationEngineOther(String name)
    {
        this.name = name;
    }

    @PostConstruct
    void init()
    {
        executorService.submit(this);
        LOG.debug("started cleaner thread for " + name);
    }

    @Override
    public void apply(Instrument instrument)
    {
        queue.put(
            OrderedValue
                .builder()
                .number(instrument.getNumber())
                .value(instrument.getValue())
                .build()
        );
    }

    @Override
    public double getResult()
    {
        Comparator<OrderedValue> reverse = comparator.reversed();
        PriorityBlockingQueue<OrderedValue> reverseOrder = new PriorityBlockingQueue<>(100, reverse);
        queue.drainTo(reverseOrder);
        List<OrderedValue> lastTen = new ArrayList<>(10);
        reverseOrder.drainTo(lastTen, 10);

        return lastTen.stream().map(OrderedValue::getValue).reduce(0.0, (a, b) -> a + b);
    }

    @Override
    public void run()
    {
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                // TODO read from property sleep time
                Thread.sleep(100);
                synchronized (this) {
                    if (queue.size() > 10) {
                        queue.poll();
                    }
                }
            } catch (InterruptedException e) {
                LOG.error(e);
            }
        }
    }

    @PreDestroy
    void shutdown()
    {
        LOG.debug("shutdown cleaner thread for " + name);
        executorService.shutdown();
    }
}
