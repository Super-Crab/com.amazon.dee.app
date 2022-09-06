package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.services.tabLayout.TabLayoutStatusService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class MainModule_ProvideTabLayoutStatusServiceFactory implements Factory<TabLayoutStatusService> {
    private final MainModule module;

    public MainModule_ProvideTabLayoutStatusServiceFactory(MainModule mainModule) {
        this.module = mainModule;
    }

    public static MainModule_ProvideTabLayoutStatusServiceFactory create(MainModule mainModule) {
        return new MainModule_ProvideTabLayoutStatusServiceFactory(mainModule);
    }

    public static TabLayoutStatusService provideInstance(MainModule mainModule) {
        return proxyProvideTabLayoutStatusService(mainModule);
    }

    public static TabLayoutStatusService proxyProvideTabLayoutStatusService(MainModule mainModule) {
        return (TabLayoutStatusService) Preconditions.checkNotNull(mainModule.provideTabLayoutStatusService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TabLayoutStatusService mo10268get() {
        return provideInstance(this.module);
    }
}
