package com.amazon.client.metrics.thirdparty.batch.transmitter;

import android.content.Context;
import com.amazon.client.metrics.thirdparty.PeriodicMetricReporter;
import com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue;
import com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration;
import com.amazon.client.metrics.thirdparty.transport.MetricsTransport;
import com.amazon.device.utils.thirdparty.BackgroundThreadFactory;
import com.amazon.dp.logger.DPLogger;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes11.dex */
public class PeriodicBatchTransmitter extends ThreadPoolBatchTransmitter {
    private static final String THREAD_NAME_PERIODIC_BATCH_TRANSMITTER = "PeriodicBatchTransmitterThread";
    private static final int THREAD_POOL_TERMINATION_WAIT_MS = 5000;
    private static final DPLogger log = new DPLogger("PeriodicBatchTransmitter");
    protected BatchPipelineConfiguration mBatchPipelineConfiguration;
    private ScheduledThreadPoolExecutor mScheduledExecutor;
    private Transmitter mTransmitter;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes11.dex */
    public class Transmitter implements Runnable {
        private final AtomicBoolean mIsActive = new AtomicBoolean(true);

        public Transmitter() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.mIsActive.get()) {
                PeriodicBatchTransmitter.log.verbose("Transmitter.run", "Shutdown invoked.", new Object[0]);
                return;
            }
            PeriodicBatchTransmitter.this.transmitBatches(false);
            PeriodicBatchTransmitter.log.verbose("Transmitter.run", "Rescheduling next transmission.", new Object[0]);
            PeriodicBatchTransmitter.this.mScheduledExecutor.schedule(PeriodicBatchTransmitter.this.mTransmitter, PeriodicBatchTransmitter.this.mBatchPipelineConfiguration.getTransmissionPeriodMillis(), TimeUnit.MILLISECONDS);
        }

        public void shutdown() {
            this.mIsActive.set(false);
        }
    }

    public PeriodicBatchTransmitter(ByteArrayQueue byteArrayQueue, MetricsTransport metricsTransport, UploadResultBroadcaster uploadResultBroadcaster, BatchPipelineConfiguration batchPipelineConfiguration, PeriodicMetricReporter periodicMetricReporter, long j, Context context) {
        super(byteArrayQueue, metricsTransport, uploadResultBroadcaster, periodicMetricReporter, context);
        this.mBatchPipelineConfiguration = batchPipelineConfiguration;
        createScheduledThreadPoolExecutor(j);
    }

    private void createScheduledThreadPoolExecutor(long j) {
        this.mScheduledExecutor = new ScheduledThreadPoolExecutor(1, new BackgroundThreadFactory(THREAD_NAME_PERIODIC_BATCH_TRANSMITTER));
        this.mScheduledExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        this.mTransmitter = new Transmitter();
        this.mScheduledExecutor.schedule(this.mTransmitter, j, TimeUnit.MILLISECONDS);
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.transmitter.ThreadPoolBatchTransmitter, com.amazon.client.metrics.thirdparty.batch.transmitter.BatchTransmitter
    public void shutdown() {
        this.mTransmitter.shutdown();
        this.mScheduledExecutor.shutdown();
        try {
            if (!this.mScheduledExecutor.awaitTermination(5000L, TimeUnit.MILLISECONDS)) {
                this.mScheduledExecutor.shutdownNow();
                if (!this.mScheduledExecutor.awaitTermination(5000L, TimeUnit.MILLISECONDS)) {
                    log.error("shutdown", "Thread pool did not terminate.", new Object[0]);
                }
            }
        } catch (InterruptedException e) {
            log.error("shutdown", "Thread pool interrupted on shutdown.", e);
            Thread.currentThread().interrupt();
        }
        super.shutdown();
    }
}
