package com.amazon.tarazed.sessionmanager;

import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notification.type.TarazedLaunchRequest;
import com.amazon.tarazed.core.session.TarazedSession;
import com.amazon.tarazed.core.session.TarazedSessionStateChangeRequest;
import com.amazon.tarazed.core.session.TarazedSessionStateChangeSource;
import com.amazon.tarazed.core.session.TarazedSessionStateChangeType;
import com.amazon.tarazed.sessionmanager.TarazedController;
import java.util.concurrent.ConcurrentSkipListSet;
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
/* compiled from: TarazedController.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.sessionmanager.TarazedController$handleLaunchRequest$3", f = "TarazedController.kt", i = {0}, l = {337}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* loaded from: classes13.dex */
public final class TarazedController$handleLaunchRequest$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ TarazedLaunchRequest $launchRequest;
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TarazedController this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedController$handleLaunchRequest$3(TarazedController tarazedController, TarazedLaunchRequest tarazedLaunchRequest, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tarazedController;
        this.$launchRequest = tarazedLaunchRequest;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TarazedController$handleLaunchRequest$3 tarazedController$handleLaunchRequest$3 = new TarazedController$handleLaunchRequest$3(this.this$0, this.$launchRequest, completion);
        tarazedController$handleLaunchRequest$3.p$ = (CoroutineScope) obj;
        return tarazedController$handleLaunchRequest$3;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TarazedController$handleLaunchRequest$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        ConcurrentSkipListSet concurrentSkipListSet;
        TarazedSessionLogger tarazedSessionLogger;
        SessionNotificationManager sessionNotificationManager;
        TarazedSessionLogger tarazedSessionLogger2;
        TarazedMetricsHelper tarazedMetricsHelper;
        ConcurrentSkipListSet concurrentSkipListSet2;
        TarazedSession session;
        TarazedSessionLogger tarazedSessionLogger3;
        TarazedMetricsHelper tarazedMetricsHelper2;
        TarazedSessionLogger tarazedSessionLogger4;
        TarazedMetricsHelper tarazedMetricsHelper3;
        TarazedController.Companion unused;
        TarazedController.Companion unused2;
        TarazedController.Companion unused3;
        TarazedController.Companion unused4;
        TarazedController.Companion unused5;
        TarazedController.Companion unused6;
        TarazedController.Companion unused7;
        TarazedController.Companion unused8;
        TarazedController.Companion unused9;
        TarazedController.Companion unused10;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.L$0 = this.p$;
            this.label = 1;
            if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        if (this.$launchRequest.getNotificationTime() < this.this$0.getLastLaunchTimestamp$TarazedAndroidLibrary_release().getValue()) {
            tarazedSessionLogger4 = this.this$0.logger;
            unused = TarazedController.Companion;
            tarazedSessionLogger4.w("TarazedSessionControl", "Notification timestamp " + this.$launchRequest.getNotificationTime() + " is older than the last received timestamp of " + this.this$0.getLastLaunchTimestamp$TarazedAndroidLibrary_release() + ", ignoring");
            tarazedMetricsHelper3 = this.this$0.metricsHelper;
            unused2 = TarazedController.Companion;
            unused3 = TarazedController.Companion;
            tarazedMetricsHelper3.addCount("TarazedSessionControl", "DatedLaunchRequest", 1.0d);
            return Unit.INSTANCE;
        }
        concurrentSkipListSet = this.this$0.encounteredLaunchRequestSet;
        if (!concurrentSkipListSet.add(this.$launchRequest)) {
            tarazedSessionLogger3 = this.this$0.logger;
            unused4 = TarazedController.Companion;
            tarazedSessionLogger3.w("TarazedSessionControl", "Launch request " + this.$launchRequest + " is identical to one already being processed, ignoring");
            tarazedMetricsHelper2 = this.this$0.metricsHelper;
            unused5 = TarazedController.Companion;
            unused6 = TarazedController.Companion;
            tarazedMetricsHelper2.addCount("TarazedSessionControl", "DuplicateLaunchRequest", 1.0d);
            return Unit.INSTANCE;
        }
        if (this.this$0.isSessionActive$TarazedAndroidLibrary_release()) {
            sessionNotificationManager = this.this$0.getSessionNotificationManager();
            sessionNotificationManager.notifySessionStarting();
            tarazedSessionLogger2 = this.this$0.logger;
            unused7 = TarazedController.Companion;
            tarazedSessionLogger2.i("TarazedSessionControl", "An older session is already active, ending and creating new instance");
            tarazedMetricsHelper = this.this$0.metricsHelper;
            unused8 = TarazedController.Companion;
            unused9 = TarazedController.Companion;
            tarazedMetricsHelper.addCount("TarazedSessionControl", "ReplacedDatedSession", 1.0d);
            concurrentSkipListSet2 = this.this$0.launchRequestQueue;
            concurrentSkipListSet2.add(this.$launchRequest);
            session = this.this$0.getSession();
            if (session != null) {
                session.sendStateChangeEventRequest(new TarazedSessionStateChangeRequest(TarazedSessionStateChangeType.END_SESSION, TarazedSessionStateChangeSource.SOURCE_OTHER));
            }
        } else {
            tarazedSessionLogger = this.this$0.logger;
            unused10 = TarazedController.Companion;
            tarazedSessionLogger.i("TarazedSessionControl", "Launching new session instance");
            this.this$0.launchSession(this.$launchRequest, false);
        }
        return Unit.INSTANCE;
    }
}
