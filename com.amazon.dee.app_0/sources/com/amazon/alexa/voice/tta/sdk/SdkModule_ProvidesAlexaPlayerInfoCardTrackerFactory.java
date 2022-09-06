package com.amazon.alexa.voice.tta.sdk;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class SdkModule_ProvidesAlexaPlayerInfoCardTrackerFactory implements Factory<AlexaPlayerInfoCardTracker> {
    private final SdkModule module;

    public SdkModule_ProvidesAlexaPlayerInfoCardTrackerFactory(SdkModule sdkModule) {
        this.module = sdkModule;
    }

    public static SdkModule_ProvidesAlexaPlayerInfoCardTrackerFactory create(SdkModule sdkModule) {
        return new SdkModule_ProvidesAlexaPlayerInfoCardTrackerFactory(sdkModule);
    }

    public static AlexaPlayerInfoCardTracker provideInstance(SdkModule sdkModule) {
        return proxyProvidesAlexaPlayerInfoCardTracker(sdkModule);
    }

    public static AlexaPlayerInfoCardTracker proxyProvidesAlexaPlayerInfoCardTracker(SdkModule sdkModule) {
        return (AlexaPlayerInfoCardTracker) Preconditions.checkNotNull(sdkModule.providesAlexaPlayerInfoCardTracker(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaPlayerInfoCardTracker mo10268get() {
        return provideInstance(this.module);
    }
}
