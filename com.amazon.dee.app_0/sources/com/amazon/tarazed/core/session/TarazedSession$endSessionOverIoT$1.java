package com.amazon.tarazed.core.session;

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
/* compiled from: TarazedSession.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.core.session.TarazedSession$endSessionOverIoT$1", f = "TarazedSession.kt", i = {0, 0}, l = {772}, m = "invokeSuspend", n = {"$this$launch", "attempt"}, s = {"L$0", "I$2"})
/* loaded from: classes13.dex */
public final class TarazedSession$endSessionOverIoT$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ TarazedSessionStateChangeSource $source;
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TarazedSession this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedSession$endSessionOverIoT$1(TarazedSession tarazedSession, TarazedSessionStateChangeSource tarazedSessionStateChangeSource, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tarazedSession;
        this.$source = tarazedSessionStateChangeSource;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TarazedSession$endSessionOverIoT$1 tarazedSession$endSessionOverIoT$1 = new TarazedSession$endSessionOverIoT$1(this.this$0, this.$source, completion);
        tarazedSession$endSessionOverIoT$1.p$ = (CoroutineScope) obj;
        return tarazedSession$endSessionOverIoT$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TarazedSession$endSessionOverIoT$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0076 -> B:16:0x0079). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 1
            java.lang.String r3 = "TarazedSession"
            if (r1 == 0) goto L22
            if (r1 != r2) goto L1a
            int r1 = r10.I$1
            int r4 = r10.I$0
            java.lang.Object r5 = r10.L$0
            kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
            kotlin.ResultKt.throwOnFailure(r11)
            r11 = r10
            goto L79
        L1a:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L22:
            kotlin.ResultKt.throwOnFailure(r11)
            kotlinx.coroutines.CoroutineScope r11 = r10.p$
            com.amazon.tarazed.core.session.TarazedSession r1 = r10.this$0
            com.amazon.tarazed.core.logging.TarazedSessionLogger r1 = com.amazon.tarazed.core.session.TarazedSession.access$getLogger$p(r1)
            java.lang.String r4 = "Sending end session message over IoT."
            r1.i(r3, r4)
            r1 = 5
            r4 = 0
            r5 = r11
            r11 = r10
        L36:
            if (r4 >= r1) goto L7b
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r4)
            int r6 = r6.intValue()
            if (r6 == 0) goto L4d
            com.amazon.tarazed.core.session.TarazedSession r7 = r11.this$0
            com.amazon.tarazed.core.logging.TarazedSessionLogger r7 = com.amazon.tarazed.core.session.TarazedSession.access$getLogger$p(r7)
            java.lang.String r8 = "No response received for end session message, resending."
            r7.w(r3, r8)
        L4d:
            com.amazon.tarazed.core.session.TarazedSession r7 = r11.this$0
            com.amazon.tarazed.core.session.TarazedSessionIoTMessenger r7 = com.amazon.tarazed.core.session.TarazedSession.access$getIotMessenger$p(r7)
            com.amazon.tarazed.core.session.TarazedSession r8 = r11.this$0
            java.lang.String r8 = r8.getPlaybackState()
            com.amazon.tarazed.core.session.TarazedSessionStateChangeSource r9 = r11.$source
            java.lang.String r9 = r9.name()
            r7.sendPlaybackStateChange(r8, r9)
            com.amazon.tarazed.core.session.TarazedSession$Companion r7 = com.amazon.tarazed.core.session.TarazedSession.Companion
            long r7 = r7.getMESSAGE_ATTEMPT_RESPONSE_PERIOD_MS$TarazedMobileCore_release()
            r11.L$0 = r5
            r11.I$0 = r4
            r11.I$1 = r1
            r11.I$2 = r6
            r11.label = r2
            java.lang.Object r6 = kotlinx.coroutines.DelayKt.delay(r7, r11)
            if (r6 != r0) goto L79
            return r0
        L79:
            int r4 = r4 + r2
            goto L36
        L7b:
            com.amazon.tarazed.core.session.TarazedSession r0 = r11.this$0
            com.amazon.tarazed.core.metrics.TarazedMetricsHelper r0 = com.amazon.tarazed.core.session.TarazedSession.access$getMetricsHelper$p(r0)
            r1 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            java.lang.String r4 = "EndSessionTimeout"
            r0.addCount(r3, r4, r1)
            com.amazon.tarazed.core.session.TarazedSession r0 = r11.this$0
            com.amazon.tarazed.core.logging.TarazedSessionLogger r0 = com.amazon.tarazed.core.session.TarazedSession.access$getLogger$p(r0)
            java.lang.String r1 = "Timeout occurred when waiting for response, ending session."
            r0.w(r3, r1)
            com.amazon.tarazed.core.session.TarazedSession r0 = r11.this$0
            com.amazon.tarazed.core.session.TarazedSessionStateChangeRequest r1 = new com.amazon.tarazed.core.session.TarazedSessionStateChangeRequest
            com.amazon.tarazed.core.session.TarazedSessionStateChangeType r2 = com.amazon.tarazed.core.session.TarazedSessionStateChangeType.END_SESSION
            com.amazon.tarazed.core.session.TarazedSessionStateChangeSource r11 = r11.$source
            r1.<init>(r2, r11)
            r0.sendStateChangeEventRequest(r1)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.session.TarazedSession$endSessionOverIoT$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
