package com.amazon.blueshift.bluefront.android.audio.encoder;

import com.google.common.base.Preconditions;
import com.google.common.net.MediaType;
import java.nio.ByteOrder;
/* loaded from: classes11.dex */
public class L16PcmEncoder implements AudioEncoder {
    private static final int FRAME_SIZE = 320;
    private static final MediaType MEDIA_TYPE = MediaType.parse("audio/x-l16");

    @Override // com.amazon.blueshift.bluefront.android.audio.encoder.AudioEncoder, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // com.amazon.blueshift.bluefront.android.audio.encoder.AudioEncoder
    public byte[] encode(short[] sArr, int i) throws AudioEncoderException {
        Preconditions.checkArgument(sArr.length >= i, "Sample buffer length must be at least as long as numSamples");
        byte[] bArr = new byte[i * 2];
        for (int i2 = 0; i2 < i; i2++) {
            if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
                int i3 = i2 * 2;
                bArr[i3] = (byte) (sArr[i2] & 255);
                bArr[i3 + 1] = (byte) ((sArr[i2] >> 8) & 255);
            } else {
                int i4 = i2 * 2;
                bArr[i4] = (byte) ((sArr[i2] >> 8) & 255);
                bArr[i4 + 1] = (byte) (sArr[i2] & 255);
            }
        }
        return bArr;
    }

    @Override // com.amazon.blueshift.bluefront.android.audio.encoder.AudioEncoder
    public int getFrameSize() {
        return 320;
    }

    @Override // com.amazon.blueshift.bluefront.android.audio.encoder.AudioEncoder
    public MediaType getMediaType() {
        return MEDIA_TYPE;
    }

    @Override // com.amazon.blueshift.bluefront.android.audio.encoder.AudioEncoder
    public int getPacketSize() {
        return 640;
    }
}
