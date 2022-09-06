package com.amazon.tarazed.core.session;

import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
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
import kotlinx.coroutines.DelayKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedSession.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.core.session.TarazedSession$connectWebRTC$2", f = "TarazedSession.kt", i = {0, 1}, l = {512, 515}, m = "invokeSuspend", n = {"$this$launch", "$this$launch"}, s = {"L$0", "L$0"})
/* loaded from: classes13.dex */
public final class TarazedSession$connectWebRTC$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TarazedSession this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedSession$connectWebRTC$2(TarazedSession tarazedSession, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tarazedSession;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TarazedSession$connectWebRTC$2 tarazedSession$connectWebRTC$2 = new TarazedSession$connectWebRTC$2(this.this$0, completion);
        tarazedSession$connectWebRTC$2.p$ = (CoroutineScope) obj;
        return tarazedSession$connectWebRTC$2;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TarazedSession$connectWebRTC$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        CoroutineScope coroutineScope;
        TarazedMetricsHelper tarazedMetricsHelper;
        TarazedSessionLogger tarazedSessionLogger;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.p$;
            long wEBRTC_START_CONNECTION_TIMEOUT_MS$TarazedMobileCore_release = TarazedSession.Companion.getWEBRTC_START_CONNECTION_TIMEOUT_MS$TarazedMobileCore_release();
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(wEBRTC_START_CONNECTION_TIMEOUT_MS$TarazedMobileCore_release, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        tarazedMetricsHelper = this.this$0.metricsHelper;
        tarazedMetricsHelper.addCount("TarazedSession", "WebRTCAttemptConnectionTimeout", 1.0d);
        tarazedSessionLogger = this.this$0.logger;
        tarazedSessionLogger.i("TarazedSession", "Timeout occurred when attempting to connect over webRTC, ending session.");
        TarazedSession tarazedSession = this.this$0;
        this.L$0 = coroutineScope;
        this.label = 2;
        if (tarazedSession.endImmediately$TarazedMobileCore_release(this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
