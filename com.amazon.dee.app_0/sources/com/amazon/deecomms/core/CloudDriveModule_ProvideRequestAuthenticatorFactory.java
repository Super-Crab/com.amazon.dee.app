package com.amazon.deecomms.core;

import com.amazon.deecomms.auth.CommsRequestAuthenticator;
import com.amazon.deecomms.common.ApplicationManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CloudDriveModule_ProvideRequestAuthenticatorFactory implements Factory<CommsRequestAuthenticator> {
    private final Provider<ApplicationManager> applicationManagerProvider;
    private final CloudDriveModule module;

    public CloudDriveModule_ProvideRequestAuthenticatorFactory(CloudDriveModule cloudDriveModule, Provider<ApplicationManager> provider) {
        this.module = cloudDriveModule;
        this.applicationManagerProvider = provider;
    }

    public static CloudDriveModule_ProvideRequestAuthenticatorFactory create(CloudDriveModule cloudDriveModule, Provider<ApplicationManager> provider) {
        return new CloudDriveModule_ProvideRequestAuthenticatorFactory(cloudDriveModule, provider);
    }

    public static CommsRequestAuthenticator provideInstance(CloudDriveModule cloudDriveModule, Provider<ApplicationManager> provider) {
        return (CommsRequestAuthenticator) Preconditions.checkNotNull(cloudDriveModule.provideRequestAuthenticator(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CommsRequestAuthenticator proxyProvideRequestAuthenticator(CloudDriveModule cloudDriveModule, ApplicationManager applicationManager) {
        return (CommsRequestAuthenticator) Preconditions.checkNotNull(cloudDriveModule.provideRequestAuthenticator(applicationManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsRequestAuthenticator mo10268get() {
        return provideInstance(this.module, this.applicationManagerProvider);
    }
}
