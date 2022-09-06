package com.amazon.dee.app.dependencies;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IdentityModule_ProvideDeviceNameTemplateFactory implements Factory<String> {
    private final Provider<Context> contextProvider;
    private final IdentityModule module;

    public IdentityModule_ProvideDeviceNameTemplateFactory(IdentityModule identityModule, Provider<Context> provider) {
        this.module = identityModule;
        this.contextProvider = provider;
    }

    public static IdentityModule_ProvideDeviceNameTemplateFactory create(IdentityModule identityModule, Provider<Context> provider) {
        return new IdentityModule_ProvideDeviceNameTemplateFactory(identityModule, provider);
    }

    public static String provideInstance(IdentityModule identityModule, Provider<Context> provider) {
        return proxyProvideDeviceNameTemplate(identityModule, provider.mo10268get());
    }

    public static String proxyProvideDeviceNameTemplate(IdentityModule identityModule, Context context) {
        return (String) Preconditions.checkNotNull(identityModule.provideDeviceNameTemplate(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public String mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
