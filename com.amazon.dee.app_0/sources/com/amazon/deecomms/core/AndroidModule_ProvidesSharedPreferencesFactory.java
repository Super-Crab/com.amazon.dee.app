package com.amazon.deecomms.core;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AndroidModule_ProvidesSharedPreferencesFactory implements Factory<SharedPreferences> {
    private final Provider<Context> contextProvider;
    private final AndroidModule module;

    public AndroidModule_ProvidesSharedPreferencesFactory(AndroidModule androidModule, Provider<Context> provider) {
        this.module = androidModule;
        this.contextProvider = provider;
    }

    public static AndroidModule_ProvidesSharedPreferencesFactory create(AndroidModule androidModule, Provider<Context> provider) {
        return new AndroidModule_ProvidesSharedPreferencesFactory(androidModule, provider);
    }

    public static SharedPreferences provideInstance(AndroidModule androidModule, Provider<Context> provider) {
        return (SharedPreferences) Preconditions.checkNotNull(androidModule.providesSharedPreferences(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static SharedPreferences proxyProvidesSharedPreferences(AndroidModule androidModule, Context context) {
        return (SharedPreferences) Preconditions.checkNotNull(androidModule.providesSharedPreferences(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SharedPreferences mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
