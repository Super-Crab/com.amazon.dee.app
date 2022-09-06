package com.amazon.tarazed.dagger.modules;

import android.content.SharedPreferences;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class LibraryModule_ProvideSharedPreferencesFactory implements Factory<SharedPreferences> {
    private final LibraryModule module;

    public LibraryModule_ProvideSharedPreferencesFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvideSharedPreferencesFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvideSharedPreferencesFactory(libraryModule);
    }

    public static SharedPreferences provideInstance(LibraryModule libraryModule) {
        return proxyProvideSharedPreferences(libraryModule);
    }

    public static SharedPreferences proxyProvideSharedPreferences(LibraryModule libraryModule) {
        return (SharedPreferences) Preconditions.checkNotNull(libraryModule.provideSharedPreferences(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SharedPreferences mo10268get() {
        return provideInstance(this.module);
    }
}
