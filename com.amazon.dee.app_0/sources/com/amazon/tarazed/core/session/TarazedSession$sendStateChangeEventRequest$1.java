package com.amazon.tarazed.core.session;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.apache.commons.net.telnet.TelnetCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedSession.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.core.session.TarazedSession$sendStateChangeEventRequest$1", f = "TarazedSession.kt", i = {0}, l = {TelnetCommand.AYT}, m = "invokeSuspend", n = {"$this$runBlocking"}, s = {"L$0"})
/* loaded from: classes13.dex */
public final class TarazedSession$sendStateChangeEventRequest$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ TarazedSessionStateChangeRequest $state;
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TarazedSession this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedSession$sendStateChangeEventRequest$1(TarazedSession tarazedSession, TarazedSessionStateChangeRequest tarazedSessionStateChangeRequest, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tarazedSession;
        this.$state = tarazedSessionStateChangeRequest;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TarazedSession$sendStateChangeEventRequest$1 tarazedSession$sendStateChangeEventRequest$1 = new TarazedSession$sendStateChangeEventRequest$1(this.this$0, this.$state, completion);
        tarazedSession$sendStateChangeEventRequest$1.p$ = (CoroutineScope) obj;
        return tarazedSession$sendStateChangeEventRequest$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TarazedSession$sendStateChangeEventRequest$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            TarazedStateChangeChannel stateChangeChannel$TarazedMobileCore_release = this.this$0.getStateChangeChannel$TarazedMobileCore_release();
            TarazedSessionStateChangeRequest tarazedSessionStateChangeRequest = this.$state;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (stateChangeChannel$TarazedMobileCore_release.send(tarazedSessionStateChangeRequest, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
