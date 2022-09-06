package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
@Immutable
/* loaded from: classes3.dex */
abstract class AbstractCompositeHashFunction extends AbstractHashFunction {
    private static final long serialVersionUID = 0;
    final HashFunction[] functions;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractCompositeHashFunction(HashFunction... hashFunctionArr) {
        for (HashFunction hashFunction : hashFunctionArr) {
            Preconditions.checkNotNull(hashFunction);
        }
        this.functions = hashFunctionArr;
    }

    private Hasher fromHashers(final Hasher[] hasherArr) {
        return new Hasher() { // from class: com.google.common.hash.AbstractCompositeHashFunction.1
            @Override // com.google.common.hash.Hasher
            public HashCode hash() {
                return AbstractCompositeHashFunction.this.makeHash(hasherArr);
            }

            @Override // com.google.common.hash.Hasher
            public <T> Hasher putObject(T t, Funnel<? super T> funnel) {
                for (Hasher hasher : hasherArr) {
                    hasher.putObject(t, funnel);
                }
                return this;
            }

            @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
            /* renamed from: putBoolean */
            public Hasher mo8194putBoolean(boolean z) {
                for (Hasher hasher : hasherArr) {
                    hasher.mo8194putBoolean(z);
                }
                return this;
            }

            @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
            /* renamed from: putByte */
            public Hasher mo8220putByte(byte b) {
                for (Hasher hasher : hasherArr) {
                    hasher.mo8220putByte(b);
                }
                return this;
            }

            @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
            /* renamed from: putChar */
            public Hasher mo8223putChar(char c) {
                for (Hasher hasher : hasherArr) {
                    hasher.mo8223putChar(c);
                }
                return this;
            }

            @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
            /* renamed from: putDouble */
            public Hasher mo8199putDouble(double d) {
                for (Hasher hasher : hasherArr) {
                    hasher.mo8199putDouble(d);
                }
                return this;
            }

            @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
            /* renamed from: putFloat */
            public Hasher mo8200putFloat(float f) {
                for (Hasher hasher : hasherArr) {
                    hasher.mo8200putFloat(f);
                }
                return this;
            }

            @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
            /* renamed from: putInt */
            public Hasher mo8224putInt(int i) {
                for (Hasher hasher : hasherArr) {
                    hasher.mo8224putInt(i);
                }
                return this;
            }

            @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
            /* renamed from: putLong */
            public Hasher mo8225putLong(long j) {
                for (Hasher hasher : hasherArr) {
                    hasher.mo8225putLong(j);
                }
                return this;
            }

            @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
            /* renamed from: putShort */
            public Hasher mo8215putShort(short s) {
                for (Hasher hasher : hasherArr) {
                    hasher.mo8215putShort(s);
                }
                return this;
            }

            @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
            /* renamed from: putString */
            public Hasher mo8226putString(CharSequence charSequence, Charset charset) {
                for (Hasher hasher : hasherArr) {
                    hasher.mo8226putString(charSequence, charset);
                }
                return this;
            }

            @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
            /* renamed from: putUnencodedChars */
            public Hasher mo8205putUnencodedChars(CharSequence charSequence) {
                for (Hasher hasher : hasherArr) {
                    hasher.mo8205putUnencodedChars(charSequence);
                }
                return this;
            }

            @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
            /* renamed from: putBytes */
            public Hasher mo8196putBytes(byte[] bArr) {
                for (Hasher hasher : hasherArr) {
                    hasher.mo8196putBytes(bArr);
                }
                return this;
            }

            @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
            /* renamed from: putBytes */
            public Hasher mo8222putBytes(byte[] bArr, int i, int i2) {
                for (Hasher hasher : hasherArr) {
                    hasher.mo8222putBytes(bArr, i, i2);
                }
                return this;
            }

            @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
            /* renamed from: putBytes */
            public Hasher mo8221putBytes(ByteBuffer byteBuffer) {
                Hasher[] hasherArr2;
                int position = byteBuffer.position();
                for (Hasher hasher : hasherArr) {
                    Java8Compatibility.position(byteBuffer, position);
                    hasher.mo8221putBytes(byteBuffer);
                }
                return this;
            }
        };
    }

    abstract HashCode makeHash(Hasher[] hasherArr);

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        Hasher[] hasherArr = new Hasher[this.functions.length];
        for (int i = 0; i < hasherArr.length; i++) {
            hasherArr[i] = this.functions[i].newHasher();
        }
        return fromHashers(hasherArr);
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public Hasher newHasher(int i) {
        Preconditions.checkArgument(i >= 0);
        Hasher[] hasherArr = new Hasher[this.functions.length];
        for (int i2 = 0; i2 < hasherArr.length; i2++) {
            hasherArr[i2] = this.functions[i2].newHasher(i);
        }
        return fromHashers(hasherArr);
    }
}
