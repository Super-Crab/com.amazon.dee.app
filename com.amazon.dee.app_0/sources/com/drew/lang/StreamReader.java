package com.drew.lang;

import com.drew.lang.annotations.NotNull;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes2.dex */
public class StreamReader extends SequentialReader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private long _pos;
    @NotNull
    private final InputStream _stream;

    public StreamReader(@NotNull InputStream inputStream) {
        if (inputStream != null) {
            this._stream = inputStream;
            this._pos = 0L;
            return;
        }
        throw new NullPointerException();
    }

    private long skipInternal(long j) throws IOException {
        long j2 = 0;
        while (j2 != j) {
            long skip = this._stream.skip(j - j2);
            j2 += skip;
            if (skip == 0) {
                break;
            }
        }
        this._pos += j2;
        return j2;
    }

    @Override // com.drew.lang.SequentialReader
    public int available() {
        try {
            return this._stream.available();
        } catch (IOException unused) {
            return 0;
        }
    }

    @Override // com.drew.lang.SequentialReader
    public byte getByte() throws IOException {
        int read = this._stream.read();
        if (read != -1) {
            this._pos++;
            return (byte) read;
        }
        throw new EOFException("End of data reached.");
    }

    @Override // com.drew.lang.SequentialReader
    public void getBytes(@NotNull byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 != i2) {
            int read = this._stream.read(bArr, i + i3, i2 - i3);
            if (read == -1) {
                throw new EOFException("End of data reached.");
            }
            i3 += read;
        }
        this._pos += i3;
    }

    @Override // com.drew.lang.SequentialReader
    @NotNull
    public byte[] getBytes(int i) throws IOException {
        byte[] bArr = new byte[i];
        getBytes(bArr, 0, i);
        return bArr;
    }

    @Override // com.drew.lang.SequentialReader
    public long getPosition() {
        return this._pos;
    }

    @Override // com.drew.lang.SequentialReader
    public void skip(long j) throws IOException {
        if (j >= 0) {
            long skipInternal = skipInternal(j);
            if (skipInternal != j) {
                throw new EOFException(String.format("Unable to skip. Requested %d bytes but skipped %d.", Long.valueOf(j), Long.valueOf(skipInternal)));
            }
            return;
        }
        throw new IllegalArgumentException("n must be zero or greater.");
    }

    @Override // com.drew.lang.SequentialReader
    public boolean trySkip(long j) throws IOException {
        if (j >= 0) {
            return skipInternal(j) == j;
        }
        throw new IllegalArgumentException("n must be zero or greater.");
    }
}
