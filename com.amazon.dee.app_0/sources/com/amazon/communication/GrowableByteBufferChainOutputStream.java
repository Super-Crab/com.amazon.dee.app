package com.amazon.communication;

import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public class GrowableByteBufferChainOutputStream extends OutputStream {
    private static final DPLogger log = new DPLogger("TComm.GrowableByteBufferChainOutputStream");
    private final List<ByteBuffer> mByteBuffers;
    private final int mCapacityIncrement;
    private boolean mIsByteBufferChainRetrieved;

    public GrowableByteBufferChainOutputStream(int i, int i2) {
        if (i >= 1) {
            if (i2 >= 1) {
                this.mCapacityIncrement = i2;
                this.mIsByteBufferChainRetrieved = false;
                this.mByteBuffers = new ArrayList();
                this.mByteBuffers.add(ByteBuffer.allocate(i));
                return;
            }
            throw new IllegalArgumentException("Capacity increment cannot be less than 1");
        }
        throw new IllegalArgumentException("Capacity cannot be less than 1");
    }

    private ByteBuffer getBufferForWrite() {
        ByteBuffer byteBuffer = (ByteBuffer) GeneratedOutlineSupport1.outline24(this.mByteBuffers, -1);
        if (!byteBuffer.hasRemaining()) {
            ByteBuffer allocate = ByteBuffer.allocate(this.mCapacityIncrement);
            this.mByteBuffers.add(allocate);
            return allocate;
        }
        return byteBuffer;
    }

    public ByteBufferChain getByteBufferChain() {
        if (!this.mIsByteBufferChainRetrieved) {
            this.mIsByteBufferChainRetrieved = true;
            ByteBufferChain byteBufferChain = new ByteBufferChain();
            for (int i = 0; i < this.mByteBuffers.size(); i++) {
                ByteBuffer byteBuffer = this.mByteBuffers.get(i);
                byteBuffer.flip();
                byteBufferChain.append(byteBuffer);
            }
            return byteBufferChain;
        }
        throw new IllegalStateException("getByteBufferChain() can only be called once and has been called");
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        getBufferForWrite().put((byte) i);
        log.warn("write", "invoking write(byte) too many times may be a potential bug.", new Object[0]);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (bArr != null) {
            if (i < 0 || i >= bArr.length || i2 < 0 || i + i2 > bArr.length) {
                throw new IndexOutOfBoundsException();
            }
            if (i2 == 0) {
                return;
            }
            while (i2 > 0) {
                ByteBuffer bufferForWrite = getBufferForWrite();
                int remaining = i2 > bufferForWrite.remaining() ? bufferForWrite.remaining() : i2;
                bufferForWrite.put(bArr, i, remaining);
                i2 -= remaining;
                i += remaining;
            }
            return;
        }
        throw new NullPointerException();
    }
}
