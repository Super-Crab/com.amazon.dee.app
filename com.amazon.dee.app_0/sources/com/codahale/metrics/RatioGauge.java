package com.codahale.metrics;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
/* loaded from: classes9.dex */
public abstract class RatioGauge implements Gauge<Double> {

    /* loaded from: classes9.dex */
    public static class Ratio {
        private final double denominator;
        private final double numerator;

        private Ratio(double d, double d2) {
            this.numerator = d;
            this.denominator = d2;
        }

        public static Ratio of(double d, double d2) {
            return new Ratio(d, d2);
        }

        public double getValue() {
            double d = this.denominator;
            if (Double.isNaN(d) || Double.isInfinite(d) || d == FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
                return Double.NaN;
            }
            return this.numerator / d;
        }

        public String toString() {
            return this.numerator + ":" + this.denominator;
        }
    }

    protected abstract Ratio getRatio();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.codahale.metrics.Gauge
    /* renamed from: getValue */
    public Double mo6796getValue() {
        return Double.valueOf(getRatio().getValue());
    }
}
