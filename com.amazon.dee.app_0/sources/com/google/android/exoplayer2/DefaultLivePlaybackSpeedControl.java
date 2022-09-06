package com.google.android.exoplayer2;

import android.os.SystemClock;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import com.google.common.primitives.Longs;
/* loaded from: classes2.dex */
public final class DefaultLivePlaybackSpeedControl implements LivePlaybackSpeedControl {
    public static final float DEFAULT_FALLBACK_MAX_PLAYBACK_SPEED = 1.03f;
    public static final float DEFAULT_FALLBACK_MIN_PLAYBACK_SPEED = 0.97f;
    public static final long DEFAULT_MAX_LIVE_OFFSET_ERROR_MS_FOR_UNIT_SPEED = 20;
    public static final float DEFAULT_MIN_POSSIBLE_LIVE_OFFSET_SMOOTHING_FACTOR = 0.999f;
    public static final long DEFAULT_MIN_UPDATE_INTERVAL_MS = 1000;
    public static final float DEFAULT_PROPORTIONAL_CONTROL_FACTOR = 0.1f;
    public static final long DEFAULT_TARGET_LIVE_OFFSET_INCREMENT_ON_REBUFFER_MS = 500;
    private float adjustedPlaybackSpeed;
    private long currentTargetLiveOffsetUs;
    private final float fallbackMaxPlaybackSpeed;
    private final float fallbackMinPlaybackSpeed;
    private long idealTargetLiveOffsetUs;
    private long lastPlaybackSpeedUpdateMs;
    private final long maxLiveOffsetErrorUsForUnitSpeed;
    private float maxPlaybackSpeed;
    private long maxTargetLiveOffsetUs;
    private long mediaConfigurationTargetLiveOffsetUs;
    private float minPlaybackSpeed;
    private final float minPossibleLiveOffsetSmoothingFactor;
    private long minTargetLiveOffsetUs;
    private final long minUpdateIntervalMs;
    private final float proportionalControlFactor;
    private long smoothedMinPossibleLiveOffsetDeviationUs;
    private long smoothedMinPossibleLiveOffsetUs;
    private long targetLiveOffsetOverrideUs;
    private final long targetLiveOffsetRebufferDeltaUs;

    /* loaded from: classes2.dex */
    public static final class Builder {
        private float fallbackMinPlaybackSpeed = 0.97f;
        private float fallbackMaxPlaybackSpeed = 1.03f;
        private long minUpdateIntervalMs = 1000;
        private float proportionalControlFactorUs = 1.0E-7f;
        private long maxLiveOffsetErrorUsForUnitSpeed = C.msToUs(20);
        private long targetLiveOffsetIncrementOnRebufferUs = C.msToUs(500);
        private float minPossibleLiveOffsetSmoothingFactor = 0.999f;

        public DefaultLivePlaybackSpeedControl build() {
            return new DefaultLivePlaybackSpeedControl(this.fallbackMinPlaybackSpeed, this.fallbackMaxPlaybackSpeed, this.minUpdateIntervalMs, this.proportionalControlFactorUs, this.maxLiveOffsetErrorUsForUnitSpeed, this.targetLiveOffsetIncrementOnRebufferUs, this.minPossibleLiveOffsetSmoothingFactor);
        }

        public Builder setFallbackMaxPlaybackSpeed(float f) {
            Assertions.checkArgument(f >= 1.0f);
            this.fallbackMaxPlaybackSpeed = f;
            return this;
        }

        public Builder setFallbackMinPlaybackSpeed(float f) {
            Assertions.checkArgument(0.0f < f && f <= 1.0f);
            this.fallbackMinPlaybackSpeed = f;
            return this;
        }

        public Builder setMaxLiveOffsetErrorMsForUnitSpeed(long j) {
            Assertions.checkArgument(j > 0);
            this.maxLiveOffsetErrorUsForUnitSpeed = C.msToUs(j);
            return this;
        }

        public Builder setMinPossibleLiveOffsetSmoothingFactor(float f) {
            Assertions.checkArgument(f >= 0.0f && f < 1.0f);
            this.minPossibleLiveOffsetSmoothingFactor = f;
            return this;
        }

        public Builder setMinUpdateIntervalMs(long j) {
            Assertions.checkArgument(j > 0);
            this.minUpdateIntervalMs = j;
            return this;
        }

