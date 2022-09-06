package amazon.speech.simclient.directive;

import android.os.ParcelFileDescriptor;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes.dex */
public class Piper extends OutputStream {
    private final OutputStream mOutputStream;
    private ParcelFileDescriptor mReadEnd;

    /* loaded from: classes.dex */
    public interface Factory {
        public static final Factory DEFAULT = new Factory() { // from class: amazon.speech.simclient.directive.Piper.Factory.1
            @Override // amazon.speech.simclient.directive.Piper.Factory
            public Piper create() throws IOException {
                return new Piper();
            }
        };

        Piper create() throws IOException;
    }

    public Piper() throws IOException {
        ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
        this.mReadEnd = createPipe[0];
        this.mOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(createPipe[1]);
    }

    public ParcelFileDescriptor claimPipeReaderOwnership() {
        ParcelFileDescriptor parcelFileDescriptor = this.mReadEnd;
        this.mReadEnd = null;
        return parcelFileDescriptor;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.mOutputStream.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.mOutputStream.flush();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.mOutputStream.write(i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.mOutputStream.write(bArr, i, i2);
    }

    Piper(ParcelFileDescriptor parcelFileDescriptor, OutputStream outputStream) {
        this.mReadEnd = parcelFileDescriptor;
        this.mOutputStream = outputStream;
    }
}
