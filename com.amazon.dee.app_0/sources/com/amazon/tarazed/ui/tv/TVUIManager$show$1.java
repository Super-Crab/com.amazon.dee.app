package com.amazon.tarazed.ui.tv;

import android.view.View;
import com.amazon.tarazed.R;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.ui.ViewGroupManager;
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
/* compiled from: TVUIManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.ui.tv.TVUIManager$show$1", f = "TVUIManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes13.dex */
public final class TVUIManager$show$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TVUIManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TVUIManager$show$1(TVUIManager tVUIManager, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tVUIManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TVUIManager$show$1 tVUIManager$show$1 = new TVUIManager$show$1(this.this$0, completion);
        tVUIManager$show$1.p$ = (CoroutineScope) obj;
        return tVUIManager$show$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TVUIManager$show$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        ViewGroupManager viewGroupManager;
        ViewGroupManager viewGroupManager2;
        View view;
        ViewGroupManager viewGroupManager3;
        View view2;
        TarazedSessionLogger tarazedSessionLogger;
        TarazedSessionLogger tarazedSessionLogger2;
        TVUIManager.Companion unused;
        TVUIManager.Companion unused2;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            TVUIManager tVUIManager = this.this$0;
            viewGroupManager = tVUIManager.viewGroupManager;
            tVUIManager.tvUIView = viewGroupManager.inflateStaticView(R.layout.tv_ui);
            this.this$0.notifyTVUIReinflated();
            viewGroupManager2 = this.this$0.viewGroupManager;
            view = this.this$0.tvUIView;
            if (viewGroupManager2.containsStaticView(view)) {
                tarazedSessionLogger2 = this.this$0.logger;
                unused = TVUIManager.Companion;
                tarazedSessionLogger2.i("TVUIManager", "UI already shown");
                return Unit.INSTANCE;
            }
            viewGroupManager3 = this.this$0.viewGroupManager;
            view2 = this.this$0.tvUIView;
            viewGroupManager3.addViewToStaticViewGroup(view2, ViewGroupManager.ViewLayer.TV_INDICATORS);
            tarazedSessionLogger = this.this$0.logger;
            unused2 = TVUIManager.Companion;
            tarazedSessionLogger.i("TVUIManager", "FireTV UI shown");
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
