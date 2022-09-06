package io.ktor.client.request;

import io.ktor.util.Attributes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/util/Attributes;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class HttpRequestBuilder$setAttributes$1 extends Lambda implements Function1<Attributes, Unit> {
    final /* synthetic */ Function1 $block;
    final /* synthetic */ Function1 $old;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpRequestBuilder$setAttributes$1(Function1 function1, Function1 function12) {
        super(1);
        this.$old = function1;
        this.$block = function12;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Attributes attributes) {
        invoke2(attributes);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull Attributes receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        this.$old.mo12165invoke(receiver$0);
        this.$block.mo12165invoke(receiver$0);
    }
}
