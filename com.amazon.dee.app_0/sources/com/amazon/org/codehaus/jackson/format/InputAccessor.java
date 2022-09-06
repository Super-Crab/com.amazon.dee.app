package com.amazon.org.codehaus.jackson.format;

import com.amazon.org.codehaus.jackson.JsonFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes13.dex */
public interface InputAccessor {
    boolean hasMoreBytes() throws IOException;

    byte nextByte() throws IOException;

    void reset();

    /* loaded from: classes13.dex */
    public static class Std implements InputAccessor {
        protected final byte[] _buffer;
        protected int _bufferedAmount;
        protected final InputStream _in;
        protected int _ptr;

        public Std(InputStream inputStream, byte[] bArr) {
            this._in = inputStream;
            this._buffer = bArr;
            this._bufferedAmount = 0;
        }

        public DataFormatMatcher createMatcher(JsonFactory jsonFactory, MatchStrength matchStrength) {
            return new DataFormatMatcher(this._in, this._buffer, this._bufferedAmount, jsonFactory, matchStrength);
        }

        @Override // com.amazon.org.codehaus.jackson.format.InputAccessor
        public boolean hasMoreBytes() throws IOException {
            int read;
            int i = this._ptr;
            if (i < this._bufferedAmount) {
                return true;
            }
            byte[] bArr = this._buffer;
            int length = bArr.length - i;
            if (length < 1 || (read = this._in.read(bArr, i, length)) <= 0) {
                return false;
            }
            this._bufferedAmount += read;
            return true;
        }

        @Override // com.amazon.org.codehaus.jackson.format.InputAccessor
        public byte nextByte() throws IOException {
            if (this._ptr > (-this._bufferedAmount) && !hasMoreBytes()) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Could not read more than ");
                outline107.append(this._ptr);
                outline107.append(" bytes (max buffer size: ");
                throw new EOFException(GeneratedOutlineSupport1.outline86(outline107, this._buffer.length, ")"));
            }
            byte[] bArr = this._buffer;
            int i = this._ptr;
            this._ptr = i + 1;
            return bArr[i];
        }

        @Override // com.amazon.org.codehaus.jackson.format.InputAccessor
        public void reset() {
            this._ptr = 0;
        }

        public Std(byte[] bArr) {
            this._in = null;
            this._buffer = bArr;
            this._bufferedAmount = bArr.length;
        }
    }
}
