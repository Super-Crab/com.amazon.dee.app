package com.esotericsoftware.kryo.io;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.util.UnsafeUtil;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import okio.Utf8;
/* loaded from: classes2.dex */
public class ByteBufferInput extends Input {
    protected static final ByteOrder nativeOrder = ByteOrder.nativeOrder();
    ByteOrder byteOrder;
    protected ByteBuffer niobuffer;
    protected boolean varIntsEnabled;

    public ByteBufferInput() {
        this.varIntsEnabled = true;
        this.byteOrder = ByteOrder.BIG_ENDIAN;
    }

    private boolean isNativeOrder() {
        return this.byteOrder == nativeOrder;
    }

    private int optional(int i) throws KryoException {
        int i2 = this.limit - this.position;
        if (i2 >= i) {
            return i;
        }
        int min = Math.min(i, this.capacity);
        ByteBuffer byteBuffer = this.niobuffer;
        int i3 = this.limit;
        int fill = fill(byteBuffer, i3, this.capacity - i3);
        if (fill == -1) {
            if (i2 != 0) {
                return Math.min(i2, min);
            }
            return -1;
        }
        int i4 = i2 + fill;
        if (i4 >= min) {
            this.limit += fill;
            return min;
        }
        this.niobuffer.compact();
        this.total += this.position;
        this.position = 0;
        do {
            int fill2 = fill(this.niobuffer, i4, this.capacity - i4);
            if (fill2 == -1) {
                break;
            }
            i4 += fill2;
        } while (i4 < min);
        this.limit = i4;
        this.niobuffer.position(this.position);
        if (i4 != 0) {
            return Math.min(i4, min);
        }
        return -1;
    }

    private String readAscii() {
        int i = this.position;
        int i2 = i - 1;
        int i3 = this.limit;
        while (i != i3) {
            i++;
            if ((this.niobuffer.get() & 128) != 0) {
                ByteBuffer byteBuffer = this.niobuffer;
                int i4 = i - 1;
                byteBuffer.put(i4, (byte) (byteBuffer.get(i4) & Byte.MAX_VALUE));
                int i5 = i - i2;
                byte[] bArr = new byte[i5];
                this.niobuffer.position(i2);
                this.niobuffer.get(bArr);
                String str = new String(bArr, 0, 0, i5);
                ByteBuffer byteBuffer2 = this.niobuffer;
                byteBuffer2.put(i4, (byte) (byteBuffer2.get(i4) | 128));
                this.position = i;
                this.niobuffer.position(this.position);
                return str;
            }
        }
        return readAscii_slow();
    }

    private String readAscii_slow() {
        this.position--;
        int i = this.limit - this.position;
        if (i > this.chars.length) {
            this.chars = new char[i * 2];
        }
        char[] cArr = this.chars;
        int i2 = this.position;
        int i3 = this.limit;
        int i4 = 0;
        while (i2 < i3) {
            cArr[i4] = (char) this.niobuffer.get(i2);
            i2++;
            i4++;
        }
        this.position = this.limit;
        while (true) {
            require(1);
            this.position++;
            byte b = this.niobuffer.get();
            if (i == cArr.length) {
                char[] cArr2 = new char[i * 2];
                System.arraycopy(cArr, 0, cArr2, 0, i);
                this.chars = cArr2;
                cArr = cArr2;
            }
            if ((b & 128) == 128) {
                cArr[i] = (char) (b & Byte.MAX_VALUE);
                return new String(cArr, 0, i + 1);
            }
            cArr[i] = (char) b;
            i++;
        }
    }

    private int readInt_slow(boolean z) {
        this.position++;
        byte b = this.niobuffer.get();
        int i = b & Byte.MAX_VALUE;
        if ((b & 128) != 0) {
            require(1);
            this.position++;
            byte b2 = this.niobuffer.get();
            i |= (b2 & Byte.MAX_VALUE) << 7;
            if ((b2 & 128) != 0) {
                require(1);
                this.position++;
                byte b3 = this.niobuffer.get();
                i |= (b3 & Byte.MAX_VALUE) << 14;
                if ((b3 & 128) != 0) {
                    require(1);
                    this.position++;
                    byte b4 = this.niobuffer.get();
                    i |= (b4 & Byte.MAX_VALUE) << 21;
                    if ((b4 & 128) != 0) {
                        require(1);
                        this.position++;
                        i |= (this.niobuffer.get() & Byte.MAX_VALUE) << 28;
                    }
                }
            }
        }
        return z ? i : (i >>> 1) ^ (-(i & 1));
    }

