package com.amazon.tarazed.ui.manager;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedDrawerUIManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0082@"}, d2 = {"hideNavigationDrawer", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.ui.manager.TarazedDrawerUIManager", f = "TarazedDrawerUIManager.kt", i = {0}, l = {121}, m = "hideNavigationDrawer", n = {"this"}, s = {"L$0"})
/* loaded from: classes13.dex */
public final class TarazedDrawerUIManager$hideNavigationDrawer$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TarazedDrawerUIManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedDrawerUIManager$hideNavigationDrawer$1(TarazedDrawerUIManager tarazedDrawerUIManager, Continuation continuation) {
        super(continuation);
        this.this$0 = tarazedDrawerUIManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.hideNavigationDrawer(this);
    }
}
