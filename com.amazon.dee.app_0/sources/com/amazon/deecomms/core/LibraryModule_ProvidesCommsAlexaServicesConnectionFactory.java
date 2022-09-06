package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesCommsAlexaServicesConnectionFactory implements Factory<AlexaServicesConnection> {
    private final Provider<Context> contextProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesCommsAlexaServicesConnectionFactory(LibraryModule libraryModule, Provider<Context> provider) {
        this.module = libraryModule;
        this.contextProvider = provider;
    }

    public static LibraryModule_ProvidesCommsAlexaServicesConnectionFactory create(LibraryModule libraryModule, Provider<Context> provider) {
        return new LibraryModule_ProvidesCommsAlexaServicesConnectionFactory(libraryModule, provider);
    }

    public static AlexaServicesConnection provideInstance(LibraryModule libraryModule, Provider<Context> provider) {
        return (AlexaServicesConnection) Preconditions.checkNotNull(libraryModule.providesCommsAlexaServicesConnection(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static AlexaServicesConnection proxyProvidesCommsAlexaServicesConnection(LibraryModule libraryModule, Context context) {
        return (AlexaServicesConnection) Preconditions.checkNotNull(libraryModule.providesCommsAlexaServicesConnection(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaServicesConnection mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
