package javax.mail.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import javax.mail.internet.SharedInputStream;
/* loaded from: classes3.dex */
public class SharedFileInputStream extends BufferedInputStream implements SharedInputStream {
    private static int defaultBufferSize = 2048;
    protected long bufpos;
    protected int bufsize;
    protected long datalen;
    protected RandomAccessFile in;
    private boolean master;
    private SharedFile sf;
    protected long start;

    public SharedFileInputStream(File file) throws IOException {
        this(file, defaultBufferSize);
    }

    private void ensureOpen() throws IOException {
        if (this.in != null) {
            return;
        }
        throw new IOException("Stream closed");
    }

    private void fill() throws IOException {
        if (((BufferedInputStream) this).markpos < 0) {
            ((BufferedInputStream) this).pos = 0;
            this.bufpos += ((BufferedInputStream) this).count;
        } else if (((BufferedInputStream) this).pos >= ((BufferedInputStream) this).buf.length) {
            int i = ((BufferedInputStream) this).markpos;
            if (i > 0) {
                int i2 = ((BufferedInputStream) this).pos - i;
                System.arraycopy(((BufferedInputStream) this).buf, ((BufferedInputStream) this).markpos, ((BufferedInputStream) this).buf, 0, i2);
                ((BufferedInputStream) this).pos = i2;
                this.bufpos += ((BufferedInputStream) this).markpos;
                ((BufferedInputStream) this).markpos = 0;
            } else {
                int length = ((BufferedInputStream) this).buf.length;
                int i3 = ((BufferedInputStream) this).marklimit;
                if (length >= i3) {
                    ((BufferedInputStream) this).markpos = -1;
                    ((BufferedInputStream) this).pos = 0;
                    this.bufpos += ((BufferedInputStream) this).count;
                } else {
                    int i4 = ((BufferedInputStream) this).pos * 2;
                    if (i4 > i3) {
                        i4 = i3;
                    }
                    byte[] bArr = new byte[i4];
                    System.arraycopy(((BufferedInputStream) this).buf, 0, bArr, 0, ((BufferedInputStream) this).pos);
                    ((BufferedInputStream) this).buf = bArr;
                }
            }
        }
        ((BufferedInputStream) this).count = ((BufferedInputStream) this).pos;
        int length2 = ((BufferedInputStream) this).buf.length;
        int i5 = ((BufferedInputStream) this).pos;
        int i6 = length2 - i5;
        long j = this.bufpos;
        long j2 = this.start;
        long j3 = (j - j2) + i5 + i6;
        long j4 = this.datalen;
        if (j3 > j4) {
            i6 = (int) (j4 - ((j - j2) + i5));
        }
        synchronized (this.in) {
            this.in.seek(this.bufpos + ((BufferedInputStream) this).pos);
            int read = this.in.read(((BufferedInputStream) this).buf, ((BufferedInputStream) this).pos, i6);
            if (read > 0) {
                ((BufferedInputStream) this).count = read + ((BufferedInputStream) this).pos;
            }
        }
    }

    private int in_available() throws IOException {
        return (int) ((this.start + this.datalen) - (this.bufpos + ((BufferedInputStream) this).count));
    }

    private void init(SharedFile sharedFile, int i) throws IOException {
        this.sf = sharedFile;
        this.in = sharedFile.open();
        this.start = 0L;
        this.datalen = this.in.length();
        this.bufsize = i;
        ((BufferedInputStream) this).buf = new byte[i];
    }

