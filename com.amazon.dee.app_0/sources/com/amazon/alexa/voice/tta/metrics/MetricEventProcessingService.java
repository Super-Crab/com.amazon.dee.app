package com.amazon.alexa.voice.tta.metrics;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.tta.utils.Worker;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEvent;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* loaded from: classes11.dex */
public class MetricEventProcessingService {
    private static final String TAG = "MetricEventProcessingService";
    private final MetricEventProcessor[] eventProcessors;
    private final Worker.Spawner workerSpawner;
    @Nullable
    private Worker worker = null;
    private boolean running = false;
    private final ReadWriteLock lock = new ReentrantReadWriteLock(true);

    public MetricEventProcessingService(Worker.Spawner spawner, MetricEventProcessor... metricEventProcessorArr) {
        this.workerSpawner = spawner;
        this.eventProcessors = metricEventProcessorArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: runEventProcessors */
    public void lambda$processEvent$2$MetricEventProcessingService(@NonNull TtaEvent ttaEvent, @NonNull EventTime eventTime) {
        MetricEventProcessor[] metricEventProcessorArr;
        boolean z = false;
        for (MetricEventProcessor metricEventProcessor : this.eventProcessors) {
            if (metricEventProcessor.processEvent(ttaEvent, eventTime)) {
                String.format("processEvent: Event '%s' handled by %s.", ttaEvent.getName(), metricEventProcessor.getClass().getSimpleName());
                z = true;
            }
        }
        if (!z) {
            String.format("processEvent: Event '%s' received but not handled..", ttaEvent.getName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startEventProcessors */
    public void lambda$start$0$MetricEventProcessingService(EventTime eventTime) {
        lambda$processEvent$2$MetricEventProcessingService(MetricsLifecycleEvent.EVENT_SERVICE_START, eventTime);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: stopEventProcessors */
    public void lambda$stop$1$MetricEventProcessingService(EventTime eventTime) {
        lambda$processEvent$2$MetricEventProcessingService(MetricsLifecycleEvent.EVENT_SERVICE_STOP, eventTime);
        this.lock.writeLock().lock();
        try {
            if (!this.running && this.worker != null) {
                this.worker.quit();
                this.worker = null;
            }
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public void processEvent(@NonNull final TtaEvent ttaEvent, @NonNull final EventTime eventTime) {
        this.lock.readLock().lock();
        try {
            if (this.running && this.worker != null) {
                this.worker.post(new Runnable() { // from class: com.amazon.alexa.voice.tta.metrics.-$$Lambda$MetricEventProcessingService$LQlP2uMZ-uHu-1d72eevNx3JYiM
                    @Override // java.lang.Runnable
                    public final void run() {
                        MetricEventProcessingService.this.lambda$processEvent$2$MetricEventProcessingService(ttaEvent, eventTime);
                    }
                });
                return;
            }
            Log.e(TAG, String.format("processEvent: Event '%s' sent while %s was not running.", ttaEvent.getName(), TAG));
        } finally {
            this.lock.readLock().unlock();
        }
    }

    public void start(final EventTime eventTime) {
        this.lock.writeLock().lock();
        try {
            if (this.running) {
                return;
            }
            this.running = true;
            if (this.worker == null) {
                this.worker = this.workerSpawner.spawn();
            }
            this.worker.post(new Runnable() { // from class: com.amazon.alexa.voice.tta.metrics.-$$Lambda$MetricEventProcessingService$EQxmkx8UPgWLMuDg2dO7wT85gmc
                @Override // java.lang.Runnable
                public final void run() {
                    MetricEventProcessingService.this.lambda$start$0$MetricEventProcessingService(eventTime);
                }
            });
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public void stop(final EventTime eventTime) {
        this.lock.writeLock().lock();
        try {
            if (this.running && this.worker != null) {
                this.running = false;
                this.worker.post(new Runnable() { // from class: com.amazon.alexa.voice.tta.metrics.-$$Lambda$MetricEventProcessingService$k0UNnx5f8fC-EHacibbHvouBLyc
                    @Override // java.lang.Runnable
                    public final void run() {
                        MetricEventProcessingService.this.lambda$stop$1$MetricEventProcessingService(eventTime);
                    }
                });
            }
        } finally {
            this.lock.writeLock().unlock();
        }
    }
}
