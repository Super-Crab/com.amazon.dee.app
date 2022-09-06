package com.facebook.common.memory;

import androidx.annotation.VisibleForTesting;
import com.facebook.common.internal.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes2.dex */
public class PooledByteStreams {
    private static final int DEFAULT_TEMP_BUF_SIZE = 16384;
    private final ByteArrayPool mByteArrayPool;
    private final int mTempBufSize;

    public PooledByteStreams(ByteArrayPool byteArrayPool) {
        this(byteArrayPool, 16384);
    }

    public long copy(final InputStream from, final OutputStream to) throws IOException {
        byte[] mo6905get = this.mByteArrayPool.mo6905get(this.mTempBufSize);
        long j = 0;
        while (true) {
            try {
                int read = from.read(mo6905get, 0, this.mTempBufSize);
                if (read == -1) {
                    return j;
                }
                to.write(mo6905get, 0, read);
                j += read;
            } finally {
                this.mByteArrayPool.release(mo6905get);
            }
        }
    }

    @VisibleForTesting
    public PooledByteStreams(ByteArrayPool byteArrayPool, int tempBufSize) {
        Preconditions.checkArgument(Boolean.valueOf(tempBufSize > 0));
        this.mTempBufSize = tempBufSize;
        this.mByteArrayPool = byteArrayPool;
    }

    public long copy(final InputStream from, final OutputStream to, final long bytesToCopy) throws IOException {
        long j = 0;
        Preconditions.checkState(bytesToCopy > 0);
        byte[] mo6905get = this.mByteArrayPool.mo6905get(this.mTempBufSize);
        while (j < bytesToCopy) {
            try {
                int read = from.read(mo6905get, 0, (int) Math.min(this.mTempBufSize, bytesToCopy - j));
                if (read == -1) {
                    break;
                }
                to.write(mo6905get, 0, read);
                j += read;
            } finally {
                this.mByteArrayPool.release(mo6905get);
            }
        }
        return j;
    }
}
