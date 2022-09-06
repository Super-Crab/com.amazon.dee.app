package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MobilyticsModule_ProvideMobilyticsConfigurationFactory implements Factory<MobilyticsConfiguration> {
    private final Provider<Context> contextProvider;
    private final Provider<EventBus> eventBusProvider;
    private final MobilyticsModule module;

    public MobilyticsModule_ProvideMobilyticsConfigurationFactory(MobilyticsModule mobilyticsModule, Provider<Context> provider, Provider<EventBus> provider2) {
        this.module = mobilyticsModule;
        this.contextProvider = provider;
        this.eventBusProvider = provider2;
    }

    public static MobilyticsModule_ProvideMobilyticsConfigurationFactory create(MobilyticsModule mobilyticsModule, Provider<Context> provider, Provider<EventBus> provider2) {
        return new MobilyticsModule_ProvideMobilyticsConfigurationFactory(mobilyticsModule, provider, provider2);
    }

    public static MobilyticsConfiguration provideInstance(MobilyticsModule mobilyticsModule, Provider<Context> provider, Provider<EventBus> provider2) {
        return proxyProvideMobilyticsConfiguration(mobilyticsModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static MobilyticsConfiguration proxyProvideMobilyticsConfiguration(MobilyticsModule mobilyticsModule, Context context, EventBus eventBus) {
        return (MobilyticsConfiguration) Preconditions.checkNotNull(mobilyticsModule.provideMobilyticsConfiguration(context, eventBus), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MobilyticsConfiguration mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.eventBusProvider);
    }
}
