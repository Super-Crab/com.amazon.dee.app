package kotlin.reflect.jvm.internal.impl.name;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FqNamesUtil.kt */
/* loaded from: classes4.dex */
public final class FqNamesUtilKt {

    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[State.values().length];

        static {
            $EnumSwitchMapping$0[State.BEGINNING.ordinal()] = 1;
            $EnumSwitchMapping$0[State.AFTER_DOT.ordinal()] = 2;
            $EnumSwitchMapping$0[State.MIDDLE.ordinal()] = 3;
        }
    }

    public static final boolean isSubpackageOf(@NotNull FqName isSubpackageOf, @NotNull FqName packageName) {
        Intrinsics.checkParameterIsNotNull(isSubpackageOf, "$this$isSubpackageOf");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        if (!Intrinsics.areEqual(isSubpackageOf, packageName) && !packageName.isRoot()) {
            String asString = isSubpackageOf.asString();
            Intrinsics.checkExpressionValueIsNotNull(asString, "this.asString()");
            String asString2 = packageName.asString();
            Intrinsics.checkExpressionValueIsNotNull(asString2, "packageName.asString()");
            return isSubpackageOf(asString, asString2);
        }
        return true;
    }

    public static final boolean isValidJavaFqName(@Nullable String str) {
        if (str == null) {
            return false;
        }
        State state = State.BEGINNING;
        int length = str.length();
        State state2 = state;
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            int i2 = WhenMappings.$EnumSwitchMapping$0[state2.ordinal()];
            if (i2 == 1 || i2 == 2) {
                if (!Character.isJavaIdentifierPart(charAt)) {
                    return false;
                }
                state2 = State.MIDDLE;
            } else if (i2 != 3) {
                continue;
            } else if (charAt == '.') {
                state2 = State.AFTER_DOT;
            } else if (!Character.isJavaIdentifierPart(charAt)) {
                return false;
            }
        }
        return state2 != State.AFTER_DOT;
    }

    @NotNull
    public static final FqName tail(@NotNull FqName tail, @NotNull FqName prefix) {
        Intrinsics.checkParameterIsNotNull(tail, "$this$tail");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        if (!isSubpackageOf(tail, prefix) || prefix.isRoot()) {
            return tail;
        }
        if (Intrinsics.areEqual(tail, prefix)) {
            FqName fqName = FqName.ROOT;
            Intrinsics.checkExpressionValueIsNotNull(fqName, "FqName.ROOT");
            return fqName;
        }
        String asString = tail.asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "asString()");
        String substring = asString.substring(prefix.asString().length() + 1);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
        return new FqName(substring);
    }

    private static final boolean isSubpackageOf(String str, String str2) {
        boolean startsWith$default;
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(str, str2, false, 2, null);
        return startsWith$default && str.charAt(str2.length()) == '.';
    }
}
