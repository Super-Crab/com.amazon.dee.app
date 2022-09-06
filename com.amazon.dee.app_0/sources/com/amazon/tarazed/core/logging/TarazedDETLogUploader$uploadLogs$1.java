package com.amazon.tarazed.core.logging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringBuilderJVMKt;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedDETLogUploader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "line", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedDETLogUploader$uploadLogs$1 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ StringBuilder $logContents;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedDETLogUploader$uploadLogs$1(StringBuilder sb) {
        super(1);
        this.$logContents = sb;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull String line) {
        Intrinsics.checkParameterIsNotNull(line, "line");
        StringBuilder sb = this.$logContents;
        sb.append(line);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        StringsKt__StringBuilderJVMKt.appendln(sb);
    }
}
