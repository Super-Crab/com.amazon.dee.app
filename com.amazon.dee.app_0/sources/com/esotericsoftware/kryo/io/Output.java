package com.esotericsoftware.kryo.io;

import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.esotericsoftware.kryo.KryoException;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes2.dex */
public class Output extends OutputStream {
    protected byte[] buffer;
    protected int capacity;
    protected int maxCapacity;
    protected OutputStream outputStream;
    protected int position;
    protected long total;

    public Output() {
    }

    public static int intLength(int i, boolean z) {
        if (!z) {
            i = (i >> 31) ^ (i << 1);
        }
        if ((i >>> 7) == 0) {
            return 1;
        }
        if ((i >>> 14) == 0) {
            return 2;
        }
        if ((i >>> 21) == 0) {
            return 3;
        }
        return (i >>> 28) == 0 ? 4 : 5;
    }

    public static int longLength(long j, boolean z) {
        if (!z) {
            j = (j >> 63) ^ (j << 1);
        }
        if ((j >>> 7) == 0) {
            return 1;
        }
        if ((j >>> 14) == 0) {
            return 2;
        }
        if ((j >>> 21) == 0) {
            return 3;
        }
        if ((j >>> 28) == 0) {
            return 4;
        }
        if ((j >>> 35) == 0) {
            return 5;
        }
        if ((j >>> 42) == 0) {
            return 6;
        }
        if ((j >>> 49) == 0) {
            return 7;
        }
        return (j >>> 56) == 0 ? 8 : 9;
    }

