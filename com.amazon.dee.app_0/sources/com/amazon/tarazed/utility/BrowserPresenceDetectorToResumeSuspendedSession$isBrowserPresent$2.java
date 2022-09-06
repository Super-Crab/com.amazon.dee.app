package com.amazon.tarazed.utility;

import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BrowserPresenceDetectorToResumeSuspendedSession.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession$isBrowserPresent$2", f = "BrowserPresenceDetectorToResumeSuspendedSession.kt", i = {0, 1, 1, 1}, l = {60, 69}, m = "invokeSuspend", n = {"$this$coroutineScope", "$this$coroutineScope", "deferred", OperationalEventType.TIMER}, s = {"L$0", "L$0", "L$1", "L$2"})
/* loaded from: classes13.dex */
public final class BrowserPresenceDetectorToResumeSuspendedSession$isBrowserPresent$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ long $timeout;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ BrowserPresenceDetectorToResumeSuspendedSession this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BrowserPresenceDetectorToResumeSuspendedSession$isBrowserPresent$2(BrowserPresenceDetectorToResumeSuspendedSession browserPresenceDetectorToResumeSuspendedSession, long j, Continuation continuation) {
        super(2, continuation);
        this.this$0 = browserPresenceDetectorToResumeSuspendedSession;
        this.$timeout = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        BrowserPresenceDetectorToResumeSuspendedSession$isBrowserPresent$2 browserPresenceDetectorToResumeSuspendedSession$isBrowserPresent$2 = new BrowserPresenceDetectorToResumeSuspendedSession$isBrowserPresent$2(this.this$0, this.$timeout, completion);
        browserPresenceDetectorToResumeSuspendedSession$isBrowserPresent$2.p$ = (CoroutineScope) obj;
        return browserPresenceDetectorToResumeSuspendedSession$isBrowserPresent$2;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((BrowserPresenceDetectorToResumeSuspendedSession$isBrowserPresent$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00b6  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 2
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L2f
            if (r1 == r4) goto L27
            if (r1 != r2) goto L1f
            java.lang.Object r0 = r8.L$2
            kotlinx.coroutines.Job r0 = (kotlinx.coroutines.Job) r0
            java.lang.Object r1 = r8.L$1
            kotlinx.coroutines.CompletableDeferred r1 = (kotlinx.coroutines.CompletableDeferred) r1
            java.lang.Object r1 = r8.L$0
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            kotlin.ResultKt.throwOnFailure(r9)
            goto L88
        L1f:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L27:
            java.lang.Object r1 = r8.L$0
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            kotlin.ResultKt.throwOnFailure(r9)
            goto L41
        L2f:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.coroutines.CoroutineScope r1 = r8.p$
            com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession r9 = r8.this$0
            r8.L$0 = r1
            r8.label = r4
            java.lang.Object r9 = r9.connectIot$TarazedAndroidLibrary_release(r8)
            if (r9 != r0) goto L41
            return r0
        L41:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 != 0) goto L4f
            r9 = 0
            java.lang.Boolean r9 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r9)
            return r9
        L4f:
            kotlinx.coroutines.CompletableDeferred r9 = kotlinx.coroutines.CompletableDeferredKt.CompletableDeferred$default(r3, r4, r3)
            com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession r5 = r8.this$0
            com.amazon.tarazed.core.signaling.TarazedEventDispatcher r5 = com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession.access$getEventDispatcher$p(r5)
            com.amazon.tarazed.core.utility.BrowserPongHandler r6 = new com.amazon.tarazed.core.utility.BrowserPongHandler
            com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession r7 = r8.this$0
            com.amazon.tarazed.core.logging.TarazedSessionLogger r7 = com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession.access$getLogger$p(r7)
            r6.<init>(r9, r7)
            r5.registerHandler(r6)
            com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession r5 = r8.this$0
            long r6 = r8.$timeout
            kotlinx.coroutines.Job r5 = r5.setPongTimer(r1, r6, r9)
            com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession r6 = r8.this$0
            com.amazon.tarazed.core.signaling.TarazedIoTManager r6 = com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession.access$getIotManager$p(r6)
            r6.pingBrowser()
            r8.L$0 = r1
            r8.L$1 = r9
            r8.L$2 = r5
            r8.label = r2
            java.lang.Object r9 = r9.await(r8)
            if (r9 != r0) goto L87
            return r0
        L87:
            r0 = r5
        L88:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            kotlinx.coroutines.Job.DefaultImpls.cancel$default(r0, r3, r4, r3)
            com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession r0 = r8.this$0
            com.amazon.tarazed.core.signaling.TarazedIoTManager r0 = com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession.access$getIotManager$p(r0)
            r0.disconnect()
            com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession r0 = r8.this$0
            com.amazon.tarazed.core.signaling.TarazedEventDispatcher r0 = com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession.access$getEventDispatcher$p(r0)
            r0.deregisterHandlers()
            java.lang.String r0 = "BrwsrDtectorResume"
            if (r9 == 0) goto Lb6
            com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession r1 = r8.this$0
            com.amazon.tarazed.core.logging.TarazedSessionLogger r1 = com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession.access$getLogger$p(r1)
            com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession.access$Companion()
            java.lang.String r2 = "Browser presence detected, able to reconnect to session"
            r1.i(r0, r2)
            goto Lc4
        Lb6:
            com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession r1 = r8.this$0
            com.amazon.tarazed.core.logging.TarazedSessionLogger r1 = com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession.access$getLogger$p(r1)
            com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession.access$Companion()
            java.lang.String r2 = "Browser presence not detected, unable to reconnect to session"
            r1.i(r0, r2)
        Lc4:
            java.lang.Boolean r9 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r9)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession$isBrowserPresent$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
