package com.amazon.tarazed.core.webrtc;

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
/* compiled from: WebRTCManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.core.webrtc.WebRTCManager$handleOffer$1", f = "WebRTCManager.kt", i = {0, 0}, l = {226}, m = "invokeSuspend", n = {"$this$launch", "it"}, s = {"L$0", "I$2"})
/* loaded from: classes13.dex */
public final class WebRTCManager$handleOffer$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $modifiedSdp;
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ WebRTCManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebRTCManager$handleOffer$1(WebRTCManager webRTCManager, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = webRTCManager;
        this.$modifiedSdp = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        WebRTCManager$handleOffer$1 webRTCManager$handleOffer$1 = new WebRTCManager$handleOffer$1(this.this$0, this.$modifiedSdp, completion);
        webRTCManager$handleOffer$1.p$ = (CoroutineScope) obj;
        return webRTCManager$handleOffer$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((WebRTCManager$handleOffer$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0095  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x006e -> B:17:0x0071). Please submit an issue!!! */
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
            java.lang.String r3 = "WebRTCManager"
            if (r1 == 0) goto L24
            if (r1 != r2) goto L1c
            int r1 = r10.I$2
            int r4 = r10.I$1
            int r5 = r10.I$0
            java.lang.Object r6 = r10.L$0
            kotlinx.coroutines.CoroutineScope r6 = (kotlinx.coroutines.CoroutineScope) r6
            kotlin.ResultKt.throwOnFailure(r11)
            r11 = r10
            goto L71
        L1c:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L24:
            kotlin.ResultKt.throwOnFailure(r11)
            kotlinx.coroutines.CoroutineScope r11 = r10.p$
            com.amazon.tarazed.core.webrtc.WebRTCManager.access$Companion()
            r1 = 2
            r4 = 0
            r6 = r11
            r5 = r4
            r11 = r10
            r4 = r1
        L32:
            if (r5 >= r4) goto L95
            java.lang.Integer r1 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
            int r1 = r1.intValue()
            boolean r7 = kotlinx.coroutines.CoroutineScopeKt.isActive(r6)
            if (r7 != 0) goto L53
            com.amazon.tarazed.core.webrtc.WebRTCManager r11 = r11.this$0
            com.amazon.tarazed.core.logging.TarazedSessionLogger r11 = com.amazon.tarazed.core.webrtc.WebRTCManager.access$getLogger$p(r11)
            com.amazon.tarazed.core.webrtc.WebRTCManager.access$Companion()
            java.lang.String r0 = "Send offer job was cancelled, not retrying"
            r11.d(r3, r0)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L53:
            com.amazon.tarazed.core.webrtc.WebRTCManager r7 = r11.this$0
            java.lang.String r8 = r11.$modifiedSdp
            com.amazon.tarazed.core.webrtc.WebRTCManager.access$sendOffer(r7, r8)
            com.amazon.tarazed.core.webrtc.WebRTCManager.access$Companion()
            r7 = 45000(0xafc8, double:2.2233E-319)
            r11.L$0 = r6
            r11.I$0 = r5
            r11.I$1 = r4
            r11.I$2 = r1
            r11.label = r2
            java.lang.Object r7 = kotlinx.coroutines.DelayKt.delay(r7, r11)
            if (r7 != r0) goto L71
            return r0
        L71:
            com.amazon.tarazed.core.webrtc.WebRTCManager r7 = r11.this$0
            com.amazon.tarazed.core.logging.TarazedSessionLogger r7 = com.amazon.tarazed.core.webrtc.WebRTCManager.access$getLogger$p(r7)
            com.amazon.tarazed.core.webrtc.WebRTCManager.access$Companion()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "Attempt "
            r8.append(r9)
            r8.append(r1)
            java.lang.String r1 = " timed out waiting for answer SDP"
            r8.append(r1)
            java.lang.String r1 = r8.toString()
            r7.w(r3, r1)
            int r5 = r5 + r2
            goto L32
        L95:
            boolean r0 = kotlinx.coroutines.CoroutineScopeKt.isActive(r6)
            if (r0 == 0) goto Lbc
            com.amazon.tarazed.core.webrtc.WebRTCManager r0 = r11.this$0
            com.amazon.tarazed.core.logging.TarazedSessionLogger r0 = com.amazon.tarazed.core.webrtc.WebRTCManager.access$getLogger$p(r0)
            com.amazon.tarazed.core.webrtc.WebRTCManager.access$Companion()
            java.lang.String r1 = "Timed out waiting for answer SDP"
            r0.e(r3, r1)
            com.amazon.tarazed.core.webrtc.WebRTCManager r11 = r11.this$0
            com.amazon.tarazed.core.metrics.TarazedMetricsHelper r11 = com.amazon.tarazed.core.webrtc.WebRTCManager.access$getMetricsHelper$p(r11)
            com.amazon.tarazed.core.webrtc.WebRTCManager.access$Companion()
            com.amazon.tarazed.core.webrtc.WebRTCManager.access$Companion()
            r0 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            java.lang.String r2 = "ReceiveAnswerTimeout"
            r11.addCountHighPriority(r3, r2, r0)
        Lbc:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.webrtc.WebRTCManager$handleOffer$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