    private void writeAscii_slow(String str, int i) throws KryoException {
        byte[] bArr = this.buffer;
        int min = Math.min(i, this.capacity - this.position);
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + min;
            str.getBytes(i2, i3, bArr, this.position);
            this.position += min;
            min = Math.min(i - i3, this.capacity);
            if (require(min)) {
                bArr = this.buffer;
            }
            i2 = i3;
        }
    }

    private void writeString_slow(CharSequence charSequence, int i, int i2) {
        while (i2 < i) {
            int i3 = this.position;
            int i4 = this.capacity;
            if (i3 == i4) {
                require(Math.min(i4, i - i2));
            }
            char charAt = charSequence.charAt(i2);
            if (charAt <= 127) {
                byte[] bArr = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                bArr[i5] = (byte) charAt;
            } else if (charAt > 2047) {
                byte[] bArr2 = this.buffer;
                int i6 = this.position;
                this.position = i6 + 1;
                bArr2[i6] = (byte) (((charAt >> '\f') & 15) | 224);
                require(2);
                byte[] bArr3 = this.buffer;
                int i7 = this.position;
                this.position = i7 + 1;
                bArr3[i7] = (byte) (((charAt >> 6) & 63) | 128);
                int i8 = this.position;
                this.position = i8 + 1;
                bArr3[i8] = (byte) ((charAt & Constants.DEFAULT_IMAGE_CHAR) | 128);
            } else {
                byte[] bArr4 = this.buffer;
                int i9 = this.position;
                this.position = i9 + 1;
                bArr4[i9] = (byte) (((charAt >> 6) & 31) | 192);
                require(1);
                byte[] bArr5 = this.buffer;
                int i10 = this.position;
                this.position = i10 + 1;
                bArr5[i10] = (byte) ((charAt & Constants.DEFAULT_IMAGE_CHAR) | 128);
            }
            i2++;
        }
    }

    private void writeUtf8Length(int i) {
        int i2 = i >>> 6;
        if (i2 == 0) {
            require(1);
            byte[] bArr = this.buffer;
            int i3 = this.position;
            this.position = i3 + 1;
            bArr[i3] = (byte) (i | 128);
            return;
        }
        int i4 = i >>> 13;
        if (i4 == 0) {
            require(2);
            byte[] bArr2 = this.buffer;
            int i5 = this.position;
            this.position = i5 + 1;
            bArr2[i5] = (byte) (i | 64 | 128);
            int i6 = this.position;
            this.position = i6 + 1;
            bArr2[i6] = (byte) i2;
            return;
        }
        int i7 = i >>> 20;
        if (i7 == 0) {
            require(3);
            byte[] bArr3 = this.buffer;
            int i8 = this.position;
            this.position = i8 + 1;
            bArr3[i8] = (byte) (i | 64 | 128);
            int i9 = this.position;
            this.position = i9 + 1;
            bArr3[i9] = (byte) (i2 | 128);
            int i10 = this.position;
            this.position = i10 + 1;
            bArr3[i10] = (byte) i4;
            return;
        }
        int i11 = i >>> 27;
        if (i11 == 0) {
            require(4);
            byte[] bArr4 = this.buffer;
            int i12 = this.position;
            this.position = i12 + 1;
            bArr4[i12] = (byte) (i | 64 | 128);
            int i13 = this.position;
            this.position = i13 + 1;
            bArr4[i13] = (byte) (i2 | 128);
            int i14 = this.position;
            this.position = i14 + 1;
            bArr4[i14] = (byte) (i4 | 128);
            int i15 = this.position;
            this.position = i15 + 1;
            bArr4[i15] = (byte) i7;
            return;
        }
        require(5);
        byte[] bArr5 = this.buffer;
        int i16 = this.position;
        this.position = i16 + 1;
        bArr5[i16] = (byte) (i | 64 | 128);
        int i17 = this.position;
        this.position = i17 + 1;
        bArr5[i17] = (byte) (i2 | 128);
        int i18 = this.position;
        this.position = i18 + 1;
        bArr5[i18] = (byte) (i4 | 128);
        int i19 = this.position;
        this.position = i19 + 1;
        bArr5[i19] = (byte) (i7 | 128);
        int i20 = this.position;
        this.position = i20 + 1;
        bArr5[i20] = (byte) i11;
    }

    public void clear() {
        this.position = 0;
        this.total = 0L;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws KryoException {
        flush();
        OutputStream outputStream = this.outputStream;
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws KryoException {
        OutputStream outputStream = this.outputStream;
        if (outputStream == null) {
            return;
        }
        try {
            outputStream.write(this.buffer, 0, this.position);
            this.total += this.position;
            this.position = 0;
        } catch (IOException e) {
            throw new KryoException(e);
        }
    }

    public byte[] getBuffer() {
        return this.buffer;
    }

    public OutputStream getOutputStream() {
        return this.outputStream;
    }

    public final int position() {
        return this.position;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean require(int i) throws KryoException {
        if (this.capacity - this.position >= i) {
            return false;
        }
        if (i <= this.maxCapacity) {
            flush();
            while (true) {
                int i2 = this.capacity;
                if (i2 - this.position >= i) {
                    return true;
                }
                if (i2 != this.maxCapacity) {
                    if (i2 == 0) {
                        this.capacity = 1;
                    }
                    this.capacity = Math.min(this.capacity * 2, this.maxCapacity);
                    if (this.capacity < 0) {
                        this.capacity = this.maxCapacity;
                    }
                    byte[] bArr = new byte[this.capacity];
                    System.arraycopy(this.buffer, 0, bArr, 0, this.position);
                    this.buffer = bArr;
                } else {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Buffer overflow. Available: ");
                    outline107.append(this.capacity - this.position);
                    outline107.append(", required: ");
                    outline107.append(i);
                    throw new KryoException(outline107.toString());
                }
            }
        } else {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Buffer overflow. Max capacity: ");
            outline1072.append(this.maxCapacity);
            outline1072.append(", required: ");
            outline1072.append(i);
            throw new KryoException(outline1072.toString());
        }
    }

    public void setBuffer(byte[] bArr) {
        setBuffer(bArr, bArr.length);
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
        this.position = 0;
        this.total = 0L;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public byte[] toBytes() {
        int i = this.position;
        byte[] bArr = new byte[i];
        System.arraycopy(this.buffer, 0, bArr, 0, i);
        return bArr;
    }

    public final long total() {
        return this.total + this.position;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws KryoException {
        if (this.position == this.capacity) {
            require(1);
        }
        byte[] bArr = this.buffer;
        int i2 = this.position;
        this.position = i2 + 1;
        bArr[i2] = (byte) i;
    }

    public void writeAscii(String str) throws KryoException {
        if (str == null) {
            writeByte(128);
            return;
        }
        int length = str.length();
        if (length == 0) {
            writeByte(129);
        } else if (length != 1) {
            int i = this.capacity;
            int i2 = this.position;
            if (i - i2 < length) {
                writeAscii_slow(str, length);
            } else {
                str.getBytes(0, length, this.buffer, i2);
                this.position += length;
            }
            byte[] bArr = this.buffer;
            int i3 = this.position - 1;
            bArr[i3] = (byte) (128 | bArr[i3]);
        } else {
            writeByte(130);
            writeByte(str.charAt(0));
        }
    }

    public void writeBoolean(boolean z) throws KryoException {
        if (this.position == this.capacity) {
            require(1);
        }
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = z ? (byte) 1 : (byte) 0;
    }

    public void writeByte(byte b) throws KryoException {
        if (this.position == this.capacity) {
            require(1);
        }
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = b;
    }

    public void writeBytes(byte[] bArr) throws KryoException {
        if (bArr != null) {
            writeBytes(bArr, 0, bArr.length);
            return;
        }
        throw new IllegalArgumentException("bytes cannot be null.");
    }

    public void writeChar(char c) throws KryoException {
        require(2);
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = (byte) (c >>> '\b');
        int i2 = this.position;
        this.position = i2 + 1;
        bArr[i2] = (byte) c;
    }

    public void writeChars(char[] cArr) throws KryoException {
        for (char c : cArr) {
            writeChar(c);
        }
    }

    public void writeDouble(double d) throws KryoException {
        writeLong(Double.doubleToLongBits(d));
    }

    public void writeDoubles(double[] dArr) throws KryoException {
        for (double d : dArr) {
            writeDouble(d);
        }
    }

    public void writeFloat(float f) throws KryoException {
        writeInt(Float.floatToIntBits(f));
    }

    public void writeFloats(float[] fArr) throws KryoException {
        for (float f : fArr) {
            writeFloat(f);
        }
    }

    public void writeInt(int i) throws KryoException {
        require(4);
        byte[] bArr = this.buffer;
        int i2 = this.position;
        this.position = i2 + 1;
        bArr[i2] = (byte) (i >> 24);
        int i3 = this.position;
        this.position = i3 + 1;
        bArr[i3] = (byte) (i >> 16);
        int i4 = this.position;
        this.position = i4 + 1;
        bArr[i4] = (byte) (i >> 8);
        int i5 = this.position;
        this.position = i5 + 1;
        bArr[i5] = (byte) i;
    }

    public void writeInts(int[] iArr, boolean z) throws KryoException {
        for (int i : iArr) {
            writeInt(i, z);
        }
    }

    public void writeLong(long j) throws KryoException {
        require(8);
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = (byte) (j >>> 56);
        int i2 = this.position;
        this.position = i2 + 1;
        bArr[i2] = (byte) (j >>> 48);
        int i3 = this.position;
        this.position = i3 + 1;
        bArr[i3] = (byte) (j >>> 40);
        int i4 = this.position;
        this.position = i4 + 1;
        bArr[i4] = (byte) (j >>> 32);
        int i5 = this.position;
        this.position = i5 + 1;
        bArr[i5] = (byte) (j >>> 24);
        int i6 = this.position;
        this.position = i6 + 1;
        bArr[i6] = (byte) (j >>> 16);
        int i7 = this.position;
        this.position = i7 + 1;
        bArr[i7] = (byte) (j >>> 8);
        int i8 = this.position;
        this.position = i8 + 1;
        bArr[i8] = (byte) j;
    }

    public void writeLongs(long[] jArr, boolean z) throws KryoException {
        for (long j : jArr) {
            writeLong(j, z);
        }
    }

    public void writeShort(int i) throws KryoException {
        require(2);
        byte[] bArr = this.buffer;
        int i2 = this.position;
        this.position = i2 + 1;
        bArr[i2] = (byte) (i >>> 8);
        int i3 = this.position;
        this.position = i3 + 1;
        bArr[i3] = (byte) i;
    }

    public void writeShorts(short[] sArr) throws KryoException {
        for (short s : sArr) {
            writeShort(s);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void writeString(java.lang.String r8) throws com.esotericsoftware.kryo.KryoException {
        /*
            r7 = this;
            r0 = 128(0x80, float:1.794E-43)
            if (r8 != 0) goto L8
            r7.writeByte(r0)
            return
        L8:
            int r1 = r8.length()
            if (r1 != 0) goto L14
            r8 = 129(0x81, float:1.81E-43)
            r7.writeByte(r8)
            return
        L14:
            r2 = 127(0x7f, float:1.78E-43)
            r3 = 0
            r4 = 1
            if (r1 <= r4) goto L2d
            r5 = 64
            if (r1 >= r5) goto L2d
            r5 = r3
        L1f:
            if (r5 >= r1) goto L2b
            char r6 = r8.charAt(r5)
            if (r6 <= r2) goto L28
            goto L2d
        L28:
            int r5 = r5 + 1
            goto L1f
        L2b:
            r5 = r4
            goto L2e
        L2d:
            r5 = r3
        L2e:
            if (r5 == 0) goto L51
            int r2 = r7.capacity
            int r5 = r7.position
            int r2 = r2 - r5
            if (r2 >= r1) goto L3b
            r7.writeAscii_slow(r8, r1)
            goto L45
        L3b:
            byte[] r2 = r7.buffer
            r8.getBytes(r3, r1, r2, r5)
            int r8 = r7.position
            int r8 = r8 + r1
            r7.position = r8
        L45:
            byte[] r8 = r7.buffer
            int r1 = r7.position
            int r1 = r1 - r4
            r2 = r8[r1]
            r0 = r0 | r2
            byte r0 = (byte) r0
            r8[r1] = r0
            goto L78
        L51:
            int r0 = r1 + 1
            r7.writeUtf8Length(r0)
            int r0 = r7.capacity
            int r4 = r7.position
            int r0 = r0 - r4
            if (r0 < r1) goto L73
            byte[] r0 = r7.buffer
        L5f:
            if (r3 >= r1) goto L71
            char r5 = r8.charAt(r3)
            if (r5 <= r2) goto L68
            goto L71
        L68:
            int r6 = r4 + 1
            byte r5 = (byte) r5
            r0[r4] = r5
            int r3 = r3 + 1
            r4 = r6
            goto L5f
        L71:
            r7.position = r4
        L73:
            if (r3 >= r1) goto L78
            r7.writeString_slow(r8, r1, r3)
        L78:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.kryo.io.Output.writeString(java.lang.String):void");
    }

    public int writeVarInt(int i, boolean z) throws KryoException {
        if (!z) {
            i = (i >> 31) ^ (i << 1);
        }
        int i2 = i >>> 7;
        if (i2 == 0) {
            require(1);
            byte[] bArr = this.buffer;
            int i3 = this.position;
            this.position = i3 + 1;
            bArr[i3] = (byte) i;
            return 1;
        }
        int i4 = i >>> 14;
        if (i4 == 0) {
            require(2);
            byte[] bArr2 = this.buffer;
            int i5 = this.position;
            this.position = i5 + 1;
            bArr2[i5] = (byte) ((i & 127) | 128);
            int i6 = this.position;
            this.position = i6 + 1;
            bArr2[i6] = (byte) i2;
            return 2;
        }
        int i7 = i >>> 21;
        if (i7 == 0) {
            require(3);
            byte[] bArr3 = this.buffer;
            int i8 = this.position;
            this.position = i8 + 1;
            bArr3[i8] = (byte) ((i & 127) | 128);
            int i9 = this.position;
            this.position = i9 + 1;
            bArr3[i9] = (byte) (i2 | 128);
            int i10 = this.position;
            this.position = i10 + 1;
            bArr3[i10] = (byte) i4;
            return 3;
        }
        int i11 = i >>> 28;
        if (i11 == 0) {
            require(4);
            byte[] bArr4 = this.buffer;
            int i12 = this.position;
            this.position = i12 + 1;
            bArr4[i12] = (byte) ((i & 127) | 128);
            int i13 = this.position;
            this.position = i13 + 1;
            bArr4[i13] = (byte) (i2 | 128);
            int i14 = this.position;
            this.position = i14 + 1;
            bArr4[i14] = (byte) (i4 | 128);
            int i15 = this.position;
            this.position = i15 + 1;
            bArr4[i15] = (byte) i7;
            return 4;
        }
        require(5);
        byte[] bArr5 = this.buffer;
        int i16 = this.position;
        this.position = i16 + 1;
        bArr5[i16] = (byte) ((i & 127) | 128);
        int i17 = this.position;
        this.position = i17 + 1;
        bArr5[i17] = (byte) (i2 | 128);
        int i18 = this.position;
        this.position = i18 + 1;
        bArr5[i18] = (byte) (i4 | 128);
        int i19 = this.position;
        this.position = i19 + 1;
        bArr5[i19] = (byte) (i7 | 128);
        int i20 = this.position;
        this.position = i20 + 1;
        bArr5[i20] = (byte) i11;
        return 5;
    }

    public int writeVarLong(long j, boolean z) throws KryoException {
        long j2 = !z ? (j << 1) ^ (j >> 63) : j;
        long j3 = j2 >>> 7;
        if (j3 == 0) {
            require(1);
            byte[] bArr = this.buffer;
            int i = this.position;
            this.position = i + 1;
            bArr[i] = (byte) j2;
            return 1;
        }
        long j4 = j2 >>> 14;
        if (j4 == 0) {
            require(2);
            byte[] bArr2 = this.buffer;
            int i2 = this.position;
            this.position = i2 + 1;
            bArr2[i2] = (byte) ((j2 & 127) | 128);
            int i3 = this.position;
            this.position = i3 + 1;
            bArr2[i3] = (byte) j3;
            return 2;
        }
        long j5 = j2 >>> 21;
        if (j5 == 0) {
            require(3);
            byte[] bArr3 = this.buffer;
            int i4 = this.position;
            this.position = i4 + 1;
            bArr3[i4] = (byte) ((j2 & 127) | 128);
            int i5 = this.position;
            this.position = i5 + 1;
            bArr3[i5] = (byte) (j3 | 128);
            int i6 = this.position;
            this.position = i6 + 1;
            bArr3[i6] = (byte) j4;
            return 3;
        }
        long j6 = j2 >>> 28;
        if (j6 == 0) {
            require(4);
            byte[] bArr4 = this.buffer;
            int i7 = this.position;
            this.position = i7 + 1;
            bArr4[i7] = (byte) ((j2 & 127) | 128);
            int i8 = this.position;
            this.position = i8 + 1;
            bArr4[i8] = (byte) (j3 | 128);
            int i9 = this.position;
            this.position = i9 + 1;
            bArr4[i9] = (byte) (j4 | 128);
            int i10 = this.position;
            this.position = i10 + 1;
            bArr4[i10] = (byte) j5;
            return 4;
        }
        long j7 = j2 >>> 35;
        if (j7 == 0) {
            require(5);
            byte[] bArr5 = this.buffer;
            int i11 = this.position;
            this.position = i11 + 1;
            bArr5[i11] = (byte) ((j2 & 127) | 128);
            int i12 = this.position;
            this.position = i12 + 1;
            bArr5[i12] = (byte) (j3 | 128);
            int i13 = this.position;
            this.position = i13 + 1;
            bArr5[i13] = (byte) (j4 | 128);
            int i14 = this.position;
            this.position = i14 + 1;
            bArr5[i14] = (byte) (j5 | 128);
            int i15 = this.position;
            this.position = i15 + 1;
            bArr5[i15] = (byte) j6;
            return 5;
        }
        long j8 = j2 >>> 42;
        if (j8 == 0) {
            require(6);
            byte[] bArr6 = this.buffer;
            int i16 = this.position;
            this.position = i16 + 1;
            bArr6[i16] = (byte) ((j2 & 127) | 128);
            int i17 = this.position;
            this.position = i17 + 1;
            bArr6[i17] = (byte) (j3 | 128);
            int i18 = this.position;
            this.position = i18 + 1;
            bArr6[i18] = (byte) (j4 | 128);
            int i19 = this.position;
            this.position = i19 + 1;
            bArr6[i19] = (byte) (j5 | 128);
            int i20 = this.position;
            this.position = i20 + 1;
            bArr6[i20] = (byte) (j6 | 128);
            int i21 = this.position;
            this.position = i21 + 1;
            bArr6[i21] = (byte) j7;
            return 6;
        }
        long j9 = j2 >>> 49;
        if (j9 == 0) {
            require(7);
            byte[] bArr7 = this.buffer;
            int i22 = this.position;
            this.position = i22 + 1;
            bArr7[i22] = (byte) ((j2 & 127) | 128);
            int i23 = this.position;
            this.position = i23 + 1;
            bArr7[i23] = (byte) (j3 | 128);
            int i24 = this.position;
            this.position = i24 + 1;
            bArr7[i24] = (byte) (j4 | 128);
            int i25 = this.position;
            this.position = i25 + 1;
            bArr7[i25] = (byte) (j5 | 128);
            int i26 = this.position;
            this.position = i26 + 1;
            bArr7[i26] = (byte) (j6 | 128);
            int i27 = this.position;
            this.position = i27 + 1;
            bArr7[i27] = (byte) (j7 | 128);
            int i28 = this.position;
            this.position = i28 + 1;
            bArr7[i28] = (byte) j8;
            return 7;
        }
        long j10 = j2 >>> 56;
        if (j10 == 0) {
            require(8);
            byte[] bArr8 = this.buffer;
            int i29 = this.position;
            this.position = i29 + 1;
            bArr8[i29] = (byte) ((j2 & 127) | 128);
            int i30 = this.position;
            this.position = i30 + 1;
            bArr8[i30] = (byte) (j3 | 128);
            int i31 = this.position;
            this.position = i31 + 1;
            bArr8[i31] = (byte) (j4 | 128);
            int i32 = this.position;
            this.position = i32 + 1;
            bArr8[i32] = (byte) (j5 | 128);
            int i33 = this.position;
            this.position = i33 + 1;
            bArr8[i33] = (byte) (j6 | 128);
            int i34 = this.position;
            this.position = i34 + 1;
            bArr8[i34] = (byte) (j7 | 128);
            int i35 = this.position;
            this.position = i35 + 1;
            bArr8[i35] = (byte) (j8 | 128);
            int i36 = this.position;
            this.position = i36 + 1;
            bArr8[i36] = (byte) j9;
            return 8;
        }
        require(9);
        byte[] bArr9 = this.buffer;
        int i37 = this.position;
        this.position = i37 + 1;
        bArr9[i37] = (byte) ((j2 & 127) | 128);
        int i38 = this.position;
        this.position = i38 + 1;
        bArr9[i38] = (byte) (j3 | 128);
        int i39 = this.position;
        this.position = i39 + 1;
        bArr9[i39] = (byte) (j4 | 128);
        int i40 = this.position;
        this.position = i40 + 1;
        bArr9[i40] = (byte) (j5 | 128);
        int i41 = this.position;
        this.position = i41 + 1;
        bArr9[i41] = (byte) (j6 | 128);
        int i42 = this.position;
        this.position = i42 + 1;
        bArr9[i42] = (byte) (j7 | 128);
        int i43 = this.position;
        this.position = i43 + 1;
        bArr9[i43] = (byte) (j8 | 128);
        int i44 = this.position;
        this.position = i44 + 1;
        bArr9[i44] = (byte) (j9 | 128);
        int i45 = this.position;
        this.position = i45 + 1;
        bArr9[i45] = (byte) j10;
        return 9;
    }

    public Output(int i) {
        this(i, i);
    }

    public void setBuffer(byte[] bArr, int i) {
        if (bArr != null) {
            if (i >= -1) {
                this.buffer = bArr;
                if (i == -1) {
                    i = Integer.MAX_VALUE;
                }
                this.maxCapacity = i;
                this.capacity = bArr.length;
                this.position = 0;
                this.total = 0L;
                this.outputStream = null;
                return;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("maxBufferSize cannot be < -1: ", i));
        }
        throw new IllegalArgumentException("buffer cannot be null.");
    }

    public int writeDouble(double d, double d2, boolean z) throws KryoException {
        return writeLong((long) (d * d2), z);
    }

    public int writeFloat(float f, float f2, boolean z) throws KryoException {
        return writeInt((int) (f * f2), z);
    }

    public Output(int i, int i2) {
        if (i2 >= -1) {
            this.capacity = i;
            this.maxCapacity = i2 == -1 ? Integer.MAX_VALUE : i2;
            this.buffer = new byte[i];
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("maxBufferSize cannot be < -1: ", i2));
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws KryoException {
        if (bArr != null) {
            writeBytes(bArr, 0, bArr.length);
            return;
        }
        throw new IllegalArgumentException("bytes cannot be null.");
    }

    public void writeByte(int i) throws KryoException {
        if (this.position == this.capacity) {
            require(1);
        }
        byte[] bArr = this.buffer;
        int i2 = this.position;
        this.position = i2 + 1;
        bArr[i2] = (byte) i;
    }

    public void writeBytes(byte[] bArr, int i, int i2) throws KryoException {
        if (bArr != null) {
            int min = Math.min(this.capacity - this.position, i2);
            while (true) {
                System.arraycopy(bArr, i, this.buffer, this.position, min);
                this.position += min;
                i2 -= min;
                if (i2 == 0) {
                    return;
                }
                i += min;
                min = Math.min(this.capacity, i2);
                require(min);
            }
        } else {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
    }

    public void writeInts(int[] iArr) throws KryoException {
        for (int i : iArr) {
            writeInt(i);
        }
    }

    public void writeLongs(long[] jArr) throws KryoException {
        for (long j : jArr) {
            writeLong(j);
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws KryoException {
        writeBytes(bArr, i, i2);
    }

    public int writeInt(int i, boolean z) throws KryoException {
        return writeVarInt(i, z);
    }

    public Output(byte[] bArr) {
        this(bArr, bArr.length);
    }

    public Output(byte[] bArr, int i) {
        if (bArr != null) {
            setBuffer(bArr, i);
            return;
        }
        throw new IllegalArgumentException("buffer cannot be null.");
    }

    public int writeLong(long j, boolean z) throws KryoException {
        return writeVarLong(j, z);
    }

    public Output(OutputStream outputStream) {
        this(4096, 4096);
        if (outputStream != null) {
            this.outputStream = outputStream;
            return;
        }
        throw new IllegalArgumentException("outputStream cannot be null.");
    }

    public Output(OutputStream outputStream, int i) {
        this(i, i);
        if (outputStream != null) {
            this.outputStream = outputStream;
            return;
        }
        throw new IllegalArgumentException("outputStream cannot be null.");
    }

    public void writeString(CharSequence charSequence) throws KryoException {
        if (charSequence == null) {
            writeByte(128);
            return;
        }
        int length = charSequence.length();
        if (length == 0) {
            writeByte(129);
            return;
        }
        writeUtf8Length(length + 1);
        int i = 0;
        int i2 = this.capacity;
        int i3 = this.position;
        if (i2 - i3 >= length) {
            byte[] bArr = this.buffer;
            while (i < length) {
                char charAt = charSequence.charAt(i);
                if (charAt > 127) {
                    break;
                }
                bArr[i3] = (byte) charAt;
                i++;
                i3++;
            }
            this.position = i3;
        }
        if (i >= length) {
            return;
        }
        writeString_slow(charSequence, length, i);
    }
}
