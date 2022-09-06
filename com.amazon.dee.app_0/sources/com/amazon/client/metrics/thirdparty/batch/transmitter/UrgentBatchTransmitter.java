package com.amazon.client.metrics.thirdparty.batch.transmitter;

import android.content.Context;
import com.amazon.client.metrics.thirdparty.PeriodicMetricReporter;
import com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue;
import com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueueListener;
import com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration;
import com.amazon.client.metrics.thirdparty.transport.MetricsTransport;
import com.amazon.client.metrics.thirdparty.transport.TransportStateNotifier;
import com.amazon.dp.logger.DPLogger;
/* loaded from: classes11.dex */
public class UrgentBatchTransmitter extends PeriodicBatchTransmitter implements ByteArrayQueueListener, TransportStateNotifier.TransportWarmedListener {
    private static final DPLogger log = new DPLogger("Metrics:UrgentBatchTransmitter");

    public UrgentBatchTransmitter(ByteArrayQueue byteArrayQueue, MetricsTransport metricsTransport, UploadResultBroadcaster uploadResultBroadcaster, BatchPipelineConfiguration batchPipelineConfiguration, PeriodicMetricReporter periodicMetricReporter, long j, Context context) {
        super(byteArrayQueue, metricsTransport, uploadResultBroadcaster, batchPipelineConfiguration, periodicMetricReporter, j, context);
        if (metricsTransport instanceof TransportStateNotifier) {
            ((TransportStateNotifier) metricsTransport).listenForTransportWarmed(this);
            byteArrayQueue.addListener(this);
            return;
        }
        throw new IllegalArgumentException("Unsupported MetricsTransport. TransportStateNotifier expected.");
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.TransportStateNotifier.TransportWarmedListener
    public void notifyTransportWarmed() {
        log.verbose("notifyTransportWarmed", "Notified that the transport is warm. Attempting to send batches.", new Object[0]);
        transmitBatches(false);
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueueListener
    public void onInsert(long j, long j2) {
        log.verbose("onInsert", "attempting transmission of batches", new Object[0]);
        transmitBatches(false);
    }
}
