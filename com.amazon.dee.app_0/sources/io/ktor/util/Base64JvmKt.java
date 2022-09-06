package io.ktor.util;

import java.util.Base64;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Base64Jvm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0001H\u0007¨\u0006\u0006"}, d2 = {"decodeBase64", "", "s", "", "encodeBase64", "bytes", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class Base64JvmKt {
    @InternalAPI
    @NotNull
    public static final byte[] decodeBase64(@NotNull String s) {
        Intrinsics.checkParameterIsNotNull(s, "s");
        byte[] decode = Base64.getDecoder().decode(s);
        Intrinsics.checkExpressionValueIsNotNull(decode, "Base64.getDecoder().decode(s)");
        return decode;
    }

    @InternalAPI
    @NotNull
    public static final String encodeBase64(@NotNull byte[] bytes) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        String encodeToString = Base64.getEncoder().encodeToString(bytes);
        Intrinsics.checkExpressionValueIsNotNull(encodeToString, "Base64.getEncoder().encodeToString(bytes)");
        return encodeToString;
    }
}
