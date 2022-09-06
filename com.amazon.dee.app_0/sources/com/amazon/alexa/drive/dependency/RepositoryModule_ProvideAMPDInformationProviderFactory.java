package com.amazon.alexa.drive.dependency;

import android.content.Context;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class RepositoryModule_ProvideAMPDInformationProviderFactory implements Factory<AMPDInformationProvider> {
    private final Provider<Context> contextProvider;
    private final RepositoryModule module;

    public RepositoryModule_ProvideAMPDInformationProviderFactory(RepositoryModule repositoryModule, Provider<Context> provider) {
        this.module = repositoryModule;
        this.contextProvider = provider;
    }

    public static RepositoryModule_ProvideAMPDInformationProviderFactory create(RepositoryModule repositoryModule, Provider<Context> provider) {
        return new RepositoryModule_ProvideAMPDInformationProviderFactory(repositoryModule, provider);
    }

    public static AMPDInformationProvider provideInstance(RepositoryModule repositoryModule, Provider<Context> provider) {
        return proxyProvideAMPDInformationProvider(repositoryModule, provider.mo10268get());
    }

    public static AMPDInformationProvider proxyProvideAMPDInformationProvider(RepositoryModule repositoryModule, Context context) {
        return (AMPDInformationProvider) Preconditions.checkNotNull(repositoryModule.provideAMPDInformationProvider(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AMPDInformationProvider mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
