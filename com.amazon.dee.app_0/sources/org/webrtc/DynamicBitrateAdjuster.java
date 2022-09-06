package org.webrtc;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
/* loaded from: classes5.dex */
class DynamicBitrateAdjuster extends BaseBitrateAdjuster {
    private static final double BITRATE_ADJUSTMENT_MAX_SCALE = 4.0d;
    private static final double BITRATE_ADJUSTMENT_SEC = 3.0d;
    private static final int BITRATE_ADJUSTMENT_STEPS = 20;
    private static final double BITS_PER_BYTE = 8.0d;
    private double deviationBytes = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    private double timeSinceLastAdjustmentMs = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    private int bitrateAdjustmentScaleExp = 0;

    @Override // org.webrtc.BaseBitrateAdjuster, org.webrtc.BitrateAdjuster
    public int getAdjustedBitrateBps() {
        return (int) (Math.pow(BITRATE_ADJUSTMENT_MAX_SCALE, this.bitrateAdjustmentScaleExp / 20.0d) * this.targetBitrateBps);
    }

    @Override // org.webrtc.BaseBitrateAdjuster, org.webrtc.BitrateAdjuster
    public void reportEncodedFrame(int i) {
        int i2 = this.targetFps;
        if (i2 == 0) {
            return;
        }
        int i3 = this.targetBitrateBps;
        this.deviationBytes = (i - ((i3 / BITS_PER_BYTE) / i2)) + this.deviationBytes;
        this.timeSinceLastAdjustmentMs = (1000.0d / i2) + this.timeSinceLastAdjustmentMs;
        double d = i3 / BITS_PER_BYTE;
        double d2 = BITRATE_ADJUSTMENT_SEC * d;
        this.deviationBytes = Math.min(this.deviationBytes, d2);
        this.deviationBytes = Math.max(this.deviationBytes, -d2);
        if (this.timeSinceLastAdjustmentMs <= 3000.0d) {
            return;
        }
        double d3 = this.deviationBytes;
        if (d3 > d) {
            this.bitrateAdjustmentScaleExp -= (int) ((d3 / d) + 0.5d);
            this.bitrateAdjustmentScaleExp = Math.max(this.bitrateAdjustmentScaleExp, -20);
            this.deviationBytes = d;
        } else {
            double d4 = -d;
            if (d3 < d4) {
                this.bitrateAdjustmentScaleExp += (int) (((-d3) / d) + 0.5d);
                this.bitrateAdjustmentScaleExp = Math.min(this.bitrateAdjustmentScaleExp, 20);
                this.deviationBytes = d4;
            }
        }
        this.timeSinceLastAdjustmentMs = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }

    @Override // org.webrtc.BaseBitrateAdjuster, org.webrtc.BitrateAdjuster
    public void setTargets(int i, int i2) {
        int i3 = this.targetBitrateBps;
        if (i3 > 0 && i < i3) {
            this.deviationBytes = (this.deviationBytes * i) / i3;
        }
        super.setTargets(i, i2);
    }
}
