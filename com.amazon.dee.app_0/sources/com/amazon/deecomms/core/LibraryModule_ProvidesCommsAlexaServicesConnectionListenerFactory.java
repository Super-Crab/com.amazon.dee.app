package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.deecomms.alexa.fireos.CommsAlexaServicesConnectionListener;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesCommsAlexaServicesConnectionListenerFactory implements Factory<CommsAlexaServicesConnectionListener> {
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;
    private final Provider<Context> contextProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesCommsAlexaServicesConnectionListenerFactory(LibraryModule libraryModule, Provider<AlexaServicesConnection> provider, Provider<Context> provider2) {
        this.module = libraryModule;
        this.alexaServicesConnectionProvider = provider;
        this.contextProvider = provider2;
    }

    public static LibraryModule_ProvidesCommsAlexaServicesConnectionListenerFactory create(LibraryModule libraryModule, Provider<AlexaServicesConnection> provider, Provider<Context> provider2) {
        return new LibraryModule_ProvidesCommsAlexaServicesConnectionListenerFactory(libraryModule, provider, provider2);
    }

    public static CommsAlexaServicesConnectionListener provideInstance(LibraryModule libraryModule, Provider<AlexaServicesConnection> provider, Provider<Context> provider2) {
        return (CommsAlexaServicesConnectionListener) Preconditions.checkNotNull(libraryModule.providesCommsAlexaServicesConnectionListener(provider.mo10268get(), provider2.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CommsAlexaServicesConnectionListener proxyProvidesCommsAlexaServicesConnectionListener(LibraryModule libraryModule, AlexaServicesConnection alexaServicesConnection, Context context) {
        return (CommsAlexaServicesConnectionListener) Preconditions.checkNotNull(libraryModule.providesCommsAlexaServicesConnectionListener(alexaServicesConnection, context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsAlexaServicesConnectionListener mo10268get() {
        return provideInstance(this.module, this.alexaServicesConnectionProvider, this.contextProvider);
    }
}
