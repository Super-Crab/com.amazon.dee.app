package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: utfEncoding.kt */
/* loaded from: classes4.dex */
public final class UtfEncodingKt {
    @NotNull
    public static final byte[] stringsToBytes(@NotNull String[] strings) {
        int i;
        Intrinsics.checkParameterIsNotNull(strings, "strings");
        boolean z = false;
        int i2 = 0;
        for (String str : strings) {
            i2 += str.length();
        }
        byte[] bArr = new byte[i2];
        int i3 = 0;
        for (String str2 : strings) {
            int length = str2.length() - 1;
            if (length >= 0) {
                int i4 = 0;
                while (true) {
                    i = i3 + 1;
                    bArr[i3] = (byte) str2.charAt(i4);
                    if (i4 == length) {
                        break;
                    }
                    i4++;
                    i3 = i;
                }
                i3 = i;
            }
        }
        if (i3 == bArr.length) {
            z = true;
        }
        if (!_Assertions.ENABLED || z) {
            return bArr;
        }
        throw new AssertionError("Should have reached the end");
    }
}
