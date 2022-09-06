package org.apache.oro.text.awk;

import java.io.IOException;
import java.io.Reader;
/* loaded from: classes4.dex */
public final class AwkStreamInput {
    static final int _DEFAULT_BUFFER_INCREMENT = 2048;
    private int __bufferIncrementUnit;
    private Reader __searchStream;
    char[] _buffer;
    int _bufferOffset;
    int _bufferSize;
    int _currentOffset;
    boolean _endOfStreamReached;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AwkStreamInput() {
        this._currentOffset = 0;
    }

    public AwkStreamInput(Reader reader) {
        this(reader, 2048);
    }

    public AwkStreamInput(Reader reader, int i) {
        this.__searchStream = reader;
        this.__bufferIncrementUnit = i;
        this._buffer = new char[i];
        this._currentOffset = 0;
        this._bufferSize = 0;
        this._bufferOffset = 0;
        this._endOfStreamReached = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int _reallocate(int i) throws IOException {
        if (this._endOfStreamReached) {
            return this._bufferSize;
        }
        int i2 = this._bufferSize - i;
        int i3 = this.__bufferIncrementUnit;
        char[] cArr = new char[i2 + i3];
        int read = this.__searchStream.read(cArr, i2, i3);
        if (read <= 0) {
            this._endOfStreamReached = true;
            if (read == 0) {
                throw new IOException("read from input stream returned 0 bytes.");
            }
            return this._bufferSize;
        }
        this._bufferOffset += i;
        this._bufferSize = read + i2;
        System.arraycopy(this._buffer, i, cArr, 0, i2);
        this._buffer = cArr;
        return i2;
    }

    public boolean endOfStream() {
        return this._endOfStreamReached;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean read() throws IOException {
        this._bufferOffset += this._bufferSize;
        this._bufferSize = this.__searchStream.read(this._buffer);
        this._endOfStreamReached = this._bufferSize == -1;
        return !this._endOfStreamReached;
    }
}
