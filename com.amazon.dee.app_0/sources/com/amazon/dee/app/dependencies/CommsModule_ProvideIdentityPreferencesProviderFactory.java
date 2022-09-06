package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.deecomms.settings.IdentityPreferencesProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CommsModule_ProvideIdentityPreferencesProviderFactory implements Factory<IdentityPreferencesProvider> {
    private final Provider<Context> contextProvider;
    private final CommsModule module;

    public CommsModule_ProvideIdentityPreferencesProviderFactory(CommsModule commsModule, Provider<Context> provider) {
        this.module = commsModule;
        this.contextProvider = provider;
    }

    public static CommsModule_ProvideIdentityPreferencesProviderFactory create(CommsModule commsModule, Provider<Context> provider) {
        return new CommsModule_ProvideIdentityPreferencesProviderFactory(commsModule, provider);
    }

    public static IdentityPreferencesProvider provideInstance(CommsModule commsModule, Provider<Context> provider) {
        return proxyProvideIdentityPreferencesProvider(commsModule, provider.mo10268get());
    }

    public static IdentityPreferencesProvider proxyProvideIdentityPreferencesProvider(CommsModule commsModule, Context context) {
        return (IdentityPreferencesProvider) Preconditions.checkNotNull(commsModule.provideIdentityPreferencesProvider(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public IdentityPreferencesProvider mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
