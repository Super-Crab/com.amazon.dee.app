package kotlin.reflect.jvm.internal.impl.utils;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: numbers.kt */
/* loaded from: classes4.dex */
public final class NumbersKt {
    @NotNull
    public static final NumberWithRadix extractRadix(@NotNull String value) {
        boolean startsWith$default;
        boolean startsWith$default2;
        boolean startsWith$default3;
        boolean startsWith$default4;
        Intrinsics.checkParameterIsNotNull(value, "value");
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(value, "0x", false, 2, null);
        if (!startsWith$default) {
            startsWith$default2 = StringsKt__StringsJVMKt.startsWith$default(value, "0X", false, 2, null);
            if (!startsWith$default2) {
                startsWith$default3 = StringsKt__StringsJVMKt.startsWith$default(value, "0b", false, 2, null);
                if (!startsWith$default3) {
                    startsWith$default4 = StringsKt__StringsJVMKt.startsWith$default(value, "0B", false, 2, null);
                    if (!startsWith$default4) {
                        return new NumberWithRadix(value, 10);
                    }
                }
                String substring = value.substring(2);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                return new NumberWithRadix(substring, 2);
            }
        }
        String substring2 = value.substring(2);
        Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
        return new NumberWithRadix(substring2, 16);
    }
}
