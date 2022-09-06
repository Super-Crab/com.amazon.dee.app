package com.codahale.metrics;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
/* loaded from: classes9.dex */
public class Snapshot {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final long[] values;

    public Snapshot(Collection<Long> collection) {
        Object[] array = collection.toArray();
        this.values = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            this.values[i] = ((Long) array[i]).longValue();
        }
        Arrays.sort(this.values);
    }

    public Snapshot(long[] jArr) {
        this.values = Arrays.copyOf(jArr, jArr.length);
        Arrays.sort(this.values);
    }

    public void dump(OutputStream outputStream) {
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream, UTF_8));
        try {
            long[] jArr = this.values;
            int length = jArr.length;
            for (int i = 0; i < length; i++) {
                printWriter.printf("%d%n", Long.valueOf(jArr[i]));
            }
        } finally {
            printWriter.close();
        }
    }

    public double get75thPercentile() {
        return getValue(0.75d);
    }

    public double get95thPercentile() {
        return getValue(0.95d);
    }

    public double get98thPercentile() {
        return getValue(0.98d);
    }

    public double get999thPercentile() {
        return getValue(0.999d);
    }

    public double get99thPercentile() {
        return getValue(0.99d);
    }

    public long getMax() {
        long[] jArr = this.values;
        if (jArr.length == 0) {
            return 0L;
        }
        return jArr[jArr.length - 1];
    }

    public double getMean() {
        long[] jArr = this.values;
        int length = jArr.length;
        double d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        if (length == 0) {
            return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        }
        for (long j : jArr) {
            d += j;
        }
        return d / this.values.length;
    }

    public double getMedian() {
        return getValue(0.5d);
    }

    public long getMin() {
        long[] jArr = this.values;
        if (jArr.length == 0) {
            return 0L;
        }
        return jArr[0];
    }

    public double getStdDev() {
        int length = this.values.length;
        double d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        if (length <= 1) {
            return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        }
        double mean = getMean();
        for (long j : this.values) {
            double d2 = j - mean;
            d += d2 * d2;
        }
        return Math.sqrt(d / (this.values.length - 1));
    }

    public double getValue(double d) {
        int i;
        long j;
        if (d < FrostVideoEffectController.VIDEO_STRENGTH_CLEAR || d > 1.0d) {
            throw new IllegalArgumentException(d + " is not in [0..1]");
        }
        long[] jArr = this.values;
        if (jArr.length == 0) {
            return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        }
        double length = d * (jArr.length + 1);
        if (length < 1.0d) {
            j = jArr[0];
        } else if (length < jArr.length) {
            double d2 = jArr[((int) length) - 1];
            return ((jArr[i] - d2) * (length - Math.floor(length))) + d2;
        } else {
            j = jArr[jArr.length - 1];
        }
        return j;
    }

    public long[] getValues() {
        long[] jArr = this.values;
        return Arrays.copyOf(jArr, jArr.length);
    }

    public int size() {
        return this.values.length;
    }
}
