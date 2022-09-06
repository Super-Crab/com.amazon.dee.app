package com.amazon.tarazed.core.utility;

import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.signaling.TarazedEventDispatcher;
import com.amazon.tarazed.core.signaling.TarazedIoTManager;
import com.amazon.tarazed.core.utility.BrowserPresenceDetectorAtSessionStart;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BrowserPresenceDetectorAtSessionStart.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.core.utility.BrowserPresenceDetectorAtSessionStart$isBrowserPresent$2", f = "BrowserPresenceDetectorAtSessionStart.kt", i = {0, 0, 0}, l = {38}, m = "invokeSuspend", n = {"$this$coroutineScope", "deferred", OperationalEventType.TIMER}, s = {"L$0", "L$1", "L$2"})
/* loaded from: classes13.dex */
public final class BrowserPresenceDetectorAtSessionStart$isBrowserPresent$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ long $timeout;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ BrowserPresenceDetectorAtSessionStart this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BrowserPresenceDetectorAtSessionStart$isBrowserPresent$2(BrowserPresenceDetectorAtSessionStart browserPresenceDetectorAtSessionStart, long j, Continuation continuation) {
        super(2, continuation);
        this.this$0 = browserPresenceDetectorAtSessionStart;
        this.$timeout = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        BrowserPresenceDetectorAtSessionStart$isBrowserPresent$2 browserPresenceDetectorAtSessionStart$isBrowserPresent$2 = new BrowserPresenceDetectorAtSessionStart$isBrowserPresent$2(this.this$0, this.$timeout, completion);
        browserPresenceDetectorAtSessionStart$isBrowserPresent$2.p$ = (CoroutineScope) obj;
        return browserPresenceDetectorAtSessionStart$isBrowserPresent$2;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((BrowserPresenceDetectorAtSessionStart$isBrowserPresent$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        TarazedEventDispatcher tarazedEventDispatcher;
        TarazedSessionLogger tarazedSessionLogger;
        TarazedIoTManager tarazedIoTManager;
        Job job;
        TarazedSessionLogger tarazedSessionLogger2;
        TarazedSessionLogger tarazedSessionLogger3;
        TarazedEventDispatcher tarazedEventDispatcher2;
        BrowserPresenceDetectorAtSessionStart.Companion unused;
        BrowserPresenceDetectorAtSessionStart.Companion unused2;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            CompletableDeferred<Boolean> CompletableDeferred$default = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
            tarazedEventDispatcher = this.this$0.eventDispatcher;
            tarazedSessionLogger = this.this$0.logger;
            tarazedEventDispatcher.registerHandler(new BrowserPongHandler(CompletableDeferred$default, tarazedSessionLogger));
            Job pongTimer = this.this$0.setPongTimer(coroutineScope, this.$timeout, CompletableDeferred$default);
            tarazedIoTManager = this.this$0.iotManager;
            tarazedIoTManager.pingBrowser();
            this.L$0 = coroutineScope;
            this.L$1 = CompletableDeferred$default;
            this.L$2 = pongTimer;
            this.label = 1;
            obj = CompletableDeferred$default.await(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            job = pongTimer;
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            job = (Job) this.L$2;
            CompletableDeferred completableDeferred = (CompletableDeferred) this.L$1;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        boolean booleanValue = ((Boolean) obj).booleanValue();
        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        for (String str : BrowserPongHandler.Companion.getHandledEventTypes$TarazedMobileCore_release()) {
            tarazedEventDispatcher2 = this.this$0.eventDispatcher;
            tarazedEventDispatcher2.deregisterHandlerForKey(str);
        }
        if (booleanValue) {
            tarazedSessionLogger3 = this.this$0.logger;
            unused = BrowserPresenceDetectorAtSessionStart.Companion;
            tarazedSessionLogger3.i("BrwsrDtectorStart", "Browser presence detected, launching primer");
        } else {
            tarazedSessionLogger2 = this.this$0.logger;
            unused2 = BrowserPresenceDetectorAtSessionStart.Companion;
            tarazedSessionLogger2.i("BrwsrDtectorStart", "Browser presence not detected, session will not launch primer");
        }
        return Boxing.boxBoolean(booleanValue);
    }
}
