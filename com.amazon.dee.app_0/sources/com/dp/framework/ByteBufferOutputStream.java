package com.dp.framework;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
/* loaded from: classes2.dex */
public class ByteBufferOutputStream extends OutputStream {
    private final ByteBuffer mBuffer;

    public ByteBufferOutputStream(ByteBuffer byteBuffer) {
        this.mBuffer = byteBuffer;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        try {
            this.mBuffer.put((byte) i);
        } catch (BufferOverflowException e) {
            throw new IOException(e);
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (bArr != null) {
            if (i >= 0 && i2 >= 0 && i + i2 <= bArr.length) {
                try {
                    this.mBuffer.put(bArr, i, i2);
                    return;
                } catch (BufferOverflowException e) {
                    throw new IOException(e);
                }
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("array size:");
            GeneratedOutlineSupport1.outline175(outline107, bArr.length, ", offset:", i, ", length:");
            outline107.append(i2);
            throw new ArrayIndexOutOfBoundsException(outline107.toString());
        }
        throw new NullPointerException("Array must not be null");
    }
}
