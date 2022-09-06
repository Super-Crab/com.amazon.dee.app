package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.calling.incallcommands.models.InCallCommandModelFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesInCallCommandModelFactoryFactory implements Factory<InCallCommandModelFactory> {
    private final Provider<CommsManager> commsManagerProvider;
    private final Provider<Context> contextProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesInCallCommandModelFactoryFactory(LibraryModule libraryModule, Provider<Context> provider, Provider<CommsManager> provider2) {
        this.module = libraryModule;
        this.contextProvider = provider;
        this.commsManagerProvider = provider2;
    }

    public static LibraryModule_ProvidesInCallCommandModelFactoryFactory create(LibraryModule libraryModule, Provider<Context> provider, Provider<CommsManager> provider2) {
        return new LibraryModule_ProvidesInCallCommandModelFactoryFactory(libraryModule, provider, provider2);
    }

    public static InCallCommandModelFactory provideInstance(LibraryModule libraryModule, Provider<Context> provider, Provider<CommsManager> provider2) {
        return (InCallCommandModelFactory) Preconditions.checkNotNull(libraryModule.providesInCallCommandModelFactory(provider.mo10268get(), provider2.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static InCallCommandModelFactory proxyProvidesInCallCommandModelFactory(LibraryModule libraryModule, Context context, CommsManager commsManager) {
        return (InCallCommandModelFactory) Preconditions.checkNotNull(libraryModule.providesInCallCommandModelFactory(context, commsManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public InCallCommandModelFactory mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.commsManagerProvider);
    }
}
