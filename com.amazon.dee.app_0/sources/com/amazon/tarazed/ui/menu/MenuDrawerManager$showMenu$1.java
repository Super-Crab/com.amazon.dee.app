package com.amazon.tarazed.ui.menu;

import android.view.View;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.ui.ViewGroupManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
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
@DebugMetadata(c = "com.amazon.tarazed.ui.menu.MenuDrawerManager$showMenu$1", f = "MenuDrawerManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes13.dex */
public final class MenuDrawerManager$showMenu$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    private CoroutineScope p$;
    final /* synthetic */ MenuDrawerManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MenuDrawerManager$showMenu$1(MenuDrawerManager menuDrawerManager, Continuation continuation) {
        super(2, continuation);
        this.this$0 = menuDrawerManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MenuDrawerManager$showMenu$1 menuDrawerManager$showMenu$1 = new MenuDrawerManager$showMenu$1(this.this$0, completion);
        menuDrawerManager$showMenu$1.p$ = (CoroutineScope) obj;
        return menuDrawerManager$showMenu$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MenuDrawerManager$showMenu$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        ViewGroupManager viewGroupManager;
        View view;
        ViewGroupManager viewGroupManager2;
        View view2;
        int i;
        ViewGroupManager viewGroupManager3;
        DeviceInfoUtility deviceInfoUtility;
        View view3;
        View view4;
        View view5;
        View view6;
        View view7;
        TarazedSessionLogger tarazedSessionLogger;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            viewGroupManager = this.this$0.viewGroupManager;
            view = this.this$0.menuView;
            if (viewGroupManager.containsStaticView(view)) {
                tarazedSessionLogger = this.this$0.logger;
                tarazedSessionLogger.i("MenuDrawerManager", "tried to show menu drawer but it is already shown");
                return Unit.INSTANCE;
            }
            viewGroupManager2 = this.this$0.viewGroupManager;
            view2 = this.this$0.menuView;
            ViewGroupManager.ViewLayer viewLayer = ViewGroupManager.ViewLayer.MENU;
            i = this.this$0.transitionResId;
            viewGroupManager2.addViewToStaticViewGroupAnimated(view2, viewLayer, i);
            this.this$0.bindMenuClickListeners();
            viewGroupManager3 = this.this$0.viewGroupManager;
            viewGroupManager3.prepareModal();
            deviceInfoUtility = this.this$0.deviceInfoUtility;
            if (deviceInfoUtility.isFireTV()) {
                view3 = this.this$0.pauseSessionButton;
                if (view3 == null || view3.getVisibility() != 0) {
                    view4 = this.this$0.resumeSessionButton;
                    if (view4 == null || view4.getVisibility() != 0) {
                        view5 = this.this$0.endSessionButton;
                        if (view5 != null) {
                            Boxing.boxBoolean(view5.requestFocus());
                        }
                    } else {
                        view6 = this.this$0.resumeSessionButton;
                        view6.requestFocus();
                    }
                } else {
                    view7 = this.this$0.pauseSessionButton;
                    view7.requestFocus();
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
