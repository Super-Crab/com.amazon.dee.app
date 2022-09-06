package com.amazon.communication;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class ByteBufferChainInputStream extends InputStream {
    private final ByteBuffer[] mBuffers;
    private int mPosition = 0;

    public ByteBufferChainInputStream(ByteBufferChain byteBufferChain) {
        this.mBuffers = byteBufferChain.getByteBuffers();
    }

    @Override // java.io.InputStream
    public int available() {
        int i = 0;
        int i2 = 0;
        while (true) {
            ByteBuffer[] byteBufferArr = this.mBuffers;
            if (i < byteBufferArr.length) {
                i2 += byteBufferArr[i].remaining();
                i++;
            } else {
                return i2;
            }
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        while (true) {
            int i = this.mPosition;
            ByteBuffer[] byteBufferArr = this.mBuffers;
            if (i >= byteBufferArr.length || byteBufferArr[i].hasRemaining()) {
                break;
            }
            this.mPosition++;
        }
        int i2 = this.mPosition;
        ByteBuffer[] byteBufferArr2 = this.mBuffers;
        if (i2 < byteBufferArr2.length) {
            try {
                return byteBufferArr2[i2].get() & 255;
            } catch (BufferUnderflowException e) {
                throw new IOException(e);
            }
        }
        return -1;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        if (i < 0 || i2 < 0 || i > bArr.length) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("length=");
            GeneratedOutlineSupport1.outline175(outline107, bArr.length, "; regionStart=", i, "; regionLength=");
            outline107.append(i2);
            throw new ArrayIndexOutOfBoundsException(outline107.toString());
        }
        int i3 = 0;
        if (i2 == 0) {
            return 0;
        }
        while (true) {
            int i4 = this.mPosition;
            ByteBuffer[] byteBufferArr = this.mBuffers;
            if (i4 >= byteBufferArr.length || byteBufferArr[i4].hasRemaining()) {
                break;
            }
            this.mPosition++;
        }
        while (i3 < i2) {
            int i5 = this.mPosition;
            ByteBuffer[] byteBufferArr2 = this.mBuffers;
            if (i5 >= byteBufferArr2.length) {
                break;
            }
            int min = Math.min(i2 - i3, byteBufferArr2[i5].remaining());
            this.mBuffers[this.mPosition].get(bArr, i + i3, min);
            i3 += min;
            if (i3 < i2) {
                this.mPosition++;
            }
        }
        if (i3 <= 0) {
            return -1;
        }
        return i3;
    }
}
