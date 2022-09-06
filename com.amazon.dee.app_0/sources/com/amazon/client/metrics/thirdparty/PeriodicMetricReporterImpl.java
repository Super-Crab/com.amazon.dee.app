package com.amazon.client.metrics.thirdparty;

import com.amazon.client.metrics.thirdparty.internal.ThreadName;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes11.dex */
public class PeriodicMetricReporterImpl implements PeriodicMetricReporter {
    private static final long SHUTDOWN_TIMEOUT_MS = 2000;
    protected final Collection<MetricEvent> mAdditionalMetricEvents;
    protected final Channel mChannel;
    protected final PeriodicCommand mCommand;
    protected final MetricEvent mMetricEvent;
    protected final MetricsFactory mMetricsFactory;
    protected final Priority mPriority;
    protected final ScheduledExecutorService mThreadPool;

    /* loaded from: classes11.dex */
    protected final class PeriodicCommand implements Runnable {
        private final AtomicBoolean mIsActive = new AtomicBoolean(true);

        public PeriodicCommand() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mIsActive.get()) {
                try {
                    PeriodicMetricReporterImpl.this.recordMetricEvent();
                } catch (Throwable unused) {
                }
            }
        }

        public void shutdown() {
            this.mIsActive.set(false);
        }
    }

    public PeriodicMetricReporterImpl(MetricsFactory metricsFactory, String str, String str2, MetricEventType metricEventType, Priority priority, Channel channel) {
        if (metricsFactory != null) {
            if (str != null && str.trim().length() != 0) {
                if (str2 != null && str2.trim().length() != 0) {
                    this.mMetricsFactory = metricsFactory;
                    this.mCommand = new PeriodicCommand();
                    this.mThreadPool = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() { // from class: com.amazon.client.metrics.thirdparty.PeriodicMetricReporterImpl.1
                        @Override // java.util.concurrent.ThreadFactory
                        public Thread newThread(Runnable runnable) {
                            Thread thread = new Thread(runnable, ThreadName.PERIODIC_METRIC_REPORTER);
                            thread.setPriority(4);
                            return thread;
                        }
                    });
                    this.mAdditionalMetricEvents = new HashSet();
                    this.mMetricEvent = this.mMetricsFactory.createConcurrentMetricEvent(str, str2, metricEventType, true);
                    this.mPriority = priority;
                    this.mChannel = channel;
                    return;
                }
                throw new IllegalArgumentException("Argument: source cannot be null or empty.");
            }
            throw new IllegalArgumentException("Argument: program cannot be null or empty.");
        }
        throw new IllegalArgumentException("Argument: factory cannot be null.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordMetricEvent() {
        HashSet<MetricEvent> hashSet;
        if (!this.mMetricEvent.getAsDataPoints().isEmpty()) {
            this.mMetricsFactory.record(this.mMetricEvent, this.mPriority, this.mChannel);
        }
        synchronized (this.mAdditionalMetricEvents) {
            hashSet = new HashSet(this.mAdditionalMetricEvents);
        }
        for (MetricEvent metricEvent : hashSet) {
            if (!metricEvent.getAsDataPoints().isEmpty()) {
                this.mMetricsFactory.record(metricEvent, this.mPriority, this.mChannel);
            }
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.PeriodicMetricReporter
    public MetricEvent createTrackedMetricEvent(String str, String str2) {
        return createTrackedMetricEvent(str, str2, MetricEventType.getDefault());
    }

    @Override // com.amazon.client.metrics.thirdparty.PeriodicMetricReporter
    public MetricEvent getMetricEvent() {
        return this.mMetricEvent;
    }

    @Override // com.amazon.client.metrics.thirdparty.PeriodicMetricReporter
    public void shutdown() {
        this.mCommand.shutdown();
        this.mThreadPool.shutdown();
        try {
            if (this.mThreadPool.awaitTermination(2000L, TimeUnit.MILLISECONDS)) {
                return;
            }
            this.mThreadPool.shutdownNow();
            this.mThreadPool.awaitTermination(2000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.PeriodicMetricReporter
    public void startRecordingPeriodically(long j, TimeUnit timeUnit) {
        this.mThreadPool.scheduleAtFixedRate(this.mCommand, j, j, timeUnit);
    }

    @Override // com.amazon.client.metrics.thirdparty.PeriodicMetricReporter
    public void stopTrackingMetricEvent(MetricEvent metricEvent) {
        synchronized (this.mAdditionalMetricEvents) {
            if (!this.mAdditionalMetricEvents.remove(metricEvent)) {
                return;
            }
            this.mMetricsFactory.record(metricEvent, this.mPriority, this.mChannel);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.PeriodicMetricReporter
    public MetricEvent createTrackedMetricEvent(String str, String str2, MetricEventType metricEventType) {
        MetricEvent createConcurrentMetricEvent;
        synchronized (this.mAdditionalMetricEvents) {
            createConcurrentMetricEvent = this.mMetricsFactory.createConcurrentMetricEvent(str, str2, metricEventType);
            this.mAdditionalMetricEvents.add(createConcurrentMetricEvent);
        }
        return createConcurrentMetricEvent;
    }

    public PeriodicMetricReporterImpl(MetricsFactory metricsFactory, String str, String str2, MetricEventType metricEventType, Priority priority) {
        Channel channel = Channel.ANONYMOUS;
        if (Priority.RESERVED_FOR_LOCATION_SERVICE.equals(priority)) {
            channel = Channel.LOCATION;
        } else if (Priority.RESERVED_FOR_NON_ANONYMOUS_METRICS.equals(priority)) {
            channel = Channel.NON_ANONYMOUS;
        }
        if (metricsFactory != null) {
            if (str != null && str.trim().length() != 0) {
                if (str2 != null && str2.trim().length() != 0) {
                    this.mMetricsFactory = metricsFactory;
                    this.mCommand = new PeriodicCommand();
                    this.mThreadPool = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() { // from class: com.amazon.client.metrics.thirdparty.PeriodicMetricReporterImpl.2
                        @Override // java.util.concurrent.ThreadFactory
                        public Thread newThread(Runnable runnable) {
                            Thread thread = new Thread(runnable, ThreadName.PERIODIC_METRIC_REPORTER);
                            thread.setPriority(4);
                            return thread;
                        }
                    });
                    this.mAdditionalMetricEvents = new HashSet();
                    this.mMetricEvent = this.mMetricsFactory.createConcurrentMetricEvent(str, str2, metricEventType, true);
                    this.mPriority = priority;
                    this.mChannel = channel;
                    return;
                }
                throw new IllegalArgumentException("Argument: source cannot be null or empty.");
            }
            throw new IllegalArgumentException("Argument: program cannot be null or empty.");
        }
        throw new IllegalArgumentException("Argument: factory cannot be null.");
    }

    public PeriodicMetricReporterImpl(MetricsFactory metricsFactory, String str, String str2, MetricEventType metricEventType) {
        this(metricsFactory, str, str2, metricEventType, Priority.NORMAL, Channel.ANONYMOUS);
    }

    public PeriodicMetricReporterImpl(MetricsFactory metricsFactory, String str, String str2) {
        this(metricsFactory, str, str2, MetricEventType.getDefault());
    }
}
