package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.auth.SecuredSharedPreference;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesSecuredSharedPreferenceFactory implements Factory<SecuredSharedPreference> {
    private final Provider<Context> contextProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesSecuredSharedPreferenceFactory(LibraryModule libraryModule, Provider<Context> provider) {
        this.module = libraryModule;
        this.contextProvider = provider;
    }

    public static LibraryModule_ProvidesSecuredSharedPreferenceFactory create(LibraryModule libraryModule, Provider<Context> provider) {
        return new LibraryModule_ProvidesSecuredSharedPreferenceFactory(libraryModule, provider);
    }

    public static SecuredSharedPreference provideInstance(LibraryModule libraryModule, Provider<Context> provider) {
        return (SecuredSharedPreference) Preconditions.checkNotNull(libraryModule.providesSecuredSharedPreference(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static SecuredSharedPreference proxyProvidesSecuredSharedPreference(LibraryModule libraryModule, Context context) {
        return (SecuredSharedPreference) Preconditions.checkNotNull(libraryModule.providesSecuredSharedPreference(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SecuredSharedPreference mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
