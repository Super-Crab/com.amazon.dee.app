package com.amazon.client.metrics.thirdparty.batch.queue;

import com.amazon.client.metrics.thirdparty.PeriodicMetricReporter;
import com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration;
import java.io.File;
import java.io.IOException;
/* loaded from: classes11.dex */
public class SemiVolatileBoundedByteArrayQueue implements ByteArrayQueue {
    NonVolatileBoundedByteArrayQueue mNonVolatileQueue;
    private String mQueueName;
    VolatileBoundedByteArrayQueue mVolatileQueue;

    public SemiVolatileBoundedByteArrayQueue(BatchPipelineConfiguration batchPipelineConfiguration, PeriodicMetricReporter periodicMetricReporter, File file) throws IllegalArgumentException, IOException {
        this.mVolatileQueue = new VolatileBoundedByteArrayQueue(batchPipelineConfiguration, periodicMetricReporter);
        this.mNonVolatileQueue = new NonVolatileBoundedByteArrayQueue(batchPipelineConfiguration, periodicMetricReporter, file);
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized void add(SerializedBatch serializedBatch, boolean z) {
        this.mVolatileQueue.add(serializedBatch, z);
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized void addFirst(SerializedBatch serializedBatch, boolean z) {
        this.mVolatileQueue.addFirst(serializedBatch, z);
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized void addListener(ByteArrayQueueListener byteArrayQueueListener) {
        this.mVolatileQueue.addListener(byteArrayQueueListener);
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized long getNumEntriesInQueue() {
        return this.mVolatileQueue.getNumEntriesInQueue() + this.mNonVolatileQueue.getNumEntriesInQueue();
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public String getQueueName() {
        return this.mQueueName;
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized void persistBatches() throws IOException {
        while (this.mVolatileQueue.getNumEntriesInQueue() > 0) {
            this.mNonVolatileQueue.add(this.mVolatileQueue.remove(), false);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized SerializedBatch remove() throws IOException {
        if (this.mVolatileQueue.getNumEntriesInQueue() > 0) {
            return this.mVolatileQueue.remove();
        } else if (this.mNonVolatileQueue.getNumEntriesInQueue() <= 0) {
            return null;
        } else {
            return this.mNonVolatileQueue.remove();
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized void removeListener(ByteArrayQueueListener byteArrayQueueListener) {
        this.mVolatileQueue.removeListener(byteArrayQueueListener);
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public void setQueueName(String str) {
        this.mQueueName = str;
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public void shutdown() {
        this.mVolatileQueue.shutdown();
        this.mNonVolatileQueue.shutdown();
    }
}
