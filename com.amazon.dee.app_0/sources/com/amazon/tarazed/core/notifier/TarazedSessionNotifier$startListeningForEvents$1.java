package com.amazon.tarazed.core.notifier;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.apache.commons.net.telnet.TelnetCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedSessionNotifier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.core.notifier.TarazedSessionNotifier$startListeningForEvents$1", f = "TarazedSessionNotifier.kt", i = {0, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3}, l = {66, TelnetCommand.NOP, 68, TelnetCommand.GA}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "sessionNotificationEvent", "$this$withLock$iv", "$this$launch", "sessionNotificationEvent", "$this$withLock$iv", "$this$forEach$iv", "element$iv", "it", "$this$launch", "sessionNotificationEvent", "$this$withLock$iv"}, s = {"L$0", "L$0", "L$1", "L$3", "L$0", "L$1", "L$3", "L$4", "L$6", "L$7", "L$0", "L$1", "L$3"})
/* loaded from: classes13.dex */
public final class TarazedSessionNotifier$startListeningForEvents$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TarazedSessionNotifier this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedSessionNotifier$startListeningForEvents$1(TarazedSessionNotifier tarazedSessionNotifier, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tarazedSessionNotifier;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TarazedSessionNotifier$startListeningForEvents$1 tarazedSessionNotifier$startListeningForEvents$1 = new TarazedSessionNotifier$startListeningForEvents$1(this.this$0, completion);
        tarazedSessionNotifier$startListeningForEvents$1.p$ = (CoroutineScope) obj;
        return tarazedSessionNotifier$startListeningForEvents$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TarazedSessionNotifier$startListeningForEvents$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00ae A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00fb A[Catch: all -> 0x005d, TryCatch #1 {all -> 0x005d, blocks: (B:28:0x00e2, B:29:0x00f5, B:31:0x00fb, B:35:0x0125, B:12:0x0054), top: B:53:0x0054 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0125 A[Catch: all -> 0x005d, TRY_LEAVE, TryCatch #1 {all -> 0x005d, blocks: (B:28:0x00e2, B:29:0x00f5, B:31:0x00fb, B:35:0x0125, B:12:0x0054), top: B:53:0x0054 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x016f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x0123 -> B:29:0x00f5). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x0130 -> B:46:0x0165). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x0146 -> B:51:0x0149). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r19) {
        /*
            Method dump skipped, instructions count: 370
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.notifier.TarazedSessionNotifier$startListeningForEvents$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
