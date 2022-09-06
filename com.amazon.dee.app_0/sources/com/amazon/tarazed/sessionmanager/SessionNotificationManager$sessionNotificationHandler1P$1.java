package com.amazon.tarazed.sessionmanager;

import com.amazon.tarazed.core.notifier.TarazedNotificationEvent;
import com.amazon.tarazed.sessionmanager.SessionNotificationManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SessionNotificationManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/amazon/tarazed/core/notifier/TarazedNotificationEvent;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.sessionmanager.SessionNotificationManager$sessionNotificationHandler1P$1", f = "SessionNotificationManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes13.dex */
public final class SessionNotificationManager$sessionNotificationHandler1P$1 extends SuspendLambda implements Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> {
    int label;
    private TarazedNotificationEvent p$0;
    final /* synthetic */ SessionNotificationManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SessionNotificationManager$sessionNotificationHandler1P$1(SessionNotificationManager sessionNotificationManager, Continuation continuation) {
        super(2, continuation);
        this.this$0 = sessionNotificationManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SessionNotificationManager$sessionNotificationHandler1P$1 sessionNotificationManager$sessionNotificationHandler1P$1 = new SessionNotificationManager$sessionNotificationHandler1P$1(this.this$0, completion);
        sessionNotificationManager$sessionNotificationHandler1P$1.p$0 = (TarazedNotificationEvent) obj;
        return sessionNotificationManager$sessionNotificationHandler1P$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(TarazedNotificationEvent tarazedNotificationEvent, Continuation<? super Unit> continuation) {
        return ((SessionNotificationManager$sessionNotificationHandler1P$1) create(tarazedNotificationEvent, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            boolean z5 = true;
            switch (SessionNotificationManager.WhenMappings.$EnumSwitchMapping$1[this.p$0.ordinal()]) {
                case 1:
                    this.this$0.notifySessionConnecting();
                    break;
                case 2:
                    z = this.this$0.isSessionPaused;
                    if (!z) {
                        this.this$0.notifySessionActive();
                    }
                    this.this$0.hasSessionStarted = true;
                    break;
                case 3:
                case 4:
                    this.this$0.notifySessionActive();
                    this.this$0.isSessionPaused = false;
                    break;
                case 5:
                case 6:
                case 7:
                    this.this$0.notifySessionPaused();
                    this.this$0.isSessionPaused = true;
                    break;
                case 8:
                    z2 = this.this$0.hasSessionStarted;
                    if (z2) {
                        z4 = this.this$0.hasProfileSwitchOccurred;
                        if (!z4) {
                            z5 = false;
                        }
                    }
                    z3 = this.this$0.hasSessionStarted;
                    if (z3) {
                        this.this$0.notifySessionEnded();
                    }
                    this.this$0.endForegroundService(z5);
                    this.this$0.hasSessionStarted = false;
                    break;
                case 9:
                    this.this$0.hasProfileSwitchOccurred = true;
                    break;
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
