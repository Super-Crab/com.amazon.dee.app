package amazon.speech.audio;

import amazon.speech.io.DataStreamReader;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InterruptedIOException;
/* loaded from: classes.dex */
public class AudioStreamReader extends DataStreamReader {
    public static final int EOF = -1;
    private static final String TAG = AudioStreamReader.class.getSimpleName();
    private long mNativeInstance = 0;
    private Runnable mInterruptRunnable = new Runnable() { // from class: amazon.speech.audio.AudioStreamReader.1
        @Override // java.lang.Runnable
        public void run() {
            AudioStreamReader.this.jniInterrupt();
        }
    };
    private byte[] mSingeByteArray = new byte[1];
    private boolean mOverrunExceptionsEnabled = false;

    /* loaded from: classes.dex */
    public static class OverrunException extends IOException {
        private static final long serialVersionUID = 7599224152391794668L;
    }

    private native int nAvailable();

    private native void nClose();

    private native long nGetPosition();

    private native void nInterrupt();

    private native int nReadByteArray(byte[] bArr, int i, int i2);

    private native long nSetPosition(long j);

    private native long nSynchronize();

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (isOpen()) {
            return jniAvailable();
        }
        throw new IOException("AudioStreamReader is closed.");
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (isRemoteObject()) {
            Log.wtf(TAG, "Trying to close a reader created remotely");
        } else if (!isOpen()) {
        } else {
            jniClose();
        }
    }

    protected void finalize() throws Throwable {
        try {
            if (!isRemoteObject() && getNativeInstance() != 0) {
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

    public boolean getOverrunExceptionsEnabled() {
        return this.mOverrunExceptionsEnabled;
    }

    @Override // amazon.speech.io.DataStreamReader
    public long getPosition() throws IOException {
        if (isOpen()) {
            long jniGetPosition = jniGetPosition();
            if (jniGetPosition < 0) {
                throw new IOException("Could not get reader position.");
            }
            return jniGetPosition;
        }
        throw new IOException("AudioStreamReader is closed.");
    }

    public void interrupt() {
        jniInterrupt();
    }

    public boolean isOpen() {
        return getNativeInstance() != 0;
    }

    protected boolean isRemoteObject() {
        return false;
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

    protected int jniReadByteArray(byte[] bArr, int i, int i2) {
        return nReadByteArray(bArr, i, i2);
    }

    protected long jniSetPosition(long j) {
        return nSetPosition(j);
    }

    protected long jniSynchronize() {
        return nSynchronize();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (isOpen()) {
            if (bArr == null) {
                throw new IllegalArgumentException("buffer is null.");
            }
            if (i < 0) {
                throw new IndexOutOfBoundsException("byteOffset is less than 0.");
            }
            if (i2 >= 0) {
                if (i + i2 <= bArr.length) {
                    while (!Thread.interrupted()) {
                        ThreadInterruptHelper.pushInterruptAction(Thread.currentThread(), this.mInterruptRunnable);
                        int jniReadByteArray = jniReadByteArray(bArr, i, i2);
                        ThreadInterruptHelper.popInterruptAction(Thread.currentThread(), this.mInterruptRunnable);
                        if (Thread.interrupted() || jniReadByteArray == -2147483629) {
                            InterruptedIOException interruptedIOException = new InterruptedIOException();
                            if (jniReadByteArray == -2147483629) {
                                jniReadByteArray = 0;
                            }
                            interruptedIOException.bytesTransferred = jniReadByteArray;
                            throw interruptedIOException;
                        } else if (i2 != 0 && jniReadByteArray == 0) {
                            return -1;
                        } else {
                            if (jniReadByteArray != -2147483631) {
                                if (jniReadByteArray == -2147483630) {
                                    close();
                                    throw new IOException("AudioStream disconnected from reader.");
                                }
                                if (jniReadByteArray < 0) {
                                    close();
                                    if (!isRemoteObject()) {
                                        throw new IOException(GeneratedOutlineSupport1.outline49("Unknown code received from native read: ", jniReadByteArray));
                                    }
                                }
                                return jniReadByteArray;
                            } else if (this.mOverrunExceptionsEnabled) {
                                throw new OverrunException();
                            }
                        }
                    }
                    InterruptedIOException interruptedIOException2 = new InterruptedIOException();
                    interruptedIOException2.bytesTransferred = 0;
                    throw interruptedIOException2;
                }
                throw new IndexOutOfBoundsException("byteOffset + byteCount index beyond buffer size.");
            }
            throw new IndexOutOfBoundsException("byteCount is less than 0.");
        }
        throw new IOException("AudioStreamReader is closed.");
    }

    public void setOverrunExceptionsEnabled(boolean z) {
        this.mOverrunExceptionsEnabled = z;
    }

    @Override // amazon.speech.io.DataStreamReader
    public long setPosition(long j) throws IOException {
        long jniSetPosition;
        if (isOpen()) {
            if (j == Long.MAX_VALUE) {
                jniSetPosition = jniSynchronize();
            } else {
                jniSetPosition = jniSetPosition(j);
            }
            if (jniSetPosition < 0) {
                throw new IOException("Could not set reader position.");
            }
            return jniSetPosition;
        }
        throw new IOException("AudioStreamReader is closed.");
    }

    public long synchronize() throws IOException {
        if (isOpen()) {
            long jniSynchronize = jniSynchronize();
            if (jniSynchronize < 0) {
                throw new IOException("Could not synchronize reader position.");
            }
            return jniSynchronize;
        }
        throw new IOException("AudioStreamReader is closed.");
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (isOpen()) {
            byte[] bArr = this.mSingeByteArray;
            int read = read(bArr, 0, bArr.length);
            byte[] bArr2 = this.mSingeByteArray;
            if (read == bArr2.length) {
                return bArr2[0] & 255;
            }
            if (read != -1) {
                throw new IOException("Unexpected error from read.");
            }
            return -1;
        }
        throw new IOException("AudioStreamReader is closed.");
    }
}
