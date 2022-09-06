package com.google.common.math;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Doubles;
@Beta
@GwtIncompatible
/* loaded from: classes3.dex */
public final class PairedStatsAccumulator {
    private final StatsAccumulator xStats = new StatsAccumulator();
    private final StatsAccumulator yStats = new StatsAccumulator();
    private double sumOfProductsOfDeltas = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;

    private static double ensureInUnitRange(double d) {
        return Doubles.constrainToRange(d, -1.0d, 1.0d);
    }

    private double ensurePositive(double d) {
        if (d > FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            return d;
        }
        return Double.MIN_VALUE;
    }

    public void add(double d, double d2) {
        this.xStats.add(d);
        if (Doubles.isFinite(d) && Doubles.isFinite(d2)) {
            if (this.xStats.count() > 1) {
                this.sumOfProductsOfDeltas = ((d2 - this.yStats.mean()) * (d - this.xStats.mean())) + this.sumOfProductsOfDeltas;
            }
        } else {
            this.sumOfProductsOfDeltas = Double.NaN;
        }
        this.yStats.add(d2);
    }

    public void addAll(PairedStats pairedStats) {
        if (pairedStats.count() == 0) {
            return;
        }
        this.xStats.addAll(pairedStats.xStats());
        if (this.yStats.count() == 0) {
            this.sumOfProductsOfDeltas = pairedStats.sumOfProductsOfDeltas();
        } else {
            this.sumOfProductsOfDeltas = ((pairedStats.yStats().mean() - this.yStats.mean()) * (pairedStats.xStats().mean() - this.xStats.mean()) * pairedStats.count()) + pairedStats.sumOfProductsOfDeltas() + this.sumOfProductsOfDeltas;
        }
        this.yStats.addAll(pairedStats.yStats());
    }

    public long count() {
        return this.xStats.count();
    }

    public final LinearTransformation leastSquaresFit() {
        boolean z = true;
        Preconditions.checkState(count() > 1);
        if (Double.isNaN(this.sumOfProductsOfDeltas)) {
            return LinearTransformation.forNaN();
        }
        double sumOfSquaresOfDeltas = this.xStats.sumOfSquaresOfDeltas();
        if (sumOfSquaresOfDeltas > FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            if (this.yStats.sumOfSquaresOfDeltas() > FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
                return LinearTransformation.mapping(this.xStats.mean(), this.yStats.mean()).withSlope(this.sumOfProductsOfDeltas / sumOfSquaresOfDeltas);
            }
            return LinearTransformation.horizontal(this.yStats.mean());
        }
        if (this.yStats.sumOfSquaresOfDeltas() <= FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            z = false;
        }
        Preconditions.checkState(z);
        return LinearTransformation.vertical(this.xStats.mean());
    }

    public final double pearsonsCorrelationCoefficient() {
        boolean z = true;
        Preconditions.checkState(count() > 1);
        if (Double.isNaN(this.sumOfProductsOfDeltas)) {
            return Double.NaN;
        }
        double sumOfSquaresOfDeltas = this.xStats.sumOfSquaresOfDeltas();
        double sumOfSquaresOfDeltas2 = this.yStats.sumOfSquaresOfDeltas();
        Preconditions.checkState(sumOfSquaresOfDeltas > FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        if (sumOfSquaresOfDeltas2 <= FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            z = false;
        }
        Preconditions.checkState(z);
        return ensureInUnitRange(this.sumOfProductsOfDeltas / Math.sqrt(ensurePositive(sumOfSquaresOfDeltas * sumOfSquaresOfDeltas2)));
    }

    public double populationCovariance() {
        Preconditions.checkState(count() != 0);
        return this.sumOfProductsOfDeltas / count();
    }

    public final double sampleCovariance() {
        Preconditions.checkState(count() > 1);
        return this.sumOfProductsOfDeltas / (count() - 1);
    }

    public PairedStats snapshot() {
        return new PairedStats(this.xStats.snapshot(), this.yStats.snapshot(), this.sumOfProductsOfDeltas);
    }

    public Stats xStats() {
        return this.xStats.snapshot();
    }

    public Stats yStats() {
        return this.yStats.snapshot();
    }
}
