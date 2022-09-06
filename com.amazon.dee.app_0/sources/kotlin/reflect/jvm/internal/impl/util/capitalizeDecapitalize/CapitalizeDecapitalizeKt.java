package kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize;

import java.util.Iterator;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: capitalizeDecapitalize.kt */
/* loaded from: classes4.dex */
public final class CapitalizeDecapitalizeKt {
    @NotNull
    public static final String capitalizeAsciiOnly(@NotNull String capitalizeAsciiOnly) {
        char charAt;
        Intrinsics.checkParameterIsNotNull(capitalizeAsciiOnly, "$this$capitalizeAsciiOnly");
        if (!(capitalizeAsciiOnly.length() == 0) && 'a' <= (charAt = capitalizeAsciiOnly.charAt(0)) && 'z' >= charAt) {
            char upperCase = Character.toUpperCase(charAt);
            String substring = capitalizeAsciiOnly.substring(1);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
            return String.valueOf(upperCase) + substring;
        }
        return capitalizeAsciiOnly;
    }

    @NotNull
    public static final String decapitalizeAsciiOnly(@NotNull String decapitalizeAsciiOnly) {
        char charAt;
        Intrinsics.checkParameterIsNotNull(decapitalizeAsciiOnly, "$this$decapitalizeAsciiOnly");
        if (!(decapitalizeAsciiOnly.length() == 0) && 'A' <= (charAt = decapitalizeAsciiOnly.charAt(0)) && 'Z' >= charAt) {
            char lowerCase = Character.toLowerCase(charAt);
            String substring = decapitalizeAsciiOnly.substring(1);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
            return String.valueOf(lowerCase) + substring;
        }
        return decapitalizeAsciiOnly;
    }

    @NotNull
    public static final String decapitalizeSmartForCompiler(@NotNull String decapitalizeSmartForCompiler, boolean z) {
        String decapitalize;
        IntRange indices;
        Integer num;
        Intrinsics.checkParameterIsNotNull(decapitalizeSmartForCompiler, "$this$decapitalizeSmartForCompiler");
        if ((decapitalizeSmartForCompiler.length() == 0) || !isUpperCaseCharAt(decapitalizeSmartForCompiler, 0, z)) {
            return decapitalizeSmartForCompiler;
        }
        if (decapitalizeSmartForCompiler.length() == 1 || !isUpperCaseCharAt(decapitalizeSmartForCompiler, 1, z)) {
            if (z) {
                return decapitalizeAsciiOnly(decapitalizeSmartForCompiler);
            }
            decapitalize = StringsKt__StringsJVMKt.decapitalize(decapitalizeSmartForCompiler);
            return decapitalize;
        }
        indices = StringsKt__StringsKt.getIndices(decapitalizeSmartForCompiler);
        Iterator<Integer> it2 = indices.iterator();
        while (true) {
            if (!it2.hasNext()) {
                num = null;
                break;
            }
            num = it2.next();
            if (!isUpperCaseCharAt(decapitalizeSmartForCompiler, num.intValue(), z)) {
                break;
            }
        }
        Integer num2 = num;
        if (num2 != null) {
            int intValue = num2.intValue() - 1;
            StringBuilder sb = new StringBuilder();
            String substring = decapitalizeSmartForCompiler.substring(0, intValue);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            sb.append(toLowerCase(substring, z));
            String substring2 = decapitalizeSmartForCompiler.substring(intValue);
            Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
            sb.append(substring2);
            return sb.toString();
        }
        return toLowerCase(decapitalizeSmartForCompiler, z);
    }

    private static final boolean isUpperCaseCharAt(@NotNull String str, int i, boolean z) {
        char charAt = str.charAt(i);
        if (z) {
            return 'A' <= charAt && 'Z' >= charAt;
        }
        return Character.isUpperCase(charAt);
    }

    private static final String toLowerCase(String str, boolean z) {
        if (z) {
            return toLowerCaseAsciiOnly(str);
        }
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = str.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
        return lowerCase;
    }

    @NotNull
    public static final String toLowerCaseAsciiOnly(@NotNull String toLowerCaseAsciiOnly) {
        Intrinsics.checkParameterIsNotNull(toLowerCaseAsciiOnly, "$this$toLowerCaseAsciiOnly");
        StringBuilder sb = new StringBuilder(toLowerCaseAsciiOnly.length());
        int length = toLowerCaseAsciiOnly.length();
        for (int i = 0; i < length; i++) {
            char charAt = toLowerCaseAsciiOnly.charAt(i);
            if ('A' <= charAt && 'Z' >= charAt) {
                charAt = Character.toLowerCase(charAt);
            }
            sb.append(charAt);
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "builder.toString()");
        return sb2;
    }
}
