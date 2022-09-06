package com.drew.lang;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.StringValue;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
/* loaded from: classes2.dex */
public abstract class RandomAccessReader {
    private boolean _isMotorolaByteOrder = true;

    public boolean getBit(int i) throws IOException {
        int i2 = i / 8;
        validateIndex(i2, 1);
        return ((getByte(i2) >> (i % 8)) & 1) == 1;
    }

    public abstract byte getByte(int i) throws IOException;

    @NotNull
    public abstract byte[] getBytes(int i, int i2) throws IOException;

    public double getDouble64(int i) throws IOException {
        return Double.longBitsToDouble(getInt64(i));
    }

    public float getFloat32(int i) throws IOException {
        return Float.intBitsToFloat(getInt32(i));
    }

    public short getInt16(int i) throws IOException {
        int i2;
        validateIndex(i, 2);
        if (this._isMotorolaByteOrder) {
            i2 = (getByte(i) << 8) & InputDeviceCompat.SOURCE_ANY;
            i++;
        } else {
            i2 = (getByte(i + 1) << 8) & InputDeviceCompat.SOURCE_ANY;
        }
        return (short) ((getByte(i) & 255) | i2);
    }

    public int getInt24(int i) throws IOException {
        int i2;
        validateIndex(i, 3);
        if (this._isMotorolaByteOrder) {
            i2 = ((getByte(i) << 16) & 16711680) | (65280 & (getByte(i + 1) << 8));
            i += 2;
        } else {
            i2 = ((getByte(i + 2) << 16) & 16711680) | (65280 & (getByte(i + 1) << 8));
        }
        return (getByte(i) & 255) | i2;
    }

    public int getInt32(int i) throws IOException {
        int i2;
        validateIndex(i, 4);
        if (this._isMotorolaByteOrder) {
            i2 = ((getByte(i) << 24) & ViewCompat.MEASURED_STATE_MASK) | (16711680 & (getByte(i + 1) << 16)) | (65280 & (getByte(i + 2) << 8));
            i += 3;
        } else {
            i2 = ((getByte(i + 3) << 24) & ViewCompat.MEASURED_STATE_MASK) | (16711680 & (getByte(i + 2) << 16)) | (65280 & (getByte(i + 1) << 8));
        }
        return (getByte(i) & 255) | i2;
    }

    public long getInt64(int i) throws IOException {
        long j;
        byte b;
        validateIndex(i, 8);
        if (this._isMotorolaByteOrder) {
            j = ((getByte(i) << 56) & (-72057594037927936L)) | (71776119061217280L & (getByte(i + 1) << 48)) | (280375465082880L & (getByte(i + 2) << 40)) | (1095216660480L & (getByte(i + 3) << 32)) | ((getByte(i + 4) << 24) & 4278190080L) | ((getByte(i + 5) << 16) & 16711680) | ((getByte(i + 6) << 8) & 65280);
            b = getByte(i + 7);
        } else {
            j = ((getByte(i + 7) << 56) & (-72057594037927936L)) | (71776119061217280L & (getByte(i + 6) << 48)) | (280375465082880L & (getByte(i + 5) << 40)) | (1095216660480L & (getByte(i + 4) << 32)) | ((getByte(i + 3) << 24) & 4278190080L) | ((getByte(i + 2) << 16) & 16711680) | ((getByte(i + 1) << 8) & 65280);
            b = getByte(i);
        }
        return j | (b & 255);
    }

    public byte getInt8(int i) throws IOException {
        validateIndex(i, 1);
        return getByte(i);
    }

    public abstract long getLength() throws IOException;

    @NotNull
    public byte[] getNullTerminatedBytes(int i, int i2) throws IOException {
        byte[] bytes = getBytes(i, i2);
        int i3 = 0;
        while (i3 < bytes.length && bytes[i3] != 0) {
            i3++;
        }
        if (i3 == i2) {
            return bytes;
        }
        byte[] bArr = new byte[i3];
        if (i3 > 0) {
            System.arraycopy(bytes, 0, bArr, 0, i3);
        }
        return bArr;
    }

    @NotNull
    public String getNullTerminatedString(int i, int i2, @NotNull Charset charset) throws IOException {
        return new String(getNullTerminatedBytes(i, i2), charset.name());
    }

    @NotNull
    public StringValue getNullTerminatedStringValue(int i, int i2, @Nullable Charset charset) throws IOException {
        return new StringValue(getNullTerminatedBytes(i, i2), charset);
    }

    public float getS15Fixed16(int i) throws IOException {
        float f;
        int i2;
        validateIndex(i, 4);
        if (this._isMotorolaByteOrder) {
            f = ((getByte(i) & 255) << 8) | (getByte(i + 1) & 255);
            i2 = (getByte(i + 2) & 255) << 8;
            i += 3;
        } else {
            f = ((getByte(i + 3) & 255) << 8) | (getByte(i + 2) & 255);
            i2 = (getByte(i + 1) & 255) << 8;
        }
        return (float) ((((getByte(i) & 255) | i2) / 65536.0d) + f);
    }

    @NotNull
    public String getString(int i, int i2, @NotNull String str) throws IOException {
        byte[] bytes = getBytes(i, i2);
        try {
            return new String(bytes, str);
        } catch (UnsupportedEncodingException unused) {
            return new String(bytes);
        }
    }

    @NotNull
    public String getString(int i, int i2, @NotNull Charset charset) throws IOException {
        return new String(getBytes(i, i2), charset.name());
    }

    @NotNull
    public StringValue getStringValue(int i, int i2, @Nullable Charset charset) throws IOException {
        return new StringValue(getBytes(i, i2), charset);
    }

    public int getUInt16(int i) throws IOException {
        int i2;
        validateIndex(i, 2);
        if (this._isMotorolaByteOrder) {
            i2 = (getByte(i) << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK;
            i++;
        } else {
            i2 = (getByte(i + 1) << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK;
        }
        return (getByte(i) & 255) | i2;
    }

    public long getUInt32(int i) throws IOException {
        long j;
        validateIndex(i, 4);
        if (this._isMotorolaByteOrder) {
            long j2 = 4278190080L & (getByte(i) << 24);
            j = (65280 & (getByte(i + 2) << 8)) | (16711680 & (getByte(i + 1) << 16)) | j2;
            i += 3;
        } else {
            long j3 = 4278190080L & (getByte(i + 3) << 24);
            j = (65280 & (getByte(i + 1) << 8)) | (16711680 & (getByte(i + 2) << 16)) | j3;
        }
        return (getByte(i) & 255) | j;
    }

    public short getUInt8(int i) throws IOException {
        validateIndex(i, 1);
        return (short) (getByte(i) & 255);
    }

    public boolean isMotorolaByteOrder() {
        return this._isMotorolaByteOrder;
    }

    protected abstract boolean isValidIndex(int i, int i2) throws IOException;

    public void setMotorolaByteOrder(boolean z) {
        this._isMotorolaByteOrder = z;
    }

    public abstract int toUnshiftedOffset(int i);

    protected abstract void validateIndex(int i, int i2) throws IOException;
}
