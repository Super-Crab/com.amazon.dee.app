package com.amazon.tarazed.ui.menu;

import android.view.View;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.ui.ViewGroupManager;
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
/* compiled from: MenuDrawerManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.ui.menu.MenuDrawerManager$hideMenuImmediate$2", f = "MenuDrawerManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes13.dex */
public final class MenuDrawerManager$hideMenuImmediate$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    private CoroutineScope p$;
    final /* synthetic */ MenuDrawerManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MenuDrawerManager$hideMenuImmediate$2(MenuDrawerManager menuDrawerManager, Continuation continuation) {
        super(2, continuation);
        this.this$0 = menuDrawerManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MenuDrawerManager$hideMenuImmediate$2 menuDrawerManager$hideMenuImmediate$2 = new MenuDrawerManager$hideMenuImmediate$2(this.this$0, completion);
        menuDrawerManager$hideMenuImmediate$2.p$ = (CoroutineScope) obj;
        return menuDrawerManager$hideMenuImmediate$2;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MenuDrawerManager$hideMenuImmediate$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        ViewGroupManager viewGroupManager;
        View view;
        ViewGroupManager viewGroupManager2;
        View view2;
        ViewGroupManager viewGroupManager3;
        TarazedSessionLogger tarazedSessionLogger;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            viewGroupManager = this.this$0.viewGroupManager;
            view = this.this$0.menuView;
            if (!viewGroupManager.containsStaticView(view)) {
                tarazedSessionLogger = this.this$0.logger;
                tarazedSessionLogger.i("MenuDrawerManager", "tried to hide menu drawer but it is already hidden");
                return Unit.INSTANCE;
            }
            viewGroupManager2 = this.this$0.viewGroupManager;
            view2 = this.this$0.menuView;
            viewGroupManager2.removeViewFromStaticViewGroup(view2);
            viewGroupManager3 = this.this$0.viewGroupManager;
            viewGroupManager3.resetModal();
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
