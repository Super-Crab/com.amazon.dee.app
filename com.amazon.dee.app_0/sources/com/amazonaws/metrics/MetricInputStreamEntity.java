package com.amazonaws.metrics;

import android.support.v4.media.session.PlaybackStateCompat;
import com.amazonaws.internal.MetricAware;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.entity.InputStreamEntity;
/* loaded from: classes13.dex */
public class MetricInputStreamEntity extends InputStreamEntity {
    private static final int BUFFER_SIZE = 2048;
    private final ByteThroughputHelper helper;

    public MetricInputStreamEntity(ThroughputMetricType throughputMetricType, InputStream inputStream, long j) {
        super(inputStream, j);
        this.helper = new ByteThroughputHelper(throughputMetricType);
    }

    private void writeToWithMetrics(OutputStream outputStream) throws IOException {
        int read;
        if (outputStream != null) {
            InputStream content = getContent();
            long contentLength = getContentLength();
            try {
                byte[] bArr = new byte[2048];
                if (contentLength < 0) {
                    while (true) {
                        int read2 = content.read(bArr);
                        if (read2 == -1) {
                            break;
                        }
                        long startTiming = this.helper.startTiming();
                        outputStream.write(bArr, 0, read2);
                        this.helper.increment(read2, startTiming);
                    }
                } else {
                    while (contentLength > 0 && (read = content.read(bArr, 0, (int) Math.min((long) PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH, contentLength))) != -1) {
                        long startTiming2 = this.helper.startTiming();
                        outputStream.write(bArr, 0, read);
                        this.helper.increment(read, startTiming2);
                        contentLength -= read;
                    }
                }
                return;
            } finally {
                this.helper.reportMetrics();
                content.close();
            }
        }
        throw new IllegalArgumentException("Output stream may not be null");
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        if ((outputStream instanceof MetricAware) && ((MetricAware) outputStream).isMetricActivated()) {
            super.writeTo(outputStream);
        } else {
            writeToWithMetrics(outputStream);
        }
    }
}
