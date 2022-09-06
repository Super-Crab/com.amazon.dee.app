package io.ktor.client.engine;

import io.ktor.http.HttpHeaders;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Utils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "key", "", "values", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class UtilsKt$mergeHeaders$2 extends Lambda implements Function2<String, List<? extends String>, Unit> {
    final /* synthetic */ Function2 $block;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UtilsKt$mergeHeaders$2(Function2 function2) {
        super(2);
        this.$block = function2;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12248invoke(String str, List<? extends String> list) {
        invoke2(str, (List<String>) list);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull String key, @NotNull List<String> values) {
        String joinToString$default;
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(values, "values");
        if (!Intrinsics.areEqual(HttpHeaders.INSTANCE.getContentLength(), key) && !Intrinsics.areEqual(HttpHeaders.INSTANCE.getContentType(), key)) {
            Function2 function2 = this.$block;
            joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(values, ";", null, null, 0, null, null, 62, null);
            function2.mo12248invoke(key, joinToString$default);
        }
    }
}
