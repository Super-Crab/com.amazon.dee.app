package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.dee.app.services.security.CertificateReaderService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideCertificateReaderServiceFactory implements Factory<CertificateReaderService> {
    private final Provider<Context> contextProvider;
    private final ServiceModule module;

    public ServiceModule_ProvideCertificateReaderServiceFactory(ServiceModule serviceModule, Provider<Context> provider) {
        this.module = serviceModule;
        this.contextProvider = provider;
    }

    public static ServiceModule_ProvideCertificateReaderServiceFactory create(ServiceModule serviceModule, Provider<Context> provider) {
        return new ServiceModule_ProvideCertificateReaderServiceFactory(serviceModule, provider);
    }

    public static CertificateReaderService provideInstance(ServiceModule serviceModule, Provider<Context> provider) {
        return proxyProvideCertificateReaderService(serviceModule, provider.mo10268get());
    }

    public static CertificateReaderService proxyProvideCertificateReaderService(ServiceModule serviceModule, Context context) {
        return (CertificateReaderService) Preconditions.checkNotNull(serviceModule.provideCertificateReaderService(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CertificateReaderService mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
