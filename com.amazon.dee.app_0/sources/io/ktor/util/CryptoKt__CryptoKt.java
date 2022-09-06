package io.ktor.util;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.ktor.http.auth.HttpAuthHeader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt__CharJVMKt;
import kotlin.text.Charsets;
import kotlinx.io.charsets.CharsetJVMKt;
import kotlinx.io.core.BytePacketBuilder;
import kotlinx.io.core.PacketJVMKt;
import kotlinx.io.core.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Crypto.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\u001a\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0007\u001a\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\tH\u0007\u001a\u001d\u0010\f\u001a\u00020\u0005*\u00020\r2\u0006\u0010\n\u001a\u00020\u0005H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a+\u0010\f\u001a\u00020\u0005*\u00020\r2\u0006\u0010\u000f\u001a\u00020\t2\f\b\u0002\u0010\u0010\u001a\u00060\u0011j\u0002`\u0012H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u0013\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"digits", "", "getDigits$CryptoKt__CryptoKt", "()[C", "generateNonce", "", "size", "", "hex", "", "bytes", "s", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lio/ktor/util/Digest;", "(Lio/ktor/util/Digest;[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "string", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lkotlinx/io/charsets/Charset;", "(Lio/ktor/util/Digest;Ljava/lang/String;Ljava/nio/charset/Charset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-utils"}, k = 5, mv = {1, 1, 13}, xs = "io/ktor/util/CryptoKt")
/* loaded from: classes3.dex */
public final /* synthetic */ class CryptoKt__CryptoKt {
    private static final char[] digits = CharsetKt.toCharArray("0123456789abcdef");

    @InternalAPI
    @Nullable
    public static final Object build(@NotNull Digest digest, @NotNull byte[] bArr, @NotNull Continuation<? super byte[]> continuation) {
        digest.plusAssign(bArr);
        return digest.build(continuation);
    }

    @InternalAPI
    @Nullable
    public static /* synthetic */ Object build$default(Digest digest, String str, Charset charset, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        return CryptoKt.build(digest, str, charset, continuation);
    }

    @InternalAPI
    @NotNull
    public static final byte[] generateNonce(int i) {
        BytePacketBuilder BytePacketBuilder = PacketJVMKt.BytePacketBuilder(0);
        while (BytePacketBuilder.getSize() < i) {
            try {
                BytePacketBuilder.writeStringUtf8(CryptoKt.generateNonce());
            } catch (Throwable th) {
                BytePacketBuilder.release();
                throw th;
            }
        }
        return StringsKt.readBytes(BytePacketBuilder.build(), i);
    }

    @KtorExperimentalAPI
    @NotNull
    public static final String hex(@NotNull byte[] bytes) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        char[] cArr = new char[bytes.length * 2];
        char[] cArr2 = digits;
        int i = 0;
        for (byte b : bytes) {
            int i2 = b & 255;
            int i3 = i + 1;
            cArr[i] = cArr2[i2 >> 4];
            i = i3 + 1;
            cArr[i3] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }

    @InternalAPI
    @Nullable
    public static final Object build(@NotNull Digest digest, @NotNull String str, @NotNull Charset charset, @NotNull Continuation<? super byte[]> continuation) {
        CharsetEncoder newEncoder = charset.newEncoder();
        Intrinsics.checkExpressionValueIsNotNull(newEncoder, "charset.newEncoder()");
        digest.plusAssign(CharsetJVMKt.encodeToByteArray(newEncoder, str, 0, str.length()));
        return digest.build(continuation);
    }

    @KtorExperimentalAPI
    @NotNull
    public static final byte[] hex(@NotNull String s) {
        int checkRadix;
        int checkRadix2;
        Intrinsics.checkParameterIsNotNull(s, "s");
        byte[] bArr = new byte[s.length() / 2];
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            String valueOf = String.valueOf(s.charAt(i2));
            checkRadix = CharsKt__CharJVMKt.checkRadix(16);
            String valueOf2 = String.valueOf(s.charAt(i2 + 1));
            checkRadix2 = CharsKt__CharJVMKt.checkRadix(16);
            bArr[i] = (byte) (Integer.parseInt(valueOf2, checkRadix2) | (Integer.parseInt(valueOf, checkRadix) << 4));
        }
        return bArr;
    }
}
