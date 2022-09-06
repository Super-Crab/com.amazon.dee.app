package com.amazon.tarazed.core.logging;

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
/* compiled from: TarazedLogFileAppender.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.core.logging.TarazedLogFileAppender$startAppenderJob$1", f = "TarazedLogFileAppender.kt", i = {0}, l = {118}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* loaded from: classes13.dex */
public final class TarazedLogFileAppender$startAppenderJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TarazedLogFileAppender this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedLogFileAppender$startAppenderJob$1(TarazedLogFileAppender tarazedLogFileAppender, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tarazedLogFileAppender;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TarazedLogFileAppender$startAppenderJob$1 tarazedLogFileAppender$startAppenderJob$1 = new TarazedLogFileAppender$startAppenderJob$1(this.this$0, completion);
        tarazedLogFileAppender$startAppenderJob$1.p$ = (CoroutineScope) obj;
        return tarazedLogFileAppender$startAppenderJob$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TarazedLogFileAppender$startAppenderJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0054  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x003b -> B:15:0x003f). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 1
            if (r1 == 0) goto L1d
            if (r1 != r2) goto L15
            java.lang.Object r1 = r7.L$0
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            kotlin.ResultKt.throwOnFailure(r8)
            r3 = r0
            r0 = r7
            goto L3f
        L15:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L1d:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.CoroutineScope r8 = r7.p$
            r1 = r8
            r8 = r7
        L24:
            boolean r3 = kotlinx.coroutines.CoroutineScopeKt.isActive(r1)
            if (r3 == 0) goto L54
            com.amazon.tarazed.core.logging.TarazedLogFileAppender r3 = r8.this$0
            kotlinx.coroutines.channels.Channel r3 = com.amazon.tarazed.core.logging.TarazedLogFileAppender.access$getAppenderChannel$p(r3)
            r8.L$0 = r1
            r8.label = r2
            java.lang.Object r3 = r3.receive(r8)
            if (r3 != r0) goto L3b
            return r0
        L3b:
            r6 = r0
            r0 = r8
            r8 = r3
            r3 = r6
        L3f:
            java.util.logging.LogRecord r8 = (java.util.logging.LogRecord) r8
            long r4 = java.lang.System.currentTimeMillis()
            r8.setMillis(r4)
            com.amazon.tarazed.core.logging.TarazedLogFileAppender r4 = r0.this$0
            java.util.logging.Logger r4 = com.amazon.tarazed.core.logging.TarazedLogFileAppender.access$getLogger$p(r4)
            r4.log(r8)
            r8 = r0
            r0 = r3
            goto L24
        L54:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.logging.TarazedLogFileAppender$startAppenderJob$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
