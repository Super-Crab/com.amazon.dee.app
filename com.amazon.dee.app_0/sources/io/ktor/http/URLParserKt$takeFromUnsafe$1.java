package io.ktor.http;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: URLParser.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "key", "", "values", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class URLParserKt$takeFromUnsafe$1 extends Lambda implements Function2<String, List<? extends String>, Unit> {
    final /* synthetic */ URLBuilder $this_takeFromUnsafe;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public URLParserKt$takeFromUnsafe$1(URLBuilder uRLBuilder) {
        super(2);
        this.$this_takeFromUnsafe = uRLBuilder;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12248invoke(String str, List<? extends String> list) {
        invoke2(str, (List<String>) list);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull String key, @NotNull List<String> values) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(values, "values");
        this.$this_takeFromUnsafe.getParameters().appendAll(key, values);
    }
}
