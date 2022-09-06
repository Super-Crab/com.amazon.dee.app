package amazon.speech.audio;

import amazon.speech.io.DataStreamWriter;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InterruptedIOException;
/* loaded from: classes.dex */
public class AudioStreamWriter extends DataStreamWriter {
    private static final String TAG = AudioStreamWriter.class.getSimpleName();
    private long mNativeInstance = 0;
    private byte[] mSingeByteArray = new byte[1];
    private Runnable mInterruptRunnable = new Runnable() { // from class: amazon.speech.audio.AudioStreamWriter.1
        @Override // java.lang.Runnable
        public void run() {
            AudioStreamWriter.this.jniInterrupt();
        }
    };

    private native int nAvailable();

    private native void nClose();

    private native long nGetPosition();

    private native void nInterrupt();

    private native long nSetPosition(long j);

    private native int nWriteByteArray(byte[] bArr, int i, int i2);

    public int available() throws IOException {
        if (isOpen()) {
            return jniAvailable();
        }
        throw new IOException("AudioStreamWriter is closed.");
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (isOpen()) {
            jniClose();
        }
    }

    protected void finalize() throws Throwable {
        try {
            if (getNativeInstance() != 0) {
                String str = TAG;
                Log.w(str, "close() should have been explicitly called on " + this);
                jniClose();
            }
        } finally {
            super.finalize();
        }
    }

    protected long getNativeInstance() {
        return this.mNativeInstance;
    }

    @Override // amazon.speech.io.DataStreamWriter
    public long getPosition() throws IOException {
        if (isOpen()) {
            long jniGetPosition = jniGetPosition();
            if (jniGetPosition < 0) {
                throw new IOException(GeneratedOutlineSupport1.outline57("Could not get writer position. (", jniGetPosition, ")"));
            }
            return jniGetPosition;
        }
        throw new IOException("AudioStreamWriter is closed.");
    }

    public void interrupt() {
        jniInterrupt();
    }

    public boolean isOpen() {
        return getNativeInstance() != 0;
    }

    protected int jniAvailable() {
        return nAvailable();
    }

    protected void jniClose() {
        nClose();
    }

    protected long jniGetPosition() {
        return nGetPosition();
    }

    protected void jniInterrupt() {
        nInterrupt();
    }

    protected long jniSetPosition(long j) {
        return nSetPosition(j);
    }

    protected int jniWriteByteArray(byte[] bArr, int i, int i2) {
        return nWriteByteArray(bArr, i, i2);
    }

    @Override // amazon.speech.io.DataStreamWriter
    public long setPosition(long j) throws IOException {
        if (isOpen()) {
            long jniSetPosition = jniSetPosition(j);
            if (jniSetPosition < 0) {
                throw new IOException(GeneratedOutlineSupport1.outline57("Could not set writer position. (", jniSetPosition, ")"));
            }
            return jniSetPosition;
        }
        throw new IOException("AudioStreamWriter is closed.");
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (isOpen()) {
            if (bArr == null) {
                throw new IllegalArgumentException("buffer is null.");
            }
            if (i < 0) {
                throw new IndexOutOfBoundsException("byteOffset is less than 0.");
            }
            if (i2 >= 0) {
                if (i + i2 <= bArr.length) {
                    if (!Thread.interrupted()) {
                        ThreadInterruptHelper.pushInterruptAction(Thread.currentThread(), this.mInterruptRunnable);
                        int jniWriteByteArray = jniWriteByteArray(bArr, i, i2);
                        ThreadInterruptHelper.popInterruptAction(Thread.currentThread(), this.mInterruptRunnable);
                        if (!Thread.interrupted() && jniWriteByteArray != -2147483629) {
                            if (jniWriteByteArray < 0) {
                                throw new IOException(GeneratedOutlineSupport1.outline49("Error writing data AudioStream. Write returned: ", jniWriteByteArray));
                            }
                            return;
                        }
                        InterruptedIOException interruptedIOException = new InterruptedIOException();
                        if (jniWriteByteArray == -2147483629) {
                            jniWriteByteArray = 0;
                        }
                        interruptedIOException.bytesTransferred = jniWriteByteArray;
                        throw interruptedIOException;
                    }
                    InterruptedIOException interruptedIOException2 = new InterruptedIOException();
                    interruptedIOException2.bytesTransferred = 0;
                    throw interruptedIOException2;
                }
                throw new IndexOutOfBoundsException("byteOffset + byteCount index beyond buffer size.");
            }
            throw new IndexOutOfBoundsException("byteCount is less than 0.");
        }
        throw new IOException("AudioStreamWriter is closed.");
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        if (isOpen()) {
            byte[] bArr = this.mSingeByteArray;
            bArr[0] = (byte) i;
            write(bArr, 0, bArr.length);
            return;
        }
        throw new IOException("AudioStreamWriter is closed.");
    }
}
