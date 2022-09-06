package com.amazon.tarazed.session.dialog;

import com.amazon.tarazed.core.notifier.TarazedNotificationEvent;
import com.amazon.tarazed.session.dialog.PrimerPermissionDialog;
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
/* compiled from: PrimerPermissionDialog.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/amazon/tarazed/core/notifier/TarazedNotificationEvent;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.session.dialog.PrimerPermissionDialog$notificationHandler$1", f = "PrimerPermissionDialog.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes13.dex */
final class PrimerPermissionDialog$notificationHandler$1 extends SuspendLambda implements Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> {
    int label;
    private TarazedNotificationEvent p$0;
    final /* synthetic */ PrimerPermissionDialog this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PrimerPermissionDialog$notificationHandler$1(PrimerPermissionDialog primerPermissionDialog, Continuation continuation) {
        super(2, continuation);
        this.this$0 = primerPermissionDialog;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PrimerPermissionDialog$notificationHandler$1 primerPermissionDialog$notificationHandler$1 = new PrimerPermissionDialog$notificationHandler$1(this.this$0, completion);
        primerPermissionDialog$notificationHandler$1.p$0 = (TarazedNotificationEvent) obj;
        return primerPermissionDialog$notificationHandler$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(TarazedNotificationEvent tarazedNotificationEvent, Continuation<? super Unit> continuation) {
        return ((PrimerPermissionDialog$notificationHandler$1) create(tarazedNotificationEvent, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            int i = PrimerPermissionDialog.WhenMappings.$EnumSwitchMapping$0[this.p$0.ordinal()];
            if (i == 1 || i == 2 || i == 3) {
                this.this$0.finish();
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
