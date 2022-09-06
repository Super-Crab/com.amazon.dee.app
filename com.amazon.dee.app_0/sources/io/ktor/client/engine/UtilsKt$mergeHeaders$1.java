package io.ktor.client.engine;

import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.content.OutgoingContent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Utils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/http/HeadersBuilder;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class UtilsKt$mergeHeaders$1 extends Lambda implements Function1<HeadersBuilder, Unit> {
    final /* synthetic */ OutgoingContent $content;
    final /* synthetic */ Headers $requestHeaders;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UtilsKt$mergeHeaders$1(Headers headers, OutgoingContent outgoingContent) {
        super(1);
        this.$requestHeaders = headers;
        this.$content = outgoingContent;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(HeadersBuilder headersBuilder) {
        invoke2(headersBuilder);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull HeadersBuilder receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.appendAll(this.$requestHeaders);
        receiver$0.appendAll(this.$content.getHeaders());
    }
}
