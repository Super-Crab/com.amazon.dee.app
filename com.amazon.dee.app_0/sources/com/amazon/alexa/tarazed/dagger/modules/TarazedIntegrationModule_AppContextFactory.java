package com.amazon.alexa.tarazed.dagger.modules;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class TarazedIntegrationModule_AppContextFactory implements Factory<Context> {
    private final TarazedIntegrationModule module;

    public TarazedIntegrationModule_AppContextFactory(TarazedIntegrationModule tarazedIntegrationModule) {
        this.module = tarazedIntegrationModule;
    }

    public static TarazedIntegrationModule_AppContextFactory create(TarazedIntegrationModule tarazedIntegrationModule) {
        return new TarazedIntegrationModule_AppContextFactory(tarazedIntegrationModule);
    }

    public static Context provideInstance(TarazedIntegrationModule tarazedIntegrationModule) {
        return proxyAppContext(tarazedIntegrationModule);
    }

    public static Context proxyAppContext(TarazedIntegrationModule tarazedIntegrationModule) {
        return (Context) Preconditions.checkNotNull(tarazedIntegrationModule.appContext(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Context mo10268get() {
        return provideInstance(this.module);
    }
}
