package amazon.speech.io;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class ByteArrayQueue {
    private final byte[] mArray;
    private long mHead = 0;
    private long mTail = 0;
    private List<ReadPointer> mReadPointerList = new ArrayList();

    /* loaded from: classes.dex */
    public class ReadPointer {
        private long mHead;

        public long getHeadPosition() {
            return this.mHead;
        }

        public long getSize() {
            return ByteArrayQueue.this.mTail - this.mHead;
        }

        public long peek(int i, byte[] bArr, int i2) {
            if (i <= bArr.length) {
                long j = this.mHead;
                int i3 = i;
                while (i3 > 0) {
                    int translatePosition = ByteArrayQueue.this.translatePosition(this.mHead);
                    int min = Math.min(ByteArrayQueue.this.getDistanceToEnd(translatePosition), i3);
                    System.arraycopy(ByteArrayQueue.this.mArray, translatePosition, bArr, (i - i3) + i2, min);
                    i3 -= min;
                    j += min;
                }
                return j;
            }
            throw new IllegalArgumentException(String.format("Length (%d) cannot exceed length of array (%d).", Integer.valueOf(i), Integer.valueOf(bArr.length)));
        }

        public void release() {
            ByteArrayQueue.this.mReadPointerList.remove(this);
        }

        public void setHeadPosition(long j) {
            this.mHead = j;
            long j2 = this.mHead;
            for (ReadPointer readPointer : ByteArrayQueue.this.mReadPointerList) {
                long j3 = readPointer.mHead;
                if (j3 < j2) {
                    j2 = j3;
                }
            }
            ByteArrayQueue.this.mHead = j2;
        }

        private ReadPointer(long j) {
            this.mHead = j;
        }
    }

    public ByteArrayQueue(int i) {
        this.mArray = new byte[i];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getDistanceToEnd(int i) {
        return this.mArray.length - i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int translatePosition(long j) {
        byte[] bArr = this.mArray;
        long length = j % bArr.length;
        return length >= 0 ? (int) length : ((int) length) + bArr.length;
    }

    public long delete(long j) {
        long min = Math.min(j, getSize());
        this.mHead += min;
        for (ReadPointer readPointer : this.mReadPointerList) {
            long j2 = readPointer.mHead;
            long j3 = this.mHead;
            if (j2 < j3) {
                readPointer.mHead = j3;
            }
        }
        return min;
    }

    public long getFreeSize() {
        long length = this.mArray.length - getSize();
        if (length >= 0) {
            return length;
        }
        return 0L;
    }

    public long getSize() {
        long j = this.mTail - this.mHead;
        if (j >= 0) {
            return j;
        }
        return 0L;
    }

    public long getTailPosition() {
        return this.mTail;
    }

    public ReadPointer newReadPointer() {
        ReadPointer readPointer = new ReadPointer(this.mHead);
        this.mReadPointerList.add(readPointer);
        return readPointer;
    }

    public long poke(byte[] bArr, int i, int i2) {
        if (i2 <= bArr.length) {
            long j = this.mTail;
            int i3 = i2;
            while (i3 > 0) {
                int translatePosition = translatePosition(j);
                int min = Math.min(getDistanceToEnd(translatePosition), i3);
                System.arraycopy(bArr, (i2 - i3) + i, this.mArray, translatePosition, min);
                i3 -= min;
                j += min;
            }
            return j;
        }
        throw new IllegalArgumentException(String.format("Length (%d) cannot exceed length of array (%d).", Integer.valueOf(i2), Integer.valueOf(bArr.length)));
    }

    public void setTailPosition(long j) {
        this.mTail = j;
        long size = getSize();
        byte[] bArr = this.mArray;
        if (size > bArr.length) {
            delete(size - bArr.length);
        }
    }
}
