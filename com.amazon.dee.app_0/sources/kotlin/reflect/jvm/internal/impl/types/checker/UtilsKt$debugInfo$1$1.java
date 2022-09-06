package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringBuilderJVMKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: utils.kt */
/* loaded from: classes4.dex */
final class UtilsKt$debugInfo$1$1 extends Lambda implements Function1<String, StringBuilder> {
    final /* synthetic */ StringBuilder $this_buildString;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UtilsKt$debugInfo$1$1(StringBuilder sb) {
        super(1);
        this.$this_buildString = sb;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final StringBuilder mo12165invoke(@NotNull String unaryPlus) {
        StringBuilder appendln;
        Intrinsics.checkParameterIsNotNull(unaryPlus, "$this$unaryPlus");
        StringBuilder sb = this.$this_buildString;
        sb.append(unaryPlus);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        appendln = StringsKt__StringBuilderJVMKt.appendln(sb);
        return appendln;
    }
}
