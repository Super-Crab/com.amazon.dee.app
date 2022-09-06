package com.amazon.commscore.commsidentity.dependencies;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CommsIdentityModule_ProvidesCommsSharedPrefsFactory implements Factory<SharedPreferences> {
    private final Provider<Context> contextProvider;
    private final CommsIdentityModule module;

    public CommsIdentityModule_ProvidesCommsSharedPrefsFactory(CommsIdentityModule commsIdentityModule, Provider<Context> provider) {
        this.module = commsIdentityModule;
        this.contextProvider = provider;
    }

    public static CommsIdentityModule_ProvidesCommsSharedPrefsFactory create(CommsIdentityModule commsIdentityModule, Provider<Context> provider) {
        return new CommsIdentityModule_ProvidesCommsSharedPrefsFactory(commsIdentityModule, provider);
    }

    public static SharedPreferences provideInstance(CommsIdentityModule commsIdentityModule, Provider<Context> provider) {
        return proxyProvidesCommsSharedPrefs(commsIdentityModule, provider.mo10268get());
    }

    public static SharedPreferences proxyProvidesCommsSharedPrefs(CommsIdentityModule commsIdentityModule, Context context) {
        return (SharedPreferences) Preconditions.checkNotNull(commsIdentityModule.providesCommsSharedPrefs(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SharedPreferences mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
