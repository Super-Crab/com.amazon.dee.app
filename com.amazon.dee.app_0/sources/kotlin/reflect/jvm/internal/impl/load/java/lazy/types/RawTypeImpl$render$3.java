package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
/* compiled from: RawType.kt */
/* loaded from: classes3.dex */
final class RawTypeImpl$render$3 extends Lambda implements Function2<String, String, String> {
    public static final RawTypeImpl$render$3 INSTANCE = new RawTypeImpl$render$3();

    RawTypeImpl$render$3() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final String mo12248invoke(@NotNull String replaceArgs, @NotNull String newArgs) {
        boolean contains$default;
        String substringBefore$default;
        String substringAfterLast$default;
        Intrinsics.checkParameterIsNotNull(replaceArgs, "$this$replaceArgs");
        Intrinsics.checkParameterIsNotNull(newArgs, "newArgs");
        contains$default = StringsKt__StringsKt.contains$default((CharSequence) replaceArgs, (char) Typography.less, false, 2, (Object) null);
        if (!contains$default) {
            return replaceArgs;
        }
        StringBuilder sb = new StringBuilder();
        substringBefore$default = StringsKt__StringsKt.substringBefore$default(replaceArgs, (char) Typography.less, (String) null, 2, (Object) null);
        sb.append(substringBefore$default);
        sb.append(Typography.less);
        sb.append(newArgs);
        sb.append(Typography.greater);
        substringAfterLast$default = StringsKt__StringsKt.substringAfterLast$default(replaceArgs, (char) Typography.greater, (String) null, 2, (Object) null);
        sb.append(substringAfterLast$default);
        return sb.toString();
    }
}
