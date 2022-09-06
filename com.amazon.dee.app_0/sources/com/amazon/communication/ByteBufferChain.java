package com.amazon.communication;

import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes12.dex */
public class ByteBufferChain {
    static final int MAX_BUFFERS_IN_TOSTRING = 30;
    private static final DPLogger log = new DPLogger("TComm.ByteBufferChain");
    private ByteBuffer[] mBufferArray;
    private final List<ByteBuffer> mBuffers;
    private int[] mSavedPositions;

    public ByteBufferChain() {
        this.mBuffers = new ArrayList();
        this.mBufferArray = null;
        this.mSavedPositions = null;
    }

    public ByteBufferChain append(ByteBuffer byteBuffer) {
        if (byteBuffer != null && byteBuffer.hasRemaining()) {
            this.mBuffers.add(byteBuffer);
            return this;
        }
        throw new IllegalArgumentException("Can't append null or empty ByteBuffer. newData: " + byteBuffer);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ByteBufferChain.class != obj.getClass()) {
            return false;
        }
        ByteBufferChain byteBufferChain = (ByteBufferChain) obj;
        List<ByteBuffer> list = this.mBuffers;
        if (list == null) {
            if (byteBufferChain.mBuffers != null) {
                return false;
            }
        } else if (!list.equals(byteBufferChain.mBuffers)) {
            return false;
        }
        return true;
    }

    public List<ByteBuffer> getByteBufferList() {
        return Collections.unmodifiableList(this.mBuffers);
    }

    public ByteBuffer[] getByteBuffers() {
        ByteBuffer[] byteBufferArr = this.mBufferArray;
        if (byteBufferArr == null || byteBufferArr.length != this.mBuffers.size()) {
            List<ByteBuffer> list = this.mBuffers;
            this.mBufferArray = (ByteBuffer[]) list.toArray(new ByteBuffer[list.size()]);
        }
        return this.mBufferArray;
    }

    public int getDataSize() {
        int i = 0;
        for (int i2 = 0; i2 < this.mBuffers.size(); i2++) {
            i += this.mBuffers.get(i2).remaining();
        }
        return i;
    }

    public ByteBufferChainInputStream getInputStream() {
        return new ByteBufferChainInputStream(this);
    }

    public ByteBufferChainOutputStream getOutputStream() {
        return new ByteBufferChainOutputStream(this);
    }

    public int hashCode() {
        List<ByteBuffer> list = this.mBuffers;
        return 31 + (list == null ? 0 : list.hashCode());
    }

    public void loadSavedPositions() {
        if (this.mSavedPositions != null && this.mBuffers.size() == this.mSavedPositions.length) {
            for (int i = 0; i < this.mBuffers.size(); i++) {
                this.mBuffers.get(i).position(this.mSavedPositions[i]);
            }
            return;
        }
        throw new IllegalStateException("loadSavedPositionsForAll cannot be called without a matching savePositionsForAll being called");
    }

    public void mark() {
        for (int i = 0; i < this.mBuffers.size(); i++) {
            this.mBuffers.get(i).mark();
        }
    }

    public ByteBufferChain prepend(ByteBuffer byteBuffer) {
        if (byteBuffer != null && byteBuffer.hasRemaining()) {
            this.mBuffers.add(0, byteBuffer);
            return this;
        }
        throw new IllegalArgumentException("Can't prepend null or empty ByteBuffer. newData: " + byteBuffer);
    }

    public void reset() {
        for (int i = 0; i < this.mBuffers.size(); i++) {
            this.mBuffers.get(i).reset();
        }
    }

    public void savePositions() {
        this.mSavedPositions = new int[this.mBuffers.size()];
        for (int i = 0; i < this.mBuffers.size(); i++) {
            this.mSavedPositions[i] = this.mBuffers.get(i).position();
        }
    }

    public boolean shrinkLimitFromEnd(int i) {
        if (i >= 0) {
            for (int size = this.mBuffers.size() - 1; i > 0 && size >= 0; size--) {
                ByteBuffer byteBuffer = this.mBuffers.get(size);
                int remaining = byteBuffer.remaining() < i ? byteBuffer.remaining() : i;
                byteBuffer.limit(byteBuffer.limit() - remaining);
                i -= remaining;
            }
            return i == 0;
        }
        throw new IllegalArgumentException("Can not shrink negative number of bytes.");
    }

    public boolean skipBytes(int i) {
        if (i >= 0) {
            for (int i2 = 0; i > 0 && i2 < this.mBuffers.size(); i2++) {
                ByteBuffer byteBuffer = this.mBuffers.get(i2);
                int remaining = byteBuffer.remaining() < i ? byteBuffer.remaining() : i;
                byteBuffer.position(byteBuffer.position() + remaining);
                i -= remaining;
            }
            return i == 0;
        }
        throw new IllegalArgumentException("Can not skip negative number of bytes.");
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (ByteBuffer byteBuffer : getByteBufferList()) {
            try {
                byteArrayOutputStream.write(byteBuffer.array());
            } catch (IOException e) {
                throw new IOException("Error encountered when converting ByteBufferChain to byte array", e);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ByteBufferChain [mBuffers=");
        Iterator<ByteBuffer> it2 = this.mBuffers.iterator();
        for (int i = 0; it2.hasNext() && i < 30; i++) {
            sb.append(it2.next() + " ");
        }
        if (this.mBuffers.size() > 30) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("(");
            outline107.append(this.mBuffers.size() - 30);
            outline107.append(" more ...)");
            sb.append(outline107.toString());
        }
        sb.append("]");
        return sb.toString();
    }

    public ByteBufferChain append(ByteBufferChain byteBufferChain) {
        if (byteBufferChain != null) {
            if (byteBufferChain.getDataSize() == 0) {
                log.warn("append", "attempting to append a ByteBufferChain with zero content; skipping", new Object[0]);
            } else {
                for (int i = 0; i < byteBufferChain.mBuffers.size(); i++) {
                    append(byteBufferChain.mBuffers.get(i));
                }
            }
            return this;
        }
        throw new IllegalArgumentException("Can't append null ByteBufferChain. Chain: " + byteBufferChain);
    }

    public ByteBufferChain(ByteBuffer byteBuffer) {
        this.mBuffers = new ArrayList();
        this.mBuffers.add(byteBuffer);
        this.mBufferArray = null;
        this.mSavedPositions = null;
    }

    public int append(InputStream inputStream, int i) throws IOException {
        if (inputStream != null) {
            if (i >= 1) {
                byte[] bArr = new byte[i];
                int read = inputStream.read(bArr);
                if (read > 0) {
                    append(ByteBuffer.wrap(bArr, 0, read));
                }
                log.debug("append", "finished appending", "bytesRead", Integer.valueOf(read), "upperBoundSize", Integer.valueOf(i));
                return read;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Size upper bound cannot be 0 or negative. upperBoundSize: ", i));
        }
        throw new IllegalArgumentException("InputStream can't be null");
    }

    public ByteBufferChain(ByteBuffer[] byteBufferArr) {
        this.mBufferArray = byteBufferArr;
        this.mBuffers = new ArrayList(byteBufferArr.length);
        for (ByteBuffer byteBuffer : byteBufferArr) {
            this.mBuffers.add(byteBuffer);
        }
    }

    public ByteBufferChain(ByteBufferChain byteBufferChain) {
        List<ByteBuffer> list = byteBufferChain.mBuffers;
        if (list != null) {
            this.mBufferArray = new ByteBuffer[list.size()];
            this.mBuffers = new ArrayList(byteBufferChain.mBuffers.size());
            for (int i = 0; i < this.mBufferArray.length; i++) {
                ByteBuffer duplicate = byteBufferChain.mBuffers.get(i).duplicate();
                this.mBufferArray[i] = duplicate;
                this.mBuffers.add(duplicate);
            }
        } else {
            this.mBuffers = new ArrayList();
            this.mBufferArray = null;
        }
        this.mSavedPositions = byteBufferChain.mSavedPositions;
    }
}
