package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
@CanIgnoreReturnValue
/* loaded from: classes3.dex */
abstract class AbstractHasher implements Hasher {
    @Override // com.google.common.hash.Hasher
    public <T> Hasher putObject(T t, Funnel<? super T> funnel) {
        funnel.funnel(t, this);
        return this;
    }

    @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    /* renamed from: putBoolean */
    public final Hasher mo8194putBoolean(boolean z) {
        return mo8220putByte(z ? (byte) 1 : (byte) 0);
    }

    @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    /* renamed from: putChar */
    public Hasher mo8223putChar(char c) {
        mo8220putByte((byte) c);
        mo8220putByte((byte) (c >>> '\b'));
        return this;
    }

    @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    /* renamed from: putDouble */
    public final Hasher mo8199putDouble(double d) {
        return mo8225putLong(Double.doubleToRawLongBits(d));
    }

    @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    /* renamed from: putFloat */
    public final Hasher mo8200putFloat(float f) {
        return mo8224putInt(Float.floatToRawIntBits(f));
    }

    @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    /* renamed from: putInt */
    public Hasher mo8224putInt(int i) {
        mo8220putByte((byte) i);
        mo8220putByte((byte) (i >>> 8));
        mo8220putByte((byte) (i >>> 16));
        mo8220putByte((byte) (i >>> 24));
        return this;
    }

    @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    /* renamed from: putLong */
    public Hasher mo8225putLong(long j) {
        for (int i = 0; i < 64; i += 8) {
            mo8220putByte((byte) (j >>> i));
        }
        return this;
    }

    @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    /* renamed from: putShort */
    public Hasher mo8215putShort(short s) {
        mo8220putByte((byte) s);
        mo8220putByte((byte) (s >>> 8));
        return this;
    }

    @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    /* renamed from: putString */
    public Hasher mo8226putString(CharSequence charSequence, Charset charset) {
        return mo8196putBytes(charSequence.toString().getBytes(charset));
    }

    @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    /* renamed from: putUnencodedChars */
    public Hasher mo8205putUnencodedChars(CharSequence charSequence) {
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            mo8223putChar(charSequence.charAt(i));
        }
        return this;
    }

    @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    /* renamed from: putBytes */
    public Hasher mo8196putBytes(byte[] bArr) {
        return mo8222putBytes(bArr, 0, bArr.length);
    }

    @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    /* renamed from: putBytes */
    public Hasher mo8222putBytes(byte[] bArr, int i, int i2) {
        Preconditions.checkPositionIndexes(i, i + i2, bArr.length);
        for (int i3 = 0; i3 < i2; i3++) {
            mo8220putByte(bArr[i + i3]);
        }
        return this;
    }

    @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    /* renamed from: putBytes */
    public Hasher mo8221putBytes(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            mo8222putBytes(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.remaining());
            Java8Compatibility.position(byteBuffer, byteBuffer.limit());
        } else {
            for (int remaining = byteBuffer.remaining(); remaining > 0; remaining--) {
                mo8220putByte(byteBuffer.get());
            }
        }
        return this;
    }
}
