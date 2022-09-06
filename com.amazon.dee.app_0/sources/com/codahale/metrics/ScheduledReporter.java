package com.codahale.metrics;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Closeable;
import java.util.Locale;
import java.util.SortedMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes9.dex */
public abstract class ScheduledReporter implements Closeable {
    private static final AtomicInteger FACTORY_ID = new AtomicInteger();
    private final double durationFactor;
    private final String durationUnit;
    private final ScheduledExecutorService executor;
    private final MetricFilter filter;
    private final double rateFactor;
    private final String rateUnit;
    private final MetricRegistry registry;

    /* loaded from: classes9.dex */
    private static class NamedThreadFactory implements ThreadFactory {
        private final ThreadGroup group;
        private final String namePrefix;
        private final AtomicInteger threadNumber;

        private NamedThreadFactory(String str) {
            this.threadNumber = new AtomicInteger(1);
            SecurityManager securityManager = System.getSecurityManager();
            this.group = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = GeneratedOutlineSupport1.outline75("metrics-", str, "-thread-");
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            ThreadGroup threadGroup = this.group;
            Thread thread = new Thread(threadGroup, runnable, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
            thread.setDaemon(true);
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
            return thread;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ScheduledReporter(MetricRegistry metricRegistry, String str, MetricFilter metricFilter, TimeUnit timeUnit, TimeUnit timeUnit2) {
        this.registry = metricRegistry;
        this.filter = metricFilter;
        StringBuilder outline108 = GeneratedOutlineSupport1.outline108(str, '-');
        outline108.append(FACTORY_ID.incrementAndGet());
        this.executor = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory(outline108.toString()));
        this.rateFactor = timeUnit.toSeconds(1L);
        this.rateUnit = calculateRateUnit(timeUnit);
        this.durationFactor = 1.0d / timeUnit2.toNanos(1L);
        this.durationUnit = timeUnit2.toString().toLowerCase(Locale.US);
    }

    private String calculateRateUnit(TimeUnit timeUnit) {
        return GeneratedOutlineSupport1.outline50(timeUnit.toString().toLowerCase(Locale.US), -1, 0);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        stop();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public double convertDuration(double d) {
        return d * this.durationFactor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public double convertRate(double d) {
        return d * this.rateFactor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getDurationUnit() {
        return this.durationUnit;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getRateUnit() {
        return this.rateUnit;
    }

    public void report() {
        report(this.registry.getGauges(this.filter), this.registry.getCounters(this.filter), this.registry.getHistograms(this.filter), this.registry.getMeters(this.filter), this.registry.getTimers(this.filter));
    }

    public abstract void report(SortedMap<String, Gauge> sortedMap, SortedMap<String, Counter> sortedMap2, SortedMap<String, Histogram> sortedMap3, SortedMap<String, Meter> sortedMap4, SortedMap<String, Timer> sortedMap5);

    public void start(long j, TimeUnit timeUnit) {
        this.executor.scheduleAtFixedRate(new Runnable() { // from class: com.codahale.metrics.ScheduledReporter.1
            @Override // java.lang.Runnable
            public void run() {
                ScheduledReporter.this.report();
            }
        }, j, j, timeUnit);
    }

    public void stop() {
        this.executor.shutdown();
        try {
            this.executor.awaitTermination(1L, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
        }
    }
}
