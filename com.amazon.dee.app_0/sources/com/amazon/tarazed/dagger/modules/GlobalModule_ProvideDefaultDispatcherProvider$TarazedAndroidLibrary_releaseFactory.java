package com.amazon.tarazed.dagger.modules;

import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class GlobalModule_ProvideDefaultDispatcherProvider$TarazedAndroidLibrary_releaseFactory implements Factory<DispatcherProvider> {
    private final GlobalModule module;

    public GlobalModule_ProvideDefaultDispatcherProvider$TarazedAndroidLibrary_releaseFactory(GlobalModule globalModule) {
        this.module = globalModule;
    }

    public static GlobalModule_ProvideDefaultDispatcherProvider$TarazedAndroidLibrary_releaseFactory create(GlobalModule globalModule) {
        return new GlobalModule_ProvideDefaultDispatcherProvider$TarazedAndroidLibrary_releaseFactory(globalModule);
    }

    public static DispatcherProvider provideInstance(GlobalModule globalModule) {
        return proxyProvideDefaultDispatcherProvider$TarazedAndroidLibrary_release(globalModule);
    }

    public static DispatcherProvider proxyProvideDefaultDispatcherProvider$TarazedAndroidLibrary_release(GlobalModule globalModule) {
        return (DispatcherProvider) Preconditions.checkNotNull(globalModule.provideDefaultDispatcherProvider$TarazedAndroidLibrary_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DispatcherProvider mo10268get() {
        return provideInstance(this.module);
    }
}
