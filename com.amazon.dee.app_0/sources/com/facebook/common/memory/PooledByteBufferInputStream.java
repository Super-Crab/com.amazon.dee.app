package com.facebook.common.memory;

import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.internal.Preconditions;
import java.io.InputStream;
import javax.annotation.concurrent.NotThreadSafe;
@NotThreadSafe
/* loaded from: classes2.dex */
public class PooledByteBufferInputStream extends InputStream {
    @VisibleForTesting
    int mMark;
    @VisibleForTesting
    int mOffset;
    @VisibleForTesting
    final PooledByteBuffer mPooledByteBuffer;

    public PooledByteBufferInputStream(PooledByteBuffer pooledByteBuffer) {
        Preconditions.checkArgument(Boolean.valueOf(!pooledByteBuffer.isClosed()));
        this.mPooledByteBuffer = (PooledByteBuffer) Preconditions.checkNotNull(pooledByteBuffer);
        this.mOffset = 0;
        this.mMark = 0;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.mPooledByteBuffer.size() - this.mOffset;
    }

    @Override // java.io.InputStream
    public void mark(int readlimit) {
        this.mMark = this.mOffset;
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.InputStream
    public int read() {
        if (available() <= 0) {
            return -1;
        }
        PooledByteBuffer pooledByteBuffer = this.mPooledByteBuffer;
        int i = this.mOffset;
        this.mOffset = i + 1;
        return pooledByteBuffer.read(i) & 255;
    }

    @Override // java.io.InputStream
    public void reset() {
        this.mOffset = this.mMark;
    }

    @Override // java.io.InputStream
    public long skip(long byteCount) {
        Preconditions.checkArgument(Boolean.valueOf(byteCount >= 0));
        int min = Math.min((int) byteCount, available());
        this.mOffset += min;
        return min;
    }

    @Override // java.io.InputStream
    public int read(byte[] buffer) {
        return read(buffer, 0, buffer.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] buffer, int offset, int length) {
        if (offset >= 0 && length >= 0 && offset + length <= buffer.length) {
            int available = available();
            if (available <= 0) {
                return -1;
            }
            if (length <= 0) {
                return 0;
            }
            int min = Math.min(available, length);
            this.mPooledByteBuffer.read(this.mOffset, buffer, offset, min);
            this.mOffset += min;
            return min;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("length=");
        GeneratedOutlineSupport1.outline175(outline107, buffer.length, "; regionStart=", offset, "; regionLength=");
        outline107.append(length);
        throw new ArrayIndexOutOfBoundsException(outline107.toString());
    }
}
