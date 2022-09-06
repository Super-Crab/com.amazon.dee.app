package com.amazon.client.metrics.thirdparty.batch.creator;

import com.amazon.client.metrics.thirdparty.BaseMetricsServiceFactory;
import com.amazon.client.metrics.thirdparty.CodecException;
import com.amazon.client.metrics.thirdparty.DeviceInfoManager;
import com.amazon.client.metrics.thirdparty.MetricBatch;
import com.amazon.client.metrics.thirdparty.MetricEntry;
import com.amazon.client.metrics.thirdparty.PeriodicMetricReporter;
import com.amazon.client.metrics.thirdparty.UserAgentHelper;
import com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue;
import com.amazon.client.metrics.thirdparty.batch.queue.SerializedBatch;
import com.amazon.client.metrics.thirdparty.codec.EncodedMetricEntry;
import com.amazon.client.metrics.thirdparty.codec.MetricBatchCodec;
import com.amazon.client.metrics.thirdparty.codec.MetricEntryCodec;
import com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration;
import com.amazon.clouddrive.extended.model.BulkOperationType;
import com.amazon.device.utils.thirdparty.BackgroundThreadFactory;
import com.amazon.dp.logger.DPLogger;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes11.dex */
public class BatchCreator {
    private static final String BATCH_COUNT = "BATCH_COUNT";
    private static final String BATCH_ENQUEUED = "BATCH_ENQUEUED";
    private static final String BATCH_OPEN_TIME = "BATCH_OPEN_TIME";
    private static final String BATCH_SIZE = "BATCH_SIZE";
    private static final String THREAD_NAME_BATCH_OPEN_TIME_WATCHER = "BatchOpenTimeWatcherThread";
    private static final int THREAD_POOL_TERMINATION_WAIT_MS = 5000;
    private static final DPLogger log = new DPLogger("Metrics:BatchCreator");
    private final BatchPipelineConfiguration mBatchPipelineConfiguration;
    private final ByteArrayQueue mBatchQueue;
    private final DeviceInfoManager mDeviceInfoManager;
    private final MetricBatchCodec mMetricBatchCodec;
    private final MetricEntryCodec mMetricEntryCodec;
    private final PeriodicMetricReporter mPeriodicMetricReporter;
    private MetricBatch mRunningBatch;
    private final UserAgentHelper mUserAgentHelper;
    private final BatchOpenTimeWatcher mBatchOpenTimeWatcher = new BatchOpenTimeWatcher();
    protected final ScheduledExecutorService mThreadPool = Executors.newSingleThreadScheduledExecutor(new BackgroundThreadFactory(THREAD_NAME_BATCH_OPEN_TIME_WATCHER));
    private final AtomicLong mTimeSinceLastPublish = new AtomicLong();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class BatchOpenTimeWatcher implements Callable<Void> {
        private BatchOpenTimeWatcher() {
        }

        @Override // java.util.concurrent.Callable
        public Void call() throws Exception {
            BatchCreator.this.checkBatchOpenTimeAndEnqueueIfReady();
            BatchCreator.this.scheduleBatchOpenTimeWatcher();
            return null;
        }
    }

    public BatchCreator(ByteArrayQueue byteArrayQueue, MetricBatchCodec metricBatchCodec, MetricEntryCodec metricEntryCodec, BatchPipelineConfiguration batchPipelineConfiguration, PeriodicMetricReporter periodicMetricReporter, DeviceInfoManager deviceInfoManager, UserAgentHelper userAgentHelper) throws CodecException {
        this.mBatchQueue = byteArrayQueue;
        this.mMetricBatchCodec = metricBatchCodec;
        this.mMetricEntryCodec = metricEntryCodec;
        this.mBatchPipelineConfiguration = batchPipelineConfiguration;
        this.mTimeSinceLastPublish.set(System.currentTimeMillis());
        this.mDeviceInfoManager = deviceInfoManager;
        this.mUserAgentHelper = userAgentHelper;
        this.mRunningBatch = new MetricBatch();
        this.mPeriodicMetricReporter = periodicMetricReporter;
        scheduleBatchOpenTimeWatcher();
    }

    private boolean maxBatchCountReached() {
        return ((long) this.mRunningBatch.getMetricEntryCount()) >= this.mBatchPipelineConfiguration.getMaxNumEntriesPerBatch();
    }

