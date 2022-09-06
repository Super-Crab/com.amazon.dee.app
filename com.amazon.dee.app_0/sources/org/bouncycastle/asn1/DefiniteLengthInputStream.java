package org.bouncycastle.asn1;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.util.io.Streams;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class DefiniteLengthInputStream extends LimitedInputStream {
    private static final byte[] EMPTY_BYTES = new byte[0];
    private final int _originalLength;
    private int _remaining;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefiniteLengthInputStream(InputStream inputStream, int i, int i2) {
        super(inputStream, i2);
        if (i >= 0) {
            this._originalLength = i;
            this._remaining = i;
            if (i != 0) {
                return;
            }
            setParentEofDetect(true);
            return;
        }
        throw new IllegalArgumentException("negative lengths not allowed");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getRemaining() {
        return this._remaining;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this._remaining == 0) {
            return -1;
        }
        int read = this._in.read();
        if (read >= 0) {
            int i = this._remaining - 1;
            this._remaining = i;
            if (i == 0) {
                setParentEofDetect(true);
            }
            return read;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DEF length ");
        outline107.append(this._originalLength);
        outline107.append(" object truncated by ");
        outline107.append(this._remaining);
        throw new EOFException(outline107.toString());
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this._remaining;
        if (i3 == 0) {
            return -1;
        }
        int read = this._in.read(bArr, i, Math.min(i2, i3));
        if (read >= 0) {
            int i4 = this._remaining - read;
            this._remaining = i4;
            if (i4 == 0) {
                setParentEofDetect(true);
            }
            return read;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DEF length ");
        outline107.append(this._originalLength);
        outline107.append(" object truncated by ");
        outline107.append(this._remaining);
        throw new EOFException(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void readAllIntoByteArray(byte[] bArr) throws IOException {
        int i = this._remaining;
        if (i == bArr.length) {
            if (i == 0) {
                return;
            }
            int limit = getLimit();
            int i2 = this._remaining;
            if (i2 >= limit) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("corrupted stream - out of bounds length found: ");
                outline107.append(this._remaining);
                outline107.append(" >= ");
                outline107.append(limit);
                throw new IOException(outline107.toString());
            }
            int readFully = i2 - Streams.readFully(this._in, bArr);
            this._remaining = readFully;
            if (readFully == 0) {
                setParentEofDetect(true);
                return;
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("DEF length ");
            outline1072.append(this._originalLength);
            outline1072.append(" object truncated by ");
            outline1072.append(this._remaining);
            throw new EOFException(outline1072.toString());
        }
        throw new IllegalArgumentException("buffer length not right for data");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] toByteArray() throws IOException {
        if (this._remaining == 0) {
            return EMPTY_BYTES;
        }
        int limit = getLimit();
        int i = this._remaining;
        if (i >= limit) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("corrupted stream - out of bounds length found: ");
            outline107.append(this._remaining);
            outline107.append(" >= ");
            outline107.append(limit);
            throw new IOException(outline107.toString());
        }
        byte[] bArr = new byte[i];
        int readFully = i - Streams.readFully(this._in, bArr);
        this._remaining = readFully;
        if (readFully == 0) {
            setParentEofDetect(true);
            return bArr;
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("DEF length ");
        outline1072.append(this._originalLength);
        outline1072.append(" object truncated by ");
        outline1072.append(this._remaining);
        throw new EOFException(outline1072.toString());
    }
}
