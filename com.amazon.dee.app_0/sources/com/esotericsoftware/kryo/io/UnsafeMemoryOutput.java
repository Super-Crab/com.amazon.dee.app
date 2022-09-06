package com.esotericsoftware.kryo.io;

import android.support.v4.media.session.PlaybackStateCompat;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.util.UnsafeUtil;
import com.esotericsoftware.kryo.util.Util;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.bouncycastle.asn1.cmc.BodyPartID;
/* loaded from: classes2.dex */
public final class UnsafeMemoryOutput extends ByteBufferOutput {
    private static final boolean isLittleEndian = ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN);
    private long bufaddress;

    public UnsafeMemoryOutput() {
        this.varIntsEnabled = false;
    }

    private void updateBufferAddress() {
        this.bufaddress = this.niobuffer.address();
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

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput
    public void setBuffer(ByteBuffer byteBuffer, int i) {
        super.setBuffer(byteBuffer, i);
        updateBufferAddress();
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public final void writeBoolean(boolean z) throws KryoException {
        this.niobuffer.position(this.position);
        super.writeBoolean(z);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public final void writeByte(int i) throws KryoException {
        this.niobuffer.position(this.position);
        super.writeByte(i);
    }

    public final void writeBytes(Object obj, long j, long j2) throws KryoException {
        writeBytes(obj, 0L, j, j2);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public final void writeChar(char c) throws KryoException {
        this.niobuffer.position(this.position);
        super.writeChar(c);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public final void writeChars(char[] cArr) throws KryoException {
        writeBytes(cArr, UnsafeUtil.charArrayBaseOffset, 0L, cArr.length << 1);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public final void writeDouble(double d) throws KryoException {
        require(8);
        UnsafeUtil.unsafe().putDouble(this.bufaddress + this.position, d);
        UnsafeUtil.unsafe().getDouble(this.bufaddress + this.position);
        this.position += 8;
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public final void writeDoubles(double[] dArr) throws KryoException {
        writeBytes(dArr, UnsafeUtil.doubleArrayBaseOffset, 0L, dArr.length << 3);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public final void writeFloat(float f) throws KryoException {
        require(4);
        UnsafeUtil.unsafe().putFloat(this.bufaddress + this.position, f);
        this.position += 4;
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public final void writeFloats(float[] fArr) throws KryoException {
        writeBytes(fArr, UnsafeUtil.floatArrayBaseOffset, 0L, fArr.length << 2);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public final void writeInt(int i) throws KryoException {
        require(4);
        UnsafeUtil.unsafe().putInt(this.bufaddress + this.position, i);
        this.position += 4;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public final void writeInts(int[] iArr, boolean z) throws KryoException {
        if (!this.varIntsEnabled) {
            writeBytes(iArr, UnsafeUtil.intArrayBaseOffset, 0L, iArr.length << 2);
        } else {
            super.writeInts(iArr, z);
        }
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public final void writeLong(long j) throws KryoException {
        require(8);
        UnsafeUtil.unsafe().putLong(this.bufaddress + this.position, j);
        this.position += 8;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public final void writeLongs(long[] jArr, boolean z) throws KryoException {
        if (!this.varIntsEnabled) {
            writeBytes(jArr, UnsafeUtil.longArrayBaseOffset, 0L, jArr.length << 3);
        } else {
            super.writeLongs(jArr, z);
        }
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public final void writeShort(int i) throws KryoException {
        require(2);
        UnsafeUtil.unsafe().putShort(this.bufaddress + this.position, (short) i);
        this.position += 2;
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public final void writeShorts(short[] sArr) throws KryoException {
        writeBytes(sArr, UnsafeUtil.shortArrayBaseOffset, 0L, sArr.length << 1);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public final int writeVarInt(int i, boolean z) throws KryoException {
        long j = i;
        if (!z) {
            j = (j >> 31) ^ (j << 1);
        }
        long j2 = j & 127;
        long j3 = j >>> 7;
        if (j3 == 0) {
            writeByte((byte) j2);
            return 1;
        }
        long j4 = j2 | 128 | ((j3 & 127) << 8);
        long j5 = j3 >>> 7;
        if (j5 == 0) {
            writeLittleEndianInt((int) j4);
            this.position -= 2;
            return 2;
        }
        long j6 = j4 | PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID | ((j5 & 127) << 16);
        long j7 = j5 >>> 7;
        if (j7 == 0) {
            writeLittleEndianInt((int) j6);
            this.position--;
            return 3;
        }
        long j8 = j6 | 8388608 | ((j7 & 127) << 24);
        long j9 = j7 >>> 7;
        if (j9 == 0) {
            writeLittleEndianInt((int) j8);
            this.position += 0;
            return 4;
        }
        writeLittleEndianLong((((j9 & 127) << 32) | (-2147483648L) | j8) & BodyPartID.bodyIdMax);
        this.position -= 3;
        return 5;
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public final int writeVarLong(long j, boolean z) throws KryoException {
        long j2;
        long j3 = !z ? (j << 1) ^ (j >> 63) : j;
        int i = (int) (j3 & 127);
        long j4 = j3 >>> 7;
        if (j4 == 0) {
            write(i);
            return 1;
        }
        int i2 = (int) (i | 128 | ((j4 & 127) << 8));
        long j5 = j4 >>> 7;
        if (j5 == 0) {
            writeLittleEndianInt(i2);
            this.position -= 2;
            return 2;
        }
        int i3 = (int) (i2 | 32768 | ((j5 & 127) << 16));
        long j6 = j5 >>> 7;
        if (j6 == 0) {
            writeLittleEndianInt(i3);
            this.position--;
            return 3;
        }
        int i4 = (int) (i3 | 8388608 | ((j6 & 127) << 24));
        long j7 = j6 >>> 7;
        if (j7 == 0) {
            writeLittleEndianInt(i4);
            this.position += 0;
            return 4;
        }
        long j8 = ((i4 | Integer.MIN_VALUE) & BodyPartID.bodyIdMax) | ((j7 & 127) << 32);
        long j9 = j7 >>> 7;
        if (j9 == 0) {
            writeLittleEndianLong(j8);
            this.position -= 3;
            return 5;
        }
        long j10 = j8 | 549755813888L | ((j9 & 127) << 40);
        long j11 = j9 >>> 7;
        if (j11 == 0) {
            writeLittleEndianLong(j10);
            this.position -= 2;
            return 6;
        }
        long j12 = j10 | 140737488355328L | ((j11 & 127) << 48);
        long j13 = j11 >>> 7;
        if (j13 == 0) {
            writeLittleEndianLong(j12);
            this.position--;
            return 7;
        }
        long j14 = j12 | 36028797018963968L | ((j13 & 127) << 56);
        if ((j13 >>> 7) == 0) {
            writeLittleEndianLong(j14);
            return 8;
        }
        writeLittleEndianLong(Long.MIN_VALUE | j14);
        write((byte) (j2 & 127));
        return 9;
    }

    private final void writeBytes(Object obj, long j, long j2, long j3) throws KryoException {
        long j4 = j3;
        int min = Math.min(this.capacity - this.position, (int) j3);
        long j5 = j2;
        while (true) {
            long j6 = min;
            UnsafeUtil.unsafe().copyMemory(obj, j + j5, (Object) null, this.bufaddress + this.position, j6);
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

    public UnsafeMemoryOutput(int i) {
        this(i, i);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public void writeByte(byte b) throws KryoException {
        this.niobuffer.position(this.position);
        super.writeByte(b);
    }

    public UnsafeMemoryOutput(int i, int i2) {
        super(i, i2);
        this.varIntsEnabled = false;
        updateBufferAddress();
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public final int writeInt(int i, boolean z) throws KryoException {
        if (!this.varIntsEnabled) {
            writeInt(i);
            return 4;
        }
        return writeVarInt(i, z);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public final int writeLong(long j, boolean z) throws KryoException {
        if (!this.varIntsEnabled) {
            writeLong(j);
            return 8;
        }
        return writeVarLong(j, z);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public final void writeInts(int[] iArr) throws KryoException {
        writeBytes(iArr, UnsafeUtil.intArrayBaseOffset, 0L, iArr.length << 2);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public final void writeLongs(long[] jArr) throws KryoException {
        writeBytes(jArr, UnsafeUtil.longArrayBaseOffset, 0L, jArr.length << 3);
    }

    public UnsafeMemoryOutput(OutputStream outputStream) {
        super(outputStream);
        this.varIntsEnabled = false;
        updateBufferAddress();
    }

    public UnsafeMemoryOutput(OutputStream outputStream, int i) {
        super(outputStream, i);
        this.varIntsEnabled = false;
        updateBufferAddress();
    }

    public UnsafeMemoryOutput(long j, int i) {
        super(j, i);
        this.varIntsEnabled = false;
        updateBufferAddress();
    }
}
