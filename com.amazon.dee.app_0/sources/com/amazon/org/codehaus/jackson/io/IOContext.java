package com.amazon.org.codehaus.jackson.io;

import com.amazon.org.codehaus.jackson.JsonEncoding;
import com.amazon.org.codehaus.jackson.util.BufferRecycler;
import com.amazon.org.codehaus.jackson.util.TextBuffer;
/* loaded from: classes13.dex */
public final class IOContext {
    protected final BufferRecycler _bufferRecycler;
    protected JsonEncoding _encoding;
    protected final boolean _managedResource;
    protected final Object _sourceRef;
    protected byte[] _readIOBuffer = null;
    protected byte[] _writeEncodingBuffer = null;
    protected char[] _tokenCBuffer = null;
    protected char[] _concatCBuffer = null;
    protected char[] _nameCopyBuffer = null;

    public IOContext(BufferRecycler bufferRecycler, Object obj, boolean z) {
        this._bufferRecycler = bufferRecycler;
        this._sourceRef = obj;
        this._managedResource = z;
    }

    public final char[] allocConcatBuffer() {
        if (this._concatCBuffer == null) {
            this._concatCBuffer = this._bufferRecycler.allocCharBuffer(BufferRecycler.CharBufferType.CONCAT_BUFFER);
            return this._concatCBuffer;
        }
        throw new IllegalStateException("Trying to call allocConcatBuffer() second time");
    }

    public final char[] allocNameCopyBuffer(int i) {
        if (this._nameCopyBuffer == null) {
            this._nameCopyBuffer = this._bufferRecycler.allocCharBuffer(BufferRecycler.CharBufferType.NAME_COPY_BUFFER, i);
            return this._nameCopyBuffer;
        }
        throw new IllegalStateException("Trying to call allocNameCopyBuffer() second time");
    }

    public final byte[] allocReadIOBuffer() {
        if (this._readIOBuffer == null) {
            this._readIOBuffer = this._bufferRecycler.allocByteBuffer(BufferRecycler.ByteBufferType.READ_IO_BUFFER);
            return this._readIOBuffer;
        }
        throw new IllegalStateException("Trying to call allocReadIOBuffer() second time");
    }

    public final char[] allocTokenBuffer() {
        if (this._tokenCBuffer == null) {
            this._tokenCBuffer = this._bufferRecycler.allocCharBuffer(BufferRecycler.CharBufferType.TOKEN_BUFFER);
            return this._tokenCBuffer;
        }
        throw new IllegalStateException("Trying to call allocTokenBuffer() second time");
    }

    public final byte[] allocWriteEncodingBuffer() {
        if (this._writeEncodingBuffer == null) {
            this._writeEncodingBuffer = this._bufferRecycler.allocByteBuffer(BufferRecycler.ByteBufferType.WRITE_ENCODING_BUFFER);
            return this._writeEncodingBuffer;
        }
        throw new IllegalStateException("Trying to call allocWriteEncodingBuffer() second time");
    }

    public final TextBuffer constructTextBuffer() {
        return new TextBuffer(this._bufferRecycler);
    }

    public final JsonEncoding getEncoding() {
        return this._encoding;
    }

    public final Object getSourceReference() {
        return this._sourceRef;
    }

    public final boolean isResourceManaged() {
        return this._managedResource;
    }

    public final void releaseConcatBuffer(char[] cArr) {
        if (cArr != null) {
            if (cArr == this._concatCBuffer) {
                this._concatCBuffer = null;
                this._bufferRecycler.releaseCharBuffer(BufferRecycler.CharBufferType.CONCAT_BUFFER, cArr);
                return;
            }
            throw new IllegalArgumentException("Trying to release buffer not owned by the context");
        }
    }

    public final void releaseNameCopyBuffer(char[] cArr) {
        if (cArr != null) {
            if (cArr == this._nameCopyBuffer) {
                this._nameCopyBuffer = null;
                this._bufferRecycler.releaseCharBuffer(BufferRecycler.CharBufferType.NAME_COPY_BUFFER, cArr);
                return;
            }
            throw new IllegalArgumentException("Trying to release buffer not owned by the context");
        }
    }

    public final void releaseReadIOBuffer(byte[] bArr) {
        if (bArr != null) {
            if (bArr == this._readIOBuffer) {
                this._readIOBuffer = null;
                this._bufferRecycler.releaseByteBuffer(BufferRecycler.ByteBufferType.READ_IO_BUFFER, bArr);
                return;
            }
            throw new IllegalArgumentException("Trying to release buffer not owned by the context");
        }
    }

    public final void releaseTokenBuffer(char[] cArr) {
        if (cArr != null) {
            if (cArr == this._tokenCBuffer) {
                this._tokenCBuffer = null;
                this._bufferRecycler.releaseCharBuffer(BufferRecycler.CharBufferType.TOKEN_BUFFER, cArr);
                return;
            }
            throw new IllegalArgumentException("Trying to release buffer not owned by the context");
        }
    }

    public final void releaseWriteEncodingBuffer(byte[] bArr) {
        if (bArr != null) {
            if (bArr == this._writeEncodingBuffer) {
                this._writeEncodingBuffer = null;
                this._bufferRecycler.releaseByteBuffer(BufferRecycler.ByteBufferType.WRITE_ENCODING_BUFFER, bArr);
                return;
            }
            throw new IllegalArgumentException("Trying to release buffer not owned by the context");
        }
    }

    public void setEncoding(JsonEncoding jsonEncoding) {
        this._encoding = jsonEncoding;
    }
}
