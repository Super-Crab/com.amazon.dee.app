package com.amazon.alexa.voice.tta.sdk;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class SdkModule_ProvidesAlexaStateTrackerFactory implements Factory<AlexaStateTracker> {
    private final SdkModule module;

    public SdkModule_ProvidesAlexaStateTrackerFactory(SdkModule sdkModule) {
        this.module = sdkModule;
    }

    public static SdkModule_ProvidesAlexaStateTrackerFactory create(SdkModule sdkModule) {
        return new SdkModule_ProvidesAlexaStateTrackerFactory(sdkModule);
    }

    public static AlexaStateTracker provideInstance(SdkModule sdkModule) {
        return proxyProvidesAlexaStateTracker(sdkModule);
    }

    public static AlexaStateTracker proxyProvidesAlexaStateTracker(SdkModule sdkModule) {
        return (AlexaStateTracker) Preconditions.checkNotNull(sdkModule.providesAlexaStateTracker(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaStateTracker mo10268get() {
        return provideInstance(this.module);
    }
}
