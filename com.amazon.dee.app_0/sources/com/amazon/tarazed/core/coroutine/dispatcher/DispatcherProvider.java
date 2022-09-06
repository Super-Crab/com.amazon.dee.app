package com.amazon.tarazed.core.coroutine.dispatcher;

import com.amazon.alexa.routing.data.RouteName;
import kotlin.Metadata;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
/* compiled from: DispatcherProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016¨\u0006\u0007"}, d2 = {"Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;", "", "default", "Lkotlinx/coroutines/CoroutineDispatcher;", "io", RouteName.MAIN, "unconfined", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface DispatcherProvider {

    /* compiled from: DispatcherProvider.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class DefaultImpls {
        @NotNull
        /* renamed from: default  reason: not valid java name */
        public static CoroutineDispatcher m4558default(DispatcherProvider dispatcherProvider) {
            return Dispatchers.getDefault();
        }

        @NotNull
        public static CoroutineDispatcher io(DispatcherProvider dispatcherProvider) {
            return Dispatchers.getIO();
        }

        @NotNull
        public static CoroutineDispatcher main(DispatcherProvider dispatcherProvider) {
            return Dispatchers.getMain();
        }

        @NotNull
        public static CoroutineDispatcher unconfined(DispatcherProvider dispatcherProvider) {
            return Dispatchers.getUnconfined();
        }
    }

    @NotNull
    /* renamed from: default */
    CoroutineDispatcher mo4557default();

    @NotNull
    CoroutineDispatcher io();

    @NotNull
    CoroutineDispatcher main();

    @NotNull
    CoroutineDispatcher unconfined();
}
