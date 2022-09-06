package com.amazon.dee.app.dependencies;

import com.amazon.alexa.permissions.DefaultPermissionsService;
import com.amazon.alexa.permissions.api.PermissionsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MainModule_ProvidePermissionsServiceFactory implements Factory<PermissionsService> {
    private final MainModule module;
    private final Provider<DefaultPermissionsService> permissionsServiceProvider;

    public MainModule_ProvidePermissionsServiceFactory(MainModule mainModule, Provider<DefaultPermissionsService> provider) {
        this.module = mainModule;
        this.permissionsServiceProvider = provider;
    }

    public static MainModule_ProvidePermissionsServiceFactory create(MainModule mainModule, Provider<DefaultPermissionsService> provider) {
        return new MainModule_ProvidePermissionsServiceFactory(mainModule, provider);
    }

    public static PermissionsService provideInstance(MainModule mainModule, Provider<DefaultPermissionsService> provider) {
        return proxyProvidePermissionsService(mainModule, provider.mo10268get());
    }

    public static PermissionsService proxyProvidePermissionsService(MainModule mainModule, DefaultPermissionsService defaultPermissionsService) {
        return (PermissionsService) Preconditions.checkNotNull(mainModule.providePermissionsService(defaultPermissionsService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PermissionsService mo10268get() {
        return provideInstance(this.module, this.permissionsServiceProvider);
    }
}
