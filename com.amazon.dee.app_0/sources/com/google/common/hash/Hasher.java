package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
@CanIgnoreReturnValue
@Beta
/* loaded from: classes3.dex */
public interface Hasher extends PrimitiveSink {
    HashCode hash();

    @Deprecated
    int hashCode();

    @Override // com.google.common.hash.PrimitiveSink
    /* renamed from: putBoolean */
    Hasher mo8194putBoolean(boolean z);

    @Override // com.google.common.hash.PrimitiveSink
    /* renamed from: putByte */
    Hasher mo8220putByte(byte b);

    @Override // com.google.common.hash.PrimitiveSink
    /* renamed from: putBytes */
    Hasher mo8221putBytes(ByteBuffer byteBuffer);

    @Override // com.google.common.hash.PrimitiveSink
    /* renamed from: putBytes */
    Hasher mo8196putBytes(byte[] bArr);

    @Override // com.google.common.hash.PrimitiveSink
    /* renamed from: putBytes */
    Hasher mo8222putBytes(byte[] bArr, int i, int i2);

    @Override // com.google.common.hash.PrimitiveSink
    /* renamed from: putChar */
    Hasher mo8223putChar(char c);

    @Override // com.google.common.hash.PrimitiveSink
    /* renamed from: putDouble */
    Hasher mo8199putDouble(double d);

    @Override // com.google.common.hash.PrimitiveSink
    /* renamed from: putFloat */
    Hasher mo8200putFloat(float f);

    @Override // com.google.common.hash.PrimitiveSink
    /* renamed from: putInt */
    Hasher mo8224putInt(int i);

    @Override // com.google.common.hash.PrimitiveSink
    /* renamed from: putLong */
    Hasher mo8225putLong(long j);

    <T> Hasher putObject(T t, Funnel<? super T> funnel);

    @Override // com.google.common.hash.PrimitiveSink
    /* renamed from: putShort */
    Hasher mo8215putShort(short s);

    @Override // com.google.common.hash.PrimitiveSink
    /* renamed from: putString */
    Hasher mo8226putString(CharSequence charSequence, Charset charset);

    @Override // com.google.common.hash.PrimitiveSink
    /* renamed from: putUnencodedChars */
    Hasher mo8205putUnencodedChars(CharSequence charSequence);
}
