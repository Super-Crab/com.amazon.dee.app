package com.amazon.client.metrics.thirdparty.codec;

import com.amazon.client.metrics.thirdparty.CodecException;
import com.amazon.client.metrics.thirdparty.MetricBatch;
import java.io.UnsupportedEncodingException;
/* loaded from: classes11.dex */
public class MetricBatchToStringCodec implements MetricBatchCodec {
    @Override // com.amazon.client.metrics.thirdparty.codec.MetricBatchCodec
    public byte[] encode(MetricBatch metricBatch) throws CodecException {
        try {
            return (metricBatch.toString() + "\n").getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new CodecException(e);
        }
    }
}
