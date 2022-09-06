package com.esotericsoftware.kryo.io;

import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.util.UnsafeUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.bouncycastle.asn1.cmc.BodyPartID;
/* loaded from: classes2.dex */
public class ByteBufferOutput extends Output {
    protected static final ByteOrder nativeOrder = ByteOrder.nativeOrder();
    ByteOrder byteOrder;
    protected ByteBuffer niobuffer;
    protected boolean varIntsEnabled;

    public ByteBufferOutput() {
        this.varIntsEnabled = true;
        this.byteOrder = ByteOrder.BIG_ENDIAN;
    }

    private boolean isNativeOrder() {
        return this.byteOrder == nativeOrder;
    }

    private void writeAscii_slow(String str, int i) throws KryoException {
        ByteBuffer byteBuffer = this.niobuffer;
        int min = Math.min(i, this.capacity - this.position);
        ByteBuffer byteBuffer2 = byteBuffer;
        int i2 = 0;
        while (i2 < i) {
            byte[] bArr = new byte[i];
            int i3 = i2 + min;
            str.getBytes(i2, i3, bArr, 0);
            byteBuffer2.put(bArr, 0, min);
            this.position += min;
            min = Math.min(i - i3, this.capacity);
            if (require(min)) {
                byteBuffer2 = this.niobuffer;
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
                ByteBuffer byteBuffer = this.niobuffer;
                int i5 = this.position;
                this.position = i5 + 1;
                byteBuffer.put(i5, (byte) charAt);
            } else if (charAt > 2047) {
                ByteBuffer byteBuffer2 = this.niobuffer;
                int i6 = this.position;
                this.position = i6 + 1;
                byteBuffer2.put(i6, (byte) (((charAt >> '\f') & 15) | 224));
                require(2);
                ByteBuffer byteBuffer3 = this.niobuffer;
                int i7 = this.position;
                this.position = i7 + 1;
                byteBuffer3.put(i7, (byte) (((charAt >> 6) & 63) | 128));
                ByteBuffer byteBuffer4 = this.niobuffer;
                int i8 = this.position;
                this.position = i8 + 1;
                byteBuffer4.put(i8, (byte) ((charAt & Constants.DEFAULT_IMAGE_CHAR) | 128));
            } else {
                ByteBuffer byteBuffer5 = this.niobuffer;
                int i9 = this.position;
                this.position = i9 + 1;
                byteBuffer5.put(i9, (byte) (((charAt >> 6) & 31) | 192));
                require(1);
                ByteBuffer byteBuffer6 = this.niobuffer;
                int i10 = this.position;
                this.position = i10 + 1;
                byteBuffer6.put(i10, (byte) ((charAt & Constants.DEFAULT_IMAGE_CHAR) | 128));
            }
            i2++;
        }
    }

