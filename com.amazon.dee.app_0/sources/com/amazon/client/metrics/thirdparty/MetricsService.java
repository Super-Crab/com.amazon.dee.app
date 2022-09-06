package com.amazon.client.metrics.thirdparty;

import com.amazon.client.metrics.thirdparty.batch.creator.BatchCreator;
import com.amazon.client.metrics.thirdparty.batch.creator.PriorityChannelPair;
import com.amazon.client.metrics.thirdparty.batch.transmitter.BatchTransmitter;
import com.amazon.device.utils.thirdparty.DeviceUtil;
import com.amazon.dp.logger.DPLogger;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class MetricsService implements UploadIntentListener, CorPfmChangeListener {
    private static final DPLogger log = new DPLogger();
    private final Map<PriorityChannelPair, BatchCreator> mBatchCreatorMap;
    private final List<BatchTransmitter> mBatchTransmitterList;
    private final DeviceUtil mDeviceUtil;

    public MetricsService(DeviceUtil deviceUtil, Map<PriorityChannelPair, BatchCreator> map, List<BatchTransmitter> list) {
        if (deviceUtil != null) {
            if (map == null) {
                throw new NullPointerException("BatchCreatorMap can not be null");
            }
            if (list != null) {
                this.mDeviceUtil = deviceUtil;
                this.mBatchCreatorMap = map;
                this.mBatchTransmitterList = list;
                MetricsBroadcastReceiver.addUploadIntentListener(this);
                return;
            }
            throw new NullPointerException("BatchTransmitter list can not be null");
        }
        throw new NullPointerException("DeviceUtil can not be null");
    }

    @Override // com.amazon.client.metrics.thirdparty.CorPfmChangeListener
    public void onCorPfmChangeEventReceived() {
        log.info("onCorPfmChangeEventReceived", "Enqueuing all batches and updating device CoR/PFM", new Object[0]);
        for (BatchCreator batchCreator : this.mBatchCreatorMap.values()) {
            batchCreator.enqueueBatchForTransmission();
        }
        this.mDeviceUtil.updateCountryOfResidenceAndPreferredMarketplace();
    }

    @Override // com.amazon.client.metrics.thirdparty.UploadIntentListener
    public void onUploadIntentReceived() {
        log.info("onUploadIntentReceived", "Enqueuing all batches and triggering transmission.", new Object[0]);
        for (BatchCreator batchCreator : this.mBatchCreatorMap.values()) {
            batchCreator.enqueueBatchForTransmission();
        }
        for (BatchTransmitter batchTransmitter : this.mBatchTransmitterList) {
            batchTransmitter.transmitBatches(true);
        }
    }

    public void record(MetricEntry metricEntry, Priority priority, Channel channel) {
        BatchCreator batchCreator = this.mBatchCreatorMap.get(new PriorityChannelPair(priority, channel));
        if (batchCreator != null) {
            batchCreator.addMetricEntry(metricEntry);
        } else {
            log.error("record", String.format("Metric dropped. No batch pipeline exists for priority %s and channel %s", priority, channel), new Object[0]);
        }
    }

    public void shutdown() {
        log.info("shutdown", "Enqueuing all batches and shutting down batch creators and transmitters.", new Object[0]);
        for (BatchCreator batchCreator : this.mBatchCreatorMap.values()) {
            try {
                batchCreator.enqueueBatchForTransmission();
            } catch (Exception e) {
                log.error("shutdown", "Exception enqueuing batches:", e.getMessage());
            }
            batchCreator.shutdown();
        }
        for (BatchTransmitter batchTransmitter : this.mBatchTransmitterList) {
            batchTransmitter.shutdown();
        }
        MetricsBroadcastReceiver.shutdown();
    }
}
