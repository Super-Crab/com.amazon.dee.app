package io.ktor.util;

import io.ktor.http.auth.AuthScheme;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Random;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.Charsets;
import kotlinx.coroutines.BuildersKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CryptoJvm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007\u001a\b\u0010\f\u001a\u00020\u000bH\u0007\u001a\r\u0010\r\u001a\u00020\u000bH\u0002¢\u0006\u0002\b\u000e\u001a%\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000bH\u0002¢\u0006\u0002\b\u0014\u001a$\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00100\u00162\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000bH\u0007\u001a\b\u0010\u0017\u001a\u00020\u000bH\u0007\u001a\u0010\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u000bH\u0007\u001a\u0010\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u0010H\u0007\"!\u0010\u0000\u001a\u00020\u00018FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\u0006\u0010\u0007\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u001c"}, d2 = {"nonceRandom", "Ljava/util/Random;", "nonceRandom$annotations", "()V", "getNonceRandom", "()Ljava/util/Random;", "nonceRandom$delegate", "Lkotlin/Lazy;", AuthScheme.Digest, "Lio/ktor/util/Digest;", "name", "", "generateNonce", "generateNonceBlocking", "generateNonceBlocking$CryptoKt__CryptoJvmKt", "getDigest", "", "text", "algorithm", "salt", "getDigest$CryptoKt__CryptoJvmKt", "getDigestFunction", "Lkotlin/Function1;", "nextNonce", "raw", "s", "sha1", "bytes", "ktor-utils"}, k = 5, mv = {1, 1, 13}, xs = "io/ktor/util/CryptoKt")
/* loaded from: classes3.dex */
public final /* synthetic */ class CryptoKt__CryptoJvmKt {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property0(new PropertyReference0Impl(Reflection.getOrCreateKotlinPackage(CryptoKt__CryptoJvmKt.class, "ktor-utils"), "nonceRandom", "getNonceRandom()Ljava/util/Random;"))};
    @NotNull
    private static final Lazy nonceRandom$delegate = LazyKt.lazy(CryptoKt__CryptoJvmKt$nonceRandom$2.INSTANCE);

    @InternalAPI
    @NotNull
    public static final Digest Digest(@NotNull final String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new Digest(name) { // from class: io.ktor.util.CryptoKt__CryptoJvmKt$Digest$1
            final /* synthetic */ String $name;
            private final MessageDigest delegate;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.$name = name;
                this.delegate = MessageDigest.getInstance(name);
            }

            @Override // io.ktor.util.Digest
            @Nullable
            public Object build(@NotNull Continuation<? super byte[]> continuation) {
                byte[] digest = this.delegate.digest();
                Intrinsics.checkExpressionValueIsNotNull(digest, "delegate.digest()");
                return digest;
            }

            @Override // io.ktor.util.Digest
            public void plusAssign(@NotNull byte[] bytes) {
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                this.delegate.update(bytes);
            }

            @Override // io.ktor.util.Digest
            public void reset() {
                this.delegate.reset();
            }
        };
    }

    @KtorExperimentalAPI
    @NotNull
    public static final String generateNonce() {
        String poll = NonceKt.getSeedChannel().poll();
        return poll != null ? poll : generateNonceBlocking$CryptoKt__CryptoJvmKt();
    }

    private static final String generateNonceBlocking$CryptoKt__CryptoJvmKt() {
        NonceKt.ensureNonceGeneratorRunning();
        return (String) BuildersKt.runBlocking$default(null, new CryptoKt__CryptoJvmKt$generateNonceBlocking$1(null), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final byte[] getDigest$CryptoKt__CryptoJvmKt(String str, String str2, String str3) {
        MessageDigest messageDigest = MessageDigest.getInstance(str2);
        Charset charset = Charsets.UTF_8;
        if (str3 != null) {
            byte[] bytes = str3.getBytes(charset);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            messageDigest.update(bytes);
            Charset charset2 = Charsets.UTF_8;
            if (str != null) {
                byte[] bytes2 = str.getBytes(charset2);
                Intrinsics.checkExpressionValueIsNotNull(bytes2, "(this as java.lang.String).getBytes(charset)");
                byte[] digest = messageDigest.digest(bytes2);
                Intrinsics.checkExpressionValueIsNotNull(digest, "digest(text.toByteArray())");
                Intrinsics.checkExpressionValueIsNotNull(digest, "with(MessageDigest.getIn…text.toByteArray())\n    }");
                return digest;
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @KtorExperimentalAPI
    @NotNull
    public static final Function1<String, byte[]> getDigestFunction(@NotNull String algorithm, @NotNull String salt) {
        Intrinsics.checkParameterIsNotNull(algorithm, "algorithm");
        Intrinsics.checkParameterIsNotNull(salt, "salt");
        return new CryptoKt__CryptoJvmKt$getDigestFunction$1(algorithm, salt);
    }

    @NotNull
    public static final Random getNonceRandom() {
        Lazy lazy = nonceRandom$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (Random) lazy.getValue();
    }

    @KtorExperimentalAPI
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use generateNonce() instead", replaceWith = @ReplaceWith(expression = "generateNonce()", imports = {}))
    @NotNull
    public static final String nextNonce() {
        return CryptoKt.generateNonce();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use generateNonce() instead")
    public static /* synthetic */ void nonceRandom$annotations() {
    }

    @KtorExperimentalAPI
    @Deprecated(level = DeprecationLevel.ERROR, message = "Will be removed in future releases", replaceWith = @ReplaceWith(expression = "s.toByteArray(Charsets.UTF_8)", imports = {}))
    @NotNull
    public static final byte[] raw(@NotNull String s) {
        Intrinsics.checkParameterIsNotNull(s, "s");
        byte[] bytes = s.getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        return bytes;
    }

    @KtorExperimentalAPI
    @NotNull
    public static final byte[] sha1(@NotNull byte[] bytes) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        byte[] digest = MessageDigest.getInstance("SHA1").digest(bytes);
        if (digest == null) {
            Intrinsics.throwNpe();
        }
        return digest;
    }
}
