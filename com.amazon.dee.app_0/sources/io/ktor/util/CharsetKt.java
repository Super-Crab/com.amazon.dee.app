package io.ktor.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Charset.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0019\n\u0002\u0010\u000e\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0007\u001a\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\u0007¨\u0006\u0006"}, d2 = {"isLowerCase", "", "", "toCharArray", "", "", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class CharsetKt {
    @InternalAPI
    public static final boolean isLowerCase(char c) {
        return Character.toLowerCase(c) == c;
    }

    @InternalAPI
    @NotNull
    public static final char[] toCharArray(@NotNull String receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        char[] cArr = new char[receiver$0.length()];
        int length = cArr.length;
        for (int i = 0; i < length; i++) {
            cArr[i] = receiver$0.charAt(i);
        }
        return cArr;
    }
}
