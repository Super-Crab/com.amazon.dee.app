package com.amazon.ptz.dagger;

import android.os.Handler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class HandlerModule_ProvideMainHandlerFactory implements Factory<Handler> {
    private final HandlerModule module;

    public HandlerModule_ProvideMainHandlerFactory(HandlerModule handlerModule) {
        this.module = handlerModule;
    }

    public static HandlerModule_ProvideMainHandlerFactory create(HandlerModule handlerModule) {
        return new HandlerModule_ProvideMainHandlerFactory(handlerModule);
    }

    public static Handler provideInstance(HandlerModule handlerModule) {
        return proxyProvideMainHandler(handlerModule);
    }

    public static Handler proxyProvideMainHandler(HandlerModule handlerModule) {
        return (Handler) Preconditions.checkNotNull(handlerModule.provideMainHandler(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Handler mo10268get() {
        return provideInstance(this.module);
    }
}
