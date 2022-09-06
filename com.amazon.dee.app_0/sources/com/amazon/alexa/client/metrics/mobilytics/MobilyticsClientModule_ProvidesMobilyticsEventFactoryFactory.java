package com.amazon.alexa.client.metrics.mobilytics;

import com.amazon.alexa.mobilytics.event.MobilyticsEventFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class MobilyticsClientModule_ProvidesMobilyticsEventFactoryFactory implements Factory<MobilyticsEventFactory> {
    private final MobilyticsClientModule module;

    public MobilyticsClientModule_ProvidesMobilyticsEventFactoryFactory(MobilyticsClientModule mobilyticsClientModule) {
        this.module = mobilyticsClientModule;
    }

    public static MobilyticsClientModule_ProvidesMobilyticsEventFactoryFactory create(MobilyticsClientModule mobilyticsClientModule) {
        return new MobilyticsClientModule_ProvidesMobilyticsEventFactoryFactory(mobilyticsClientModule);
    }

    public static MobilyticsEventFactory provideInstance(MobilyticsClientModule mobilyticsClientModule) {
        return proxyProvidesMobilyticsEventFactory(mobilyticsClientModule);
    }

    public static MobilyticsEventFactory proxyProvidesMobilyticsEventFactory(MobilyticsClientModule mobilyticsClientModule) {
        return (MobilyticsEventFactory) Preconditions.checkNotNull(mobilyticsClientModule.providesMobilyticsEventFactory(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MobilyticsEventFactory mo10268get() {
        return provideInstance(this.module);
    }
}
