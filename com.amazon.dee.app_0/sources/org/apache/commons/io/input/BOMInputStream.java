package org.apache.commons.io.input;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.ByteOrderMark;
/* loaded from: classes4.dex */
public class BOMInputStream extends ProxyInputStream {
    private final List<ByteOrderMark> boms;
    private ByteOrderMark byteOrderMark;
    private int fbIndex;
    private int fbLength;
    private int[] firstBytes;
    private final boolean include;
    private int markFbIndex;
    private boolean markedAtStart;

    public BOMInputStream(InputStream inputStream) {
        this(inputStream, false, ByteOrderMark.UTF_8);
    }

    private ByteOrderMark find() {
        for (ByteOrderMark byteOrderMark : this.boms) {
            if (matches(byteOrderMark)) {
                return byteOrderMark;
            }
        }
        return null;
    }

    private boolean matches(ByteOrderMark byteOrderMark) {
        if (byteOrderMark.length() != this.fbLength) {
            return false;
        }
        for (int i = 0; i < byteOrderMark.length(); i++) {
            if (byteOrderMark.get(i) != this.firstBytes[i]) {
                return false;
            }
        }
        return true;
    }

    private int readFirstBytes() throws IOException {
        getBOM();
        int i = this.fbIndex;
        if (i < this.fbLength) {
            int[] iArr = this.firstBytes;
            this.fbIndex = i + 1;
            return iArr[i];
        }
        return -1;
    }

    public ByteOrderMark getBOM() throws IOException {
        if (this.firstBytes == null) {
            this.fbLength = 0;
            int i = 0;
            for (ByteOrderMark byteOrderMark : this.boms) {
                i = Math.max(i, byteOrderMark.length());
            }
            this.firstBytes = new int[i];
            int i2 = 0;
            while (true) {
                int[] iArr = this.firstBytes;
                if (i2 >= iArr.length) {
                    break;
                }
                iArr[i2] = ((FilterInputStream) this).in.read();
                this.fbLength++;
                if (this.firstBytes[i2] < 0) {
                    break;
                }
                this.byteOrderMark = find();
                if (this.byteOrderMark == null) {
                    i2++;
                } else if (!this.include) {
                    this.fbLength = 0;
                }
            }
        }
        return this.byteOrderMark;
    }

    public String getBOMCharsetName() throws IOException {
        getBOM();
        ByteOrderMark byteOrderMark = this.byteOrderMark;
        if (byteOrderMark == null) {
            return null;
        }
        return byteOrderMark.getCharsetName();
    }

    public boolean hasBOM() throws IOException {
        return getBOM() != null;
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i) {
        this.markFbIndex = this.fbIndex;
        this.markedAtStart = this.firstBytes == null;
        ((FilterInputStream) this).in.mark(i);
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int readFirstBytes = readFirstBytes();
        return readFirstBytes >= 0 ? readFirstBytes : ((FilterInputStream) this).in.read();
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        this.fbIndex = this.markFbIndex;
        if (this.markedAtStart) {
            this.firstBytes = null;
        }
        ((FilterInputStream) this).in.reset();
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        while (j > 0 && readFirstBytes() >= 0) {
            j--;
        }
        return ((FilterInputStream) this).in.skip(j);
    }

    public BOMInputStream(InputStream inputStream, boolean z) {
        this(inputStream, z, ByteOrderMark.UTF_8);
    }

    public boolean hasBOM(ByteOrderMark byteOrderMark) throws IOException {
        if (this.boms.contains(byteOrderMark)) {
            return this.byteOrderMark != null && getBOM().equals(byteOrderMark);
        }
        throw new IllegalArgumentException("Stream not configure to detect " + byteOrderMark);
    }

    public BOMInputStream(InputStream inputStream, ByteOrderMark... byteOrderMarkArr) {
        this(inputStream, false, byteOrderMarkArr);
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        int i4 = 0;
        while (i2 > 0 && i3 >= 0) {
            i3 = readFirstBytes();
            if (i3 >= 0) {
                bArr[i] = (byte) (i3 & 255);
                i2--;
                i4++;
                i++;
            }
        }
        int read = ((FilterInputStream) this).in.read(bArr, i, i2);
        if (read < 0) {
            if (i4 <= 0) {
                return -1;
            }
            return i4;
        }
        return i4 + read;
    }

    public BOMInputStream(InputStream inputStream, boolean z, ByteOrderMark... byteOrderMarkArr) {
        super(inputStream);
        if (byteOrderMarkArr != null && byteOrderMarkArr.length != 0) {
            this.include = z;
            this.boms = Arrays.asList(byteOrderMarkArr);
            return;
        }
        throw new IllegalArgumentException("No BOMs specified");
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }
}
