package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: RawType.kt */
/* loaded from: classes3.dex */
final class RawTypeImpl$render$1 extends Lambda implements Function2<String, String, Boolean> {
    public static final RawTypeImpl$render$1 INSTANCE = new RawTypeImpl$render$1();

    RawTypeImpl$render$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12248invoke(String str, String str2) {
        return Boolean.valueOf(invoke2(str, str2));
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final boolean invoke2(@NotNull String first, @NotNull String second) {
        String removePrefix;
        Intrinsics.checkParameterIsNotNull(first, "first");
        Intrinsics.checkParameterIsNotNull(second, "second");
        removePrefix = StringsKt__StringsKt.removePrefix(second, (CharSequence) "out ");
        return Intrinsics.areEqual(first, removePrefix) || Intrinsics.areEqual(second, "*");
    }
}
