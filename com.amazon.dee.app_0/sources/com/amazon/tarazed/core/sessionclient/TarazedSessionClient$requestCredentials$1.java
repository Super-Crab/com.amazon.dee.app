package com.amazon.tarazed.core.sessionclient;

import com.amazon.alexa.routing.api.RouteParameter;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedSessionClient.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0082@"}, d2 = {"requestCredentials", "", "request", "Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/CreateCredentialsRequest;", "requestFromCache", "", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/CreateCredentialsResponse;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.core.sessionclient.TarazedSessionClient", f = "TarazedSessionClient.kt", i = {0, 0, 0, 0, 0}, l = {121}, m = "requestCredentials", n = {"this", "request", "requestFromCache", "body", RouteParameter.PATH}, s = {"L$0", "L$1", "Z$0", "L$2", "L$3"})
/* loaded from: classes13.dex */
public final class TarazedSessionClient$requestCredentials$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TarazedSessionClient this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedSessionClient$requestCredentials$1(TarazedSessionClient tarazedSessionClient, Continuation continuation) {
        super(continuation);
        this.this$0 = tarazedSessionClient;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.requestCredentials(null, false, this);
    }
}
