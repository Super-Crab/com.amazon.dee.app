package io.ktor.client.call;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HttpClientCall.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022)\b\u0002\u0010\u0003\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004¢\u0006\u0002\b\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0006H\u0086@ø\u0001\u0000"}, d2 = {"call", "", "Lio/ktor/client/HttpClient;", "block", "Lkotlin/Function2;", "Lio/ktor/client/request/HttpRequestBuilder;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "continuation", "Lio/ktor/client/call/HttpClientCall;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.client.call.HttpClientCallKt", f = "HttpClientCall.kt", i = {0, 0, 0, 1, 1}, l = {80, 80}, m = "call", n = {"$receiver", "block", "$receiver", "$receiver", "block"}, s = {"L$0", "L$1", "L$3", "L$0", "L$1"})
/* loaded from: classes3.dex */
public final class HttpClientCallKt$call$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpClientCallKt$call$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return HttpClientCallKt.call(null, null, this);
    }
}
