package com.amazonaws.auth;

import com.amazonaws.AmazonClientException;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
class DecodedStreamBuffer {
    private static final Log log = LogFactory.getLog(DecodedStreamBuffer.class);
    private byte[] bufferArray;
    private boolean bufferSizeOverflow;
    private int byteBuffered;
    private int maxBufferSize;
    private int pos = -1;

    public DecodedStreamBuffer(int i) {
        this.bufferArray = new byte[i];
        this.maxBufferSize = i;
    }

    public void buffer(byte b) {
        this.pos = -1;
        int i = this.byteBuffered;
        if (i >= this.maxBufferSize) {
            if (log.isDebugEnabled()) {
                Log log2 = log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Buffer size ");
                outline107.append(this.maxBufferSize);
                outline107.append(" has been exceeded and the input stream will not be repeatable. Freeing buffer memory");
                log2.debug(outline107.toString());
            }
            this.bufferSizeOverflow = true;
            return;
        }
        byte[] bArr = this.bufferArray;
        this.byteBuffered = i + 1;
        bArr[i] = b;
    }

    public boolean hasNext() {
        int i = this.pos;
        return i != -1 && i < this.byteBuffered;
    }

    public byte next() {
        byte[] bArr = this.bufferArray;
        int i = this.pos;
        this.pos = i + 1;
        return bArr[i];
    }

    public void startReadBuffer() {
        if (!this.bufferSizeOverflow) {
            this.pos = 0;
            return;
        }
        throw new AmazonClientException(GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("The input stream is not repeatable since the buffer size "), this.maxBufferSize, " has been exceeded."));
    }

    public void buffer(byte[] bArr, int i, int i2) {
        this.pos = -1;
        int i3 = this.byteBuffered;
        if (i3 + i2 > this.maxBufferSize) {
            if (log.isDebugEnabled()) {
                Log log2 = log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Buffer size ");
                outline107.append(this.maxBufferSize);
                outline107.append(" has been exceeded and the input stream will not be repeatable. Freeing buffer memory");
                log2.debug(outline107.toString());
            }
            this.bufferSizeOverflow = true;
            return;
        }
        System.arraycopy(bArr, i, this.bufferArray, i3, i2);
        this.byteBuffered += i2;
    }
}
