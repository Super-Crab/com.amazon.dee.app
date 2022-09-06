package com.amazon.client.metrics.thirdparty.codec;

import com.amazon.client.metrics.thirdparty.CodecException;
import com.amazon.client.metrics.thirdparty.MetricBatch;
/* loaded from: classes11.dex */
public interface MetricBatchCodec {
    byte[] encode(MetricBatch metricBatch) throws CodecException;
}
