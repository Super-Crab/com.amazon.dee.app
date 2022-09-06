package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.api.CommsManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ApplicationModule_ProvidesCommsManagerFactory implements Factory<CommsManager> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesCommsManagerFactory(ApplicationModule applicationModule, Provider<Context> provider) {
        this.module = applicationModule;
        this.contextProvider = provider;
    }

    public static ApplicationModule_ProvidesCommsManagerFactory create(ApplicationModule applicationModule, Provider<Context> provider) {
        return new ApplicationModule_ProvidesCommsManagerFactory(applicationModule, provider);
    }

    public static CommsManager provideInstance(ApplicationModule applicationModule, Provider<Context> provider) {
        return (CommsManager) Preconditions.checkNotNull(applicationModule.providesCommsManager(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CommsManager proxyProvidesCommsManager(ApplicationModule applicationModule, Context context) {
        return (CommsManager) Preconditions.checkNotNull(applicationModule.providesCommsManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
