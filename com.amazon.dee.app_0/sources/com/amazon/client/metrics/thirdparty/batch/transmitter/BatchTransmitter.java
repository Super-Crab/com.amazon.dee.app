package com.amazon.client.metrics.thirdparty.batch.transmitter;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.client.metrics.thirdparty.MetricEvent;
import com.amazon.client.metrics.thirdparty.PeriodicMetricReporter;
import com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue;
import com.amazon.client.metrics.thirdparty.batch.queue.SerializedBatch;
import com.amazon.client.metrics.thirdparty.transport.MetricsTransport;
import com.amazon.client.metrics.thirdparty.transport.UploadResult;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes11.dex */
public abstract class BatchTransmitter {
    private static final String DAILY_UPLOAD_KEY = "lastUploadDay";
    private static final String MONTHLY_UPLOAD_KEY = "lastUploadMonth";
    private static final String WEEKLY_UPLOAD_KEY = "lastUploadWeek";
    private static final DPLogger log = new DPLogger("Metrics:BatchTransmitter");
    protected final ByteArrayQueue mByteArrayQueue;
    private Context mContext;
    protected final MetricsTransport mMetricsTransport;
    final PeriodicMetricReporter mPeriodicMetricReporter;
    protected final QueuePusher mQueuePusher;

    /* loaded from: classes11.dex */
    protected class QueuePusher implements Runnable {
        private boolean mBroadcastResultForCurrentRun;
        private final UploadResultBroadcaster mUploadResultBroadcaster;
        private final AtomicBoolean mIsActive = new AtomicBoolean(true);
        private final AtomicBoolean mEnableBroadcastResultForNextRun = new AtomicBoolean(false);

        public QueuePusher(UploadResultBroadcaster uploadResultBroadcaster) {
            this.mUploadResultBroadcaster = uploadResultBroadcaster;
        }

        private Throwable getExceptionRootCause(Exception exc) {
            if (exc == null) {
                return null;
            }
            while (true) {
                Throwable cause = exc.getCause();
                if (cause == null || exc == cause) {
                    break;
                }
                exc = cause;
            }
            return exc;
        }

        private boolean sendBatches(ByteArrayQueue byteArrayQueue) {
            String queueName = byteArrayQueue.getQueueName();
            LinkedList linkedList = new LinkedList();
            boolean z = false;
            boolean z2 = false;
            int i = 0;
            while (!z) {
                try {
                    try {
                        SerializedBatch remove = byteArrayQueue.remove();
                        if (remove == null) {
                            break;
                        }
                        UploadResult transmitBatch = transmitBatch(remove);
                        int uploadStatus = transmitBatch.getUploadStatus();
                        if (uploadStatus != 1) {
                            if (uploadStatus != 2) {
                                if (uploadStatus != 4 && uploadStatus != 5) {
                                    if (uploadStatus != 6) {
                                        if (uploadStatus != 7) {
                                            if (uploadStatus != 10) {
                                            }
                                        }
                                    }
                                }
                                z = true;
                            }
                            linkedList.push(remove);
                            if (this.mBroadcastResultForCurrentRun) {
                                this.mUploadResultBroadcaster.broadcastResult(transmitBatch.getUploadStatus(), 0, queueName);
                            }
                            z2 = true;
                        } else {
                            i++;
                        }
                    } catch (IOException e) {
                        BatchTransmitter.log.error("QueuePusher.sendBatches", "Unable to send  " + queueName + " queue batches" + e, new Object[0]);
                        if (this.mBroadcastResultForCurrentRun) {
                            this.mUploadResultBroadcaster.broadcastResult(-1, 0, queueName);
                        }
                        BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("QueuePusher.BATCH_FAILURE", 1.0d);
                        Throwable exceptionRootCause = getExceptionRootCause(e);
                        BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("QueuePusher.BATCH_FAILURE.Exception." + exceptionRootCause.getClass().getSimpleName(), 1.0d);
                    }
                } finally {
                    BatchTransmitter.this.mMetricsTransport.close();
                }
            }
            if (z2) {
                BatchTransmitter.log.info("QueuePusher.sendBatches", "Partial number of " + queueName + " queue batches sent", Integer.valueOf(i));
                if (this.mBroadcastResultForCurrentRun) {
                    this.mUploadResultBroadcaster.broadcastResult(-1, i, queueName);
                }
                while (!linkedList.isEmpty()) {
                    byteArrayQueue.addFirst((SerializedBatch) linkedList.pop(), false);
                }
                byteArrayQueue.persistBatches();
                return false;
            }
            BatchTransmitter.log.info("QueuePusher.sendBatches", "Drained batch queue.", "Number of " + queueName + " queue batches sent", Integer.valueOf(i));
            if (this.mBroadcastResultForCurrentRun) {
                this.mUploadResultBroadcaster.broadcastResult(1, i, queueName);
            }
            if (i > 0) {
                updateActiveUploadStats();
            }
            return true;
        }

