package com.dp.framework;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
/* loaded from: classes2.dex */
public final class ByteBufferInputStream extends InputStream {
    public static final int BYTE_TO_INT_FLAG = 255;
    private final ByteBuffer mBuffer;

    public ByteBufferInputStream(ByteBuffer byteBuffer) {
        this.mBuffer = byteBuffer;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.mBuffer.remaining();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.mBuffer.hasRemaining()) {
            try {
                return this.mBuffer.get() & 255;
            } catch (BufferUnderflowException e) {
                throw new IOException(e);
            }
        }
        return -1;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            if (i < 0 || i >= bArr.length || i2 < 0 || i + i2 > bArr.length) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("array size:");
                GeneratedOutlineSupport1.outline175(outline107, bArr.length, ", offset:", i, ", length:");
                outline107.append(i2);
                throw new ArrayIndexOutOfBoundsException(outline107.toString());
            } else if (i2 == 0) {
                return 0;
            } else {
                int i3 = -1;
                if (this.mBuffer.hasRemaining()) {
                    i3 = this.mBuffer.remaining();
                    if (i2 <= i3) {
                        i3 = i2;
                    }
                    this.mBuffer.get(bArr, i, i3);
                }
                return i3;
            }
        }
        throw new NullPointerException("Array cannot be null");
    }
}