    private boolean maxBatchOpenTimeReached() {
        return System.currentTimeMillis() - this.mTimeSinceLastPublish.get() >= this.mBatchPipelineConfiguration.getMaxBatchOpenTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleBatchOpenTimeWatcher() {
        try {
            this.mThreadPool.schedule(this.mBatchOpenTimeWatcher, this.mBatchPipelineConfiguration.getCheckBatchOpenTimeMillis(), TimeUnit.MILLISECONDS);
        } catch (RejectedExecutionException e) {
            log.error("scheduleLastPublishTimeWatcher", "Unexpected rejected execution exception while scheduling LastPublishTimeWatcher", e);
            throw e;
        }
    }

    public synchronized void addMetricEntry(MetricEntry metricEntry) {
        EncodedMetricEntry encode;
        if (metricEntry != null) {
            this.mUserAgentHelper.addUserAgentIfNotSet(metricEntry);
            try {
                encode = this.mMetricEntryCodec.encode(metricEntry);
            } catch (CodecException e) {
                this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("addEntry.CodecException", 1.0d);
                log.error(BulkOperationType.add, "Codec Exception while trying to add metric to batch.", e);
            } catch (Exception e2) {
                this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("addEntry.UnexpectedException", 1.0d);
                log.error(BulkOperationType.add, "Unexpected exception while trying to add metric to batch.", e2);
            }
            if (encode != null && encode.getEncodedSize() != 0) {
                log.debug("addMetricEntry", "Adding metric entry", "metricEntry", metricEntry.toString());
                this.mRunningBatch.addEntry(encode);
                if (!BaseMetricsServiceFactory.PROGRAM_NAME_METRICS_SERVICE.equals(metricEntry.getProgram()) || !BaseMetricsServiceFactory.SOURCE_NAME_RECORD_METRIC.equals(metricEntry.getSource())) {
                    this.mRunningBatch.setContainsUserMetrics(true);
                }
                if (maxBatchCountReached() || maxBatchSizeReached()) {
                    enqueueBatchForTransmission();
                }
            } else {
                throw new IllegalArgumentException("Metric entry serialized to null or nothing.");
            }
        } else {
            throw new IllegalArgumentException("Cannot add null metric entry");
        }
    }

    protected synchronized void checkBatchOpenTimeAndEnqueueIfReady() {
        if (maxBatchOpenTimeReached() && this.mRunningBatch.containsUserMetrics()) {
            enqueueBatchForTransmission();
        }
    }

    public synchronized void enqueueBatchForTransmission() {
        if (this.mRunningBatch.getBatchSizeInBytes() == 0) {
            log.debug("enqueueBatchForTransmission", "Metrics Batch is empty. Aborting enqueue operation.", new Object[0]);
            return;
        }
        if (maxBatchCountReached()) {
            this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("enqueueReason.batchCount", 1.0d);
        } else if (maxBatchSizeReached()) {
            this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("enqueueReason.batchSize", 1.0d);
        } else if (maxBatchOpenTimeReached()) {
            this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("enqueueReason.batchOpenTime", 1.0d);
        } else {
            this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("enqueueReason.forceEnqueue", 1.0d);
        }
        try {
            try {
                this.mRunningBatch.putMetricsDeviceInfo(this.mDeviceInfoManager.getDeviceInfo().getDeviceInfo());
                log.debug("enqueueBatchForTransmission", "Metrics Batch created. Adding to queue", new Object[0]);
                this.mBatchQueue.add(new SerializedBatch(this.mMetricBatchCodec.encode(this.mRunningBatch)), true);
                this.mPeriodicMetricReporter.getMetricEvent().incrementCounter(BATCH_COUNT, this.mRunningBatch.getMetricEntryCount());
                this.mPeriodicMetricReporter.getMetricEvent().incrementCounter(BATCH_SIZE, this.mRunningBatch.getBatchSizeInBytes());
                this.mPeriodicMetricReporter.getMetricEvent().addTimer(BATCH_OPEN_TIME, System.currentTimeMillis() - this.mTimeSinceLastPublish.get());
                this.mPeriodicMetricReporter.getMetricEvent().incrementCounter(BATCH_ENQUEUED, 1.0d);
            } catch (CodecException e) {
                this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("enqueBatch.CodecException", 1.0d);
                log.error("enqueueBatchForTransmission", "Exception trying to serialize metrics batch", e);
            } catch (IOException e2) {
                this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("enqueBatch.IOException", 1.0d);
                log.error("enqueueBatchForTransmission", "IOException while trying to add metrics batch to queue.", e2);
            }
        } catch (IllegalArgumentException e3) {
            this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("enqueBatch.IllegalArgumentException", 1.0d);
            log.error("enqueueBatchForTransmission", "IllegalArguementException while trying to add metrics batch to queue", e3);
        } catch (Exception e4) {
            this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("enqueBatch.UnknownException", 1.0d);
            log.error("enqueueBatchForTransmission", "Unexpected Exception while trying to add metrics batch to queue", e4);
        }
        this.mRunningBatch = new MetricBatch();
        this.mTimeSinceLastPublish.set(System.currentTimeMillis());
    }

    protected boolean maxBatchSizeReached() {
        return this.mRunningBatch.getBatchSizeInBytes() >= this.mBatchPipelineConfiguration.getMaxSizePerBatchBytes();
    }

    public synchronized void shutdown() {
        this.mThreadPool.shutdown();
        try {
            if (!this.mThreadPool.awaitTermination(5000L, TimeUnit.MILLISECONDS)) {
                this.mThreadPool.shutdownNow();
                if (!this.mThreadPool.awaitTermination(5000L, TimeUnit.MILLISECONDS)) {
                    log.error("shutdown", "Thread pool did not terminate.", new Object[0]);
                }
            }
        } catch (InterruptedException e) {
            this.mThreadPool.shutdownNow();
            log.error("shutdown", "Thread pool interrupted on shutdown.", e);
            Thread.currentThread().interrupt();
        }
    }
}
