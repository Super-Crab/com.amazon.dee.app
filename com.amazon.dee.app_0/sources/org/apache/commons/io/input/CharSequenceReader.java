package org.apache.commons.io.input;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Reader;
import java.io.Serializable;
/* loaded from: classes4.dex */
public class CharSequenceReader extends Reader implements Serializable {
    private final CharSequence charSequence;
    private int idx;
    private int mark;

    public CharSequenceReader(String str) {
        this.charSequence = str == null ? "" : str;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.idx = 0;
        this.mark = 0;
    }

    @Override // java.io.Reader
    public void mark(int i) {
        this.mark = this.idx;
    }

    @Override // java.io.Reader
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.Reader
    public int read() {
        if (this.idx >= this.charSequence.length()) {
            return -1;
        }
        CharSequence charSequence = this.charSequence;
        int i = this.idx;
        this.idx = i + 1;
        return charSequence.charAt(i);
    }

    @Override // java.io.Reader
    public void reset() {
        this.idx = this.mark;
    }

    @Override // java.io.Reader
    public long skip(long j) {
        if (j >= 0) {
            if (this.idx >= this.charSequence.length()) {
                return -1L;
            }
            int min = (int) Math.min(this.charSequence.length(), this.idx + j);
            this.idx = min;
            return min - this.idx;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("Number of characters to skip is less than zero: ", j));
    }

    public String toString() {
        return this.charSequence.toString();
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) {
        if (this.idx >= this.charSequence.length()) {
            return -1;
        }
        if (cArr != null) {
            if (i2 < 0 || i < 0 || i + i2 > cArr.length) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Array Size=");
                GeneratedOutlineSupport1.outline175(outline107, cArr.length, ", offset=", i, ", length=");
                outline107.append(i2);
                throw new IndexOutOfBoundsException(outline107.toString());
            }
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                int read = read();
                if (read == -1) {
                    return i3;
                }
                cArr[i + i4] = (char) read;
                i3++;
            }
            return i3;
        }
        throw new NullPointerException("Character array is missing");
    }
}
