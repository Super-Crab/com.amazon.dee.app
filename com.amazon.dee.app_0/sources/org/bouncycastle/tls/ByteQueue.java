package org.bouncycastle.tls;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes5.dex */
public class ByteQueue {
    private int available;
    private byte[] databuf;
    private boolean readOnlyBuf;
    private int skipped;

    public ByteQueue() {
        this(0);
    }

    public ByteQueue(int i) {
        this.skipped = 0;
        this.available = 0;
        this.readOnlyBuf = false;
        this.databuf = i == 0 ? TlsUtils.EMPTY_BYTES : new byte[i];
    }

    public ByteQueue(byte[] bArr, int i, int i2) {
        this.skipped = 0;
        this.available = 0;
        this.readOnlyBuf = false;
        this.databuf = bArr;
        this.skipped = i;
        this.available = i2;
        this.readOnlyBuf = true;
    }

    public static int nextTwoPow(int i) {
        int i2 = i | (i >> 1);
        int i3 = i2 | (i2 >> 2);
        int i4 = i3 | (i3 >> 4);
        int i5 = i4 | (i4 >> 8);
        return (i5 | (i5 >> 16)) + 1;
    }

    public void addData(byte[] bArr, int i, int i2) {
        if (!this.readOnlyBuf) {
            int i3 = this.skipped;
            int i4 = this.available;
            if (i3 + i4 + i2 > this.databuf.length) {
                int nextTwoPow = nextTwoPow(i4 + i2);
                byte[] bArr2 = this.databuf;
                if (nextTwoPow > bArr2.length) {
                    byte[] bArr3 = new byte[nextTwoPow];
                    System.arraycopy(bArr2, this.skipped, bArr3, 0, this.available);
                    this.databuf = bArr3;
                } else {
                    System.arraycopy(bArr2, this.skipped, bArr2, 0, this.available);
                }
                this.skipped = 0;
            }
            System.arraycopy(bArr, i, this.databuf, this.skipped + this.available, i2);
            this.available += i2;
            return;
        }
        throw new IllegalStateException("Cannot add data to read-only buffer");
    }

    public int available() {
        return this.available;
    }

    public void copyTo(OutputStream outputStream, int i) throws IOException {
        if (i <= this.available) {
            outputStream.write(this.databuf, this.skipped, i);
            return;
        }
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Cannot copy ", i, " bytes, only got ");
        outline109.append(this.available);
        throw new IllegalStateException(outline109.toString());
    }

    public void read(byte[] bArr, int i, int i2, int i3) {
        if (bArr.length - i >= i2) {
            if (this.available - i3 < i2) {
                throw new IllegalStateException("Not enough data to read");
            }
            System.arraycopy(this.databuf, this.skipped + i3, bArr, i, i2);
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Buffer size of ");
        outline107.append(bArr.length);
        outline107.append(" is too small for a read of ");
        outline107.append(i2);
        outline107.append(" bytes");
        throw new IllegalArgumentException(outline107.toString());
    }

    public HandshakeMessageInput readHandshakeMessage(int i) {
        int i2 = this.available;
        if (i > i2) {
            StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Cannot read ", i, " bytes, only got ");
            outline109.append(this.available);
            throw new IllegalStateException(outline109.toString());
        }
        int i3 = this.skipped;
        this.available = i2 - i;
        this.skipped = i3 + i;
        return new HandshakeMessageInput(this.databuf, i3, i);
    }

    public int readInt32() {
        if (this.available >= 4) {
            return TlsUtils.readInt32(this.databuf, this.skipped);
        }
        throw new IllegalStateException("Not enough data to read");
    }

    public void removeData(int i) {
        int i2 = this.available;
        if (i <= i2) {
            this.available = i2 - i;
            this.skipped += i;
            return;
        }
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Cannot remove ", i, " bytes, only got ");
        outline109.append(this.available);
        throw new IllegalStateException(outline109.toString());
    }

    public void removeData(byte[] bArr, int i, int i2, int i3) {
        read(bArr, i, i2, i3);
        removeData(i3 + i2);
    }

    public byte[] removeData(int i, int i2) {
        byte[] bArr = new byte[i];
        removeData(bArr, 0, i, i2);
        return bArr;
    }

    public void shrink() {
        int i = this.available;
        if (i == 0) {
            this.databuf = TlsUtils.EMPTY_BYTES;
        } else {
            int nextTwoPow = nextTwoPow(i);
            byte[] bArr = this.databuf;
            if (nextTwoPow >= bArr.length) {
                return;
            }
            byte[] bArr2 = new byte[nextTwoPow];
            System.arraycopy(bArr, this.skipped, bArr2, 0, this.available);
            this.databuf = bArr2;
        }
        this.skipped = 0;
    }
}
