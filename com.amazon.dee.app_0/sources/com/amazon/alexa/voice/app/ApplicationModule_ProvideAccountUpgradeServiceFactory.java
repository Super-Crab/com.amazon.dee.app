package com.amazon.alexa.voice.app;

import com.amazon.alexa.identity.api.AccountUpgradeService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class ApplicationModule_ProvideAccountUpgradeServiceFactory implements Factory<AccountUpgradeService> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideAccountUpgradeServiceFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideAccountUpgradeServiceFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideAccountUpgradeServiceFactory(applicationModule);
    }

    public static AccountUpgradeService provideInstance(ApplicationModule applicationModule) {
        return proxyProvideAccountUpgradeService(applicationModule);
    }

    public static AccountUpgradeService proxyProvideAccountUpgradeService(ApplicationModule applicationModule) {
        return (AccountUpgradeService) Preconditions.checkNotNull(applicationModule.provideAccountUpgradeService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AccountUpgradeService mo10268get() {
        return provideInstance(this.module);
    }
}