    private void writeUtf8Length(int i) {
        int i2 = i >>> 6;
        if (i2 == 0) {
            require(1);
            this.niobuffer.put((byte) (i | 128));
            this.position++;
            return;
        }
        int i3 = i >>> 13;
        if (i3 == 0) {
            require(2);
            this.niobuffer.put((byte) (i | 64 | 128));
            this.niobuffer.put((byte) i2);
            this.position += 2;
            return;
        }
        int i4 = i >>> 20;
        if (i4 == 0) {
            require(3);
            this.niobuffer.put((byte) (i | 64 | 128));
            this.niobuffer.put((byte) (i2 | 128));
            this.niobuffer.put((byte) i3);
            this.position += 3;
            return;
        }
        int i5 = i >>> 27;
        if (i5 == 0) {
            require(4);
            this.niobuffer.put((byte) (i | 64 | 128));
            this.niobuffer.put((byte) (i2 | 128));
            this.niobuffer.put((byte) (i3 | 128));
            this.niobuffer.put((byte) i4);
            this.position += 4;
            return;
        }
        require(5);
        this.niobuffer.put((byte) (i | 64 | 128));
        this.niobuffer.put((byte) (i2 | 128));
        this.niobuffer.put((byte) (i3 | 128));
        this.niobuffer.put((byte) (i4 | 128));
        this.niobuffer.put((byte) i5);
        this.position += 5;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void clear() {
        this.niobuffer.clear();
        this.position = 0;
        this.total = 0L;
    }

    @Override // com.esotericsoftware.kryo.io.Output, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
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

    @Override // com.esotericsoftware.kryo.io.Output, java.io.OutputStream, java.io.Flushable
    public void flush() throws KryoException {
        if (this.outputStream == null) {
            return;
        }
        try {
            byte[] bArr = new byte[this.position];
            this.niobuffer.position(0);
            this.niobuffer.get(bArr);
            this.niobuffer.position(0);
            this.outputStream.write(bArr, 0, this.position);
            this.total += this.position;
            this.position = 0;
        } catch (IOException e) {
            throw new KryoException(e);
        }
    }

    public ByteBuffer getByteBuffer() {
        this.niobuffer.position(this.position);
        return this.niobuffer;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public OutputStream getOutputStream() {
        return this.outputStream;
    }

    public boolean getVarIntsEnabled() {
        return this.varIntsEnabled;
    }

    public ByteOrder order() {
        return this.byteOrder;
    }

    public void release() {
        clear();
        UnsafeUtil.releaseBuffer(this.niobuffer);
        this.niobuffer = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.esotericsoftware.kryo.io.Output
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
                    ByteBuffer byteBuffer = this.niobuffer;
                    ByteBuffer allocateDirect = (byteBuffer == null || byteBuffer.isDirect()) ? ByteBuffer.allocateDirect(this.capacity) : ByteBuffer.allocate(this.capacity);
                    this.niobuffer.position(0);
                    allocateDirect.put(this.niobuffer);
                    allocateDirect.order(this.byteOrder);
                    this.niobuffer = allocateDirect;
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

    public void setBuffer(ByteBuffer byteBuffer) {
        setBuffer(byteBuffer, byteBuffer.capacity());
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
        this.position = 0;
        this.total = 0L;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void setPosition(int i) {
        this.position = i;
    }

    public void setVarIntsEnabled(boolean z) {
        this.varIntsEnabled = z;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public byte[] toBytes() {
        int i = this.position;
        byte[] bArr = new byte[i];
        this.niobuffer.position(i);
        this.niobuffer.position(0);
        this.niobuffer.get(bArr, 0, this.position);
        return bArr;
    }

    @Override // com.esotericsoftware.kryo.io.Output, java.io.OutputStream
    public void write(int i) throws KryoException {
        if (this.position == this.capacity) {
            require(1);
        }
        this.niobuffer.put((byte) i);
        this.position++;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeAscii(String str) throws KryoException {
        if (str == null) {
            writeByte(128);
            return;
        }
        int length = str.length();
        if (length == 0) {
            writeByte(129);
            return;
        }
        if (this.capacity - this.position < length) {
            writeAscii_slow(str, length);
        } else {
            byte[] bytes = str.getBytes();
            this.niobuffer.put(bytes, 0, bytes.length);
            this.position += length;
        }
        ByteBuffer byteBuffer = this.niobuffer;
        int i = this.position;
        byteBuffer.put(i - 1, (byte) (128 | byteBuffer.get(i - 1)));
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeBoolean(boolean z) throws KryoException {
        require(1);
        this.niobuffer.put(z ? (byte) 1 : (byte) 0);
        this.position++;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeByte(byte b) throws KryoException {
        if (this.position == this.capacity) {
            require(1);
        }
        this.niobuffer.put(b);
        this.position++;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeBytes(byte[] bArr) throws KryoException {
        if (bArr != null) {
            writeBytes(bArr, 0, bArr.length);
            return;
        }
        throw new IllegalArgumentException("bytes cannot be null.");
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeChar(char c) throws KryoException {
        require(2);
        this.niobuffer.putChar(c);
        this.position += 2;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeChars(char[] cArr) throws KryoException {
        if (this.capacity - this.position >= cArr.length * 2 && isNativeOrder()) {
            this.niobuffer.asCharBuffer().put(cArr);
            this.position = (cArr.length * 2) + this.position;
            return;
        }
        super.writeChars(cArr);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeDouble(double d) throws KryoException {
        require(8);
        this.niobuffer.putDouble(d);
        this.position += 8;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeDoubles(double[] dArr) throws KryoException {
        if (this.capacity - this.position >= dArr.length * 8 && isNativeOrder()) {
            this.niobuffer.asDoubleBuffer().put(dArr);
            this.position = (dArr.length * 8) + this.position;
            return;
        }
        super.writeDoubles(dArr);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeFloat(float f) throws KryoException {
        require(4);
        this.niobuffer.putFloat(f);
        this.position += 4;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeFloats(float[] fArr) throws KryoException {
        if (this.capacity - this.position >= fArr.length * 4 && isNativeOrder()) {
            this.niobuffer.asFloatBuffer().put(fArr);
            this.position = (fArr.length * 4) + this.position;
            return;
        }
        super.writeFloats(fArr);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeInt(int i) throws KryoException {
        require(4);
        this.niobuffer.putInt(i);
        this.position += 4;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeInts(int[] iArr) throws KryoException {
        if (this.capacity - this.position >= iArr.length * 4 && isNativeOrder()) {
            this.niobuffer.asIntBuffer().put(iArr);
            this.position = (iArr.length * 4) + this.position;
            return;
        }
        super.writeInts(iArr);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeLong(long j) throws KryoException {
        require(8);
        this.niobuffer.putLong(j);
        this.position += 8;
    }

    public int writeLongS(long j, boolean z) throws KryoException {
        long j2 = !z ? (j << 1) ^ (j >> 63) : j;
        long j3 = j2 >>> 7;
        if (j3 == 0) {
            require(1);
            this.niobuffer.put((byte) j2);
            this.position++;
            return 1;
        }
        long j4 = j2 >>> 14;
        if (j4 == 0) {
            require(2);
            this.niobuffer.put((byte) ((j2 & 127) | 128));
            this.niobuffer.put((byte) j3);
            this.position += 2;
            return 2;
        }
        long j5 = j2 >>> 21;
        if (j5 == 0) {
            require(3);
            this.niobuffer.put((byte) ((j2 & 127) | 128));
            this.niobuffer.put((byte) (j3 | 128));
            this.niobuffer.put((byte) j4);
            this.position += 3;
            return 3;
        }
        long j6 = j2 >>> 28;
        if (j6 == 0) {
            require(4);
            this.niobuffer.put((byte) ((j2 & 127) | 128));
            this.niobuffer.put((byte) (j3 | 128));
            this.niobuffer.put((byte) (j4 | 128));
            this.niobuffer.put((byte) j5);
            this.position += 4;
            return 4;
        }
        long j7 = j2 >>> 35;
        if (j7 == 0) {
            require(5);
            this.niobuffer.put((byte) ((j2 & 127) | 128));
            this.niobuffer.put((byte) (j3 | 128));
            this.niobuffer.put((byte) (j4 | 128));
            this.niobuffer.put((byte) (j5 | 128));
            this.niobuffer.put((byte) j6);
            this.position += 5;
            return 5;
        }
        long j8 = j2 >>> 42;
        if (j8 == 0) {
            require(6);
            this.niobuffer.put((byte) ((j2 & 127) | 128));
            this.niobuffer.put((byte) (j3 | 128));
            this.niobuffer.put((byte) (j4 | 128));
            this.niobuffer.put((byte) (j5 | 128));
            this.niobuffer.put((byte) (j6 | 128));
            this.niobuffer.put((byte) j7);
            this.position += 6;
            return 6;
        }
        long j9 = j2 >>> 49;
        if (j9 == 0) {
            require(7);
            this.niobuffer.put((byte) ((j2 & 127) | 128));
            this.niobuffer.put((byte) (j3 | 128));
            this.niobuffer.put((byte) (j4 | 128));
            this.niobuffer.put((byte) (j5 | 128));
            this.niobuffer.put((byte) (j6 | 128));
            this.niobuffer.put((byte) (j7 | 128));
            this.niobuffer.put((byte) j8);
            this.position += 7;
            return 7;
        }
        long j10 = j2 >>> 56;
        if (j10 == 0) {
            require(8);
            this.niobuffer.put((byte) ((j2 & 127) | 128));
            this.niobuffer.put((byte) (j3 | 128));
            this.niobuffer.put((byte) (j4 | 128));
            this.niobuffer.put((byte) (j5 | 128));
            this.niobuffer.put((byte) (j6 | 128));
            this.niobuffer.put((byte) (j7 | 128));
            this.niobuffer.put((byte) (j8 | 128));
            this.niobuffer.put((byte) j9);
            this.position += 8;
            return 8;
        }
        require(9);
        this.niobuffer.put((byte) ((j2 & 127) | 128));
        this.niobuffer.put((byte) (j3 | 128));
        this.niobuffer.put((byte) (j4 | 128));
        this.niobuffer.put((byte) (j5 | 128));
        this.niobuffer.put((byte) (j6 | 128));
        this.niobuffer.put((byte) (j7 | 128));
        this.niobuffer.put((byte) (j8 | 128));
        this.niobuffer.put((byte) (j9 | 128));
        this.niobuffer.put((byte) j10);
        this.position += 9;
        return 9;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeLongs(long[] jArr) throws KryoException {
        if (this.capacity - this.position >= jArr.length * 8 && isNativeOrder()) {
            this.niobuffer.asLongBuffer().put(jArr);
            this.position = (jArr.length * 8) + this.position;
            return;
        }
        super.writeLongs(jArr);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeShort(int i) throws KryoException {
        require(2);
        this.niobuffer.putShort((short) i);
        this.position += 2;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeShorts(short[] sArr) throws KryoException {
        if (this.capacity - this.position >= sArr.length * 2 && isNativeOrder()) {
            this.niobuffer.asShortBuffer().put(sArr);
            this.position = (sArr.length * 2) + this.position;
            return;
        }
        super.writeShorts(sArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0062  */
    @Override // com.esotericsoftware.kryo.io.Output
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void writeString(java.lang.String r8) throws com.esotericsoftware.kryo.KryoException {
        /*
            r7 = this;
            java.nio.ByteBuffer r0 = r7.niobuffer
            int r1 = r7.position
            r0.position(r1)
            r0 = 128(0x80, float:1.794E-43)
            if (r8 != 0) goto Lf
            r7.writeByte(r0)
            return
        Lf:
            int r1 = r8.length()
            if (r1 != 0) goto L1b
            r8 = 129(0x81, float:1.81E-43)
            r7.writeByte(r8)
            return
        L1b:
            r2 = 127(0x7f, float:1.78E-43)
            r3 = 0
            r4 = 1
            if (r1 <= r4) goto L34
            r5 = 64
            if (r1 >= r5) goto L34
            r5 = r3
        L26:
            if (r5 >= r1) goto L32
            char r6 = r8.charAt(r5)
            if (r6 <= r2) goto L2f
            goto L34
        L2f:
            int r5 = r5 + 1
            goto L26
        L32:
            r5 = r4
            goto L35
        L34:
            r5 = r3
        L35:
            if (r5 == 0) goto L62
            int r2 = r7.capacity
            int r5 = r7.position
            int r2 = r2 - r5
            if (r2 >= r1) goto L42
            r7.writeAscii_slow(r8, r1)
            goto L51
        L42:
            byte[] r8 = r8.getBytes()
            java.nio.ByteBuffer r2 = r7.niobuffer
            int r5 = r8.length
            r2.put(r8, r3, r5)
            int r8 = r7.position
            int r8 = r8 + r1
            r7.position = r8
        L51:
            java.nio.ByteBuffer r8 = r7.niobuffer
            int r1 = r7.position
            int r2 = r1 + (-1)
            int r1 = r1 - r4
            byte r1 = r8.get(r1)
            r0 = r0 | r1
            byte r0 = (byte) r0
            r8.put(r2, r0)
            goto L96
        L62:
            int r0 = r1 + 1
            r7.writeUtf8Length(r0)
            int r0 = r7.capacity
            int r4 = r7.position
            int r0 = r0 - r4
            if (r0 < r1) goto L8a
        L6e:
            if (r3 >= r1) goto L83
            char r0 = r8.charAt(r3)
            if (r0 <= r2) goto L77
            goto L83
        L77:
            java.nio.ByteBuffer r5 = r7.niobuffer
            int r6 = r4 + 1
            byte r0 = (byte) r0
            r5.put(r4, r0)
            int r3 = r3 + 1
            r4 = r6
            goto L6e
        L83:
            r7.position = r4
            java.nio.ByteBuffer r0 = r7.niobuffer
            r0.position(r4)
        L8a:
            if (r3 >= r1) goto L8f
            r7.writeString_slow(r8, r1, r3)
        L8f:
            java.nio.ByteBuffer r8 = r7.niobuffer
            int r0 = r7.position
            r8.position(r0)
        L96:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.kryo.io.ByteBufferOutput.writeString(java.lang.String):void");
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public int writeVarInt(int i, boolean z) throws KryoException {
        this.niobuffer.position(this.position);
        if (!z) {
            i = (i >> 31) ^ (i << 1);
        }
        int i2 = i & 127;
        int i3 = i >>> 7;
        if (i3 == 0) {
            writeByte(i2);
            return 1;
        }
        int i4 = i2 | 128 | ((i3 & 127) << 8);
        int i5 = i3 >>> 7;
        if (i5 == 0) {
            this.niobuffer.order(ByteOrder.LITTLE_ENDIAN);
            writeInt(i4);
            this.niobuffer.order(this.byteOrder);
            this.position -= 2;
            this.niobuffer.position(this.position);
            return 2;
        }
        int i6 = i4 | 32768 | ((i5 & 127) << 16);
        int i7 = i5 >>> 7;
        if (i7 == 0) {
            this.niobuffer.order(ByteOrder.LITTLE_ENDIAN);
            writeInt(i6);
            this.niobuffer.order(this.byteOrder);
            this.position--;
            this.niobuffer.position(this.position);
            return 3;
        }
        int i8 = i6 | 8388608 | ((i7 & 127) << 24);
        int i9 = i7 >>> 7;
        if (i9 == 0) {
            this.niobuffer.order(ByteOrder.LITTLE_ENDIAN);
            writeInt(i8);
            this.niobuffer.order(this.byteOrder);
            this.position += 0;
            return 4;
        }
        long j = (i9 << 32) | ((i8 | Integer.MIN_VALUE) & BodyPartID.bodyIdMax);
        this.niobuffer.order(ByteOrder.LITTLE_ENDIAN);
        writeLong(j);
        this.niobuffer.order(this.byteOrder);
        this.position -= 3;
        this.niobuffer.position(this.position);
        return 5;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public int writeVarLong(long j, boolean z) throws KryoException {
        long j2 = !z ? (j << 1) ^ (j >> 63) : j;
        int i = (int) (j2 & 127);
        long j3 = j2 >>> 7;
        if (j3 == 0) {
            writeByte(i);
            return 1;
        }
        int i2 = (int) (i | 128 | ((j3 & 127) << 8));
        long j4 = j3 >>> 7;
        if (j4 == 0) {
            this.niobuffer.order(ByteOrder.LITTLE_ENDIAN);
            writeInt(i2);
            this.niobuffer.order(this.byteOrder);
            this.position -= 2;
            this.niobuffer.position(this.position);
            return 2;
        }
        int i3 = (int) (i2 | 32768 | ((j4 & 127) << 16));
        long j5 = j4 >>> 7;
        if (j5 == 0) {
            this.niobuffer.order(ByteOrder.LITTLE_ENDIAN);
            writeInt(i3);
            this.niobuffer.order(this.byteOrder);
            this.position--;
            this.niobuffer.position(this.position);
            return 3;
        }
        int i4 = (int) (i3 | 8388608 | ((j5 & 127) << 24));
        long j6 = j5 >>> 7;
        if (j6 == 0) {
            this.niobuffer.order(ByteOrder.LITTLE_ENDIAN);
            writeInt(i4);
            this.niobuffer.order(this.byteOrder);
            this.position += 0;
            return 4;
        }
        long j7 = ((i4 | Integer.MIN_VALUE) & BodyPartID.bodyIdMax) | ((j6 & 127) << 32);
        long j8 = j6 >>> 7;
        if (j8 == 0) {
            this.niobuffer.order(ByteOrder.LITTLE_ENDIAN);
            writeLong(j7);
            this.niobuffer.order(this.byteOrder);
            this.position -= 3;
            this.niobuffer.position(this.position);
            return 5;
        }
        long j9 = j7 | 549755813888L | ((j8 & 127) << 40);
        long j10 = j8 >>> 7;
        if (j10 == 0) {
            this.niobuffer.order(ByteOrder.LITTLE_ENDIAN);
            writeLong(j9);
            this.niobuffer.order(this.byteOrder);
            this.position -= 2;
            this.niobuffer.position(this.position);
            return 6;
        }
        long j11 = j9 | 140737488355328L | ((j10 & 127) << 48);
        long j12 = j10 >>> 7;
        if (j12 == 0) {
            this.niobuffer.order(ByteOrder.LITTLE_ENDIAN);
            writeLong(j11);
            this.niobuffer.order(this.byteOrder);
            this.position--;
            this.niobuffer.position(this.position);
            return 7;
        }
        long j13 = ((127 & j12) << 56) | j11 | 36028797018963968L;
        long j14 = j12 >>> 7;
        if (j14 == 0) {
            this.niobuffer.order(ByteOrder.LITTLE_ENDIAN);
            writeLong(j13);
            this.niobuffer.order(this.byteOrder);
            return 8;
        }
        this.niobuffer.order(ByteOrder.LITTLE_ENDIAN);
        writeLong(j13 | Long.MIN_VALUE);
        this.niobuffer.order(this.byteOrder);
        write((byte) j14);
        return 9;
    }

    public void order(ByteOrder byteOrder) {
        this.byteOrder = byteOrder;
    }

    public void setBuffer(ByteBuffer byteBuffer, int i) {
        if (byteBuffer != null) {
            if (i >= -1) {
                this.niobuffer = byteBuffer;
                if (i == -1) {
                    i = Integer.MAX_VALUE;
                }
                this.maxCapacity = i;
                this.byteOrder = byteBuffer.order();
                this.capacity = byteBuffer.capacity();
                this.position = byteBuffer.position();
                this.total = 0L;
                this.outputStream = null;
                return;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("maxBufferSize cannot be < -1: ", i));
        }
        throw new IllegalArgumentException("buffer cannot be null.");
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeBytes(byte[] bArr, int i, int i2) throws KryoException {
        if (bArr != null) {
            int min = Math.min(this.capacity - this.position, i2);
            while (true) {
                this.niobuffer.put(bArr, i, min);
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

    public ByteBufferOutput(int i) {
        this(i, i);
    }

    @Override // com.esotericsoftware.kryo.io.Output, java.io.OutputStream
    public void write(byte[] bArr) throws KryoException {
        if (bArr != null) {
            writeBytes(bArr, 0, bArr.length);
            return;
        }
        throw new IllegalArgumentException("bytes cannot be null.");
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeByte(int i) throws KryoException {
        if (this.position == this.capacity) {
            require(1);
        }
        this.niobuffer.put((byte) i);
        this.position++;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public int writeDouble(double d, double d2, boolean z) throws KryoException {
        return writeLong((long) (d * d2), z);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public int writeFloat(float f, float f2, boolean z) throws KryoException {
        return writeInt((int) (f * f2), z);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public int writeInt(int i, boolean z) throws KryoException {
        if (!this.varIntsEnabled) {
            writeInt(i);
            return 4;
        }
        return writeVarInt(i, z);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public int writeLong(long j, boolean z) throws KryoException {
        if (!this.varIntsEnabled) {
            writeLong(j);
            return 8;
        }
        return writeVarLong(j, z);
    }

    public ByteBufferOutput(int i, int i2) {
        this.varIntsEnabled = true;
        this.byteOrder = ByteOrder.BIG_ENDIAN;
        if (i2 >= -1) {
            this.capacity = i;
            this.maxCapacity = i2 == -1 ? Integer.MAX_VALUE : i2;
            this.niobuffer = ByteBuffer.allocateDirect(i);
            this.niobuffer.order(this.byteOrder);
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("maxBufferSize cannot be < -1: ", i2));
    }

    @Override // com.esotericsoftware.kryo.io.Output, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws KryoException {
        writeBytes(bArr, i, i2);
    }

    public ByteBufferOutput(OutputStream outputStream) {
        this(4096, 4096);
        if (outputStream != null) {
            this.outputStream = outputStream;
            return;
        }
        throw new IllegalArgumentException("outputStream cannot be null.");
    }

    public ByteBufferOutput(OutputStream outputStream, int i) {
        this(i, i);
        if (outputStream != null) {
            this.outputStream = outputStream;
            return;
        }
        throw new IllegalArgumentException("outputStream cannot be null.");
    }

    public ByteBufferOutput(ByteBuffer byteBuffer) {
        this.varIntsEnabled = true;
        this.byteOrder = ByteOrder.BIG_ENDIAN;
        setBuffer(byteBuffer);
    }

    @Override // com.esotericsoftware.kryo.io.Output
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
            while (i < length) {
                char charAt = charSequence.charAt(i);
                if (charAt > 127) {
                    break;
                }
                this.niobuffer.put(i3, (byte) charAt);
                i++;
                i3++;
            }
            this.position = i3;
            this.niobuffer.position(i3);
        }
        if (i < length) {
            writeString_slow(charSequence, length, i);
        }
        this.niobuffer.position(this.position);
    }

    public ByteBufferOutput(ByteBuffer byteBuffer, int i) {
        this.varIntsEnabled = true;
        this.byteOrder = ByteOrder.BIG_ENDIAN;
        setBuffer(byteBuffer, i);
    }

    public ByteBufferOutput(long j, int i) {
        this.varIntsEnabled = true;
        this.byteOrder = ByteOrder.BIG_ENDIAN;
        this.niobuffer = UnsafeUtil.getDirectBufferAt(j, i);
        setBuffer(this.niobuffer, i);
    }
}
