package com.amazon.alexa.drive.dependency;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class RepositoryModule_ProvideAlexaServicesConnectionFactory implements Factory<AlexaServicesConnection> {
    private final Provider<Context> contextProvider;
    private final RepositoryModule module;

    public RepositoryModule_ProvideAlexaServicesConnectionFactory(RepositoryModule repositoryModule, Provider<Context> provider) {
        this.module = repositoryModule;
        this.contextProvider = provider;
    }

    public static RepositoryModule_ProvideAlexaServicesConnectionFactory create(RepositoryModule repositoryModule, Provider<Context> provider) {
        return new RepositoryModule_ProvideAlexaServicesConnectionFactory(repositoryModule, provider);
    }

    public static AlexaServicesConnection provideInstance(RepositoryModule repositoryModule, Provider<Context> provider) {
        return proxyProvideAlexaServicesConnection(repositoryModule, provider.mo10268get());
    }

    public static AlexaServicesConnection proxyProvideAlexaServicesConnection(RepositoryModule repositoryModule, Context context) {
        return (AlexaServicesConnection) Preconditions.checkNotNull(repositoryModule.provideAlexaServicesConnection(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaServicesConnection mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
