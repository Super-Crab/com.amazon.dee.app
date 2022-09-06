package com.amazon.tarazed.core.session;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.bouncycastle.tls.CipherSuite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedSession.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.core.session.TarazedSession$stateChangeRequestReceiverJob$1", f = "TarazedSession.kt", i = {0, 1, 1}, l = {194, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "event"}, s = {"L$0", "L$0", "L$1"})
/* loaded from: classes13.dex */
public final class TarazedSession$stateChangeRequestReceiverJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TarazedSession this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedSession$stateChangeRequestReceiverJob$1(TarazedSession tarazedSession, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tarazedSession;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TarazedSession$stateChangeRequestReceiverJob$1 tarazedSession$stateChangeRequestReceiverJob$1 = new TarazedSession$stateChangeRequestReceiverJob$1(this.this$0, completion);
        tarazedSession$stateChangeRequestReceiverJob$1.p$ = (CoroutineScope) obj;
        return tarazedSession$stateChangeRequestReceiverJob$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TarazedSession$stateChangeRequestReceiverJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0054 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00bd  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x007e -> B:22:0x0081). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0085 -> B:22:0x0081). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L36
            if (r1 == r3) goto L28
            if (r1 != r2) goto L20
            java.lang.Object r1 = r11.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r11.L$1
            com.amazon.tarazed.core.session.TarazedSessionStateChangeRequest r4 = (com.amazon.tarazed.core.session.TarazedSessionStateChangeRequest) r4
            java.lang.Object r4 = r11.L$0
            kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
            kotlin.ResultKt.throwOnFailure(r12)
            r5 = r0
            r0 = r11
            goto L81
        L20:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L28:
            java.lang.Object r1 = r11.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r11.L$0
            kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
            kotlin.ResultKt.throwOnFailure(r12)
            r5 = r0
            r0 = r11
            goto L5a
        L36:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlinx.coroutines.CoroutineScope r12 = r11.p$
            com.amazon.tarazed.core.session.TarazedSession r1 = r11.this$0
            com.amazon.tarazed.core.session.TarazedStateChangeChannel r1 = r1.getStateChangeChannel$TarazedMobileCore_release()
            kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
            r4 = r0
            r0 = r12
            r12 = r11
        L48:
            r12.L$0 = r0
            r12.L$1 = r1
            r12.label = r3
            java.lang.Object r5 = r1.hasNext(r12)
            if (r5 != r4) goto L55
            return r4
        L55:
            r10 = r0
            r0 = r12
            r12 = r5
            r5 = r4
            r4 = r10
        L5a:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto Lbd
            java.lang.Object r12 = r1.next()
            com.amazon.tarazed.core.session.TarazedSessionStateChangeRequest r12 = (com.amazon.tarazed.core.session.TarazedSessionStateChangeRequest) r12
            com.amazon.tarazed.core.session.TarazedSession r6 = r0.this$0
            boolean r6 = com.amazon.tarazed.core.session.TarazedSession.access$isValidStateChange(r6, r12)
            if (r6 == 0) goto L85
            com.amazon.tarazed.core.session.TarazedSession r6 = r0.this$0
            r0.L$0 = r4
            r0.L$1 = r12
            r0.L$2 = r1
            r0.label = r2
            java.lang.Object r12 = r6.handleStateChangeEvent(r12, r0)
            if (r12 != r5) goto L81
            return r5
        L81:
            r12 = r0
            r0 = r4
            r4 = r5
            goto L48
        L85:
            com.amazon.tarazed.core.session.TarazedSession r6 = r0.this$0
            com.amazon.tarazed.core.logging.TarazedSessionLogger r6 = com.amazon.tarazed.core.session.TarazedSession.access$getLogger$p(r6)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Cannot "
            r7.append(r8)
            r7.append(r12)
            java.lang.String r12 = " since current state is "
            r7.append(r12)
            com.amazon.tarazed.core.session.TarazedSession r12 = r0.this$0
            java.lang.String r12 = r12.getPlaybackState()
            r7.append(r12)
            java.lang.String r12 = r7.toString()
            java.lang.String r7 = "TarazedSession"
            r6.w(r7, r12)
            com.amazon.tarazed.core.session.TarazedSession r12 = r0.this$0
            com.amazon.tarazed.core.metrics.TarazedMetricsHelper r12 = com.amazon.tarazed.core.session.TarazedSession.access$getMetricsHelper$p(r12)
            r8 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            java.lang.String r6 = "InvalidStateChangeAttempt"
            r12.addCount(r7, r6, r8)
            goto L81
        Lbd:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.session.TarazedSession$stateChangeRequestReceiverJob$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
