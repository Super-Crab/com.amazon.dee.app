package io.ktor.util;

import java.nio.charset.Charset;
import java.util.Random;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"io/ktor/util/CryptoKt__CryptoJvmKt", "io/ktor/util/CryptoKt__CryptoKt"}, k = 4, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class CryptoKt {
    @InternalAPI
    @NotNull
    public static final Digest Digest(@NotNull String str) {
        return CryptoKt__CryptoJvmKt.Digest(str);
    }

    @InternalAPI
    @Nullable
    public static final Object build(@NotNull Digest digest, @NotNull String str, @NotNull Charset charset, @NotNull Continuation<? super byte[]> continuation) {
        return CryptoKt__CryptoKt.build(digest, str, charset, continuation);
    }

    @KtorExperimentalAPI
    @NotNull
    public static final String generateNonce() {
        return CryptoKt__CryptoJvmKt.generateNonce();
    }

    @KtorExperimentalAPI
    @NotNull
    public static final Function1<String, byte[]> getDigestFunction(@NotNull String str, @NotNull String str2) {
        return CryptoKt__CryptoJvmKt.getDigestFunction(str, str2);
    }

    @NotNull
    public static final Random getNonceRandom() {
        return CryptoKt__CryptoJvmKt.getNonceRandom();
    }

    @KtorExperimentalAPI
    @NotNull
    public static final String hex(@NotNull byte[] bArr) {
        return CryptoKt__CryptoKt.hex(bArr);
    }

    @KtorExperimentalAPI
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use generateNonce() instead", replaceWith = @ReplaceWith(expression = "generateNonce()", imports = {}))
    @NotNull
    public static final String nextNonce() {
        return CryptoKt__CryptoJvmKt.nextNonce();
    }

    @KtorExperimentalAPI
    @Deprecated(level = DeprecationLevel.ERROR, message = "Will be removed in future releases", replaceWith = @ReplaceWith(expression = "s.toByteArray(Charsets.UTF_8)", imports = {}))
    @NotNull
    public static final byte[] raw(@NotNull String str) {
        return CryptoKt__CryptoJvmKt.raw(str);
    }

    @KtorExperimentalAPI
    @NotNull
    public static final byte[] sha1(@NotNull byte[] bArr) {
        return CryptoKt__CryptoJvmKt.sha1(bArr);
    }

    @InternalAPI
    @Nullable
    public static final Object build(@NotNull Digest digest, @NotNull byte[] bArr, @NotNull Continuation<? super byte[]> continuation) {
        return CryptoKt__CryptoKt.build(digest, bArr, continuation);
    }

    @InternalAPI
    @NotNull
    public static final byte[] generateNonce(int i) {
        return CryptoKt__CryptoKt.generateNonce(i);
    }

    @KtorExperimentalAPI
    @NotNull
    public static final byte[] hex(@NotNull String str) {
        return CryptoKt__CryptoKt.hex(str);
    }
}
