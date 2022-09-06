package com.amazon.client.metrics.thirdparty.batch.transmitter;

import android.content.Context;
import com.amazon.client.metrics.thirdparty.PeriodicMetricReporter;
import com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue;
import com.amazon.client.metrics.thirdparty.transport.MetricsTransport;
import com.amazon.device.utils.thirdparty.BackgroundThreadFactory;
import com.amazon.dp.logger.DPLogger;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public abstract class ThreadPoolBatchTransmitter extends BatchTransmitter {
    private static final int THREAD_KEEP_ALIVE_TIME_MS = 1;
    private static final String THREAD_NAME_BATCH_TRANSMITTER = "BatchTransmitterThreadName";
    private static final int THREAD_POOL_CORE_SIZE = 1;
    private static final int THREAD_POOL_MAX_SIZE = 1;
    private static final int THREAD_POOL_QUEUE_CAPACITY = 2;
    private static final int THREAD_POOL_TERMINATION_WAIT_MS = 5000;
    private static final DPLogger log = new DPLogger("Metrics:ThreadPoolBatchTransmitter");
    protected ThreadPoolExecutor mThreadPoolExecutor;

    public ThreadPoolBatchTransmitter(ByteArrayQueue byteArrayQueue, MetricsTransport metricsTransport, UploadResultBroadcaster uploadResultBroadcaster, PeriodicMetricReporter periodicMetricReporter, Context context) {
        super(byteArrayQueue, metricsTransport, uploadResultBroadcaster, periodicMetricReporter, context);
        createDefaultThreadPoolExecutor();
    }

    private void createDefaultThreadPoolExecutor() {
        this.mThreadPoolExecutor = new ThreadPoolExecutor(1, 1, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue(2), new BackgroundThreadFactory(THREAD_NAME_BATCH_TRANSMITTER));
        this.mThreadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.transmitter.BatchTransmitter
    public void shutdown() {
        super.shutdown();
        this.mThreadPoolExecutor.shutdown();
        try {
            if (this.mThreadPoolExecutor.awaitTermination(5000L, TimeUnit.MILLISECONDS)) {
                return;
            }
            this.mThreadPoolExecutor.shutdownNow();
            if (this.mThreadPoolExecutor.awaitTermination(5000L, TimeUnit.MILLISECONDS)) {
                return;
            }
            log.error("shutdown", "Thread pool did not terminate.", new Object[0]);
        } catch (InterruptedException e) {
            log.error("shutdown", "Thread pool interrupted on shutdown.", e);
            Thread.currentThread().interrupt();
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.transmitter.BatchTransmitter
    public synchronized void transmitBatches(boolean z) {
        if (z) {
            try {
                log.verbose("transmitBatches", "Enabling broadcast result for next run.", new Object[0]);
                this.mQueuePusher.enableBroadcastResultForNextRun();
            } catch (RejectedExecutionException e) {
                log.error("transmitBatches", "Unexpected rejected execution exception while executing QueuePusher", e);
            }
        }
        this.mThreadPoolExecutor.execute(this.mQueuePusher);
    }
}
