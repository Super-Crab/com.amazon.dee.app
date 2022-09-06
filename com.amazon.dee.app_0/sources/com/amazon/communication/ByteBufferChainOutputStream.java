package com.amazon.communication;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class ByteBufferChainOutputStream extends OutputStream {
    private final ByteBuffer[] mBuffers;
    private int mPosition = 0;

    public ByteBufferChainOutputStream(ByteBufferChain byteBufferChain) {
        this.mBuffers = byteBufferChain.getByteBuffers();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        while (this.mPosition < this.mBuffers.length && !this.mBuffers[this.mPosition].hasRemaining()) {
            try {
                this.mPosition++;
            } catch (BufferOverflowException e) {
                throw new IOException(e);
            }
        }
        if (this.mPosition < this.mBuffers.length) {
            this.mBuffers[this.mPosition].put((byte) i);
            return;
        }
        throw new IOException("Reached the end of the ByteBufferChain and did not manage to write the byte!");
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (bArr != null) {
            if (i >= 0 && i2 >= 0 && i + i2 <= bArr.length) {
                while (true) {
                    int i3 = this.mPosition;
                    ByteBuffer[] byteBufferArr = this.mBuffers;
                    if (i3 >= byteBufferArr.length || byteBufferArr[i3].hasRemaining()) {
                        break;
                    }
                    this.mPosition++;
                }
                if (this.mPosition == this.mBuffers.length) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No bytes could be written because we are already at the end of the ByteBufferChain.  mPosition = ");
                    outline107.append(this.mPosition);
                    outline107.append(", mBuffers.length = ");
                    outline107.append(this.mBuffers.length);
                    throw new IOException(outline107.toString());
                }
                int i4 = 0;
                while (i4 < i2) {
                    int i5 = this.mPosition;
                    ByteBuffer[] byteBufferArr2 = this.mBuffers;
                    if (i5 >= byteBufferArr2.length) {
                        break;
                    }
                    int min = Math.min(i2 - i4, byteBufferArr2[i5].remaining());
                    this.mBuffers[this.mPosition].put(bArr, i + i4, min);
                    i4 += min;
                    if (i4 < i2) {
                        this.mPosition++;
                    }
                }
                if (i4 < i2) {
                    throw new IOException(GeneratedOutlineSupport1.outline54("Reached the end of the ByteBufferChain and only managed to write ", i4, " bytes out of ", i2, " requested"));
                }
                return;
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("array size:");
            GeneratedOutlineSupport1.outline175(outline1072, bArr.length, ", offset:", i, ", length:");
            outline1072.append(i2);
            throw new ArrayIndexOutOfBoundsException(outline1072.toString());
        }
        throw new NullPointerException("Array must not be null");
    }
}
