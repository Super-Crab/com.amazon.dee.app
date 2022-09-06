package com.amazon.dee.app.dependencies;

import com.amazon.alexa.mobilytics.event.MobilyticsEventFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class MobilyticsModule_ProvideMobilyticsEventFactoryFactory implements Factory<MobilyticsEventFactory> {
    private final MobilyticsModule module;

    public MobilyticsModule_ProvideMobilyticsEventFactoryFactory(MobilyticsModule mobilyticsModule) {
        this.module = mobilyticsModule;
    }

    public static MobilyticsModule_ProvideMobilyticsEventFactoryFactory create(MobilyticsModule mobilyticsModule) {
        return new MobilyticsModule_ProvideMobilyticsEventFactoryFactory(mobilyticsModule);
    }

    public static MobilyticsEventFactory provideInstance(MobilyticsModule mobilyticsModule) {
        return proxyProvideMobilyticsEventFactory(mobilyticsModule);
    }

    public static MobilyticsEventFactory proxyProvideMobilyticsEventFactory(MobilyticsModule mobilyticsModule) {
        return (MobilyticsEventFactory) Preconditions.checkNotNull(mobilyticsModule.provideMobilyticsEventFactory(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MobilyticsEventFactory mo10268get() {
        return provideInstance(this.module);
    }
}
