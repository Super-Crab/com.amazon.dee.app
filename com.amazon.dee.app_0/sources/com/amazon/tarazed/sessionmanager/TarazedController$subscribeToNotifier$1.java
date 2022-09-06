package com.amazon.tarazed.sessionmanager;

import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.notifier.ListenerPriority;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.sessionmanager.TarazedController;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedController.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.sessionmanager.TarazedController$subscribeToNotifier$1", f = "TarazedController.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes13.dex */
public final class TarazedController$subscribeToNotifier$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TarazedController this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedController$subscribeToNotifier$1(TarazedController tarazedController, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tarazedController;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TarazedController$subscribeToNotifier$1 tarazedController$subscribeToNotifier$1 = new TarazedController$subscribeToNotifier$1(this.this$0, completion);
        tarazedController$subscribeToNotifier$1.p$ = (CoroutineScope) obj;
        return tarazedController$subscribeToNotifier$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TarazedController$subscribeToNotifier$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        TarazedSessionNotifier sessionNotifier;
        Function2 function2;
        DeviceInfoUtility deviceInfoUtility;
        TarazedSessionNotifier sessionNotifier2;
        SessionNotificationManager sessionNotificationManager;
        HeadsUpNotificationManager headsUpNotificationManager;
        TarazedSessionLogger tarazedSessionLogger;
        TarazedSessionNotifier sessionNotifier3;
        SessionNotificationManager sessionNotificationManager2;
        TarazedController.Companion unused;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            sessionNotifier = this.this$0.getSessionNotifier();
            function2 = this.this$0.sessionNotificationHandler;
            TarazedSessionNotifier.subscribe$default(sessionNotifier, function2, ListenerPriority.MEDIUM, false, 4, null);
            deviceInfoUtility = this.this$0.deviceInfo;
            if (deviceInfoUtility.is1PDevice()) {
                sessionNotifier3 = this.this$0.getSessionNotifier();
                sessionNotificationManager2 = this.this$0.getSessionNotificationManager();
                TarazedSessionNotifier.subscribe$default(sessionNotifier3, sessionNotificationManager2.getSessionNotificationHandler1P(), ListenerPriority.MEDIUM, false, 4, null);
            } else {
                sessionNotifier2 = this.this$0.getSessionNotifier();
                sessionNotificationManager = this.this$0.getSessionNotificationManager();
                TarazedSessionNotifier.subscribe$default(sessionNotifier2, sessionNotificationManager.getSessionNotificationHandler3P(), ListenerPriority.MEDIUM, false, 4, null);
                headsUpNotificationManager = this.this$0.headsUpNotificationManager;
                headsUpNotificationManager.subscribeToNotifier();
            }
            tarazedSessionLogger = this.this$0.logger;
            unused = TarazedController.Companion;
            tarazedSessionLogger.i("TarazedSessionControl", "Subscribed to notifier.");
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
