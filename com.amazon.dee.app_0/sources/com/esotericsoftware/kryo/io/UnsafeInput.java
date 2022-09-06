package com.esotericsoftware.kryo.io;

import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.util.UnsafeUtil;
import java.io.InputStream;
/* loaded from: classes2.dex */
public final class UnsafeInput extends Input {
    private boolean varIntsEnabled;

    public UnsafeInput() {
        this.varIntsEnabled = false;
    }

    public boolean getVarIntsEnabled() {
        return this.varIntsEnabled;
    }

    public final void readBytes(Object obj, long j, long j2) throws KryoException {
        if (obj.getClass().isArray()) {
            readBytes(obj, 0L, j, (int) j2);
            return;
        }
        throw new KryoException("Only bulk reads of arrays is supported");
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public final char[] readChars(int i) throws KryoException {
        int i2 = i << 1;
        char[] cArr = new char[i];
        readBytes(cArr, UnsafeUtil.charArrayBaseOffset, 0L, i2);
        return cArr;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public double readDouble() throws KryoException {
        require(8);
        double d = UnsafeUtil.unsafe().getDouble(this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position);
        this.position += 8;
        return d;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public final double[] readDoubles(int i) throws KryoException {
        int i2 = i << 3;
        double[] dArr = new double[i];
        readBytes(dArr, UnsafeUtil.doubleArrayBaseOffset, 0L, i2);
        return dArr;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public float readFloat() throws KryoException {
        require(4);
        float f = UnsafeUtil.unsafe().getFloat(this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position);
        this.position += 4;
        return f;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public final float[] readFloats(int i) throws KryoException {
        int i2 = i << 2;
        float[] fArr = new float[i];
        readBytes(fArr, UnsafeUtil.floatArrayBaseOffset, 0L, i2);
        return fArr;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public int readInt() throws KryoException {
        require(4);
        int i = UnsafeUtil.unsafe().getInt(this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position);
        this.position += 4;
        return i;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public final int[] readInts(int i, boolean z) throws KryoException {
        if (!this.varIntsEnabled) {
            int i2 = i << 2;
            int[] iArr = new int[i];
            readBytes(iArr, UnsafeUtil.intArrayBaseOffset, 0L, i2);
            return iArr;
        }
        return super.readInts(i, z);
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public long readLong() throws KryoException {
        require(8);
        long j = UnsafeUtil.unsafe().getLong(this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position);
        this.position += 8;
        return j;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public final long[] readLongs(int i, boolean z) throws KryoException {
        if (!this.varIntsEnabled) {
            int i2 = i << 3;
            long[] jArr = new long[i];
            readBytes(jArr, UnsafeUtil.longArrayBaseOffset, 0L, i2);
            return jArr;
        }
        return super.readLongs(i, z);
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public short readShort() throws KryoException {
        require(2);
        short s = UnsafeUtil.unsafe().getShort(this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position);
        this.position += 2;
        return s;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public final short[] readShorts(int i) throws KryoException {
        int i2 = i << 1;
        short[] sArr = new short[i];
        readBytes(sArr, UnsafeUtil.shortArrayBaseOffset, 0L, i2);
        return sArr;
    }

    public void setVarIntsEnabled(boolean z) {
        this.varIntsEnabled = z;
    }

    public UnsafeInput(int i) {
        super(i);
        this.varIntsEnabled = false;
    }

    private final void readBytes(Object obj, long j, long j2, int i) throws KryoException {
        int min = Math.min(this.limit - this.position, i);
        int i2 = i;
        long j3 = j2;
        while (true) {
            long j4 = min;
            UnsafeUtil.unsafe().copyMemory(this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position, obj, j + j3, j4);
            this.position += min;
            i2 -= min;
            if (i2 == 0) {
                return;
            }
            j3 += j4;
            min = Math.min(i2, this.capacity);
            require(min);
        }
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public int readInt(boolean z) throws KryoException {
        if (!this.varIntsEnabled) {
            return readInt();
        }
        return super.readInt(z);
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public long readLong(boolean z) throws KryoException {
        if (!this.varIntsEnabled) {
            return readLong();
        }
        return super.readLong(z);
    }

    public UnsafeInput(byte[] bArr) {
        super(bArr);
        this.varIntsEnabled = false;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public final int[] readInts(int i) throws KryoException {
        int i2 = i << 2;
        int[] iArr = new int[i];
        readBytes(iArr, UnsafeUtil.intArrayBaseOffset, 0L, i2);
        return iArr;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public final long[] readLongs(int i) throws KryoException {
        int i2 = i << 3;
        long[] jArr = new long[i];
        readBytes(jArr, UnsafeUtil.longArrayBaseOffset, 0L, i2);
        return jArr;
    }

    public UnsafeInput(byte[] bArr, int i, int i2) {
        super(bArr, i, i2);
        this.varIntsEnabled = false;
    }

    public UnsafeInput(InputStream inputStream) {
        super(inputStream);
        this.varIntsEnabled = false;
    }

    public UnsafeInput(InputStream inputStream, int i) {
        super(inputStream, i);
        this.varIntsEnabled = false;
    }
}
