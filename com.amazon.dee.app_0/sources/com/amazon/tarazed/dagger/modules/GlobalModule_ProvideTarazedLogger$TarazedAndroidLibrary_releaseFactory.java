package com.amazon.tarazed.dagger.modules;

import com.amazon.tarazed.core.logging.TarazedLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class GlobalModule_ProvideTarazedLogger$TarazedAndroidLibrary_releaseFactory implements Factory<TarazedLogger> {
    private final GlobalModule module;

    public GlobalModule_ProvideTarazedLogger$TarazedAndroidLibrary_releaseFactory(GlobalModule globalModule) {
        this.module = globalModule;
    }

    public static GlobalModule_ProvideTarazedLogger$TarazedAndroidLibrary_releaseFactory create(GlobalModule globalModule) {
        return new GlobalModule_ProvideTarazedLogger$TarazedAndroidLibrary_releaseFactory(globalModule);
    }

    public static TarazedLogger provideInstance(GlobalModule globalModule) {
        return proxyProvideTarazedLogger$TarazedAndroidLibrary_release(globalModule);
    }

    public static TarazedLogger proxyProvideTarazedLogger$TarazedAndroidLibrary_release(GlobalModule globalModule) {
        return (TarazedLogger) Preconditions.checkNotNull(globalModule.provideTarazedLogger$TarazedAndroidLibrary_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TarazedLogger mo10268get() {
        return provideInstance(this.module);
    }
}
