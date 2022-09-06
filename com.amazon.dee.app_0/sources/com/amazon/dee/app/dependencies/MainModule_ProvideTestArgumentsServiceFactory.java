package com.amazon.dee.app.dependencies;

import android.app.Activity;
import com.amazon.dee.app.services.security.CertificateReaderService;
import com.amazon.dee.app.services.testing.TestArgumentsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MainModule_ProvideTestArgumentsServiceFactory implements Factory<TestArgumentsService> {
    private final Provider<Activity> activityProvider;
    private final Provider<CertificateReaderService> certificateReaderServiceProvider;
    private final MainModule module;

    public MainModule_ProvideTestArgumentsServiceFactory(MainModule mainModule, Provider<Activity> provider, Provider<CertificateReaderService> provider2) {
        this.module = mainModule;
        this.activityProvider = provider;
        this.certificateReaderServiceProvider = provider2;
    }

    public static MainModule_ProvideTestArgumentsServiceFactory create(MainModule mainModule, Provider<Activity> provider, Provider<CertificateReaderService> provider2) {
        return new MainModule_ProvideTestArgumentsServiceFactory(mainModule, provider, provider2);
    }

    public static TestArgumentsService provideInstance(MainModule mainModule, Provider<Activity> provider, Provider<CertificateReaderService> provider2) {
        return proxyProvideTestArgumentsService(mainModule, provider.mo10268get(), DoubleCheck.lazy(provider2));
    }

    public static TestArgumentsService proxyProvideTestArgumentsService(MainModule mainModule, Activity activity, Lazy<CertificateReaderService> lazy) {
        return (TestArgumentsService) Preconditions.checkNotNull(mainModule.provideTestArgumentsService(activity, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TestArgumentsService mo10268get() {
        return provideInstance(this.module, this.activityProvider, this.certificateReaderServiceProvider);
    }
}
