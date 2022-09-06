package com.amazon.tarazed.core.coroutine.dispatcher;

import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import kotlin.Metadata;
import kotlinx.coroutines.CoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
/* compiled from: DefaultDispatcherProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/tarazed/core/coroutine/dispatcher/DefaultDispatcherProvider;", "Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;", "()V", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DefaultDispatcherProvider implements DispatcherProvider {
    @Override // com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider
    @NotNull
    /* renamed from: default  reason: not valid java name */
    public CoroutineDispatcher mo4557default() {
        return DispatcherProvider.DefaultImpls.m4558default(this);
    }

    @Override // com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider
    @NotNull
    public CoroutineDispatcher io() {
        return DispatcherProvider.DefaultImpls.io(this);
    }

    @Override // com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider
    @NotNull
    public CoroutineDispatcher main() {
        return DispatcherProvider.DefaultImpls.main(this);
    }

    @Override // com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider
    @NotNull
    public CoroutineDispatcher unconfined() {
        return DispatcherProvider.DefaultImpls.unconfined(this);
    }
}