        public Builder setProportionalControlFactor(float f) {
            Assertions.checkArgument(f > 0.0f);
            this.proportionalControlFactorUs = f / 1000000.0f;
            return this;
        }

        public Builder setTargetLiveOffsetIncrementOnRebufferMs(long j) {
            Assertions.checkArgument(j >= 0);
            this.targetLiveOffsetIncrementOnRebufferUs = C.msToUs(j);
            return this;
        }
    }

    private void adjustTargetLiveOffsetUs(long j) {
        long j2 = (this.smoothedMinPossibleLiveOffsetDeviationUs * 3) + this.smoothedMinPossibleLiveOffsetUs;
        if (this.currentTargetLiveOffsetUs > j2) {
            float msToUs = (float) C.msToUs(this.minUpdateIntervalMs);
            this.currentTargetLiveOffsetUs = Longs.max(j2, this.idealTargetLiveOffsetUs, this.currentTargetLiveOffsetUs - (((this.adjustedPlaybackSpeed - 1.0f) * msToUs) + ((this.maxPlaybackSpeed - 1.0f) * msToUs)));
            return;
        }
        this.currentTargetLiveOffsetUs = Util.constrainValue(j - (Math.max(0.0f, this.adjustedPlaybackSpeed - 1.0f) / this.proportionalControlFactor), this.currentTargetLiveOffsetUs, j2);
        long j3 = this.maxTargetLiveOffsetUs;
        if (j3 == C.TIME_UNSET || this.currentTargetLiveOffsetUs <= j3) {
            return;
        }
        this.currentTargetLiveOffsetUs = j3;
    }

    private void maybeResetTargetLiveOffsetUs() {
        long j = this.mediaConfigurationTargetLiveOffsetUs;
        if (j != C.TIME_UNSET) {
            long j2 = this.targetLiveOffsetOverrideUs;
            if (j2 != C.TIME_UNSET) {
                j = j2;
            }
            long j3 = this.minTargetLiveOffsetUs;
            if (j3 != C.TIME_UNSET && j < j3) {
                j = j3;
            }
            long j4 = this.maxTargetLiveOffsetUs;
            if (j4 != C.TIME_UNSET && j > j4) {
                j = j4;
            }
        } else {
            j = -9223372036854775807L;
        }
        if (this.idealTargetLiveOffsetUs == j) {
            return;
        }
        this.idealTargetLiveOffsetUs = j;
        this.currentTargetLiveOffsetUs = j;
        this.smoothedMinPossibleLiveOffsetUs = C.TIME_UNSET;
        this.smoothedMinPossibleLiveOffsetDeviationUs = C.TIME_UNSET;
        this.lastPlaybackSpeedUpdateMs = C.TIME_UNSET;
    }

    private static long smooth(long j, long j2, float f) {
        return ((1.0f - f) * ((float) j2)) + (((float) j) * f);
    }

    private void updateSmoothedMinPossibleLiveOffsetUs(long j, long j2) {
        long j3 = j - j2;
        long j4 = this.smoothedMinPossibleLiveOffsetUs;
        if (j4 == C.TIME_UNSET) {
            this.smoothedMinPossibleLiveOffsetUs = j3;
            this.smoothedMinPossibleLiveOffsetDeviationUs = 0L;
            return;
        }
        this.smoothedMinPossibleLiveOffsetUs = Math.max(j3, smooth(j4, j3, this.minPossibleLiveOffsetSmoothingFactor));
        this.smoothedMinPossibleLiveOffsetDeviationUs = smooth(this.smoothedMinPossibleLiveOffsetDeviationUs, Math.abs(j3 - this.smoothedMinPossibleLiveOffsetUs), this.minPossibleLiveOffsetSmoothingFactor);
    }