    private int read1(byte[] bArr, int i, int i2) throws IOException {
        int i3 = ((BufferedInputStream) this).count - ((BufferedInputStream) this).pos;
        if (i3 <= 0) {
            fill();
            i3 = ((BufferedInputStream) this).count - ((BufferedInputStream) this).pos;
            if (i3 <= 0) {
                return -1;
            }
        }
        if (i3 < i2) {
            i2 = i3;
        }
        System.arraycopy(((BufferedInputStream) this).buf, ((BufferedInputStream) this).pos, bArr, i, i2);
        ((BufferedInputStream) this).pos += i2;
        return i2;
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() throws IOException {
        ensureOpen();
        return (((BufferedInputStream) this).count - ((BufferedInputStream) this).pos) + in_available();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [javax.mail.util.SharedFileInputStream$SharedFile, java.io.RandomAccessFile, byte[]] */
    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.in == null) {
            return;
        }
        try {
            if (this.master) {
                this.sf.forceClose();
            } else {
                this.sf.close();
            }
        } finally {
            this.sf = null;
            this.in = null;
            ((BufferedInputStream) this).buf = null;
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        close();
    }

    @Override // javax.mail.internet.SharedInputStream
    public long getPosition() {
        if (this.in != null) {
            return (this.bufpos + ((BufferedInputStream) this).pos) - this.start;
        }
        throw new RuntimeException("Stream closed");
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i) {
        ((BufferedInputStream) this).marklimit = i;
        ((BufferedInputStream) this).markpos = ((BufferedInputStream) this).pos;
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    @Override // javax.mail.internet.SharedInputStream
    public synchronized InputStream newStream(long j, long j2) {
        if (this.in == null) {
            throw new RuntimeException("Stream closed");
        }
        if (j >= 0) {
            if (j2 == -1) {
                j2 = this.datalen;
            }
        } else {
            throw new IllegalArgumentException("start < 0");
        }
        return new SharedFileInputStream(this.sf, this.start + j, j2 - j, this.bufsize);
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public synchronized int read() throws IOException {
        ensureOpen();
        if (((BufferedInputStream) this).pos >= ((BufferedInputStream) this).count) {
            fill();
            if (((BufferedInputStream) this).pos >= ((BufferedInputStream) this).count) {
                return -1;
            }
        }
        byte[] bArr = ((BufferedInputStream) this).buf;
        int i = ((BufferedInputStream) this).pos;
        ((BufferedInputStream) this).pos = i + 1;
        return bArr[i] & 255;
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        ensureOpen();
        if (((BufferedInputStream) this).markpos >= 0) {
            ((BufferedInputStream) this).pos = ((BufferedInputStream) this).markpos;
        } else {
            throw new IOException("Resetting to invalid mark");
        }
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public synchronized long skip(long j) throws IOException {
        ensureOpen();
        if (j <= 0) {
            return 0L;
        }
        long j2 = ((BufferedInputStream) this).count - ((BufferedInputStream) this).pos;
        if (j2 <= 0) {
            fill();
            j2 = ((BufferedInputStream) this).count - ((BufferedInputStream) this).pos;
            if (j2 <= 0) {
                return 0L;
            }
        }
        if (j2 < j) {
            j = j2;
        }
        ((BufferedInputStream) this).pos = (int) (((BufferedInputStream) this).pos + j);
        return j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class SharedFile {
        private int cnt;
        private RandomAccessFile in;

        SharedFile(String str) throws IOException {
            this.in = new RandomAccessFile(str, "r");
        }

        public synchronized void close() throws IOException {
            if (this.cnt > 0) {
                int i = this.cnt - 1;
                this.cnt = i;
                if (i <= 0) {
                    this.in.close();
                }
            }
        }

        protected void finalize() throws Throwable {
            super.finalize();
            this.in.close();
        }

        public synchronized void forceClose() throws IOException {
            if (this.cnt > 0) {
                this.cnt = 0;
                this.in.close();
            } else {
                try {
                    this.in.close();
                } catch (IOException unused) {
                }
            }
        }

        public synchronized RandomAccessFile open() {
            this.cnt++;
            return this.in;
        }

        SharedFile(File file) throws IOException {
            this.in = new RandomAccessFile(file, "r");
        }
    }

    public SharedFileInputStream(String str) throws IOException {
        this(str, defaultBufferSize);
    }

    public SharedFileInputStream(File file, int i) throws IOException {
        super(null);
        this.start = 0L;
        this.master = true;
        if (i > 0) {
            init(new SharedFile(file), i);
            return;
        }
        throw new IllegalArgumentException("Buffer size <= 0");
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] bArr, int i, int i2) throws IOException {
        ensureOpen();
        int i3 = i + i2;
        if ((i | i2 | i3 | (bArr.length - i3)) >= 0) {
            if (i2 == 0) {
                return 0;
            }
            int read1 = read1(bArr, i, i2);
            if (read1 <= 0) {
                return read1;
            }
            while (read1 < i2) {
                int read12 = read1(bArr, i + read1, i2 - read1);
                if (read12 <= 0) {
                    break;
                }
                read1 += read12;
            }
            return read1;
        }
        throw new IndexOutOfBoundsException();
    }

    public SharedFileInputStream(String str, int i) throws IOException {
        super(null);
        this.start = 0L;
        this.master = true;
        if (i > 0) {
            init(new SharedFile(str), i);
            return;
        }
        throw new IllegalArgumentException("Buffer size <= 0");
    }

    private SharedFileInputStream(SharedFile sharedFile, long j, long j2, int i) {
        super(null);
        this.start = 0L;
        this.master = true;
        this.master = false;
        this.sf = sharedFile;
        this.in = sharedFile.open();
        this.start = j;
        this.bufpos = j;
        this.datalen = j2;
        this.bufsize = i;
        ((BufferedInputStream) this).buf = new byte[i];
    }
}
