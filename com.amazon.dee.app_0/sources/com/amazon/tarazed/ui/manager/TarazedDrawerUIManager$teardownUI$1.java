package com.amazon.tarazed.ui.manager;

import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.ui.ViewGroupManager;
import com.amazon.tarazed.ui.border.StaticBorderManager;
import com.amazon.tarazed.ui.tv.TVUIManager;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedDrawerUIManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.ui.manager.TarazedDrawerUIManager$teardownUI$1", f = "TarazedDrawerUIManager.kt", i = {0}, l = {211}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* loaded from: classes13.dex */
public final class TarazedDrawerUIManager$teardownUI$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TarazedDrawerUIManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedDrawerUIManager$teardownUI$1(TarazedDrawerUIManager tarazedDrawerUIManager, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tarazedDrawerUIManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TarazedDrawerUIManager$teardownUI$1 tarazedDrawerUIManager$teardownUI$1 = new TarazedDrawerUIManager$teardownUI$1(this.this$0, completion);
        tarazedDrawerUIManager$teardownUI$1.p$ = (CoroutineScope) obj;
        return tarazedDrawerUIManager$teardownUI$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TarazedDrawerUIManager$teardownUI$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        StaticBorderManager staticBorderManager;
        DeviceInfoUtility deviceInfoUtility;
        ViewGroupManager viewGroupManager;
        ViewGroupManager viewGroupManager2;
        TVUIManager tVUIManager;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            TarazedDrawerUIManager tarazedDrawerUIManager = this.this$0;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (tarazedDrawerUIManager.hideNavigationDrawer(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        staticBorderManager = this.this$0.borderManager;
        staticBorderManager.removeBorder();
        deviceInfoUtility = this.this$0.deviceInfoUtility;
        if (!deviceInfoUtility.isTouchableDevice()) {
            tVUIManager = this.this$0.tvManager;
            tVUIManager.hide$TarazedAndroidLibrary_release();
        }
        viewGroupManager = this.this$0.viewGroupManager;
        viewGroupManager.resetModal();
        viewGroupManager2 = this.this$0.viewGroupManager;
        viewGroupManager2.removeViewGroupsFromWindowManager();
        this.this$0.setEnabled(false);
        return Unit.INSTANCE;
    }
}
