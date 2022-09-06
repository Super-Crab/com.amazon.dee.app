package com.amazon.tarazed.sessionmanager;

import com.amazon.tarazed.core.notifier.TarazedNotificationEvent;
import com.amazon.tarazed.sessionmanager.TarazedResourceManager;
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
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedResourceManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/amazon/tarazed/core/notifier/TarazedNotificationEvent;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.sessionmanager.TarazedResourceManager$sessionNotificationHandler$1", f = "TarazedResourceManager.kt", i = {0}, l = {173}, m = "invokeSuspend", n = {"it"}, s = {"L$0"})
/* loaded from: classes13.dex */
public final class TarazedResourceManager$sessionNotificationHandler$1 extends SuspendLambda implements Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    private TarazedNotificationEvent p$0;
    final /* synthetic */ TarazedResourceManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedResourceManager$sessionNotificationHandler$1(TarazedResourceManager tarazedResourceManager, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tarazedResourceManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TarazedResourceManager$sessionNotificationHandler$1 tarazedResourceManager$sessionNotificationHandler$1 = new TarazedResourceManager$sessionNotificationHandler$1(this.this$0, completion);
        tarazedResourceManager$sessionNotificationHandler$1.p$0 = (TarazedNotificationEvent) obj;
        return tarazedResourceManager$sessionNotificationHandler$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(TarazedNotificationEvent tarazedNotificationEvent, Continuation<? super Unit> continuation) {
        return ((TarazedResourceManager$sessionNotificationHandler$1) create(tarazedNotificationEvent, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            TarazedNotificationEvent tarazedNotificationEvent = this.p$0;
            if (TarazedResourceManager.WhenMappings.$EnumSwitchMapping$0[tarazedNotificationEvent.ordinal()] == 1) {
                TarazedResourceManager tarazedResourceManager = this.this$0;
                this.L$0 = tarazedNotificationEvent;
                this.label = 1;
                if (tarazedResourceManager.subscribeToActivityChanges(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            TarazedNotificationEvent tarazedNotificationEvent2 = (TarazedNotificationEvent) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
