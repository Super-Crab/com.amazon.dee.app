package com.amazon.client.metrics.thirdparty.codec;

import com.amazon.client.metrics.thirdparty.CodecException;
import com.amazon.client.metrics.thirdparty.DataPoint;
import com.amazon.client.metrics.thirdparty.DataPointType;
import com.amazon.client.metrics.thirdparty.MetricEntry;
import com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage;
/* loaded from: classes11.dex */
public class MetricEntryProtocolBuffersCodec implements MetricEntryCodec {

    /* renamed from: com.amazon.client.metrics.thirdparty.codec.MetricEntryProtocolBuffersCodec$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$client$metrics$thirdparty$DataPointType = new int[DataPointType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$DataPointType[DataPointType.CT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$DataPointType[DataPointType.TI.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$DataPointType[DataPointType.DV.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$DataPointType[DataPointType.CK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static DeviceMetricsMessage.DataPointMessage.DataType getDataType(DataPointType dataPointType) throws CodecException {
        int ordinal = dataPointType.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return DeviceMetricsMessage.DataPointMessage.DataType.TIMER;
            }
            if (ordinal == 2) {
                return DeviceMetricsMessage.DataPointMessage.DataType.DISCRETE;
            }
            if (ordinal == 3) {
                return DeviceMetricsMessage.DataPointMessage.DataType.CLICKSTREAM;
            }
            throw new CodecException("Invalid DataPoint type");
        }
        return DeviceMetricsMessage.DataPointMessage.DataType.COUNTER;
    }

    @Override // com.amazon.client.metrics.thirdparty.codec.MetricEntryCodec
    public EncodedMetricEntry encode(MetricEntry metricEntry) throws CodecException {
        if (metricEntry != null) {
            if (metricEntry.getDatapoints().size() != 0) {
                DeviceMetricsMessage.MetricEntryMessage.Builder timestamp = DeviceMetricsMessage.MetricEntryMessage.newBuilder().setProgram(metricEntry.getProgram()).setSource(metricEntry.getSource()).setTimestamp(metricEntry.getTimestamp());
                for (DataPoint dataPoint : metricEntry.getDatapoints()) {
                    timestamp.addDataPoint(DeviceMetricsMessage.DataPointMessage.newBuilder().setName(dataPoint.getName()).setValue(dataPoint.getValue()).setSampleSize(dataPoint.getSamples()).setType(getDataType(dataPoint.getType())).mo10084build());
                }
                return new ProtocolBuffersEncodedMetricEntry(timestamp.mo10084build());
            }
            throw new CodecException("Metric entry contains no data points");
        }
        throw new CodecException("Metric entry is null");
    }
}
