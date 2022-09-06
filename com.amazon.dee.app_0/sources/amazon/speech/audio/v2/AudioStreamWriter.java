package amazon.speech.audio.v2;
/* loaded from: classes.dex */
public class AudioStreamWriter extends amazon.speech.audio.AudioStreamWriter {
    private static final String TAG = AudioStreamWriter.class.getSimpleName();
    private long mNativeInstance = 0;

    private native int nAvailable();

    private native void nClose();

    private native long nGetPosition();

    private native void nInterrupt();

    private native long nSetPosition(long j);

    private native int nWriteByteArray(byte[] bArr, int i, int i2);

    @Override // amazon.speech.audio.AudioStreamWriter
    protected long getNativeInstance() {
        return this.mNativeInstance;
    }

    @Override // amazon.speech.audio.AudioStreamWriter
    protected int jniAvailable() {
        return nAvailable();
    }

    @Override // amazon.speech.audio.AudioStreamWriter
    protected void jniClose() {
        nClose();
    }

    @Override // amazon.speech.audio.AudioStreamWriter
    protected long jniGetPosition() {
        return nGetPosition();
    }

    @Override // amazon.speech.audio.AudioStreamWriter
    protected void jniInterrupt() {
        nInterrupt();
    }

    @Override // amazon.speech.audio.AudioStreamWriter
    protected long jniSetPosition(long j) {
        return nSetPosition(j);
    }

    @Override // amazon.speech.audio.AudioStreamWriter
    protected int jniWriteByteArray(byte[] bArr, int i, int i2) {
        return nWriteByteArray(bArr, i, i2);
    }
}
