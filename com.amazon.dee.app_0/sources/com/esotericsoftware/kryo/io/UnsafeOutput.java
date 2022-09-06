package com.esotericsoftware.kryo.io;

import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.util.UnsafeUtil;
import com.esotericsoftware.kryo.util.Util;
import java.io.OutputStream;
import java.nio.ByteOrder;
/* loaded from: classes2.dex */
public final class UnsafeOutput extends Output {
    private static final boolean isLittleEndian = ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN);
    private boolean supportVarInts;

    public UnsafeOutput() {
        this.supportVarInts = false;
    }

    private final void writeLittleEndianInt(int i) {
        if (isLittleEndian) {
            writeInt(i);
        } else {
            writeInt(Util.swapInt(i));
        }
    }

    private final void writeLittleEndianLong(long j) {
        if (isLittleEndian) {
            writeLong(j);
        } else {
            writeLong(Util.swapLong(j));
        }
    }

    public boolean supportVarInts() {
        return this.supportVarInts;
    }

    public final void writeBytes(Object obj, long j, long j2) throws KryoException {
        writeBytes(obj, 0L, j, j2);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public final void writeChars(char[] cArr) throws KryoException {
        writeBytes(cArr, UnsafeUtil.charArrayBaseOffset, 0L, cArr.length << 1);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public final void writeDouble(double d) throws KryoException {
        require(8);
        UnsafeUtil.unsafe().putDouble(this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position, d);
        this.position += 8;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public final void writeDoubles(double[] dArr) throws KryoException {
        writeBytes(dArr, UnsafeUtil.doubleArrayBaseOffset, 0L, dArr.length << 3);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public final void writeFloat(float f) throws KryoException {
        require(4);
        UnsafeUtil.unsafe().putFloat(this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position, f);
        this.position += 4;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public final void writeFloats(float[] fArr) throws KryoException {
        writeBytes(fArr, UnsafeUtil.floatArrayBaseOffset, 0L, fArr.length << 2);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public final void writeInt(int i) throws KryoException {
        require(4);
        UnsafeUtil.unsafe().putInt(this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position, i);
        this.position += 4;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public final void writeInts(int[] iArr, boolean z) throws KryoException {
        if (!this.supportVarInts) {
            writeBytes(iArr, UnsafeUtil.intArrayBaseOffset, 0L, iArr.length << 2);
        } else {
            super.writeInts(iArr, z);
        }
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public final void writeLong(long j) throws KryoException {
        require(8);
        UnsafeUtil.unsafe().putLong(this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position, j);
        this.position += 8;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public final void writeLongs(long[] jArr, boolean z) throws KryoException {
        if (!this.supportVarInts) {
            writeBytes(jArr, UnsafeUtil.longArrayBaseOffset, 0L, jArr.length << 3);
        } else {
            super.writeLongs(jArr, z);
        }
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public final void writeShort(int i) throws KryoException {
        require(2);
        UnsafeUtil.unsafe().putShort(this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position, (short) i);
        this.position += 2;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public final void writeShorts(short[] sArr) throws KryoException {
        writeBytes(sArr, UnsafeUtil.shortArrayBaseOffset, 0L, sArr.length << 1);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public final int writeVarInt(int i, boolean z) throws KryoException {
        int i2;
        if (!z) {
            i = (i >> 31) ^ (i << 1);
        }
        int i3 = i & 127;
        int i4 = i >>> 7;
        if (i4 == 0) {
            write(i3);
            return 1;
        }
        int i5 = i3 | 128 | ((i4 & 127) << 8);
        int i6 = i4 >>> 7;
        if (i6 == 0) {
            writeLittleEndianInt(i5);
            this.position -= 2;
            return 2;
        }
        int i7 = i5 | 32768 | ((i6 & 127) << 16);
        int i8 = i6 >>> 7;
        if (i8 == 0) {
            writeLittleEndianInt(i7);
            this.position--;
            return 3;
        }
        int i9 = i7 | 8388608 | ((i8 & 127) << 24);
        if ((i8 >>> 7) == 0) {
            writeLittleEndianInt(i9);
            this.position += 0;
            return 4;
        }
        writeLittleEndianLong(((i2 & 127) << 32) | i9 | Integer.MIN_VALUE);
        this.position -= 3;
        return 5;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public final int writeVarLong(long j, boolean z) throws KryoException {
        long j2;
        if (!z) {
            j = (j >> 63) ^ (j << 1);
        }
        int i = (int) (127 & j);
        long j3 = j >>> 7;
        if (j3 == 0) {
            writeByte(i);
            return 1;
        }
        int i2 = (int) (i | 128 | (j3 << 8));
        long j4 = j3 >>> 7;
        if (j4 == 0) {
            writeLittleEndianInt(i2);
            this.position -= 2;
            return 2;
        }
        int i3 = (int) (i2 | 32768 | (j4 << 16));
        long j5 = j4 >>> 7;
        if (j5 == 0) {
            writeLittleEndianInt(i3);
            this.position--;
            return 3;
        }
        int i4 = (int) (i3 | 8388608 | (j5 << 24));
        long j6 = j5 >>> 7;
        if (j6 == 0) {
            writeLittleEndianInt(i4);
            this.position += 0;
            return 4;
        }
        long j7 = i4 | Integer.MIN_VALUE;
        long j8 = (j6 << 32) | j7;
        long j9 = j6 >>> 7;
        if (j9 == 0) {
            writeLittleEndianLong(j8);
            this.position -= 3;
            return 5;
        }
        long j10 = (j9 << 40) | j7;
        long j11 = j9 >>> 7;
        if (j11 == 0) {
            writeLittleEndianLong(j10);
            this.position -= 2;
            return 6;
        }
        long j12 = (j11 << 48) | j7;
        long j13 = j11 >>> 7;
        if (j13 == 0) {
            writeLittleEndianLong(j12);
            this.position--;
            return 7;
        }
        long j14 = j7 | (j13 << 56);
        if ((j13 >>> 7) == 0) {
            writeLittleEndianLong(j14);
            return 8;
        }
        writeLittleEndianLong((-2147483648L) | j14);
        write((byte) (j2 >>> 7));
        return 9;
    }

    private final void writeBytes(Object obj, long j, long j2, long j3) throws KryoException {
        long j4 = j3;
        int min = Math.min(this.capacity - this.position, (int) j3);
        long j5 = j2;
        while (true) {
            long j6 = min;
            UnsafeUtil.unsafe().copyMemory(obj, j + j5, this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position, j6);
            this.position += min;
            j4 -= j6;
            if (j4 == 0) {
                return;
            }
            j5 += j6;
            min = Math.min(this.capacity, (int) j4);
            require(min);
        }
    }

    public void supportVarInts(boolean z) {
        this.supportVarInts = z;
    }

    public UnsafeOutput(int i) {
        this(i, i);
    }

    public UnsafeOutput(int i, int i2) {
        super(i, i2);
        this.supportVarInts = false;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public final int writeInt(int i, boolean z) throws KryoException {
        if (!this.supportVarInts) {
            writeInt(i);
            return 4;
        }
        return writeVarInt(i, z);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public final int writeLong(long j, boolean z) throws KryoException {
        if (!this.supportVarInts) {
            writeLong(j);
            return 8;
        }
        return writeVarLong(j, z);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public final void writeInts(int[] iArr) throws KryoException {
        writeBytes(iArr, UnsafeUtil.intArrayBaseOffset, 0L, iArr.length << 2);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public final void writeLongs(long[] jArr) throws KryoException {
        writeBytes(jArr, UnsafeUtil.longArrayBaseOffset, 0L, jArr.length << 3);
    }

    public UnsafeOutput(byte[] bArr) {
        this(bArr, bArr.length);
    }

    public UnsafeOutput(byte[] bArr, int i) {
        super(bArr, i);
        this.supportVarInts = false;
    }

    public UnsafeOutput(OutputStream outputStream) {
        super(outputStream);
        this.supportVarInts = false;
    }

    public UnsafeOutput(OutputStream outputStream, int i) {
        super(outputStream, i);
        this.supportVarInts = false;
    }
}
