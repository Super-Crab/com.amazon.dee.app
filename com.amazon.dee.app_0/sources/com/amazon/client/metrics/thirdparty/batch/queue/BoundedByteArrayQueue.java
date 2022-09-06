package com.amazon.client.metrics.thirdparty.batch.queue;

import com.amazon.client.metrics.thirdparty.PeriodicMetricReporter;
import com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration;
import com.amazon.device.utils.thirdparty.BackgroundThreadFactory;
import com.amazon.dp.logger.DPLogger;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes11.dex */
public abstract class BoundedByteArrayQueue implements ByteArrayQueue {
    protected static final String METRIC_NAME_DROPPED_BATCHES = "droppedBatches";
    protected static final String METRIC_NAME_EXPIRED_BATCHES = "expiredBatches";
    private static final String THREAD_NAME_BATCH_QUEUE_PURGER = "BatchQueuePurgerThread";
    private static final int THREAD_POOL_TERMINATION_WAIT_MS = 5000;
    private static final DPLogger log = new DPLogger("Metrics:BoundedByteArrayQueue");
    protected final BatchPipelineConfiguration mBatchPipelineConfiguration;
    protected final PeriodicMetricReporter mPeriodicMetricReporter;
    private String mQueueName;
    private ScheduledThreadPoolExecutor mQueuePurgerExecutor;
    protected final Set<ByteArrayQueueListener> mListeners = new HashSet(1);
    private final QueuePurger mQueuePurger = new QueuePurger();
    protected long mBytesUsed = 0;
    protected long mNumEntries = 0;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class QueuePurger implements Runnable {
        private final AtomicBoolean mIsActive = new AtomicBoolean(true);

        public QueuePurger() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.mIsActive.get()) {
                BoundedByteArrayQueue.log.verbose("QueuePurger.run", "Shutdown invoked.", new Object[0]);
                return;
            }
            BoundedByteArrayQueue.log.verbose("QueuePurger.run", "Purging expired batches.", new Object[0]);
            BoundedByteArrayQueue.this.purgeExpiredBatches();
        }

        public void shutdown() {
            this.mIsActive.set(false);
        }
    }

    public BoundedByteArrayQueue(BatchPipelineConfiguration batchPipelineConfiguration, PeriodicMetricReporter periodicMetricReporter) throws IllegalArgumentException {
        if (batchPipelineConfiguration.getMaxBatchQueueCapacityBytes() > 0) {
            if (batchPipelineConfiguration.getExpiryTimeMillis() >= 0) {
                if (batchPipelineConfiguration.getPurgePeriodMillis() < 0) {
                    throw new IllegalArgumentException("PurgePeriodMillis must not be negative.");
                }
                if (periodicMetricReporter != null) {
                    this.mPeriodicMetricReporter = periodicMetricReporter;
                    this.mBatchPipelineConfiguration = batchPipelineConfiguration;
                    initializeQueuePurger();
                    return;
                }
                throw new IllegalArgumentException("Periodic metric reporter must not be null.");
            }
            throw new IllegalArgumentException("ExpiryTimeMillis must not be negative.");
        }
        throw new IllegalArgumentException("Capacity of queue must be greater than 0 bytes.");
    }

    private void initializeQueuePurger() {
        this.mQueuePurgerExecutor = new ScheduledThreadPoolExecutor(1, new BackgroundThreadFactory(THREAD_NAME_BATCH_QUEUE_PURGER));
        this.mQueuePurgerExecutor.scheduleAtFixedRate(this.mQueuePurger, this.mBatchPipelineConfiguration.getPurgePeriodMillis(), this.mBatchPipelineConfiguration.getPurgePeriodMillis(), TimeUnit.MILLISECONDS);
        this.mQueuePurgerExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized void addListener(ByteArrayQueueListener byteArrayQueueListener) {
        if (byteArrayQueueListener != null) {
            this.mListeners.add(byteArrayQueueListener);
        } else {
            throw new IllegalArgumentException("listener cannot be null");
        }
    }

    protected long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized long getNumEntriesInQueue() {
        return this.mNumEntries;
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public String getQueueName() {
        return this.mQueueName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void notifyListeners() {
        for (ByteArrayQueueListener byteArrayQueueListener : this.mListeners) {
            byteArrayQueueListener.onInsert(this.mNumEntries, this.mBytesUsed);
        }
    }

    protected abstract void purgeExpiredBatches();

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized void removeListener(ByteArrayQueueListener byteArrayQueueListener) {
        if (byteArrayQueueListener != null) {
            this.mListeners.remove(byteArrayQueueListener);
        } else {
            throw new IllegalArgumentException("listener cannot be null");
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public void setQueueName(String str) {
        this.mQueueName = str;
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized void shutdown() {
        this.mQueuePurger.shutdown();
        this.mQueuePurgerExecutor.shutdown();
        try {
            if (!this.mQueuePurgerExecutor.awaitTermination(5000L, TimeUnit.MILLISECONDS)) {
                this.mQueuePurgerExecutor.shutdownNow();
                if (!this.mQueuePurgerExecutor.awaitTermination(5000L, TimeUnit.MILLISECONDS)) {
                    log.error("shutdown", "Thread pool did not terminate.", new Object[0]);
                }
            }
        } catch (InterruptedException e) {
            this.mQueuePurgerExecutor.shutdownNow();
            log.error("shutdown", "Thread pool interrupted on shutdown.", e);
            Thread.currentThread().interrupt();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void validateInput(SerializedBatch serializedBatch) throws IllegalArgumentException {
        if (serializedBatch != null && serializedBatch.getSerializedBytes() != null) {
            if (serializedBatch.getLength() > 0) {
                if (serializedBatch.getLength() > this.mBatchPipelineConfiguration.getMaxBatchQueueCapacityBytes()) {
                    throw new IllegalArgumentException("Serialized object size is larger than the maximum capacity.");
                }
                return;
            }
            throw new IllegalArgumentException("Serialized batch cannot be empty.");
        }
        throw new IllegalArgumentException("Serialized batch cannot be null.");
    }
}
