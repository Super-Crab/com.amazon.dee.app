package amazon.csm.neoplayer;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class NeoAudioTrack extends AudioTrack {
    private static final String UNSUPPORTED_MESSAGE = "Use write(byte[] audioData, int offsetInBytes, int sizeInBytes) or write(ByteBuffer audioData, int sizeInBytes, int writeMode) instead.";
    private final AtomicInteger mBytesWritten;
    private final int mInitialBufferSize;

    public NeoAudioTrack(int i, int i2, int i3, int i4, int i5, int i6) throws IllegalArgumentException {
        super(i, i2, i3, i4, i5, i6);
        this.mInitialBufferSize = i5;
        this.mBytesWritten = new AtomicInteger();
    }

    public int getBytesWritten() {
        return this.mBytesWritten.get();
    }

    public int getInitialBufferSize() {
        return this.mInitialBufferSize;
    }

    @Override // android.media.AudioTrack
    public int write(byte[] bArr, int i, int i2) {
        int write = super.write(bArr, i, i2);
        this.mBytesWritten.addAndGet(write);
        return write;
    }

    @Override // android.media.AudioTrack
    public int write(short[] sArr, int i, int i2) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    public NeoAudioTrack(int i, int i2, int i3, int i4, int i5, int i6, int i7) throws IllegalArgumentException {
        super(i, i2, i3, i4, i5, i6, i7);
        this.mInitialBufferSize = i5;
        this.mBytesWritten = new AtomicInteger();
    }

    @Override // android.media.AudioTrack
    public int write(float[] fArr, int i, int i2, int i3) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override // android.media.AudioTrack
    public int write(ByteBuffer byteBuffer, int i, int i2) {
        int write = super.write(byteBuffer, i, i2);
        this.mBytesWritten.addAndGet(write);
        return write;
    }

    public NeoAudioTrack(AudioAttributes audioAttributes, AudioFormat audioFormat, int i, int i2, int i3) throws IllegalArgumentException {
        super(audioAttributes, audioFormat, i, i2, i3);
        this.mInitialBufferSize = i;
        this.mBytesWritten = new AtomicInteger();
    }
}
