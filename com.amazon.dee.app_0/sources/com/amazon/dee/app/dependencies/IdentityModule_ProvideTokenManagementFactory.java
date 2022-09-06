package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.identity.auth.device.api.TokenManagement;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IdentityModule_ProvideTokenManagementFactory implements Factory<TokenManagement> {
    private final Provider<Context> contextProvider;
    private final IdentityModule module;

    public IdentityModule_ProvideTokenManagementFactory(IdentityModule identityModule, Provider<Context> provider) {
        this.module = identityModule;
        this.contextProvider = provider;
    }

    public static IdentityModule_ProvideTokenManagementFactory create(IdentityModule identityModule, Provider<Context> provider) {
        return new IdentityModule_ProvideTokenManagementFactory(identityModule, provider);
    }

    public static TokenManagement provideInstance(IdentityModule identityModule, Provider<Context> provider) {
        return proxyProvideTokenManagement(identityModule, provider.mo10268get());
    }

    public static TokenManagement proxyProvideTokenManagement(IdentityModule identityModule, Context context) {
        return (TokenManagement) Preconditions.checkNotNull(identityModule.provideTokenManagement(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TokenManagement mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
