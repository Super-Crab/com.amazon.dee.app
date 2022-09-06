package com.amazon.alexa.handsfree.protocols.uservoicerecognition.audio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
/* loaded from: classes8.dex */
public class ScaledVolumeProcessor {
    private static final float RMS_WINDOW_SIZE = 3000.0f;
    private double mRmsWindowStart = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;

    private double computeArrayRms(@NonNull ShortBuffer shortBuffer) {
        int remaining = shortBuffer.remaining();
        double d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        while (shortBuffer.remaining() != 0) {
            short s = shortBuffer.get();
            d += s * s;
        }
        return Math.sqrt(d / remaining);
    }

    public float computeScaledVolume(@Nullable byte[] bArr, int i) {
        if (bArr == null || i <= 0) {
            return 0.0f;
        }
        if (i <= bArr.length) {
            return computeScaledVolume(ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer());
        }
        throw new IllegalArgumentException("dataSize must be less than or equal to the byte array length");
    }

    public float computeScaledVolume(@Nullable ShortBuffer shortBuffer) {
        if (shortBuffer == null || shortBuffer.remaining() == 0) {
            return 0.0f;
        }
        double computeArrayRms = computeArrayRms(shortBuffer);
        double d = this.mRmsWindowStart;
        if (computeArrayRms < d) {
            this.mRmsWindowStart = computeArrayRms;
        } else if (computeArrayRms > d + 3000.0d) {
            this.mRmsWindowStart = computeArrayRms - 3000.0d;
        }
        double d2 = this.mRmsWindowStart;
        return (float) Math.min(1.0d, Math.max((double) FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, (computeArrayRms - d2) / ((3000.0d + d2) - d2)));
    }
}
