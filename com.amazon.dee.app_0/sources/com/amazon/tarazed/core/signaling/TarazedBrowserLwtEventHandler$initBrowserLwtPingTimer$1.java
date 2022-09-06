package com.amazon.tarazed.core.signaling;

import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
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
/* compiled from: TarazedBrowserLwtEventHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.core.signaling.TarazedBrowserLwtEventHandler$initBrowserLwtPingTimer$1", f = "TarazedBrowserLwtEventHandler.kt", i = {0}, l = {103}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* loaded from: classes13.dex */
public final class TarazedBrowserLwtEventHandler$initBrowserLwtPingTimer$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TarazedBrowserLwtEventHandler this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedBrowserLwtEventHandler$initBrowserLwtPingTimer$1(TarazedBrowserLwtEventHandler tarazedBrowserLwtEventHandler, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tarazedBrowserLwtEventHandler;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TarazedBrowserLwtEventHandler$initBrowserLwtPingTimer$1 tarazedBrowserLwtEventHandler$initBrowserLwtPingTimer$1 = new TarazedBrowserLwtEventHandler$initBrowserLwtPingTimer$1(this.this$0, completion);
        tarazedBrowserLwtEventHandler$initBrowserLwtPingTimer$1.p$ = (CoroutineScope) obj;
        return tarazedBrowserLwtEventHandler$initBrowserLwtPingTimer$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TarazedBrowserLwtEventHandler$initBrowserLwtPingTimer$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        TarazedSessionLogger tarazedSessionLogger;
        TarazedMetricsHelper tarazedMetricsHelper;
        TarazedIoTManager tarazedIoTManager;
        TarazedSessionNotifier tarazedSessionNotifier;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            long mAX_RECONNECT_WAIT_MS$TarazedMobileCore_release = TarazedBrowserLwtEventHandler.Companion.getMAX_RECONNECT_WAIT_MS$TarazedMobileCore_release();
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(mAX_RECONNECT_WAIT_MS$TarazedMobileCore_release, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        tarazedSessionLogger = this.this$0.logger;
        tarazedSessionLogger.w("BrowserLwtEventHandler", "IoT reconnect wait time has elapsed after receiving LWT, ending session");
        tarazedMetricsHelper = this.this$0.metricsHelper;
        tarazedMetricsHelper.addCountHighPriority("BrowserLwtEventHandler", "BrowserLwtReconnectTimeout", 1.0d);
        tarazedIoTManager = this.this$0.iotManager;
        tarazedIoTManager.disconnect();
        tarazedSessionNotifier = this.this$0.sessionNotifier;
        tarazedSessionNotifier.notifyIotDisconnected();
        return Unit.INSTANCE;
    }
}
