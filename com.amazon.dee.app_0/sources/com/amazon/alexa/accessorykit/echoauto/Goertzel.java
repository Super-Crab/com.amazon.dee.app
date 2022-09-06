package com.amazon.alexa.accessorykit.echoauto;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
/* loaded from: classes6.dex */
public final class Goertzel {
    private final double coefficient;
    private final double cosine;
    private double q1;
    private double q2;
    private final double sine;

    public Goertzel(float f, float f2, int i) {
        float f3;
        boolean z = true;
        Preconditions.precondition(f > 0.0f, "samplingRate <= 0");
        Preconditions.precondition(i > 0, "numberOfSamples <= 0");
        Preconditions.precondition(f <= 2.0f * f2 ? false : z, "samplingRate < targetFrequency * 2");
        double d = (((int) (((f2 * f3) / f) + 0.5d)) * 6.283185307179586d) / i;
        this.sine = Math.sin(d);
        this.cosine = Math.cos(d);
        this.coefficient = this.cosine * 2.0d;
    }

    public double getMagnitudeSquared() {
        double d = this.q1;
        double d2 = this.q2;
        return ((d2 * d2) + (d * d)) - ((d * d2) * this.coefficient);
    }

    public double[] getRealImag() {
        double d = this.q1;
        double d2 = this.q2;
        return new double[]{d - (this.cosine * d2), d2 * this.sine};
    }

    public void processSample(double d) {
        double d2 = this.coefficient;
        double d3 = this.q1;
        this.q2 = d3;
        this.q1 = ((d2 * d3) - this.q2) + d;
    }

    public void resetGoertzel() {
        this.q1 = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        this.q2 = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }
}
