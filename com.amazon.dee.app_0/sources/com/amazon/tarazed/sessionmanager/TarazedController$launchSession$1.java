package com.amazon.tarazed.sessionmanager;

import com.amazon.tarazed.core.notification.type.TarazedLaunchRequest;
import javax.servlet.http.HttpServletResponse;
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
/* compiled from: TarazedController.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.sessionmanager.TarazedController$launchSession$1", f = "TarazedController.kt", i = {0, 1, 2}, l = {387, 395, HttpServletResponse.SC_PAYMENT_REQUIRED}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "$this$launch"}, s = {"L$0", "L$0", "L$0"})
/* loaded from: classes13.dex */
public final class TarazedController$launchSession$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ TarazedLaunchRequest $launchRequest;
    final /* synthetic */ boolean $pingBrowser;
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TarazedController this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedController$launchSession$1(TarazedController tarazedController, boolean z, TarazedLaunchRequest tarazedLaunchRequest, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tarazedController;
        this.$pingBrowser = z;
        this.$launchRequest = tarazedLaunchRequest;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TarazedController$launchSession$1 tarazedController$launchSession$1 = new TarazedController$launchSession$1(this.this$0, this.$pingBrowser, this.$launchRequest, completion);
        tarazedController$launchSession$1.p$ = (CoroutineScope) obj;
        return tarazedController$launchSession$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TarazedController$launchSession$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ca A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00d7  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r8) {
        /*
            Method dump skipped, instructions count: 250
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.sessionmanager.TarazedController$launchSession$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
