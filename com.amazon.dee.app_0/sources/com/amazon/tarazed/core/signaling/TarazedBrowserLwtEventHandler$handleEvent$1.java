package com.amazon.tarazed.core.signaling;

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
/* compiled from: TarazedBrowserLwtEventHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.core.signaling.TarazedBrowserLwtEventHandler$handleEvent$1", f = "TarazedBrowserLwtEventHandler.kt", i = {0, 0}, l = {77}, m = "invokeSuspend", n = {"$this$launch", "it"}, s = {"L$0", "I$2"})
/* loaded from: classes13.dex */
final class TarazedBrowserLwtEventHandler$handleEvent$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $pingMesageJson;
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TarazedBrowserLwtEventHandler this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedBrowserLwtEventHandler$handleEvent$1(TarazedBrowserLwtEventHandler tarazedBrowserLwtEventHandler, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tarazedBrowserLwtEventHandler;
        this.$pingMesageJson = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TarazedBrowserLwtEventHandler$handleEvent$1 tarazedBrowserLwtEventHandler$handleEvent$1 = new TarazedBrowserLwtEventHandler$handleEvent$1(this.this$0, this.$pingMesageJson, completion);
        tarazedBrowserLwtEventHandler$handleEvent$1.p$ = (CoroutineScope) obj;
        return tarazedBrowserLwtEventHandler$handleEvent$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TarazedBrowserLwtEventHandler$handleEvent$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0072  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x006d -> B:17:0x0070). Please submit an issue!!! */
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
            if (r1 == 0) goto L20
            if (r1 != r2) goto L18
            int r1 = r10.I$1
            int r3 = r10.I$0
            java.lang.Object r4 = r10.L$0
            kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
            kotlin.ResultKt.throwOnFailure(r11)
            r11 = r10
            goto L70
        L18:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L20:
            kotlin.ResultKt.throwOnFailure(r11)
            kotlinx.coroutines.CoroutineScope r11 = r10.p$
            double r3 = com.amazon.tarazed.core.signaling.TarazedBrowserLwtEventHandler.access$getMAX_BROWSER_PING_ATTEMPTS$cp()
            int r1 = kotlin.math.MathKt.roundToInt(r3)
            r3 = 0
            r4 = r11
            r11 = r10
        L30:
            if (r3 >= r1) goto L72
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r3)
            int r5 = r5.intValue()
            com.amazon.tarazed.core.signaling.TarazedBrowserLwtEventHandler r6 = r11.this$0
            kotlinx.coroutines.Job r6 = com.amazon.tarazed.core.signaling.TarazedBrowserLwtEventHandler.access$getBrowserLwtPingTimer$p(r6)
            boolean r6 = r6.isActive()
            r7 = 0
            if (r6 != 0) goto L51
            kotlin.coroutines.CoroutineContext r11 = r4.getCoroutineContext()
            kotlinx.coroutines.JobKt.cancel$default(r11, r7, r2, r7)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L51:
            com.amazon.tarazed.core.signaling.TarazedBrowserLwtEventHandler r6 = r11.this$0
            com.amazon.tarazed.core.signaling.TarazedIoTManager r6 = com.amazon.tarazed.core.signaling.TarazedBrowserLwtEventHandler.access$getIotManager$p(r6)
            java.lang.String r8 = r11.$pingMesageJson
            r9 = 2
            com.amazon.tarazed.core.signaling.TarazedIoTManager.sendEvent$default(r6, r8, r7, r9, r7)
            r6 = 5000(0x1388, double:2.4703E-320)
            r11.L$0 = r4
            r11.I$0 = r3
            r11.I$1 = r1
            r11.I$2 = r5
            r11.label = r2
            java.lang.Object r5 = kotlinx.coroutines.DelayKt.delay(r6, r11)
            if (r5 != r0) goto L70
            return r0
        L70:
            int r3 = r3 + r2
            goto L30
        L72:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.signaling.TarazedBrowserLwtEventHandler$handleEvent$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
