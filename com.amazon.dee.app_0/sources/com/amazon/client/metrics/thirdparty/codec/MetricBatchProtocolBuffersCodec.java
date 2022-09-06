package com.amazon.client.metrics.thirdparty.codec;

import com.amazon.client.metrics.thirdparty.CodecException;
import com.amazon.client.metrics.thirdparty.MetricBatch;
import com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage;
import java.util.Map;
/* loaded from: classes11.dex */
public class MetricBatchProtocolBuffersCodec implements MetricBatchCodec {
    @Override // com.amazon.client.metrics.thirdparty.codec.MetricBatchCodec
    public byte[] encode(MetricBatch metricBatch) throws CodecException {
        if (metricBatch != null) {
            if (metricBatch.getBatchSizeInBytes() != 0) {
                DeviceMetricsMessage.MetricBatchMessage.Builder newBuilder = DeviceMetricsMessage.MetricBatchMessage.newBuilder();
                newBuilder.setDeviceSerialNumber(metricBatch.getDeviceSerialNumber());
                if (metricBatch.getDeviceType() != null) {
                    newBuilder.setDeviceType(metricBatch.getDeviceType());
                }
                for (Map.Entry<String, String> entry : metricBatch.getDeviceInfoMap().entrySet()) {
                    if (entry.getValue() != null) {
                        newBuilder.addMetadata(DeviceMetricsMessage.KeyValue.newBuilder().setKey(entry.getKey()).setValue(entry.getValue()).mo10084build());
                    }
                }
                for (int i = 0; i < metricBatch.getMetricEntryCount(); i++) {
                    newBuilder.addMetricEntry((DeviceMetricsMessage.MetricEntryMessage) metricBatch.getMetricEntry(i).mo2992getEncodedMetricEntry());
                }
                byte[] byteArray = newBuilder.mo10084build().toByteArray();
                if (byteArray != null) {
                    if (byteArray.length == 0) {
                        throw new CodecException("serialized batch is empty");
                    }
                    return byteArray;
                }
                throw new CodecException("serialized batch is null");
            }
            throw new CodecException("MetricEntryBatch is empty");
        }
        throw new CodecException("MetricEntryBatch is null");
    }
}
