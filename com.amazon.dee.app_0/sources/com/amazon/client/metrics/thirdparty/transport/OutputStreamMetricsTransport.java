package com.amazon.client.metrics.thirdparty.transport;

import android.util.Log;
import com.amazon.client.metrics.thirdparty.transport.TransportStateNotifier;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes11.dex */
public class OutputStreamMetricsTransport implements MetricsTransport, TransportStateNotifier {
    private static final String TAG = "Metrics:OutputStreamMetricsTransport";
    private final OutputStream mOutputStream;

    public OutputStreamMetricsTransport(OutputStream outputStream) {
        if (outputStream != null) {
            this.mOutputStream = outputStream;
            return;
        }
        throw new NullPointerException("The OutputStream may not be null.");
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.MetricsTransport
    public void close() {
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.TransportStateNotifier
    public void listenForTransportWarmed(TransportStateNotifier.TransportWarmedListener transportWarmedListener) {
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.MetricsTransport
    public void shutdown() {
        try {
            this.mOutputStream.close();
        } catch (IOException e) {
            Log.e(TAG, "Unable to shutdown transport.", e);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.MetricsTransport
    public UploadResult transmit(byte[] bArr) {
        if (bArr != null && bArr.length != 0) {
            try {
                this.mOutputStream.write(bArr);
                this.mOutputStream.flush();
                return new UploadResult(1);
            } catch (IOException e) {
                Log.e(TAG, "Unable to transmit.", e);
                return new UploadResult(10, e);
            }
        }
        return new UploadResult(8);
    }
}
