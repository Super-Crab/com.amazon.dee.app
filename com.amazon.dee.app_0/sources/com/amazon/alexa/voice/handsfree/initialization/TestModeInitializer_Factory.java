package com.amazon.alexa.voice.handsfree.initialization;

import com.amazon.alexa.handsfree.vendor.bridge.VoiceAppPackageInfoProvider;
import com.amazon.alexa.voice.handsfree.features.IdentityServiceSubscriber;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class TestModeInitializer_Factory implements Factory<TestModeInitializer> {
    private final Provider<IdentityServiceSubscriber> identityServiceSubscriberProvider;
    private final Provider<VoiceAppPackageInfoProvider> voiceAppPackageInfoProvider;

    public TestModeInitializer_Factory(Provider<VoiceAppPackageInfoProvider> provider, Provider<IdentityServiceSubscriber> provider2) {
        this.voiceAppPackageInfoProvider = provider;
        this.identityServiceSubscriberProvider = provider2;
    }

    public static TestModeInitializer_Factory create(Provider<VoiceAppPackageInfoProvider> provider, Provider<IdentityServiceSubscriber> provider2) {
        return new TestModeInitializer_Factory(provider, provider2);
    }

    public static TestModeInitializer newTestModeInitializer(VoiceAppPackageInfoProvider voiceAppPackageInfoProvider, IdentityServiceSubscriber identityServiceSubscriber) {
        return new TestModeInitializer(voiceAppPackageInfoProvider, identityServiceSubscriber);
    }

    public static TestModeInitializer provideInstance(Provider<VoiceAppPackageInfoProvider> provider, Provider<IdentityServiceSubscriber> provider2) {
        return new TestModeInitializer(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TestModeInitializer mo10268get() {
        return provideInstance(this.voiceAppPackageInfoProvider, this.identityServiceSubscriberProvider);
    }
}
