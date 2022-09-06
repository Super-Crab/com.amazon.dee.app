package com.amazon.photos.discovery.internal.dagger.module;

import android.content.SharedPreferences;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DataModule_ProvideSharedPreferencesFactory implements Factory<SharedPreferences> {
    private final DataModule module;

    public DataModule_ProvideSharedPreferencesFactory(DataModule dataModule) {
        this.module = dataModule;
    }

    public static DataModule_ProvideSharedPreferencesFactory create(DataModule dataModule) {
        return new DataModule_ProvideSharedPreferencesFactory(dataModule);
    }

    public static SharedPreferences provideSharedPreferences(DataModule dataModule) {
        return (SharedPreferences) Preconditions.checkNotNull(dataModule.provideSharedPreferences(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SharedPreferences mo10268get() {
        return provideSharedPreferences(this.module);
    }
}
