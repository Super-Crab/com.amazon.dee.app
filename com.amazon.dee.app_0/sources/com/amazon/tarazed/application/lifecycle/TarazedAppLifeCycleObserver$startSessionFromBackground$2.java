package com.amazon.tarazed.application.lifecycle;

import androidx.lifecycle.Lifecycle;
import com.amazon.tarazed.application.lifecycle.TarazedAppLifeCycleObserver;
import com.amazon.tarazed.core.logging.TarazedLogger;
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
/* compiled from: TarazedAppLifeCycleObserver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.application.lifecycle.TarazedAppLifeCycleObserver$startSessionFromBackground$2", f = "TarazedAppLifeCycleObserver.kt", i = {0}, l = {145}, m = "invokeSuspend", n = {"$this$withContext"}, s = {"L$0"})
/* loaded from: classes13.dex */
public final class TarazedAppLifeCycleObserver$startSessionFromBackground$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TarazedAppLifeCycleObserver this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedAppLifeCycleObserver$startSessionFromBackground$2(TarazedAppLifeCycleObserver tarazedAppLifeCycleObserver, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tarazedAppLifeCycleObserver;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TarazedAppLifeCycleObserver$startSessionFromBackground$2 tarazedAppLifeCycleObserver$startSessionFromBackground$2 = new TarazedAppLifeCycleObserver$startSessionFromBackground$2(this.this$0, completion);
        tarazedAppLifeCycleObserver$startSessionFromBackground$2.p$ = (CoroutineScope) obj;
        return tarazedAppLifeCycleObserver$startSessionFromBackground$2;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TarazedAppLifeCycleObserver$startSessionFromBackground$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        long j;
        TarazedLogger tarazedLogger;
        long j2;
        TarazedSessionNotifier tarazedSessionNotifier;
        TarazedAppLifeCycleObserver.Companion unused;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            j = this.this$0.startSessionNotificationDelay;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(j, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        tarazedLogger = this.this$0.logger;
        unused = TarazedAppLifeCycleObserver.Companion;
        StringBuilder sb = new StringBuilder();
        sb.append(Lifecycle.Event.ON_START);
        sb.append(" lifecycle event has not been called after ");
        j2 = this.this$0.startSessionNotificationDelay;
        sb.append(j2);
        sb.append("ms, notifying system that session is starting with app in background");
        tarazedLogger.i("TarazedLifeCycObserver", sb.toString());
        tarazedSessionNotifier = this.this$0.notifier;
        tarazedSessionNotifier.notify3pSessionStartingInBackground();
        this.this$0.startSessionTimer = null;
        return Unit.INSTANCE;
    }
}
