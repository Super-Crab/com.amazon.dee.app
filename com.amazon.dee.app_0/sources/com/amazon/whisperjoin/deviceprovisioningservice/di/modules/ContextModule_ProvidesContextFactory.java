package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ContextModule_ProvidesContextFactory implements Factory<Context> {
    private final ContextModule module;

    public ContextModule_ProvidesContextFactory(ContextModule contextModule) {
        this.module = contextModule;
    }

    public static ContextModule_ProvidesContextFactory create(ContextModule contextModule) {
        return new ContextModule_ProvidesContextFactory(contextModule);
    }

    public static Context provideInstance(ContextModule contextModule) {
        return proxyProvidesContext(contextModule);
    }

    public static Context proxyProvidesContext(ContextModule contextModule) {
        return (Context) Preconditions.checkNotNull(contextModule.providesContext(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Context mo10268get() {
        return provideInstance(this.module);
    }
}
