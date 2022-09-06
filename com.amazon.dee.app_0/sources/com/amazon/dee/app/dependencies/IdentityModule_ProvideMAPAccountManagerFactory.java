package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IdentityModule_ProvideMAPAccountManagerFactory implements Factory<MAPAccountManager> {
    private final Provider<Context> contextProvider;
    private final IdentityModule module;

    public IdentityModule_ProvideMAPAccountManagerFactory(IdentityModule identityModule, Provider<Context> provider) {
        this.module = identityModule;
        this.contextProvider = provider;
    }

    public static IdentityModule_ProvideMAPAccountManagerFactory create(IdentityModule identityModule, Provider<Context> provider) {
        return new IdentityModule_ProvideMAPAccountManagerFactory(identityModule, provider);
    }

    public static MAPAccountManager provideInstance(IdentityModule identityModule, Provider<Context> provider) {
        return proxyProvideMAPAccountManager(identityModule, provider.mo10268get());
    }

    public static MAPAccountManager proxyProvideMAPAccountManager(IdentityModule identityModule, Context context) {
        return (MAPAccountManager) Preconditions.checkNotNull(identityModule.provideMAPAccountManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MAPAccountManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
