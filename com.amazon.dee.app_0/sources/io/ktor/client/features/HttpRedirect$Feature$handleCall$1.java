package io.ktor.client.features;

import io.ktor.client.features.HttpRedirect;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HttpRedirect.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0082@ø\u0001\u0000"}, d2 = {"handleCall", "", "Lio/ktor/client/features/Sender;", "origin", "Lio/ktor/client/call/HttpClientCall;", "continuation", "Lkotlin/coroutines/Continuation;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.client.features.HttpRedirect$Feature", f = "HttpRedirect.kt", i = {0, 0, 0, 0, 0}, l = {34}, m = "handleCall", n = {"this", "$receiver", "origin", "call", "location"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
/* loaded from: classes3.dex */
public final class HttpRedirect$Feature$handleCall$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HttpRedirect.Feature this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpRedirect$Feature$handleCall$1(HttpRedirect.Feature feature, Continuation continuation) {
        super(continuation);
        this.this$0 = feature;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.handleCall(null, null, this);
    }
}
