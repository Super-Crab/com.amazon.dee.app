package com.amazon.alexa.client.alexaservice.audio;

import androidx.annotation.Nullable;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
public class ScaledVolumeProcessor {
    public double zZm = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;

    public float zZm(@Nullable byte[] bArr, int i) {
        if (bArr == null || i <= 0) {
            return 0.0f;
        }
        if (i <= bArr.length) {
            return zZm(ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer());
        }
        throw new IllegalArgumentException("dataSize must be less than or equal to the byte array length");
    }

    public float zZm(@Nullable ShortBuffer shortBuffer) {
        if (shortBuffer == null || shortBuffer.remaining() == 0) {
            return 0.0f;
        }
        int remaining = shortBuffer.remaining();
        double d = 0.0d;
        while (shortBuffer.remaining() != 0) {
            short s = shortBuffer.get();
            d += s * s;
        }
        double sqrt = Math.sqrt(d / remaining);
        double d2 = this.zZm;
        if (sqrt < d2) {
            this.zZm = sqrt;
        } else if (sqrt > d2 + 3000.0d) {
            this.zZm = sqrt - 3000.0d;
        }
        double d3 = this.zZm;
        return (float) Math.min(1.0d, Math.max((double) FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, (sqrt - d3) / ((3000.0d + d3) - d3)));
    }
}