        private UploadResult transmitBatch(SerializedBatch serializedBatch) {
            UploadResult transmit = BatchTransmitter.this.mMetricsTransport.transmit(serializedBatch.getSerializedBytes());
            switch (transmit.getUploadStatus()) {
                case 1:
                    BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.SUCCESS", 1.0d);
                    break;
                case 2:
                    BatchTransmitter.log.warn("QueuePusher.sendBatches", "Batch transmission failed on network error, re-enqueuing batch", new Object[0]);
                    BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.NETWORK_ERROR", 1.0d);
                    break;
                case 3:
                    BatchTransmitter.log.error("QueuePusher.sendBatches", "Batch transmission failed on client error, discarded.", new Object[0]);
                    BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.CLIENT_ERROR", 1.0d);
                    MetricEvent metricEvent = BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent();
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MetricsTransport.CLIENT_ERROR.Response.");
                    outline107.append(transmit.getResponseCode());
                    metricEvent.incrementCounter(outline107.toString(), 1.0d);
                    break;
                case 4:
                    BatchTransmitter.log.warn("QueuePusher.sendBatches", "Batch transmission failed on server error, re-enqueuing batch", new Object[0]);
                    BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.SERVER_ERROR", 1.0d);
                    MetricEvent metricEvent2 = BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent();
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("MetricsTransport.SERVER_ERROR.Response.");
                    outline1072.append(transmit.getResponseCode());
                    metricEvent2.incrementCounter(outline1072.toString(), 1.0d);
                    break;
                case 5:
                    BatchTransmitter.log.warn("QueuePusher.sendBatches", "Batch transmission failed on credentials error, re-enqueuing batch.", new Object[0]);
                    BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.CREDENTIALS_ERROR", 1.0d);
                    break;
                case 6:
                    BatchTransmitter.log.error("QueuePusher.sendBatches", "Batch transmission failed on unknown error, re-enqueuing batch", new Object[0]);
                    BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.UNEXPECTED_ERROR", 1.0d);
                    MetricEvent metricEvent3 = BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent();
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("MetricsTransport.UNEXPECTED_ERROR.Response.");
                    outline1073.append(transmit.getResponseCode());
                    metricEvent3.incrementCounter(outline1073.toString(), 1.0d);
                    break;
                case 7:
                    BatchTransmitter.log.warn("QueuePusher.sendBatches", "Batch transmission failed due to no usable connection, re-enqueuing batch", new Object[0]);
                    BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.NO_USABLE_CONNECTION", 1.0d);
                    break;
                case 8:
                    BatchTransmitter.log.error("QueuePusher.sendBatches", "Batch transmission failed on empty data error, discarded.", new Object[0]);
                    BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.EMPTY_DATA_ERROR", 1.0d);
                    break;
                case 10:
                    BatchTransmitter.log.error("QueuePusher.sendBatches", "Batch transmission failed on IOException, re-enqueueing batch.", new Object[0]);
                    BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.IO_ERROR", 1.0d);
                    Throwable exceptionRootCause = getExceptionRootCause(transmit.getExceptionThrown());
                    if (exceptionRootCause != null) {
                        MetricEvent metricEvent4 = BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent();
                        StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("MetricsTransport.IO_ERROR.Exception.");
                        outline1074.append(exceptionRootCause.getClass().getSimpleName());
                        metricEvent4.incrementCounter(outline1074.toString(), 1.0d);
                        break;
                    }
                    break;
            }
            return transmit;
        }

        private void updateActiveUploadStats() {
            SharedPreferences sharedPreferences = BatchTransmitter.this.mContext.getSharedPreferences("ActiveUploadStats", 0);
            Calendar calendar = Calendar.getInstance();
            SharedPreferences.Editor edit = sharedPreferences.edit();
            int i = calendar.get(6);
            int i2 = calendar.get(3);
            int i3 = calendar.get(2);
            if (sharedPreferences.getInt(BatchTransmitter.DAILY_UPLOAD_KEY, -1) != i) {
                edit.putInt(BatchTransmitter.DAILY_UPLOAD_KEY, i);
                BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.DAILY_ACTIVE_UPLOADS", 1.0d);
            }
            if (sharedPreferences.getInt(BatchTransmitter.WEEKLY_UPLOAD_KEY, -1) != i2) {
                edit.putInt(BatchTransmitter.WEEKLY_UPLOAD_KEY, i2);
                BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.WEEKLY_ACTIVE_UPLOADS", 1.0d);
            }
            if (sharedPreferences.getInt(BatchTransmitter.MONTHLY_UPLOAD_KEY, -1) != i3) {
                edit.putInt(BatchTransmitter.MONTHLY_UPLOAD_KEY, i3);
                BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.MONTHLY_ACTIVE_UPLOADS", 1.0d);
            }
            edit.apply();
        }

        public void enableBroadcastResultForNextRun() {
            this.mEnableBroadcastResultForNextRun.set(true);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.mIsActive.get()) {
                BatchTransmitter.log.verbose("QueuePusher.run", "Shutdown invoked.", new Object[0]);
                return;
            }
            this.mBroadcastResultForCurrentRun = this.mEnableBroadcastResultForNextRun.getAndSet(false);
            BatchTransmitter.log.verbose("QueuePusher.run", "Transmitting batches.", new Object[0]);
            sendBatches(BatchTransmitter.this.mByteArrayQueue);
        }

        public void shutdown() {
            this.mIsActive.set(false);
        }
    }

    public BatchTransmitter(ByteArrayQueue byteArrayQueue, MetricsTransport metricsTransport, UploadResultBroadcaster uploadResultBroadcaster, PeriodicMetricReporter periodicMetricReporter, Context context) {
        this.mByteArrayQueue = byteArrayQueue;
        this.mMetricsTransport = metricsTransport;
        this.mQueuePusher = new QueuePusher(uploadResultBroadcaster);
        if (context != null) {
            if (this.mByteArrayQueue != null) {
                if (this.mMetricsTransport != null) {
                    this.mPeriodicMetricReporter = periodicMetricReporter;
                    this.mContext = context;
                    return;
                }
                throw new IllegalArgumentException("Metrics transport instance cannot be null.");
            }
            throw new IllegalArgumentException("Byte array queue cannot be null");
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    public void shutdown() {
        this.mQueuePusher.shutdown();
        this.mByteArrayQueue.shutdown();
        this.mMetricsTransport.shutdown();
    }

    public abstract void transmitBatches(boolean z);
}
