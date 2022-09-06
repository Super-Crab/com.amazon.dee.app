package com.esotericsoftware.reflectasm.shaded.org.objectweb.asm;

import com.amazon.deecomms.common.Constants;
/* loaded from: classes2.dex */
public class ByteVector {
    byte[] a;
    int b;

    public ByteVector() {
        this.a = new byte[64];
    }

    public ByteVector(int i) {
        this.a = new byte[i];
    }

    private void a(int i) {
        int length = this.a.length * 2;
        int i2 = i + this.b;
        if (length > i2) {
            i2 = length;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.a, 0, bArr, 0, this.b);
        this.a = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteVector a(int i, int i2) {
        int i3 = this.b;
        if (i3 + 2 > this.a.length) {
            a(2);
        }
        byte[] bArr = this.a;
        int i4 = i3 + 1;
        bArr[i3] = (byte) i;
        bArr[i4] = (byte) i2;
        this.b = i4 + 1;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteVector b(int i, int i2) {
        int i3 = this.b;
        if (i3 + 3 > this.a.length) {
            a(3);
        }
        byte[] bArr = this.a;
        int i4 = i3 + 1;
        bArr[i3] = (byte) i;
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i2 >>> 8);
        bArr[i5] = (byte) i2;
        this.b = i5 + 1;
        return this;
    }

    public ByteVector putByte(int i) {
        int i2 = this.b;
        int i3 = i2 + 1;
        if (i3 > this.a.length) {
            a(1);
        }
        this.a[i2] = (byte) i;
        this.b = i3;
        return this;
    }

    public ByteVector putByteArray(byte[] bArr, int i, int i2) {
        if (this.b + i2 > this.a.length) {
            a(i2);
        }
        if (bArr != null) {
            System.arraycopy(bArr, i, this.a, this.b, i2);
        }
        this.b += i2;
        return this;
    }

    public ByteVector putInt(int i) {
        int i2 = this.b;
        if (i2 + 4 > this.a.length) {
            a(4);
        }
        byte[] bArr = this.a;
        int i3 = i2 + 1;
        bArr[i2] = (byte) (i >>> 24);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i >>> 16);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i >>> 8);
        bArr[i5] = (byte) i;
        this.b = i5 + 1;
        return this;
    }

    public ByteVector putLong(long j) {
        int i = this.b;
        if (i + 8 > this.a.length) {
            a(8);
        }
        byte[] bArr = this.a;
        int i2 = (int) (j >>> 32);
        int i3 = i + 1;
        bArr[i] = (byte) (i2 >>> 24);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i2 >>> 16);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i2 >>> 8);
        int i6 = i5 + 1;
        bArr[i5] = (byte) i2;
        int i7 = (int) j;
        int i8 = i6 + 1;
        bArr[i6] = (byte) (i7 >>> 24);
        int i9 = i8 + 1;
        bArr[i8] = (byte) (i7 >>> 16);
        int i10 = i9 + 1;
        bArr[i9] = (byte) (i7 >>> 8);
        bArr[i10] = (byte) i7;
        this.b = i10 + 1;
        return this;
    }

    public ByteVector putShort(int i) {
        int i2 = this.b;
        if (i2 + 2 > this.a.length) {
            a(2);
        }
        byte[] bArr = this.a;
        int i3 = i2 + 1;
        bArr[i2] = (byte) (i >>> 8);
        bArr[i3] = (byte) i;
        this.b = i3 + 1;
        return this;
    }

    public ByteVector putUTF8(String str) {
        int i;
        int length = str.length();
        int i2 = this.b;
        if (i2 + 2 + length > this.a.length) {
            a(length + 2);
        }
        byte[] bArr = this.a;
        int i3 = i2 + 1;
        bArr[i2] = (byte) (length >>> 8);
        int i4 = i3 + 1;
        bArr[i3] = (byte) length;
        int i5 = 0;
        while (i5 < length) {
            char charAt = str.charAt(i5);
            if (charAt < 1 || charAt > 127) {
                int i6 = i5;
                int i7 = i6;
                while (i6 < length) {
                    char charAt2 = str.charAt(i6);
                    i7 = (charAt2 < 1 || charAt2 > 127) ? charAt2 > 2047 ? i7 + 3 : i7 + 2 : i7 + 1;
                    i6++;
                }
                int i8 = this.b;
                bArr[i8] = (byte) (i7 >>> 8);
                bArr[i8 + 1] = (byte) i7;
                if (i8 + 2 + i7 > bArr.length) {
                    this.b = i4;
                    a(i7 + 2);
                    bArr = this.a;
                }
                while (i5 < length) {
                    char charAt3 = str.charAt(i5);
                    if (charAt3 < 1 || charAt3 > 127) {
                        int i9 = i4 + 1;
                        if (charAt3 > 2047) {
                            bArr[i4] = (byte) (((charAt3 >> '\f') & 15) | 224);
                            int i10 = i9 + 1;
                            bArr[i9] = (byte) (((charAt3 >> 6) & 63) | 128);
                            i = i10 + 1;
                            bArr[i10] = (byte) ((charAt3 & Constants.DEFAULT_IMAGE_CHAR) | 128);
                        } else {
                            bArr[i4] = (byte) (((charAt3 >> 6) & 31) | 192);
                            i4 = i9 + 1;
                            bArr[i9] = (byte) ((charAt3 & Constants.DEFAULT_IMAGE_CHAR) | 128);
                            i5++;
                        }
                    } else {
                        i = i4 + 1;
                        bArr[i4] = (byte) charAt3;
                    }
                    i4 = i;
                    i5++;
                }
                this.b = i4;
                return this;
            }
            bArr[i4] = (byte) charAt;
            i5++;
            i4++;
        }
        this.b = i4;
        return this;
    }
}
