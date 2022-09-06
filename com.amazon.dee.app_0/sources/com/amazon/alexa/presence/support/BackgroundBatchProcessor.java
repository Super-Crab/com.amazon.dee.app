package com.amazon.alexa.presence.support;

import android.util.Log;
import com.amazon.alexa.presence.Presence;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes9.dex */
public class BackgroundBatchProcessor<T> {
    private static final String TAG = Presence.tag();
    private final Clock clock;
    private final Config config;
    private final Handler<T> handler;
    private final AtomicBoolean isRunning;
    private long lastUpdatedMilliseconds;
    private final AbstractQueue<Work<T>> queue;
    private final ExecutorService workConsumerExecutor;

    /* loaded from: classes9.dex */
    public interface Clock {
        long currentTimeMillis();
    }

    /* loaded from: classes9.dex */
    public static final class Config {
        public static final Config DEFAULT = new Config();
        private static final int DEFAULT_MAX_BATCH_SIZE = 10;
        private static final int DEFAULT_MAX_CHECK_BACK_SLEEP_DURATION_MS = 1000;
        private static final int DEFAULT_MAX_ITEM_AGE_MS = 5000;
        private static final int DEFAULT_MAX_QUEUE_IDLE_TO_FLUSH_MS = 2000;
        private final int maxBatchSize;
        private final int maxCheckBackSleepDurationMs;
        private final int maxItemAgeMs;
        private final int maxQueueIdleToFlushMs;

        /* loaded from: classes9.dex */
        public static final class Builder {
            private int maxBatchSize = 10;
            private int maxItemAgeMs = 5000;
            private int maxQueueIdleToFlushMs = 2000;
            private int maxCheckBackSleepDurationMs = 1000;

            public Config build() {
                return new Config(this.maxBatchSize, this.maxItemAgeMs, this.maxQueueIdleToFlushMs, this.maxCheckBackSleepDurationMs);
            }

            public Builder withMaxBatchSize(int i) {
                this.maxBatchSize = i;
                return this;
            }

            public Builder withMaxCheckBackSleepDurationMs(int i) {
                this.maxCheckBackSleepDurationMs = i;
                return this;
            }

            public Builder withMaxItemAgeMs(int i) {
                this.maxItemAgeMs = i;
                return this;
            }

            public Builder withMaxQueueIdleToFlushMs(int i) {
                this.maxQueueIdleToFlushMs = i;
                return this;
            }
        }

        public Config() {
            this(10, 5000, 2000, 1000);
        }

        public int getMaxBatchSize() {
            return this.maxBatchSize;
        }

        public int getMaxCheckBackSleepDurationMs() {
            return this.maxCheckBackSleepDurationMs;
        }

        public int getMaxItemAgeMs() {
            return this.maxItemAgeMs;
        }

        public int getMaxQueueIdleToFlushMs() {
            return this.maxQueueIdleToFlushMs;
        }

        public Config(int i, int i2, int i3, int i4) {
            this.maxBatchSize = i;
            this.maxItemAgeMs = i2;
            this.maxQueueIdleToFlushMs = i3;
            this.maxCheckBackSleepDurationMs = i4;
        }
    }

    /* loaded from: classes9.dex */
    public interface Handler<T> {
        void accept(List<T> list);
    }

    /* loaded from: classes9.dex */
    public static final class Work<T> {
        private long createdTimeMillis;
        private T entity;

        public Work(T t) {
            this(t, System.currentTimeMillis());
        }

        public long getCreatedTimeMillis() {
            return this.createdTimeMillis;
        }

        public T getEntity() {
            return this.entity;
        }

        public Work(T t, long j) {
            this.entity = t;
            this.createdTimeMillis = j;
        }
    }

    public BackgroundBatchProcessor(Handler<T> handler) {
        this(handler, Config.DEFAULT);
    }

    private boolean attemptSoftStop() {
        this.isRunning.set(false);
        if (this.queue.isEmpty()) {
            return true;
        }
        this.isRunning.set(true);
        return false;
    }

    private void kickConsumer() {
        if (this.isRunning.compareAndSet(false, true)) {
            this.workConsumerExecutor.submit(new Runnable() { // from class: com.amazon.alexa.presence.support.-$$Lambda$BackgroundBatchProcessor$Hgw5ig66VG3k7Af9R3d0v1DUtMM
                @Override // java.lang.Runnable
                public final void run() {
                    BackgroundBatchProcessor.this.start();
                }
            });
        }
    }

    private boolean maxItemAgeExceeded() {
        Work<T> peek = this.queue.peek();
        return peek != null && now() - peek.getCreatedTimeMillis() >= ((long) this.config.getMaxItemAgeMs());
    }

    private long now() {
        return this.clock.currentTimeMillis();
    }

    private void processBatch() {
        ArrayList arrayList = new ArrayList(this.config.getMaxBatchSize());
        int i = 0;
        while (this.queue.peek() != null) {
            int i2 = i + 1;
            if (i < this.config.getMaxBatchSize()) {
                arrayList.add(this.queue.poll().getEntity());
                i = i2;
            }
        }
        try {
            this.handler.accept(arrayList);
        } catch (Throwable th) {
            Log.w(TAG, "Batching processor encountered an error when calling the batch handler:", th);
        }
    }

    private boolean queueExceedsMaxBatchSize() {
        Iterator<Work<T>> it2 = this.queue.iterator();
        int i = 0;
        while (it2.hasNext() && (i = i + 1) <= this.config.getMaxBatchSize()) {
            it2.next();
        }
        return i >= this.config.getMaxBatchSize();
    }

    private boolean queueIdleTimeExceeded() {
        return now() - this.lastUpdatedMilliseconds >= ((long) this.config.getMaxQueueIdleToFlushMs());
    }

    private void rest() {
        try {
            String.format("Resting for %s ms...", Integer.valueOf(this.config.getMaxCheckBackSleepDurationMs()));
            Thread.sleep(this.config.getMaxCheckBackSleepDurationMs());
        } catch (InterruptedException unused) {
            Log.w(TAG, "Interrupted while waiting for next check in on work queue.");
        }
    }

    private boolean shouldProcessBatch() {
        return queueExceedsMaxBatchSize() || maxItemAgeExceeded() || queueIdleTimeExceeded();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void start() {
        while (this.isRunning.get() && !this.queue.isEmpty()) {
            if (shouldProcessBatch()) {
                processBatch();
            }
            if (this.queue.isEmpty() && attemptSoftStop()) {
                return;
            }
            if (!shouldProcessBatch()) {
                rest();
            }
        }
    }

    public void process(T t) {
        process((List) Arrays.asList(t));
    }

    public void processWork(List<Work<T>> list) {
        this.queue.addAll(list);
        this.lastUpdatedMilliseconds = now();
        kickConsumer();
    }

    public BackgroundBatchProcessor(Handler<T> handler, Config config) {
        this(handler, $$Lambda$z7k87RMWW4LEyQSY4lJPQxLBqtM.INSTANCE, config);
    }

    public void process(List<T> list) {
        ArrayList arrayList = new ArrayList();
        for (T t : list) {
            arrayList.add(new Work<>(t, now()));
        }
        processWork(arrayList);
    }

    public BackgroundBatchProcessor(Handler<T> handler, Clock clock, Config config) {
        this.workConsumerExecutor = Executors.newSingleThreadExecutor();
        this.isRunning = new AtomicBoolean(false);
        this.queue = new ConcurrentLinkedQueue();
        this.lastUpdatedMilliseconds = 0L;
        this.handler = handler;
        this.clock = clock;
        this.config = config;
    }
}
