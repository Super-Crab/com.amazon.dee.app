package com.amazon.alexa.voice.sdk;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class SdkModule_ProvideAlexaStateTrackerFactory implements Factory<AlexaStateTracker> {
    private static final SdkModule_ProvideAlexaStateTrackerFactory INSTANCE = new SdkModule_ProvideAlexaStateTrackerFactory();

    public static SdkModule_ProvideAlexaStateTrackerFactory create() {
        return INSTANCE;
    }

    public static AlexaStateTracker provideInstance() {
        return proxyProvideAlexaStateTracker();
    }

    public static AlexaStateTracker proxyProvideAlexaStateTracker() {
        return (AlexaStateTracker) Preconditions.checkNotNull(SdkModule.provideAlexaStateTracker(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaStateTracker mo10268get() {
        return provideInstance();
    }
}
