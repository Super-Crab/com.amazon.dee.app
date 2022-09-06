package com.amazon.client.metrics.thirdparty.transport;
/* loaded from: classes11.dex */
public interface MetricsTransport {
    void close();

    void shutdown();

    UploadResult transmit(byte[] bArr);
}
