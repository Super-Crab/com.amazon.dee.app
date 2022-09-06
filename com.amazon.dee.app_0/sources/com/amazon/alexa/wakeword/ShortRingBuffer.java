package com.amazon.alexa.wakeword;

import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class ShortRingBuffer {
    private static final String TAG = "ShortRingBuffer";
    private final int capacity;
    private final short[] elements;
    private long writePosition = -1;
    private long bufferStartPosition = 0;

    /* loaded from: classes11.dex */
    public class RingBufferReader {
        private long readPosition;

        private boolean canRead() {
            return available() > 0;
        }

        public int available() {
            int max;
            synchronized (ShortRingBuffer.this) {
                max = (int) ((ShortRingBuffer.this.writePosition - Math.max(this.readPosition, ShortRingBuffer.this.bufferStartPosition)) + 1);
            }
            return max;
        }

        public int read(short[] sArr, int i) {
            int i2;
            synchronized (ShortRingBuffer.this) {
                int i3 = 0;
                while (i3 < i) {
                    try {
                        if (!canRead()) {
                            break;
                        }
                        i2 = i3 + 1;
                        try {
                            sArr[i3] = read();
                            i3 = i2;
                        } catch (IllegalStateException unused) {
                            String str = ShortRingBuffer.TAG;
                            Log.w(str, "can't read more than " + i2);
                            return i2;
                        }
                    } catch (IllegalStateException unused2) {
                        i2 = i3;
                    }
                }
                return i3;
            }
        }

        public int readAsBytes(byte[] bArr, int i) {
            int read;
            synchronized (ShortRingBuffer.this) {
                int i2 = i / 2;
                short[] sArr = new short[i2];
                read = read(sArr, i2) * 2;
                ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().put(sArr);
            }
            return read;
        }

        public int skip(int i) {
            int min;
            synchronized (ShortRingBuffer.this) {
                min = Math.min(available(), i);
                this.readPosition += min;
            }
            return min;
        }

        private RingBufferReader(long j) {
            this.readPosition = j;
        }

        public short read() {
            short s;
            synchronized (ShortRingBuffer.this) {
                if (canRead()) {
                    short[] sArr = ShortRingBuffer.this.elements;
                    long j = this.readPosition;
                    this.readPosition = 1 + j;
                    s = sArr[(int) (j % ShortRingBuffer.this.capacity)];
                } else {
                    throw new IllegalStateException("ring buffer overrun");
                }
            }
            return s;
        }
    }

    public ShortRingBuffer(int i) {
        this.capacity = i;
        this.elements = new short[i];
    }

    public synchronized int available() {
        return (int) ((this.writePosition - this.bufferStartPosition) + 1);
    }

    public RingBufferReader getReader() {
        return new RingBufferReader(this.bufferStartPosition);
    }

    public synchronized void reset() {
        this.writePosition = -1L;
        this.bufferStartPosition = 0L;
    }

    public String toString() {
        return Arrays.toString(this.elements);
    }

    public synchronized void write(short s) {
        short[] sArr = this.elements;
        long j = this.writePosition + 1;
        this.writePosition = j;
        sArr[(int) (j % this.capacity)] = s;
        if (available() > this.capacity) {
            this.bufferStartPosition++;
        }
    }

    public synchronized void write(short[] sArr, int i) {
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + 1;
            write(sArr[i2]);
            i2 = i3;
        }
    }
}
