package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesMuffinAlexaServicesConnectionFactory implements Factory<AlexaServicesConnection> {
    private final Provider<Context> contextProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesMuffinAlexaServicesConnectionFactory(LibraryModule libraryModule, Provider<Context> provider) {
        this.module = libraryModule;
        this.contextProvider = provider;
    }

    public static LibraryModule_ProvidesMuffinAlexaServicesConnectionFactory create(LibraryModule libraryModule, Provider<Context> provider) {
        return new LibraryModule_ProvidesMuffinAlexaServicesConnectionFactory(libraryModule, provider);
    }

    public static AlexaServicesConnection provideInstance(LibraryModule libraryModule, Provider<Context> provider) {
        return (AlexaServicesConnection) Preconditions.checkNotNull(libraryModule.providesMuffinAlexaServicesConnection(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static AlexaServicesConnection proxyProvidesMuffinAlexaServicesConnection(LibraryModule libraryModule, Context context) {
        return (AlexaServicesConnection) Preconditions.checkNotNull(libraryModule.providesMuffinAlexaServicesConnection(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaServicesConnection mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