    @Override // com.google.android.exoplayer2.LivePlaybackSpeedControl
    public float getAdjustedPlaybackSpeed(long j, long j2) {
        if (this.mediaConfigurationTargetLiveOffsetUs == C.TIME_UNSET) {
            return 1.0f;
        }
        updateSmoothedMinPossibleLiveOffsetUs(j, j2);
        if (this.lastPlaybackSpeedUpdateMs != C.TIME_UNSET && SystemClock.elapsedRealtime() - this.lastPlaybackSpeedUpdateMs < this.minUpdateIntervalMs) {
            return this.adjustedPlaybackSpeed;
        }
        this.lastPlaybackSpeedUpdateMs = SystemClock.elapsedRealtime();
        adjustTargetLiveOffsetUs(j);
        long j3 = j - this.currentTargetLiveOffsetUs;
        if (Math.abs(j3) < this.maxLiveOffsetErrorUsForUnitSpeed) {
            this.adjustedPlaybackSpeed = 1.0f;
        } else {
            this.adjustedPlaybackSpeed = Util.constrainValue((this.proportionalControlFactor * ((float) j3)) + 1.0f, this.minPlaybackSpeed, this.maxPlaybackSpeed);
        }
        return this.adjustedPlaybackSpeed;
    }

    @Override // com.google.android.exoplayer2.LivePlaybackSpeedControl
    public long getTargetLiveOffsetUs() {
        return this.currentTargetLiveOffsetUs;
    }

    @Override // com.google.android.exoplayer2.LivePlaybackSpeedControl
    public void notifyRebuffer() {
        long j = this.currentTargetLiveOffsetUs;
        if (j == C.TIME_UNSET) {
            return;
        }
        this.currentTargetLiveOffsetUs = j + this.targetLiveOffsetRebufferDeltaUs;
        long j2 = this.maxTargetLiveOffsetUs;
        if (j2 != C.TIME_UNSET && this.currentTargetLiveOffsetUs > j2) {
            this.currentTargetLiveOffsetUs = j2;
        }
        this.lastPlaybackSpeedUpdateMs = C.TIME_UNSET;
    }

    @Override // com.google.android.exoplayer2.LivePlaybackSpeedControl
    public void setLiveConfiguration(MediaItem.LiveConfiguration liveConfiguration) {
        this.mediaConfigurationTargetLiveOffsetUs = C.msToUs(liveConfiguration.targetOffsetMs);
        this.minTargetLiveOffsetUs = C.msToUs(liveConfiguration.minOffsetMs);
        this.maxTargetLiveOffsetUs = C.msToUs(liveConfiguration.maxOffsetMs);
        float f = liveConfiguration.minPlaybackSpeed;
        if (f == -3.4028235E38f) {
            f = this.fallbackMinPlaybackSpeed;
        }
        this.minPlaybackSpeed = f;
        float f2 = liveConfiguration.maxPlaybackSpeed;
        if (f2 == -3.4028235E38f) {
            f2 = this.fallbackMaxPlaybackSpeed;
        }
        this.maxPlaybackSpeed = f2;
        maybeResetTargetLiveOffsetUs();
    }

    @Override // com.google.android.exoplayer2.LivePlaybackSpeedControl
    public void setTargetLiveOffsetOverrideUs(long j) {
        this.targetLiveOffsetOverrideUs = j;
        maybeResetTargetLiveOffsetUs();
    }

    private DefaultLivePlaybackSpeedControl(float f, float f2, long j, float f3, long j2, long j3, float f4) {
        this.fallbackMinPlaybackSpeed = f;
        this.fallbackMaxPlaybackSpeed = f2;
        this.minUpdateIntervalMs = j;
        this.proportionalControlFactor = f3;
        this.maxLiveOffsetErrorUsForUnitSpeed = j2;
        this.targetLiveOffsetRebufferDeltaUs = j3;
        this.minPossibleLiveOffsetSmoothingFactor = f4;
        this.mediaConfigurationTargetLiveOffsetUs = C.TIME_UNSET;
        this.targetLiveOffsetOverrideUs = C.TIME_UNSET;
        this.minTargetLiveOffsetUs = C.TIME_UNSET;
        this.maxTargetLiveOffsetUs = C.TIME_UNSET;
        this.minPlaybackSpeed = f;
        this.maxPlaybackSpeed = f2;
        this.adjustedPlaybackSpeed = 1.0f;
        this.lastPlaybackSpeedUpdateMs = C.TIME_UNSET;
        this.idealTargetLiveOffsetUs = C.TIME_UNSET;
        this.currentTargetLiveOffsetUs = C.TIME_UNSET;
        this.smoothedMinPossibleLiveOffsetUs = C.TIME_UNSET;
        this.smoothedMinPossibleLiveOffsetDeviationUs = C.TIME_UNSET;
    }
}
