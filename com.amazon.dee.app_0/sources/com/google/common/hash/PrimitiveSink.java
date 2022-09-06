package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
@CanIgnoreReturnValue
@Beta
/* loaded from: classes3.dex */
public interface PrimitiveSink {
    /* renamed from: putBoolean */
    PrimitiveSink mo8194putBoolean(boolean z);

    /* renamed from: putByte */
    PrimitiveSink mo8220putByte(byte b);

    /* renamed from: putBytes */
    PrimitiveSink mo8221putBytes(ByteBuffer byteBuffer);

    /* renamed from: putBytes */
    PrimitiveSink mo8196putBytes(byte[] bArr);

    /* renamed from: putBytes */
    PrimitiveSink mo8222putBytes(byte[] bArr, int i, int i2);

    /* renamed from: putChar */
    PrimitiveSink mo8223putChar(char c);

    /* renamed from: putDouble */
    PrimitiveSink mo8199putDouble(double d);

    /* renamed from: putFloat */
    PrimitiveSink mo8200putFloat(float f);

    /* renamed from: putInt */
    PrimitiveSink mo8224putInt(int i);

    /* renamed from: putLong */
    PrimitiveSink mo8225putLong(long j);

    /* renamed from: putShort */
    PrimitiveSink mo8215putShort(short s);

    /* renamed from: putString */
    PrimitiveSink mo8226putString(CharSequence charSequence, Charset charset);

    /* renamed from: putUnencodedChars */
    PrimitiveSink mo8205putUnencodedChars(CharSequence charSequence);
}
