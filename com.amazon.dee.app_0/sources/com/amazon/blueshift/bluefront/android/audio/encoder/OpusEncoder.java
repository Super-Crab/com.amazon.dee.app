package com.amazon.blueshift.bluefront.android.audio.encoder;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.google.common.net.MediaType;
import java.util.Set;
/* loaded from: classes11.dex */
public class OpusEncoder implements AudioEncoder {
    private static final int APPLICATION_AUDIO = 2049;
    private static final int BIT_RATE_INTERVAL = 400;
    private static final int DEFAULT_BIT_RATE = 32000;
    private static final int DEFAULT_CHANNELS = 1;
    private static final int DEFAULT_COMPLEXITY = 10;
    private static final int DEFAULT_FRAME_SIZE_MILLIS = 20;
    private static final int DEFAULT_SAMPLE_RATE = 16000;
    private static final int IS_VBR = 0;
    private static final int MAX_BIT_RATE = 512000;
    private static final int MAX_COMPLEXITY = 10;
    private static final int MIN_BIT_RATE = 6000;
    private static final int MIN_COMPLEXITY = 1;
    private static final int MS_PER_SEC = 1000;
    private static final Set<Integer> VALID_FRAME_SIZE_MILLIS = Sets.newHashSet(5, 10, 20, 40, 60);
    private static final Set<Integer> VALID_SAMPLES_RATES = Sets.newHashSet(8000, 12000, 16000, 24000, 48000);
    private final int mFrameSize;
    private final MediaType mMediaType;
    private com.amazon.opus.OpusEncoder mOpusEncoder;
    private final int mPacketSize;

    public OpusEncoder() throws AudioEncoderException {
        this(20, 16000, 1, 32000, 10);
    }

    @Override // com.amazon.blueshift.bluefront.android.audio.encoder.AudioEncoder, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (this.mOpusEncoder != null) {
            this.mOpusEncoder.close();
            this.mOpusEncoder = null;
        }
    }

    @Override // com.amazon.blueshift.bluefront.android.audio.encoder.AudioEncoder
    public synchronized byte[] encode(short[] sArr, int i) throws AudioEncoderException {
        byte[] bArr;
        try {
            Preconditions.checkNotNull(this.mOpusEncoder, "Opus encoder is not initialized");
            Preconditions.checkNotNull(sArr, "Samples buffer cannot be null");
            Preconditions.checkArgument(sArr.length >= i, "Number of samples cannot exceed buffer size");
            bArr = new byte[this.mPacketSize];
            this.mOpusEncoder.encode(sArr, this.mFrameSize, bArr);
        } catch (Exception e) {
            throw new AudioEncoderException(e);
        }
        return bArr;
    }

    @Override // com.amazon.blueshift.bluefront.android.audio.encoder.AudioEncoder
    public int getFrameSize() {
        return this.mFrameSize;
    }

    @Override // com.amazon.blueshift.bluefront.android.audio.encoder.AudioEncoder
    public MediaType getMediaType() {
        return this.mMediaType;
    }

    @Override // com.amazon.blueshift.bluefront.android.audio.encoder.AudioEncoder
    public int getPacketSize() {
        return this.mPacketSize;
    }

    public OpusEncoder(int i, int i2, int i3, int i4, int i5) throws AudioEncoderException {
        boolean z;
        try {
            Preconditions.checkArgument(VALID_FRAME_SIZE_MILLIS.contains(Integer.valueOf(i)), "Frame size is invalid");
            Preconditions.checkArgument(VALID_SAMPLES_RATES.contains(Integer.valueOf(i2)), "Sample rate is invalid");
            boolean z2 = true;
            if (i3 != 1 && i3 != 2) {
                z = false;
                Preconditions.checkArgument(z, "Number of channels is invalid");
                Preconditions.checkArgument(i4 < MIN_BIT_RATE && i4 <= MAX_BIT_RATE && (i4 + (-6000)) % 400 == 0, "Bitrate is invalid");
                if (i5 >= 1 || i5 > 10) {
                    z2 = false;
                }
                Preconditions.checkArgument(z2, "Complexity is invalid");
                this.mOpusEncoder = new com.amazon.opus.OpusEncoder();
                this.mOpusEncoder.init(i2, i3, 2049);
                this.mOpusEncoder.setBitrate(i4);
                this.mOpusEncoder.setComplexity(i5);
                this.mOpusEncoder.useVbr(0);
                this.mPacketSize = (i4 * i) / 8000;
                this.mFrameSize = (i2 * i) / 1000;
                this.mMediaType = MediaType.parse("audio/x-cbr-opus-with-preamble").withParameter("bit-rate", Integer.toString(i4)).withParameter("frame-size-milliseconds", Integer.toString(i)).withParameter("preamble-size", "0");
            }
            z = true;
            Preconditions.checkArgument(z, "Number of channels is invalid");
            Preconditions.checkArgument(i4 < MIN_BIT_RATE && i4 <= MAX_BIT_RATE && (i4 + (-6000)) % 400 == 0, "Bitrate is invalid");
            if (i5 >= 1) {
            }
            z2 = false;
            Preconditions.checkArgument(z2, "Complexity is invalid");
            this.mOpusEncoder = new com.amazon.opus.OpusEncoder();
            this.mOpusEncoder.init(i2, i3, 2049);
            this.mOpusEncoder.setBitrate(i4);
            this.mOpusEncoder.setComplexity(i5);
            this.mOpusEncoder.useVbr(0);
            this.mPacketSize = (i4 * i) / 8000;
            this.mFrameSize = (i2 * i) / 1000;
            this.mMediaType = MediaType.parse("audio/x-cbr-opus-with-preamble").withParameter("bit-rate", Integer.toString(i4)).withParameter("frame-size-milliseconds", Integer.toString(i)).withParameter("preamble-size", "0");
        } catch (Exception e) {
            throw new AudioEncoderException(e);
        }
    }
}
