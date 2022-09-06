package com.amazon.tarazed.core.notifier;

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
import kotlinx.coroutines.channels.Channel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedSessionNotifier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.core.notifier.TarazedSessionNotifier$notifyEvent$1", f = "TarazedSessionNotifier.kt", i = {0}, l = {232}, m = "invokeSuspend", n = {"$this$runBlocking"}, s = {"L$0"})
/* loaded from: classes13.dex */
public final class TarazedSessionNotifier$notifyEvent$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ TarazedNotificationEvent $eventID;
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TarazedSessionNotifier this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedSessionNotifier$notifyEvent$1(TarazedSessionNotifier tarazedSessionNotifier, TarazedNotificationEvent tarazedNotificationEvent, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tarazedSessionNotifier;
        this.$eventID = tarazedNotificationEvent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TarazedSessionNotifier$notifyEvent$1 tarazedSessionNotifier$notifyEvent$1 = new TarazedSessionNotifier$notifyEvent$1(this.this$0, this.$eventID, completion);
        tarazedSessionNotifier$notifyEvent$1.p$ = (CoroutineScope) obj;
        return tarazedSessionNotifier$notifyEvent$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TarazedSessionNotifier$notifyEvent$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        Channel channel;
        TarazedMetricsHelper tarazedMetricsHelper;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            channel = this.this$0.eventChannel;
            TarazedNotificationEvent tarazedNotificationEvent = this.$eventID;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (channel.send(tarazedNotificationEvent, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        if (this.this$0.getBufferSize$TarazedMobileCore_release().incrementAndGet() > 3) {
            tarazedMetricsHelper = this.this$0.metricsHelper;
            tarazedMetricsHelper.addCount(TarazedSessionNotifier.TAG, TarazedSessionNotifier.METRIC_BUFFER_EXCEEDED_EXPECTED_SIZE, 1.0d);
        }
        return Unit.INSTANCE;
    }
}
