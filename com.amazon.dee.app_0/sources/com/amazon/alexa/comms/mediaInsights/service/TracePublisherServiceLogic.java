package com.amazon.alexa.comms.mediaInsights.service;

import android.content.Context;
import android.os.Handler;
import android.os.Process;
import android.util.Log;
import com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration;
import com.amazon.alexa.comms.mediaInsights.service.factories.NetworkDetailsCollectorFactory;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class TracePublisherServiceLogic {
    @NonNull
    private Context appContext;
    @NonNull
    private TracePublisherServiceConfiguration serviceConfig;
    private Handler serviceHandler;
    private long timestampOfFirstTraceInQueue;
    private long timestampOfLastDeviceDetailsJob = 0;
    private long totalTracesSizeInBytes;
    @NonNull
    private TraceFlusher traceFlusher;
    private Queue<String> traces;

    public TracePublisherServiceLogic(TracePublisherServiceConfiguration tracePublisherServiceConfiguration, TraceFlusher traceFlusher, Context context) {
        this.serviceConfig = tracePublisherServiceConfiguration;
        this.traceFlusher = traceFlusher;
        this.appContext = context;
        initializeTracesQueue();
    }

    private Runnable createFlusherRunnable() {
        return new Runnable() { // from class: com.amazon.alexa.comms.mediaInsights.service.TracePublisherServiceLogic.1
            @Override // java.lang.Runnable
            public void run() {
                TracePublisherServiceLogic.this.flush();
            }
        };
    }

    private Runnable createNetworkDetailCollectorRunnable(final Context context) {
        return new Runnable() { // from class: com.amazon.alexa.comms.mediaInsights.service.TracePublisherServiceLogic.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Process.setThreadPriority(10);
                    NetworkDetailsCollectorFactory.create(context).collect();
                } catch (Throwable th) {
                    Log.e(LoggingUtils.MEDIA_INSIGHTS_TAG, "Network details could not be collected", th);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void flush() {
        if (this.totalTracesSizeInBytes == 0) {
            return;
        }
        Queue<String> queue = this.traces;
        initializeTracesQueue();
        this.traceFlusher.asyncFlushTraces(queue);
    }

    private boolean haveTracesExceededStorageSizeLimit() {
        return this.totalTracesSizeInBytes >= ((long) this.serviceConfig.getTotalTracesCapacityInBytes().intValue());
    }

    private void initializeTracesQueue() {
        this.traces = new LinkedBlockingQueue();
        this.totalTracesSizeInBytes = 0L;
        this.timestampOfFirstTraceInQueue = 0L;
    }

    private void scheduleDeviceDetailsJob() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.timestampOfLastDeviceDetailsJob >= this.serviceConfig.getDeviceDetailsJobIntervalInMillis().intValue()) {
            this.timestampOfLastDeviceDetailsJob = currentTimeMillis;
            new Thread(createNetworkDetailCollectorRunnable(this.appContext)).start();
        }
    }

    private void scheduleFlushJob() {
        this.serviceHandler.postDelayed(createFlusherRunnable(), this.serviceConfig.getFlushJobIntervalInMillis().longValue());
    }

    public void addTrace(String str) {
        if (str == null) {
            return;
        }
        this.traces.add(str);
        this.totalTracesSizeInBytes += str.length();
        scheduleDeviceDetailsJob();
        if (haveTracesExceededStorageSizeLimit()) {
            flush();
        } else if (this.timestampOfFirstTraceInQueue != 0) {
        } else {
            this.timestampOfFirstTraceInQueue = System.currentTimeMillis();
            scheduleFlushJob();
        }
    }

    public void forceFlush() {
        flush();
    }

    long getTimestampOfFirstTraceInQueue() {
        return this.timestampOfFirstTraceInQueue;
    }

    long getTotalTracesSizeInBytes() {
        return this.totalTracesSizeInBytes;
    }

    Queue<String> getTraces() {
        return this.traces;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setServiceHandler(Handler handler) {
        this.serviceHandler = handler;
    }
}
