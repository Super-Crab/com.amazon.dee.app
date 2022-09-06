package com.amazon.deecomms.core;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CapabilitiesManager_Factory implements Factory<CapabilitiesManager> {
    private final Provider<Context> contextProvider;
    private final Provider<FeatureFlagManager> ffManagerProvider;

    public CapabilitiesManager_Factory(Provider<FeatureFlagManager> provider, Provider<Context> provider2) {
        this.ffManagerProvider = provider;
        this.contextProvider = provider2;
    }

    public static CapabilitiesManager_Factory create(Provider<FeatureFlagManager> provider, Provider<Context> provider2) {
        return new CapabilitiesManager_Factory(provider, provider2);
    }

    public static CapabilitiesManager newCapabilitiesManager(FeatureFlagManager featureFlagManager, Context context) {
        return new CapabilitiesManager(featureFlagManager, context);
    }

    public static CapabilitiesManager provideInstance(Provider<FeatureFlagManager> provider, Provider<Context> provider2) {
        return new CapabilitiesManager(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CapabilitiesManager mo10268get() {
        return provideInstance(this.ffManagerProvider, this.contextProvider);
    }
}