    private long readLong_slow(boolean z) {
        byte b;
        byte b2;
        byte b3;
        byte b4;
        byte b5;
        byte b6;
        byte b7;
        this.position++;
        byte b8 = this.niobuffer.get();
        long j = b8 & Byte.MAX_VALUE;
        if ((b8 & 128) != 0) {
            require(1);
            this.position++;
            j |= (b & Byte.MAX_VALUE) << 7;
            if ((this.niobuffer.get() & 128) != 0) {
                require(1);
                this.position++;
                j |= (b2 & Byte.MAX_VALUE) << 14;
                if ((this.niobuffer.get() & 128) != 0) {
                    require(1);
                    this.position++;
                    j |= (b3 & Byte.MAX_VALUE) << 21;
                    if ((this.niobuffer.get() & 128) != 0) {
                        require(1);
                        this.position++;
                        j |= (b4 & Byte.MAX_VALUE) << 28;
                        if ((this.niobuffer.get() & 128) != 0) {
                            require(1);
                            this.position++;
                            j |= (b5 & Byte.MAX_VALUE) << 35;
                            if ((this.niobuffer.get() & 128) != 0) {
                                require(1);
                                this.position++;
                                j |= (b6 & Byte.MAX_VALUE) << 42;
                                if ((this.niobuffer.get() & 128) != 0) {
                                    require(1);
                                    this.position++;
                                    j |= (b7 & Byte.MAX_VALUE) << 49;
                                    if ((this.niobuffer.get() & 128) != 0) {
                                        require(1);
                                        this.position++;
                                        j |= this.niobuffer.get() << 56;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!z) {
            return (-(j & 1)) ^ (j >>> 1);
        }
        return j;
    }

    private void readUtf8(int i) {
        char[] cArr = this.chars;
        int min = Math.min(require(1), i);
        int i2 = this.position;
        int i3 = 0;
        while (true) {
            if (i3 >= min) {
                break;
            }
            i2++;
            byte b = this.niobuffer.get();
            if (b < 0) {
                i2--;
                break;
            } else {
                cArr[i3] = (char) b;
                i3++;
            }
        }
        this.position = i2;
        if (i3 < i) {
            this.niobuffer.position(i2);
            readUtf8_slow(i, i3);
        }
    }

    private int readUtf8Length(int i) {
        int i2 = i & 63;
        if ((i & 64) != 0) {
            this.position++;
            byte b = this.niobuffer.get();
            int i3 = i2 | ((b & Byte.MAX_VALUE) << 6);
            if ((b & 128) == 0) {
                return i3;
            }
            this.position++;
            byte b2 = this.niobuffer.get();
            int i4 = i3 | ((b2 & Byte.MAX_VALUE) << 13);
            if ((b2 & 128) == 0) {
                return i4;
            }
            this.position++;
            byte b3 = this.niobuffer.get();
            int i5 = i4 | ((b3 & Byte.MAX_VALUE) << 20);
            if ((b3 & 128) == 0) {
                return i5;
            }
            this.position++;
            return i5 | ((this.niobuffer.get() & Byte.MAX_VALUE) << 27);
        }
        return i2;
    }

    private int readUtf8Length_slow(int i) {
        int i2 = i & 63;
        if ((i & 64) != 0) {
            require(1);
            this.position++;
            byte b = this.niobuffer.get();
            int i3 = i2 | ((b & Byte.MAX_VALUE) << 6);
            if ((b & 128) == 0) {
                return i3;
            }
            require(1);
            this.position++;
            byte b2 = this.niobuffer.get();
            int i4 = i3 | ((b2 & Byte.MAX_VALUE) << 13);
            if ((b2 & 128) == 0) {
                return i4;
            }
            require(1);
            this.position++;
            byte b3 = this.niobuffer.get();
            int i5 = i4 | ((b3 & Byte.MAX_VALUE) << 20);
            if ((b3 & 128) == 0) {
                return i5;
            }
            require(1);
            this.position++;
            return i5 | ((this.niobuffer.get() & Byte.MAX_VALUE) << 27);
        }
        return i2;
    }

    private void readUtf8_slow(int i, int i2) {
        char[] cArr = this.chars;
        while (i2 < i) {
            if (this.position == this.limit) {
                require(1);
            }
            this.position++;
            int i3 = this.niobuffer.get() & 255;
            switch (i3 >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    cArr[i2] = (char) i3;
                    break;
                case 12:
                case 13:
                    if (this.position == this.limit) {
                        require(1);
                    }
                    this.position++;
                    cArr[i2] = (char) (((i3 & 31) << 6) | (this.niobuffer.get() & Utf8.REPLACEMENT_BYTE));
                    break;
                case 14:
                    require(2);
                    this.position += 2;
                    cArr[i2] = (char) (((i3 & 15) << 12) | ((this.niobuffer.get() & Utf8.REPLACEMENT_BYTE) << 6) | (this.niobuffer.get() & Utf8.REPLACEMENT_BYTE));
                    break;
            }
            i2++;
        }
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public boolean canReadInt() throws KryoException {
        if (this.limit - this.position >= 5) {
            return true;
        }
        if (optional(5) <= 0) {
            return false;
        }
        int i = this.position;
        int i2 = i + 1;
        if ((this.niobuffer.get(i) & 128) == 0) {
            return true;
        }
        if (i2 == this.limit) {
            return false;
        }
        int i3 = i2 + 1;
        if ((this.niobuffer.get(i2) & 128) == 0) {
            return true;
        }
        if (i3 == this.limit) {
            return false;
        }
        int i4 = i3 + 1;
        if ((this.niobuffer.get(i3) & 128) == 0) {
            return true;
        }
        if (i4 == this.limit) {
            return false;
        }
        return (this.niobuffer.get(i4) & 128) == 0 || i4 + 1 != this.limit;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public boolean canReadLong() throws KryoException {
        if (this.limit - this.position >= 9) {
            return true;
        }
        if (optional(5) <= 0) {
            return false;
        }
        int i = this.position;
        int i2 = i + 1;
        if ((this.niobuffer.get(i) & 128) == 0) {
            return true;
        }
        if (i2 == this.limit) {
            return false;
        }
        int i3 = i2 + 1;
        if ((this.niobuffer.get(i2) & 128) == 0) {
            return true;
        }
        if (i3 == this.limit) {
            return false;
        }
        int i4 = i3 + 1;
        if ((this.niobuffer.get(i3) & 128) == 0) {
            return true;
        }
        if (i4 == this.limit) {
            return false;
        }
        int i5 = i4 + 1;
        if ((this.niobuffer.get(i4) & 128) == 0) {
            return true;
        }
        if (i5 == this.limit) {
            return false;
        }
        int i6 = i5 + 1;
        if ((this.niobuffer.get(i5) & 128) == 0) {
            return true;
        }
        if (i6 == this.limit) {
            return false;
        }
        int i7 = i6 + 1;
        if ((this.niobuffer.get(i6) & 128) == 0) {
            return true;
        }
        if (i7 == this.limit) {
            return false;
        }
        int i8 = i7 + 1;
        if ((this.niobuffer.get(i7) & 128) == 0) {
            return true;
        }
        if (i8 == this.limit) {
            return false;
        }
        return (this.niobuffer.get(i8) & 128) == 0 || i8 + 1 != this.limit;
    }

    @Override // com.esotericsoftware.kryo.io.Input, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws KryoException {
        InputStream inputStream = this.inputStream;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    protected int fill(ByteBuffer byteBuffer, int i, int i2) throws KryoException {
        InputStream inputStream = this.inputStream;
        if (inputStream == null) {
            return -1;
        }
        try {
            byte[] bArr = new byte[i2];
            int read = inputStream.read(bArr, 0, i2);
            byteBuffer.position(i);
            if (read >= 0) {
                byteBuffer.put(bArr, 0, read);
                byteBuffer.position(i);
            }
            return read;
        } catch (IOException e) {
            throw new KryoException(e);
        }
    }

    public ByteBuffer getByteBuffer() {
        return this.niobuffer;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public InputStream getInputStream() {
        return this.inputStream;
    }

    public boolean getVarIntsEnabled() {
        return this.varIntsEnabled;
    }

    public ByteOrder order() {
        return this.byteOrder;
    }

    @Override // com.esotericsoftware.kryo.io.Input, java.io.InputStream
    public int read() throws KryoException {
        if (optional(1) <= 0) {
            return -1;
        }
        this.niobuffer.position(this.position);
        this.position++;
        return this.niobuffer.get() & 255;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public boolean readBoolean() throws KryoException {
        require(1);
        this.position++;
        return this.niobuffer.get() == 1;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public byte readByte() throws KryoException {
        this.niobuffer.position(this.position);
        require(1);
        this.position++;
        return this.niobuffer.get();
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public int readByteUnsigned() throws KryoException {
        require(1);
        this.position++;
        return this.niobuffer.get() & 255;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public byte[] readBytes(int i) throws KryoException {
        byte[] bArr = new byte[i];
        readBytes(bArr, 0, i);
        return bArr;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public char readChar() throws KryoException {
        require(2);
        this.position += 2;
        return this.niobuffer.getChar();
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public char[] readChars(int i) throws KryoException {
        int i2 = i * 2;
        if (this.capacity - this.position >= i2 && isNativeOrder()) {
            char[] cArr = new char[i];
            this.niobuffer.asCharBuffer().get(cArr);
            this.position += i2;
            this.niobuffer.position(this.position);
            return cArr;
        }
        return super.readChars(i);
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public double readDouble() throws KryoException {
        require(8);
        this.position += 8;
        return this.niobuffer.getDouble();
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public double[] readDoubles(int i) throws KryoException {
        int i2 = i * 8;
        if (this.capacity - this.position >= i2 && isNativeOrder()) {
            double[] dArr = new double[i];
            this.niobuffer.asDoubleBuffer().get(dArr);
            this.position += i2;
            this.niobuffer.position(this.position);
            return dArr;
        }
        return super.readDoubles(i);
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public float readFloat() throws KryoException {
        require(4);
        this.position += 4;
        return this.niobuffer.getFloat();
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public float[] readFloats(int i) throws KryoException {
        int i2 = i * 4;
        if (this.capacity - this.position >= i2 && isNativeOrder()) {
            float[] fArr = new float[i];
            this.niobuffer.asFloatBuffer().get(fArr);
            this.position += i2;
            this.niobuffer.position(this.position);
            return fArr;
        }
        return super.readFloats(i);
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public int readInt() throws KryoException {
        require(4);
        this.position += 4;
        return this.niobuffer.getInt();
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public int[] readInts(int i) throws KryoException {
        int i2 = i * 4;
        if (this.capacity - this.position >= i2 && isNativeOrder()) {
            int[] iArr = new int[i];
            this.niobuffer.asIntBuffer().get(iArr);
            this.position += i2;
            this.niobuffer.position(this.position);
            return iArr;
        }
        return super.readInts(i);
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public long readLong() throws KryoException {
        require(8);
        this.position += 8;
        return this.niobuffer.getLong();
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public long[] readLongs(int i) throws KryoException {
        int i2 = i * 8;
        if (this.capacity - this.position >= i2 && isNativeOrder()) {
            long[] jArr = new long[i];
            this.niobuffer.asLongBuffer().get(jArr);
            this.position += i2;
            this.niobuffer.position(this.position);
            return jArr;
        }
        return super.readLongs(i);
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public short readShort() throws KryoException {
        require(2);
        this.position += 2;
        return this.niobuffer.getShort();
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public int readShortUnsigned() throws KryoException {
        require(2);
        this.position += 2;
        return this.niobuffer.getShort();
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public short[] readShorts(int i) throws KryoException {
        int i2 = i * 2;
        if (this.capacity - this.position >= i2 && isNativeOrder()) {
            short[] sArr = new short[i];
            this.niobuffer.asShortBuffer().get(sArr);
            this.position += i2;
            this.niobuffer.position(this.position);
            return sArr;
        }
        return super.readShorts(i);
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public String readString() {
        this.niobuffer.position(this.position);
        int require = require(1);
        this.position++;
        byte b = this.niobuffer.get();
        if ((b & 128) == 0) {
            return readAscii();
        }
        int readUtf8Length = require >= 5 ? readUtf8Length(b) : readUtf8Length_slow(b);
        if (readUtf8Length == 0) {
            return null;
        }
        if (readUtf8Length == 1) {
            return "";
        }
        int i = readUtf8Length - 1;
        if (this.chars.length < i) {
            this.chars = new char[i];
        }
        readUtf8(i);
        return new String(this.chars, 0, i);
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public StringBuilder readStringBuilder() {
        this.niobuffer.position(this.position);
        int require = require(1);
        this.position++;
        byte b = this.niobuffer.get();
        if ((b & 128) == 0) {
            return new StringBuilder(readAscii());
        }
        int readUtf8Length = require >= 5 ? readUtf8Length(b) : readUtf8Length_slow(b);
        if (readUtf8Length == 0) {
            return null;
        }
        if (readUtf8Length != 1) {
            int i = readUtf8Length - 1;
            if (this.chars.length < i) {
                this.chars = new char[i];
            }
            readUtf8(i);
            StringBuilder sb = new StringBuilder(i);
            sb.append(this.chars, 0, i);
            return sb;
        }
        return new StringBuilder("");
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public int readVarInt(boolean z) throws KryoException {
        this.niobuffer.position(this.position);
        if (require(1) < 5) {
            return readInt_slow(z);
        }
        this.position++;
        byte b = this.niobuffer.get();
        int i = b & Byte.MAX_VALUE;
        if ((b & 128) != 0) {
            this.position++;
            byte b2 = this.niobuffer.get();
            i |= (b2 & Byte.MAX_VALUE) << 7;
            if ((b2 & 128) != 0) {
                this.position++;
                byte b3 = this.niobuffer.get();
                i |= (b3 & Byte.MAX_VALUE) << 14;
                if ((b3 & 128) != 0) {
                    this.position++;
                    byte b4 = this.niobuffer.get();
                    i |= (b4 & Byte.MAX_VALUE) << 21;
                    if ((b4 & 128) != 0) {
                        this.position++;
                        i |= (this.niobuffer.get() & Byte.MAX_VALUE) << 28;
                    }
                }
            }
        }
        return z ? i : (i >>> 1) ^ (-(i & 1));
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public long readVarLong(boolean z) throws KryoException {
        byte b;
        byte b2;
        byte b3;
        byte b4;
        byte b5;
        byte b6;
        byte b7;
        this.niobuffer.position(this.position);
        if (require(1) < 9) {
            return readLong_slow(z);
        }
        this.position++;
        byte b8 = this.niobuffer.get();
        long j = b8 & Byte.MAX_VALUE;
        if ((b8 & 128) != 0) {
            this.position++;
            j |= (b & Byte.MAX_VALUE) << 7;
            if ((this.niobuffer.get() & 128) != 0) {
                this.position++;
                j |= (b2 & Byte.MAX_VALUE) << 14;
                if ((this.niobuffer.get() & 128) != 0) {
                    this.position++;
                    j |= (b3 & Byte.MAX_VALUE) << 21;
                    if ((this.niobuffer.get() & 128) != 0) {
                        this.position++;
                        j |= (b4 & Byte.MAX_VALUE) << 28;
                        if ((this.niobuffer.get() & 128) != 0) {
                            this.position++;
                            j |= (b5 & Byte.MAX_VALUE) << 35;
                            if ((this.niobuffer.get() & 128) != 0) {
                                this.position++;
                                j |= (b6 & Byte.MAX_VALUE) << 42;
                                if ((this.niobuffer.get() & 128) != 0) {
                                    this.position++;
                                    j |= (b7 & Byte.MAX_VALUE) << 49;
                                    if ((this.niobuffer.get() & 128) != 0) {
                                        this.position++;
                                        j |= this.niobuffer.get() << 56;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (z) {
            return j;
        }
        return (-(j & 1)) ^ (j >>> 1);
    }

    public void release() {
        close();
        UnsafeUtil.releaseBuffer(this.niobuffer);
        this.niobuffer = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.esotericsoftware.kryo.io.Input
    public final int require(int i) throws KryoException {
        int i2 = this.limit;
        int i3 = i2 - this.position;
        if (i3 >= i) {
            return i3;
        }
        int i4 = this.capacity;
        if (i <= i4) {
            if (i3 > 0) {
                int fill = fill(this.niobuffer, i2, i4 - i2);
                if (fill == -1) {
                    throw new KryoException("Buffer underflow.");
                }
                i3 += fill;
                if (i3 >= i) {
                    this.limit += fill;
                    return i3;
                }
            }
            this.niobuffer.position(this.position);
            this.niobuffer.compact();
            this.total += this.position;
            this.position = 0;
            while (true) {
                int fill2 = fill(this.niobuffer, i3, this.capacity - i3);
                if (fill2 != -1) {
                    i3 += fill2;
                    if (i3 >= i) {
                        break;
                    }
                } else if (i3 < i) {
                    throw new KryoException("Buffer underflow.");
                } else {
                    break;
                }
            }
            this.limit = i3;
            this.niobuffer.position(0);
            return i3;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Buffer too small: capacity: ");
        outline107.append(this.capacity);
        outline107.append(", required: ");
        outline107.append(i);
        throw new KryoException(outline107.toString());
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public void rewind() {
        super.rewind();
        this.niobuffer.position(0);
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public void setBuffer(byte[] bArr) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(bArr.length);
        allocateDirect.put(bArr);
        allocateDirect.position(0);
        allocateDirect.limit(bArr.length);
        allocateDirect.order(this.byteOrder);
        setBuffer(allocateDirect);
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        this.limit = 0;
        rewind();
    }

    public void setVarIntsEnabled(boolean z) {
        this.varIntsEnabled = z;
    }

    @Override // com.esotericsoftware.kryo.io.Input, java.io.InputStream
    public long skip(long j) throws KryoException {
        long j2 = j;
        while (j2 > 0) {
            int min = (int) Math.min(2147483647L, j2);
            skip(min);
            j2 -= min;
        }
        return j;
    }

    public void order(ByteOrder byteOrder) {
        this.byteOrder = byteOrder;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public void readBytes(byte[] bArr) throws KryoException {
        readBytes(bArr, 0, bArr.length);
    }

    public ByteBufferInput(int i) {
        this.varIntsEnabled = true;
        this.byteOrder = ByteOrder.BIG_ENDIAN;
        this.capacity = i;
        this.niobuffer = ByteBuffer.allocateDirect(i);
        this.niobuffer.order(this.byteOrder);
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public void readBytes(byte[] bArr, int i, int i2) throws KryoException {
        if (bArr != null) {
            int min = Math.min(this.limit - this.position, i2);
            while (true) {
                this.niobuffer.get(bArr, i, min);
                this.position += min;
                i2 -= min;
                if (i2 == 0) {
                    return;
                }
                i += min;
                min = Math.min(i2, this.capacity);
                require(min);
            }
        } else {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public double readDouble(double d, boolean z) throws KryoException {
        return readLong(z) / d;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public float readFloat(float f, boolean z) throws KryoException {
        return readInt(z) / f;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public int readInt(boolean z) throws KryoException {
        if (this.varIntsEnabled) {
            return readVarInt(z);
        }
        return readInt();
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public long readLong(boolean z) throws KryoException {
        if (this.varIntsEnabled) {
            return readVarLong(z);
        }
        return readLong();
    }

    @Override // com.esotericsoftware.kryo.io.Input, java.io.InputStream
    public int read(byte[] bArr) throws KryoException {
        this.niobuffer.position(this.position);
        return read(bArr, 0, bArr.length);
    }

    @Override // com.esotericsoftware.kryo.io.Input, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws KryoException {
        this.niobuffer.position(this.position);
        if (bArr != null) {
            int min = Math.min(this.limit - this.position, i2);
            int i3 = i2;
            while (true) {
                this.niobuffer.get(bArr, i, min);
                this.position += min;
                i3 -= min;
                if (i3 != 0) {
                    i += min;
                    min = optional(i3);
                    if (min != -1) {
                        if (this.position == this.limit) {
                            break;
                        }
                    } else if (i2 == i3) {
                        return -1;
                    }
                } else {
                    break;
                }
            }
            return i2 - i3;
        }
        throw new IllegalArgumentException("bytes cannot be null.");
    }

    public void setBuffer(ByteBuffer byteBuffer) {
        if (byteBuffer != null) {
            this.niobuffer = byteBuffer;
            this.position = byteBuffer.position();
            this.limit = byteBuffer.limit();
            this.capacity = byteBuffer.capacity();
            this.total = 0L;
            this.inputStream = null;
            return;
        }
        throw new IllegalArgumentException("buffer cannot be null.");
    }

    public ByteBufferInput(byte[] bArr) {
        this.varIntsEnabled = true;
        this.byteOrder = ByteOrder.BIG_ENDIAN;
        setBuffer(bArr);
    }

    public ByteBufferInput(ByteBuffer byteBuffer) {
        this.varIntsEnabled = true;
        this.byteOrder = ByteOrder.BIG_ENDIAN;
        setBuffer(byteBuffer);
    }

    public ByteBufferInput(InputStream inputStream) {
        this(4096);
        if (inputStream != null) {
            this.inputStream = inputStream;
            return;
        }
        throw new IllegalArgumentException("inputStream cannot be null.");
    }

    public ByteBufferInput(InputStream inputStream, int i) {
        this(i);
        if (inputStream != null) {
            this.inputStream = inputStream;
            return;
        }
        throw new IllegalArgumentException("inputStream cannot be null.");
    }

    public ByteBufferInput(long j, int i) {
        this.varIntsEnabled = true;
        this.byteOrder = ByteOrder.BIG_ENDIAN;
        setBuffer(UnsafeUtil.getDirectBufferAt(j, i));
    }
}
