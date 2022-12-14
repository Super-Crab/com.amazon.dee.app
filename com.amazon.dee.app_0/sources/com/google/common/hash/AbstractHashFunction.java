package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
@Immutable
/* loaded from: classes3.dex */
abstract class AbstractHashFunction implements HashFunction {
    @Override // com.google.common.hash.HashFunction
    public HashCode hashBytes(byte[] bArr) {
        return hashBytes(bArr, 0, bArr.length);
    }

    @Override // com.google.common.hash.HashFunction
    public HashCode hashInt(int i) {
        return newHasher(4).mo8224putInt(i).hash();
    }

    @Override // com.google.common.hash.HashFunction
    public HashCode hashLong(long j) {
        return newHasher(8).mo8225putLong(j).hash();
    }

    @Override // com.google.common.hash.HashFunction
    public <T> HashCode hashObject(T t, Funnel<? super T> funnel) {
        return newHasher().putObject(t, funnel).hash();
    }

    @Override // com.google.common.hash.HashFunction
    public HashCode hashString(CharSequence charSequence, Charset charset) {
        return newHasher().mo8226putString(charSequence, charset).hash();
    }

    @Override // com.google.common.hash.HashFunction
    public HashCode hashUnencodedChars(CharSequence charSequence) {
        return newHasher(charSequence.length() * 2).mo8205putUnencodedChars(charSequence).hash();
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher(int i) {
        Preconditions.checkArgument(i >= 0, "expectedInputSize must be >= 0 but was %s", i);
        return newHasher();
    }

    @Override // com.google.common.hash.HashFunction
    public HashCode hashBytes(byte[] bArr, int i, int i2) {
        Preconditions.checkPositionIndexes(i, i + i2, bArr.length);
        return newHasher(i2).mo8222putBytes(bArr, i, i2).hash();
    }

    @Override // com.google.common.hash.HashFunction
    public HashCode hashBytes(ByteBuffer byteBuffer) {
        return newHasher(byteBuffer.remaining()).mo8221putBytes(byteBuffer).hash();
    }
}
