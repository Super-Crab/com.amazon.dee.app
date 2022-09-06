package com.amazon.client.metrics.thirdparty.batch.queue;

import java.io.IOException;
/* loaded from: classes11.dex */
public interface ByteArrayQueue {
    void add(SerializedBatch serializedBatch, boolean z) throws IOException;

    void addFirst(SerializedBatch serializedBatch, boolean z) throws IOException;

    void addListener(ByteArrayQueueListener byteArrayQueueListener);

    long getNumEntriesInQueue();

    String getQueueName();

    void persistBatches() throws IOException;

    SerializedBatch remove() throws IOException;

    void removeListener(ByteArrayQueueListener byteArrayQueueListener);

    void setQueueName(String str);

    void shutdown();
}
