package com.amazon.tarazed.dagger.modules;

import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class GlobalModule_ProvideTarazedSessionLogger$TarazedAndroidLibrary_releaseFactory implements Factory<TarazedSessionLogger> {
    private final GlobalModule module;

    public GlobalModule_ProvideTarazedSessionLogger$TarazedAndroidLibrary_releaseFactory(GlobalModule globalModule) {
        this.module = globalModule;
    }

    public static GlobalModule_ProvideTarazedSessionLogger$TarazedAndroidLibrary_releaseFactory create(GlobalModule globalModule) {
        return new GlobalModule_ProvideTarazedSessionLogger$TarazedAndroidLibrary_releaseFactory(globalModule);
    }

    public static TarazedSessionLogger provideInstance(GlobalModule globalModule) {
        return proxyProvideTarazedSessionLogger$TarazedAndroidLibrary_release(globalModule);
    }

    public static TarazedSessionLogger proxyProvideTarazedSessionLogger$TarazedAndroidLibrary_release(GlobalModule globalModule) {
        return (TarazedSessionLogger) Preconditions.checkNotNull(globalModule.provideTarazedSessionLogger$TarazedAndroidLibrary_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TarazedSessionLogger mo10268get() {
        return provideInstance(this.module);
    }
}
