package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.ui.main.MainHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class MainModule_ProvideMainHandlerFactory implements Factory<MainHandler> {
    private final MainModule module;

    public MainModule_ProvideMainHandlerFactory(MainModule mainModule) {
        this.module = mainModule;
    }

    public static MainModule_ProvideMainHandlerFactory create(MainModule mainModule) {
        return new MainModule_ProvideMainHandlerFactory(mainModule);
    }

    public static MainHandler provideInstance(MainModule mainModule) {
        return proxyProvideMainHandler(mainModule);
    }

    public static MainHandler proxyProvideMainHandler(MainModule mainModule) {
        return (MainHandler) Preconditions.checkNotNull(mainModule.provideMainHandler(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MainHandler mo10268get() {
        return provideInstance(this.module);
    }
}
