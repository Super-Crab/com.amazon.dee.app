package com.amazon.client.metrics.thirdparty.codec;

import com.amazon.client.metrics.thirdparty.CodecException;
import com.amazon.client.metrics.thirdparty.MetricEntry;
/* loaded from: classes11.dex */
public interface MetricEntryCodec {
    EncodedMetricEntry encode(MetricEntry metricEntry) throws CodecException;
}
