package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.smsmessaging.service.fetchsms.SMSFetchManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesSMSFetchManagerFactory implements Factory<SMSFetchManager> {
    private final Provider<Context> contextProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesSMSFetchManagerFactory(LibraryModule libraryModule, Provider<Context> provider) {
        this.module = libraryModule;
        this.contextProvider = provider;
    }

    public static LibraryModule_ProvidesSMSFetchManagerFactory create(LibraryModule libraryModule, Provider<Context> provider) {
        return new LibraryModule_ProvidesSMSFetchManagerFactory(libraryModule, provider);
    }

    public static SMSFetchManager provideInstance(LibraryModule libraryModule, Provider<Context> provider) {
        return (SMSFetchManager) Preconditions.checkNotNull(libraryModule.providesSMSFetchManager(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static SMSFetchManager proxyProvidesSMSFetchManager(LibraryModule libraryModule, Context context) {
        return (SMSFetchManager) Preconditions.checkNotNull(libraryModule.providesSMSFetchManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SMSFetchManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
