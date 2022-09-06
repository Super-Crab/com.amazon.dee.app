package com.amazon.client.metrics.thirdparty.batch.queue;

import com.amazon.client.metrics.thirdparty.PeriodicMetricReporter;
import com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration;
import com.amazon.clouddrive.extended.model.BulkOperationType;
import com.amazon.dp.logger.DPLogger;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
/* loaded from: classes11.dex */
public class VolatileBoundedByteArrayQueue extends BoundedByteArrayQueue {
    private static final DPLogger log = new DPLogger("Metrics:VolatileBoundedByteArrayQueue");
    protected final Set<ByteArrayQueueListener> mListeners;
    private Deque<SerializedBatch> mQueue;

    public VolatileBoundedByteArrayQueue(BatchPipelineConfiguration batchPipelineConfiguration, PeriodicMetricReporter periodicMetricReporter) {
        super(batchPipelineConfiguration, periodicMetricReporter);
        this.mQueue = new LinkedList();
        this.mListeners = new HashSet(1);
    }

    private void ensureCapacity(SerializedBatch serializedBatch) {
        this.mBytesUsed = serializedBatch.getLength() + this.mBytesUsed;
        while (this.mBytesUsed > this.mBatchPipelineConfiguration.getMaxBatchQueueCapacityBytes()) {
            log.debug(BulkOperationType.add, "Queue is full. Dropping an item from the queue.", new Object[0]);
            this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("droppedBatches", 1.0d);
            if (remove() == null) {
                throw new IllegalArgumentException("All items removed and the queue is still full");
            }
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized void add(SerializedBatch serializedBatch, boolean z) throws IllegalArgumentException {
        validateInput(serializedBatch);
        ensureCapacity(serializedBatch);
        this.mQueue.add(serializedBatch);
        this.mNumEntries++;
        if (z) {
            notifyListeners();
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized void addFirst(SerializedBatch serializedBatch, boolean z) {
        validateInput(serializedBatch);
        ensureCapacity(serializedBatch);
        this.mQueue.addFirst(serializedBatch);
        this.mNumEntries++;
        if (z) {
            notifyListeners();
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public void persistBatches() {
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.BoundedByteArrayQueue
    public synchronized void purgeExpiredBatches() {
        long currentTimeMillis = System.currentTimeMillis() - this.mBatchPipelineConfiguration.getExpiryTimeMillis();
        long j = this.mNumEntries;
        while (this.mQueue.peek() != null && this.mQueue.peek().getTimestamp() < currentTimeMillis) {
            remove();
            this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("expiredBatches", 1.0d);
        }
        log.debug("purgeExpiredBatches", "Number of batches purged: ", Long.valueOf(j - this.mNumEntries));
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized SerializedBatch remove() {
        SerializedBatch poll;
        poll = this.mQueue.peek() == null ? null : this.mQueue.poll();
        if (poll != null && poll.getSerializedBytes() != null) {
            this.mBytesUsed -= poll.getLength();
            this.mNumEntries--;
        }
        return poll;
    }
}
