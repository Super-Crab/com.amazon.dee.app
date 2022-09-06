package com.amazon.blueshift.bluefront.android.audio.encoder;

import com.google.common.base.Preconditions;
import com.google.common.net.MediaType;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class BufferedAudioEncoder implements AudioEncoder {
    private final AudioEncoder mEncoder;
    private final ShortBuffer mSamplesBuffer;

    public BufferedAudioEncoder(AudioEncoder audioEncoder) {
        Preconditions.checkNotNull(audioEncoder, "AudioEncoder cannot be null");
        this.mEncoder = audioEncoder;
        this.mSamplesBuffer = ShortBuffer.wrap(new short[this.mEncoder.getFrameSize()]);
    }

    private byte[] concatenateBytes(List<byte[]> list) {
        int i = 0;
        for (byte[] bArr : list) {
            i += bArr.length;
        }
        byte[] bArr2 = new byte[i];
        int i2 = 0;
        for (byte[] bArr3 : list) {
            System.arraycopy(bArr3, 0, bArr2, i2, bArr3.length);
            i2 += bArr3.length;
        }
        return bArr2;
    }

    @Override // com.amazon.blueshift.bluefront.android.audio.encoder.AudioEncoder, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.mEncoder.close();
    }

    @Override // com.amazon.blueshift.bluefront.android.audio.encoder.AudioEncoder
    public byte[] encode(short[] sArr, int i) throws AudioEncoderException {
        Preconditions.checkArgument(sArr.length >= i, "Number of samples cannot exceed buffer size");
        ArrayList arrayList = new ArrayList();
        short[] sArr2 = new short[this.mSamplesBuffer.capacity()];
        int i2 = 0;
        while (true) {
            int i3 = i - i2;
            if (this.mSamplesBuffer.remaining() <= i3) {
                int remaining = this.mSamplesBuffer.remaining();
                System.arraycopy(sArr, i2, sArr2, 0, remaining);
                this.mSamplesBuffer.put(sArr2, 0, remaining);
                i2 += remaining;
                arrayList.add(this.mEncoder.encode(this.mSamplesBuffer.array(), this.mSamplesBuffer.capacity()));
                this.mSamplesBuffer.clear();
            } else {
                this.mSamplesBuffer.put(sArr, i2, i3);
                return concatenateBytes(arrayList);
            }
        }
    }

    @Override // com.amazon.blueshift.bluefront.android.audio.encoder.AudioEncoder
    public int getFrameSize() {
        return this.mEncoder.getFrameSize();
    }

    @Override // com.amazon.blueshift.bluefront.android.audio.encoder.AudioEncoder
    public MediaType getMediaType() {
        return this.mEncoder.getMediaType();
    }

    @Override // com.amazon.blueshift.bluefront.android.audio.encoder.AudioEncoder
    public int getPacketSize() {
        return this.mEncoder.getPacketSize();
    }
}
