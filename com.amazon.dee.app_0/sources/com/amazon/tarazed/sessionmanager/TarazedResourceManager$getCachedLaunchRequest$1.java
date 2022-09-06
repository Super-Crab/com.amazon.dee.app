package com.amazon.tarazed.sessionmanager;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedResourceManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H\u0080@"}, d2 = {"getCachedLaunchRequest", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.sessionmanager.TarazedResourceManager", f = "TarazedResourceManager.kt", i = {0}, l = {233}, m = "getCachedLaunchRequest$TarazedAndroidLibrary_release", n = {"this"}, s = {"L$0"})
/* loaded from: classes13.dex */
public final class TarazedResourceManager$getCachedLaunchRequest$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TarazedResourceManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedResourceManager$getCachedLaunchRequest$1(TarazedResourceManager tarazedResourceManager, Continuation continuation) {
        super(continuation);
        this.this$0 = tarazedResourceManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getCachedLaunchRequest$TarazedAndroidLibrary_release(this);
    }
}
