package com.amazon.alexa.client.core.configuration;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class ResourcesConfigurationLoader_Factory implements Factory<ResourcesConfigurationLoader> {
    private final Provider<Context> contextProvider;

    public ResourcesConfigurationLoader_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static ResourcesConfigurationLoader_Factory create(Provider<Context> provider) {
        return new ResourcesConfigurationLoader_Factory(provider);
    }

    public static ResourcesConfigurationLoader newResourcesConfigurationLoader(Context context) {
        return new ResourcesConfigurationLoader(context);
    }

    public static ResourcesConfigurationLoader provideInstance(Provider<Context> provider) {
        return new ResourcesConfigurationLoader(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ResourcesConfigurationLoader mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
