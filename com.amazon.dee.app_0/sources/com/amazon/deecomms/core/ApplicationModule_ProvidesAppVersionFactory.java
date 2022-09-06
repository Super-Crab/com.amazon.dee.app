package com.amazon.deecomms.core;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ApplicationModule_ProvidesAppVersionFactory implements Factory<String> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesAppVersionFactory(ApplicationModule applicationModule, Provider<Context> provider) {
        this.module = applicationModule;
        this.contextProvider = provider;
    }

    public static ApplicationModule_ProvidesAppVersionFactory create(ApplicationModule applicationModule, Provider<Context> provider) {
        return new ApplicationModule_ProvidesAppVersionFactory(applicationModule, provider);
    }

    public static String provideInstance(ApplicationModule applicationModule, Provider<Context> provider) {
        return (String) Preconditions.checkNotNull(applicationModule.providesAppVersion(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static String proxyProvidesAppVersion(ApplicationModule applicationModule, Context context) {
        return (String) Preconditions.checkNotNull(applicationModule.providesAppVersion(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public String mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
