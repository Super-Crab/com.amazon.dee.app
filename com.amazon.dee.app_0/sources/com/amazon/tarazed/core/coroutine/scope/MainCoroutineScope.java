package com.amazon.tarazed.core.coroutine.scope;

import com.amazon.tarazed.core.coroutine.dispatcher.DefaultDispatcherProvider;
import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: MainCoroutineScope.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/amazon/tarazed/core/coroutine/scope/MainCoroutineScope;", "Lkotlinx/coroutines/CoroutineScope;", "dispatchers", "Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;", "(Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;)V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MainCoroutineScope implements CoroutineScope {
    @NotNull
    private final CoroutineContext coroutineContext;

    public MainCoroutineScope() {
        this(null, 1, null);
    }

    public MainCoroutineScope(@NotNull DispatcherProvider dispatchers) {
        CompletableJob Job$default;
        Intrinsics.checkParameterIsNotNull(dispatchers, "dispatchers");
        Job$default = JobKt__JobKt.Job$default((Job) null, 1, (Object) null);
        this.coroutineContext = Job$default.plus(dispatchers.main());
    }

    @Override // kotlinx.coroutines.CoroutineScope
    @NotNull
    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    public /* synthetic */ MainCoroutineScope(DispatcherProvider dispatcherProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new DefaultDispatcherProvider() : dispatcherProvider);
    }
}
