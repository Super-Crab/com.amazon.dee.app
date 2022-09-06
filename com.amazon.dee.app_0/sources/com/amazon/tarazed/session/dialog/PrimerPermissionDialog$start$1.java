package com.amazon.tarazed.session.dialog;

import com.amazon.tarazed.core.metrics.BizMetricsConstants;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import java.util.HashMap;
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
import kotlinx.coroutines.DelayKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: PrimerPermissionDialog.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.session.dialog.PrimerPermissionDialog$start$1", f = "PrimerPermissionDialog.kt", i = {0}, l = {87}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* loaded from: classes13.dex */
final class PrimerPermissionDialog$start$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ PrimerPermissionDialog this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PrimerPermissionDialog$start$1(PrimerPermissionDialog primerPermissionDialog, Continuation continuation) {
        super(2, continuation);
        this.this$0 = primerPermissionDialog;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PrimerPermissionDialog$start$1 primerPermissionDialog$start$1 = new PrimerPermissionDialog$start$1(this.this$0, completion);
        primerPermissionDialog$start$1.p$ = (CoroutineScope) obj;
        return primerPermissionDialog$start$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PrimerPermissionDialog$start$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        TarazedMetricsHelper tarazedMetricsHelper;
        BizMetricsHelper bizMetricsHelper;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            long primerTimeoutMS$TarazedAndroidLibrary_release = this.this$0.getPrimerTimeoutMS$TarazedAndroidLibrary_release();
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(primerTimeoutMS$TarazedAndroidLibrary_release, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        tarazedMetricsHelper = this.this$0.metricsHelper;
        tarazedMetricsHelper.addCountHighPriority("PrimerPermDialog", "PrimerTimedOut", 1.0d);
        this.this$0.getOnTimeoutCallback().mo12560invoke();
        HashMap hashMap = new HashMap();
        hashMap.put(BizMetricsConstants.ACCEPTED_BY_CUSTOMER_METADATA_NAME, "timeout");
        bizMetricsHelper = this.this$0.bizMetricsHelper;
        bizMetricsHelper.publishBizMetric(BizMetricsConstants.ASK_START_SESSION_EVENT_NAME, hashMap);
        return Unit.INSTANCE;
    }
}
