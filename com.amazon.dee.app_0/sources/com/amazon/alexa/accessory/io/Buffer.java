package com.amazon.alexa.accessory.io;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.Arrays;
/* loaded from: classes.dex */
public final class Buffer implements SizedSource, Sink {
    public static final int DEFAULT_SIZE = 16;
    private byte[] data;
    private volatile int position;

    public Buffer() {
        this(16);
    }

    public int capacity() {
        return this.data.length;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.position = 0;
    }

    public Buffer copy() {
        Buffer buffer = new Buffer(this.position);
        buffer.write(this.data, 0, this.position);
        return buffer;
    }

    public byte[] data() {
        return this.data;
    }

    public void ensureCapacity(int i) {
        byte[] bArr = this.data;
        if (i <= bArr.length) {
            return;
        }
        this.data = Arrays.copyOf(bArr, Math.max(bArr.length << 1, i));
    }

    @Override // com.amazon.alexa.accessory.io.Sink
    public void flush() {
    }

    @Override // com.amazon.alexa.accessory.io.Source
    public int read(byte[] bArr, int i, int i2) {
        Preconditions.notNegative(i2, "count");
        Preconditions.notNegative(i, "offset");
        Preconditions.precondition(i + i2 <= bArr.length, "destination buffer out of bounds");
        Preconditions.notNull(bArr, "buf");
        if (this.position == 0) {
            return -1;
        }
        int min = Math.min(this.position, i2);
        System.arraycopy(this.data, 0, bArr, i, min);
        this.position -= min;
        if (this.position > 0) {
            byte[] bArr2 = this.data;
            System.arraycopy(bArr2, min, bArr2, 0, this.position);
        }
        return min;
    }

    @Override // com.amazon.alexa.accessory.io.SizedSource
    public void reset() {
    }

    public void resize(int i) {
        ensureCapacity(i);
        this.position = i;
    }

    @Override // com.amazon.alexa.accessory.io.SizedSource
    public int size() {
        return this.position;
    }

    @Override // com.amazon.alexa.accessory.io.Sink
    public void write(byte[] bArr, int i, int i2) {
        Preconditions.notNegative(i, "offset");
        Preconditions.notNegative(i2, "size");
        Preconditions.precondition(i + i2 <= bArr.length, "Source buffer out of bounds.");
        Preconditions.notNull(bArr, "destBuffer");
        ensureCapacity(this.position + i2);
        System.arraycopy(bArr, i, this.data, this.position, i2);
        this.position += i2;
    }

    public Buffer(int i) {
        Preconditions.notNegative(i, "Capacity must be positive.");
        this.data = new byte[i];
    }
}
