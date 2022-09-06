package com.amazon.alexa.comms.mediaInsights.impl;

import android.util.Log;
import java.util.ArrayDeque;
import java.util.Queue;
/* loaded from: classes6.dex */
public class EvictingBoundedQueue<T> {
    private final int capacity;
    private final Queue<T> queue;

    public EvictingBoundedQueue(int i) {
        this.capacity = i;
        this.queue = new ArrayDeque(i);
    }

    public synchronized void add(T t) {
        if (this.queue.size() >= this.capacity) {
            this.queue.poll();
            Log.e(LoggingUtils.MEDIA_INSIGHTS_TAG, "EvictingBoundedQueue is exceeding limits, message is being evicted");
        }
        this.queue.add(t);
    }

    public synchronized T poll() {
        return this.queue.poll();
    }
}
