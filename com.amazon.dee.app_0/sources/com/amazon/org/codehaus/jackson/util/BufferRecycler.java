package com.amazon.org.codehaus.jackson.util;
/* loaded from: classes13.dex */
public class BufferRecycler {
    public static final int DEFAULT_WRITE_CONCAT_BUFFER_LEN = 2000;
    protected final byte[][] _byteBuffers = new byte[ByteBufferType.values().length];
    protected final char[][] _charBuffers = new char[CharBufferType.values().length];

    /* loaded from: classes13.dex */
    public enum ByteBufferType {
        READ_IO_BUFFER(4000),
        WRITE_ENCODING_BUFFER(4000),
        WRITE_CONCAT_BUFFER(2000);
        
        private final int size;

        ByteBufferType(int i) {
            this.size = i;
        }
    }

    /* loaded from: classes13.dex */
    public enum CharBufferType {
        TOKEN_BUFFER(2000),
        CONCAT_BUFFER(2000),
        TEXT_BUFFER(200),
        NAME_COPY_BUFFER(200);
        
        private final int size;

        CharBufferType(int i) {
            this.size = i;
        }
    }

    private final byte[] balloc(int i) {
        return new byte[i];
    }

    private final char[] calloc(int i) {
        return new char[i];
    }

    public final byte[] allocByteBuffer(ByteBufferType byteBufferType) {
        int ordinal = byteBufferType.ordinal();
        byte[][] bArr = this._byteBuffers;
        byte[] bArr2 = bArr[ordinal];
        if (bArr2 == null) {
            return balloc(byteBufferType.size);
        }
        bArr[ordinal] = null;
        return bArr2;
    }

    public final char[] allocCharBuffer(CharBufferType charBufferType) {
        return allocCharBuffer(charBufferType, 0);
    }

    public final void releaseByteBuffer(ByteBufferType byteBufferType, byte[] bArr) {
        this._byteBuffers[byteBufferType.ordinal()] = bArr;
    }

    public final void releaseCharBuffer(CharBufferType charBufferType, char[] cArr) {
        this._charBuffers[charBufferType.ordinal()] = cArr;
    }

    public final char[] allocCharBuffer(CharBufferType charBufferType, int i) {
        if (charBufferType.size > i) {
            i = charBufferType.size;
        }
        int ordinal = charBufferType.ordinal();
        char[][] cArr = this._charBuffers;
        char[] cArr2 = cArr[ordinal];
        if (cArr2 != null && cArr2.length >= i) {
            cArr[ordinal] = null;
            return cArr2;
        }
        return calloc(i);
    }
}
