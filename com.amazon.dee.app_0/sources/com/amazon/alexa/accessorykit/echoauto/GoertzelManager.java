package com.amazon.alexa.accessorykit.echoauto;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
/* loaded from: classes6.dex */
public class GoertzelManager {
    private static final String TAG = "PlaybackLatency";
    private long[] detectionTimes;
    private final Goertzel[] goertzels;
    private int index;
    private final double[] maxMagnitudes;
    private int samplesProcessed;
    private int[] samplesProcessedBeforeDetection;
    private final int size;
    private final boolean[] switches;
    private final double[] thresholds;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GoertzelManager(float f, int i, float[] fArr, double[] dArr) {
        boolean z = true;
        Preconditions.precondition(f > 0.0f, "samplingRate <= 0");
        Preconditions.precondition(i <= 0 ? false : z, "numberOfSamples <= 0");
        Preconditions.equals(fArr.length, dArr.length, "frequencies.length != thresholds.length");
        this.thresholds = dArr;
        this.size = fArr.length;
        int i2 = this.size;
        this.maxMagnitudes = new double[i2];
        this.goertzels = new Goertzel[i2];
        int i3 = 0;
        while (true) {
            int i4 = this.size;
            if (i3 < i4) {
                this.goertzels[i3] = new Goertzel(f, fArr[i3], i);
                i3++;
            } else {
                this.switches = new boolean[i4];
                this.detectionTimes = new long[i4];
                this.samplesProcessedBeforeDetection = new int[i4];
                this.index = 0;
                this.samplesProcessed = 0;
                return;
            }
        }
    }

    private boolean detectedUpToHere(int i) {
        if (i >= this.size) {
            return false;
        }
        for (int i2 = 0; i2 <= i; i2++) {
            if (!this.switches[i2]) {
                return false;
            }
        }
        return true;
    }

    private int maxMagnitudeIndex(double d) {
        StringBuilder sb = new StringBuilder();
        double d2 = 0.0d;
        int i = -1;
        for (int i2 = 0; i2 < this.size; i2++) {
            double sqrt = Math.sqrt(this.goertzels[i2].getMagnitudeSquared()) / (d == FrostVideoEffectController.VIDEO_STRENGTH_CLEAR ? 1.0d : d);
            if (sqrt > this.thresholds[i2] && sqrt > d2) {
                i = i2;
                d2 = sqrt;
            }
            double[] dArr = this.maxMagnitudes;
            if (dArr[i2] < sqrt) {
                dArr[i2] = sqrt;
            }
            sb.append(String.format("%.2f ", Double.valueOf(sqrt)));
        }
        sb.toString();
        return i;
    }

    private double rootMeanSquare(float[] fArr) {
        int length = fArr.length;
        double d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        if (length == 0) {
            return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        }
        for (float f : fArr) {
            d += f * f;
        }
        return Math.sqrt(d / length);
    }

    public long detectionTime(int i) {
        if (i >= this.size) {
            return -1L;
        }
        return this.detectionTimes[i];
    }

    public double[] getMaxMagnitudes() {
        return this.maxMagnitudes;
    }

    public boolean isDetected() {
        int i = this.index;
        int i2 = this.size;
        if (i < i2 - 1) {
            return false;
        }
        return detectedUpToHere(i2 - 1);
    }

    public int processSamples(float[] fArr) {
        for (int i = 0; i < this.size; i++) {
            this.goertzels[i].resetGoertzel();
        }
        for (float f : fArr) {
            for (int i2 = 0; i2 < this.size; i2++) {
                this.goertzels[i2].processSample(f);
            }
        }
        this.samplesProcessed += fArr.length;
        int maxMagnitudeIndex = maxMagnitudeIndex(rootMeanSquare(fArr));
        if (maxMagnitudeIndex != -1 && detectedUpToHere(maxMagnitudeIndex - 1) && !this.switches[maxMagnitudeIndex]) {
            this.detectionTimes[maxMagnitudeIndex] = System.currentTimeMillis();
            this.samplesProcessedBeforeDetection[maxMagnitudeIndex] = this.samplesProcessed;
            this.switches[maxMagnitudeIndex] = true;
            this.index++;
            String.format("new index: %d", Integer.valueOf(this.index));
        }
        return maxMagnitudeIndex;
    }

    public int samplesProcessedBeforeDetection(int i) {
        if (i >= this.size) {
            return -1;
        }
        return this.samplesProcessedBeforeDetection[i];
    }
}
